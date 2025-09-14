package com.dinoproo.legendsawaken.jurassic.block.entity.custom;

import com.dinoproo.legendsawaken.jurassic.block.entity.JurassicBlockEntities;
import com.dinoproo.legendsawaken.jurassic.item.JurassicItems;
import com.dinoproo.legendsawaken.jurassic.recipe.*;
import com.dinoproo.legendsawaken.jurassic.screen.custom.CultivatorScreenHandler;
import com.dinoproo.legendsawaken.util.ImplementedInventory;
import com.dinoproo.legendsawaken.util.ModTags;
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
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

public class CultivatorBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    private static final int EGG_SLOT = 0;
    private static final int DNA_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 1200;

    private float rotation = 0;

    public float getRenderingRotation() {
        rotation += 0.5f;
        if(rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }

    public CultivatorBlockEntity(BlockPos pos, BlockState state) {
        super(JurassicBlockEntities.CULTIVATOR_BE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CultivatorBlockEntity.this.progress;
                    case 1 -> CultivatorBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: CultivatorBlockEntity.this.progress = value;
                    break;
                    case 1: CultivatorBlockEntity.this.maxProgress = value;
                    break;
                }
            }

            @Override
            public int size() {
                return 3;
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
        return Text.translatable("recipe.legendsawaken.cultivating");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CultivatorScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("cultivator.progress", progress);
        nbt.putInt("cultivator.max_progress", maxProgress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("cultivator.progress");
        maxProgress = nbt.getInt("cultivator.max_progress");
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
            ItemStack output = recipe.value().output();

            removeStack(EGG_SLOT, 1);
            removeStack(DNA_SLOT, 1);

            ItemStack currentOutput = getStack(OUTPUT_SLOT);
            int newCount = currentOutput.getCount() + output.getCount();

            ItemStack result = new ItemStack(output.getItem(), newCount);
            setStack(OUTPUT_SLOT, result);
        });
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<CultivatingRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }

        ItemStack output = recipe.get().value().output();
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeEntry<CultivatingRecipe>> getCurrentRecipe() {
        return Objects.requireNonNull(this.getWorld()).getRecipeManager()
                .getFirstMatch(JurassicRecipes.CULTIVATING_TYPE,
                        new CultivatingRecipeInput(inventory.get(EGG_SLOT), inventory.get(DNA_SLOT)), this.getWorld());
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = this.getStack(OUTPUT_SLOT).isEmpty() ? 64 : this.getStack(OUTPUT_SLOT).getMaxCount();
        int currentCount = this.getStack(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        if (slot == EGG_SLOT) {
            return stack.isIn(ModTags.Items.DNA);
        } else if (slot == DNA_SLOT) {
            return stack.isOf(JurassicItems.SYNTETIC_EGG);
        }
        return false;
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