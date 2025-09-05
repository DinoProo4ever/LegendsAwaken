package com.dinoproo.legendsawaken.jurassic.block.entity.custom;

import com.dinoproo.legendsawaken.component.ModDataComponents;
import com.dinoproo.legendsawaken.jurassic.block.entity.JurassicBlockEntities;
import com.dinoproo.legendsawaken.jurassic.item.custom.DNAItem;
import com.dinoproo.legendsawaken.jurassic.recipe.ExtractingRecipe;
import com.dinoproo.legendsawaken.jurassic.recipe.ExtractingRecipeInput;
import com.dinoproo.legendsawaken.jurassic.recipe.JurassicRecipes;
import com.dinoproo.legendsawaken.jurassic.screen.custom.DNAExtractorScreenHandler;
import com.dinoproo.legendsawaken.jurassic.util.JurassicSpecies;
import com.dinoproo.legendsawaken.util.ImplementedInventory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DNAExtractorBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 600;

    public DNAExtractorBlockEntity(BlockPos pos, BlockState state) {
        super(JurassicBlockEntities.DNA_EXTRACTOR_BE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> DNAExtractorBlockEntity.this.progress;
                    case 1 -> DNAExtractorBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: DNAExtractorBlockEntity.this.progress = value;
                    break;
                    case 1: DNAExtractorBlockEntity.this.maxProgress = value;
                    break;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("recipe.legendsawaken.extracting");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new DNAExtractorScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("growth_chamber.progress", progress);
        nbt.putInt("growth_chamber.max_progress", maxProgress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("growth_chamber.progress");
        maxProgress = nbt.getInt("growth_chamber.max_progress");
        super.readNbt(nbt, registryLookup);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(hasRecipe()) {
            this.progress++;
            markDirty(world, pos, state);

            if(hasCraftingFinished()) {
                craftItem();
                this.progress = 0;
            }
        } else {
            this.progress = 0;
        }
    }

    private void craftItem() {
        getCurrentRecipe().ifPresent(recipe -> {
            ItemStack result = recipe.value().craft(
                    new ExtractingRecipeInput(inventory.get(INPUT_SLOT), world),
                    Objects.requireNonNull(getWorld()).getRegistryManager()
            );

            removeStack(INPUT_SLOT, 1);
            setStack(OUTPUT_SLOT, result);

            int level = result.getOrDefault(ModDataComponents.DNA_LEVEL, 0);
            spawnCraftingParticles(level);
            playCraftingSound(level);
        });
    }

    private void spawnCraftingParticles(int dnaLevel) {
        if (world == null || world.isClient) return;

        ParticleEffect particle;
        int count;

        if (dnaLevel >= 80) {
            particle = ParticleTypes.ENCHANT;
            count = 20;
        } else if (dnaLevel >= 50) {
            particle = ParticleTypes.HAPPY_VILLAGER;
            count = 10;
        } else {
            particle = ParticleTypes.SMOKE;
            count = 5;
        }

        ((ServerWorld) world).spawnParticles(
                particle, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5,
                count, 0.3, 0.3, 0.3, 0.01
        );
    }

    private void playCraftingSound(int dnaLevel) {
        if (world == null || world.isClient) return;

        SoundEvent sound;
        float volume = 0.5f;
        float pitch;

        if (dnaLevel >= 80) {
            sound = SoundEvents.ENTITY_PLAYER_LEVELUP;
            pitch = 1.5f;
        } else if (dnaLevel >= 50) {
            sound = SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP;
            pitch = 1.2f;
        } else {
            sound = SoundEvents.BLOCK_ANVIL_LAND;
            pitch = 0.6f;
        }

        world.playSound(
                null,
                pos,
                sound,
                SoundCategory.BLOCKS,
                volume,
                pitch
        );
    }


    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<ExtractingRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) return false;

        ItemStack result = createCraftResult(recipe.get().value());

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot();
    }

    private Optional<RecipeEntry<ExtractingRecipe>> getCurrentRecipe() {
        return Objects.requireNonNull(this.getWorld()).getRecipeManager()
                .getFirstMatch(JurassicRecipes.EXTRACTING_TYPE, new ExtractingRecipeInput(inventory.get(INPUT_SLOT), world), this.getWorld());
    }

    private ItemStack createCraftResult(ExtractingRecipe recipe) {
        ItemStack result = recipe.output().copy();

        if (result.getItem() instanceof DNAItem) {
            List<JurassicSpecies> speciesList = new ArrayList<>(JurassicSpecies.values());
            assert this.world != null;
            JurassicSpecies randomSpecies = speciesList.get(this.world.random.nextInt(speciesList.size()));

            result.set(ModDataComponents.SPECIES, randomSpecies.id().toString());
            result.set(ModDataComponents.DNA_LEVEL, this.world.random.nextInt(100) + 1);
        }

        return result;
    }

    private boolean canInsertItemIntoOutputSlot() {
        return this.getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = this.getStack(OUTPUT_SLOT).isEmpty() ? 64 : this.getStack(OUTPUT_SLOT).getMaxCount();
        int currentCount = this.getStack(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}