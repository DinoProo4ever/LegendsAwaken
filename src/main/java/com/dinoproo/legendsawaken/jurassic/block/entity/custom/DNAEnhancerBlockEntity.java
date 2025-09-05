package com.dinoproo.legendsawaken.jurassic.block.entity.custom;

import com.dinoproo.legendsawaken.component.ModDataComponents;
import com.dinoproo.legendsawaken.jurassic.block.entity.JurassicBlockEntities;
import com.dinoproo.legendsawaken.jurassic.screen.custom.DNAEnhancerScreenHandler;
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
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DNAEnhancerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    private static final int DNA_SLOT_A = 0;
    private static final int DNA_SLOT_B = 1;
    private static final int OUTPUT_SLOT = 2;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 600;

    public DNAEnhancerBlockEntity(BlockPos pos, BlockState state) {
        super(JurassicBlockEntities.DNA_ENHANCER_BE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> DNAEnhancerBlockEntity.this.progress;
                    case 1 -> DNAEnhancerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: DNAEnhancerBlockEntity.this.progress = value;
                    break;
                    case 1: DNAEnhancerBlockEntity.this.maxProgress = value;
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
        return Text.translatable("recipe.legendsawaken.enhancing");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new DNAEnhancerScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("dna_enhancer.progress", progress);
        nbt.putInt("dna_enhancer.max_progress", maxProgress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("dna_enhancer.progress");
        maxProgress = nbt.getInt("dna_enhancer.max_progress");
        super.readNbt(nbt, registryLookup);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (canEnhance()) {
            this.progress++;
            markDirty(world, pos, state);

            if (this.progress >= this.maxProgress) {
                enhanceDNA();
                this.progress = 0;
            }
        } else {
            this.progress = 0;
        }
    }

    private void enhanceDNA() {
        ItemStack dna1 = getStack(DNA_SLOT_A);
        ItemStack dna2 = getStack(DNA_SLOT_B);

        String species = dna1.getOrDefault(ModDataComponents.SPECIES, "none");
        int level1 = dna1.getOrDefault(ModDataComponents.DNA_LEVEL, 0);
        int level2 = dna2.getOrDefault(ModDataComponents.DNA_LEVEL, 0);

        int combinedLevel = Math.min(level1 + level2, 100);

        ItemStack result = new ItemStack(dna1.getItem());
        result.set(ModDataComponents.SPECIES, species);
        result.set(ModDataComponents.DNA_LEVEL, combinedLevel);

        ItemStack output = getStack(OUTPUT_SLOT);
        if (output.isEmpty()) {
            setStack(OUTPUT_SLOT, result);
        } else {
            output.increment(1);
        }

        removeStack(DNA_SLOT_A, 1);
        removeStack(DNA_SLOT_B, 1);
    }

    private boolean canEnhance() {
        ItemStack dna1 = getStack(DNA_SLOT_A);
        ItemStack dna2 = getStack(DNA_SLOT_B);

        if (dna1.isEmpty() || dna2.isEmpty()) return false;
        if (!dna1.isOf(dna2.getItem())) return false;

        String species1 = dna1.getOrDefault(ModDataComponents.SPECIES, "unknown");
        String species2 = dna2.getOrDefault(ModDataComponents.SPECIES, "unknown");

        if (!species1.equals(species2)) return false;

        int level1 = dna1.getOrDefault(ModDataComponents.DNA_LEVEL, 0);
        int level2 = dna2.getOrDefault(ModDataComponents.DNA_LEVEL, 0);

        if (level1 == 0 && level2 == 0) return false;

        return this.getStack(OUTPUT_SLOT).isEmpty();
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