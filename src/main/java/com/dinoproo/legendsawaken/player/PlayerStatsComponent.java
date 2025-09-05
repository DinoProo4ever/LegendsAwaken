package com.dinoproo.legendsawaken.player;

import com.dinoproo.legendsawaken.LegendsAwaken;
import com.dinoproo.legendsawaken.component.ModComponents;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class PlayerStatsComponent implements AutoSyncedComponent {
    private int level = 1;
    private double xp = 0;
    private int availablePoints = 1;

    private int healthLevel = 0;
    private int oxygenLevel = 0;
    private int meleeLevel = 0;
    private int fortitudeLevel = 0;

    public static final int MAX_LEVEL = 100;

    public int getLevel() {
        return level;
    }

    public double getXp() {
        return xp;
    }

    public int getAvailablePoints() {
        return availablePoints;
    }

    public void sync(PlayerEntity player) {
        ModComponents.STATS.sync(player);
    }

    public static PlayerStatsComponent get(PlayerEntity player) {
        return ModComponents.STATS.get(player);
    }

    public void addXp(PlayerEntity player, double amount) {
        if (level >= MAX_LEVEL) {
            xp = 0;
            ModComponents.STATS.sync(player);
            return;
        }

        this.xp += amount;

        while (level < MAX_LEVEL && xp >= getXpForLevel(level + 1)) {
            xp -= getXpForLevel(level + 1);
            levelUp();
        }

        if (level >= MAX_LEVEL) {
            level = MAX_LEVEL;
            xp = 0;
        }

        applyModifiers(player);
        ModComponents.STATS.sync(player);
    }


    public void setXp(PlayerEntity player, double newXp) {
        this.xp = Math.max(0, newXp);
        while (level < MAX_LEVEL && xp >= getXpForLevel(level + 1)) {
            xp -= getXpForLevel(level + 1);
            levelUp();
        }
        ModComponents.STATS.sync(player);
    }

    public void addPoints(PlayerEntity player, int amount) {
        this.availablePoints += amount;
        ModComponents.STATS.sync(player);
    }

    public void resetStats(PlayerEntity player) {
        this.level = 1;
        this.xp = 0;
        this.availablePoints = 1;

        this.healthLevel = 0;
        this.oxygenLevel = 0;
        this.meleeLevel = 0;
        this.fortitudeLevel = 0;

        applyModifiers(player);
        ModComponents.STATS.sync(player);
    }

    private void levelUp() {
        level++;
        availablePoints++;
    }

    public boolean spendPoint() {
        if (availablePoints > 0) {
            availablePoints--;
            return true;
        }
        return false;
    }

    public int getHealthLevel() {
        return healthLevel;
    }
    public int getOxygenLevel() {
        return oxygenLevel;
    }
    public int getMeleeLevel() {
        return meleeLevel;
    }
    public int getFortitudeLevel() {
        return fortitudeLevel;
    }


    public boolean increaseAttributeLevel(PlayerEntity player, Attribute attr) {
        if (!spendPoint()) return false;
        switch (attr) {
            case HEALTH -> healthLevel++;
            case OXYGEN -> oxygenLevel++;
            case MELEE_DAMAGE -> meleeLevel++;
            case FORTITUDE -> fortitudeLevel++;
        }
        applyModifiers(player);
        ModComponents.STATS.sync(player);
        return true;
    }

    public static double getXpForLevel(int level) {
        final double baseXp = 50;
        final double growthRate = 1.15;
        return Math.floor(baseXp * Math.pow(growthRate, level - 1));
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        this.level = tag.getInt("Level");
        this.xp = tag.getDouble("XP");
        this.availablePoints = tag.getInt("AvailablePoints");

        this.healthLevel = tag.getInt("HealthLevel");
        this.oxygenLevel = tag.getInt("OxygenLevel");
        this.meleeLevel = tag.getInt("MeleeLevel");
        this.fortitudeLevel = tag.getInt("FortitudeLevel");
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.putInt("Level", level);
        tag.putDouble("XP", xp);
        tag.putInt("AvailablePoints", availablePoints);

        tag.putInt("HealthLevel", healthLevel);
        tag.putInt("OxygenLevel", oxygenLevel);
        tag.putInt("MeleeLevel", meleeLevel);
        tag.putInt("FortitudeLevel", fortitudeLevel);
    }

    public enum Attribute {
        HEALTH,
        OXYGEN,
        MELEE_DAMAGE,
        FORTITUDE
    }

    public void applyModifiers(PlayerEntity player) {
        applyAttributeModifier(player, EntityAttributes.GENERIC_MAX_HEALTH,
                "health_bonus", 2.0 * healthLevel);

        applyAttributeModifier(player, EntityAttributes.GENERIC_OXYGEN_BONUS,
                "oxygen_bonus", 30F * oxygenLevel);

        applyAttributeModifier(player, EntityAttributes.GENERIC_ATTACK_DAMAGE,
                "melee_bonus", 0.05 * meleeLevel, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE);

        applyAttributeModifier(player, EntityAttributes.GENERIC_ARMOR,
                "fortitude_bonus", fortitudeLevel);
    }

    private void applyAttributeModifier(PlayerEntity player, RegistryEntry<EntityAttribute> attribute, String name, double amount) {
        applyAttributeModifier(player, attribute, name, amount, EntityAttributeModifier.Operation.ADD_VALUE);
    }

    private void applyAttributeModifier(PlayerEntity player, RegistryEntry<EntityAttribute> attribute,
                                        String name, double amount, EntityAttributeModifier.Operation operation) {
        var attrInstance = player.getAttributes().getCustomInstance(attribute);
        if (attrInstance == null) return;

        Identifier id = Identifier.of(LegendsAwaken.MOD_ID + name);

        EntityAttributeModifier existing = attrInstance.getModifier(id);
        if (existing != null) {
            attrInstance.removeModifier(id);
        }

        if (amount != 0) {
            EntityAttributeModifier modifier = new EntityAttributeModifier(id, amount, operation);
            attrInstance.addPersistentModifier(modifier);
        }
    }
}
