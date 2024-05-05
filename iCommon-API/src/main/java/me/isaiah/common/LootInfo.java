package me.isaiah.common;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.jetbrains.annotations.Nullable;

/**
 * Represents additional information a {@link LootTable} can use to modify it's
 * generated loot.
 */
public final class LootInfo {

    public static final int DEFAULT_LOOT_MODIFIER = -1;

    private final BlockPos pos;
    private final World world;
    
    //private final Location location;
    private final float luck;
    private int lootingModifier = DEFAULT_LOOT_MODIFIER;
    private final Entity lootedEntity;
    private final Entity killer;

    private LootInfo(BlockPos pos, World world, float luck, int lootingModifier, Entity lootedEntity, Entity killer) {
    	this.world = world;
    	this.pos = pos;

        //this.location = location;
        this.luck = luck;
        this.lootingModifier = lootingModifier;
        this.lootedEntity = lootedEntity;
        this.killer = killer;
    }
    
    public World get_world() {
        return world;
    }
    
    public BlockPos get_pos() {
        return pos;
    }

    /**
     * The {@link Location} to store where the loot will be generated.
     *
     * @return the Location of where the loot will be generated
     */
    //@NotNull
    //public Location get_location() {
    //    return location;
   // }

    /**
     * Represents the {@link org.bukkit.potion.PotionEffectType#LUCK} that an
     * entity can have. The higher the value the better chance of receiving more
     * loot.
     *
     * @return luck
     */
    public float get_luck() {
        return luck;
    }

    /**
     * Represents the
     * {@link Enchantment#LOOTING} the
     * {@link #getKiller()} entity has on their equipped item.
     *
     * This value is only set via
     * {@link LootInfo.Builder#lootingModifier(int)}. If not set, the
     * {@link #getKiller()} entity's looting level will be used instead.
     *
     * @return the looting level
     */
    public int get_looting_modifier() {
        return lootingModifier;
    }

    /**
     * Get the {@link Entity} that was killed. Can be null.
     */
    @Nullable
    public Entity get_looted_entity() {
        return lootedEntity;
    }

    /**
     * Get the {@link HumanEntity} who killed the {@link #getLootedEntity()}.
     * Can be null.
     */
    @Nullable
    public Entity get_killer() {
        return killer;
    }

}
