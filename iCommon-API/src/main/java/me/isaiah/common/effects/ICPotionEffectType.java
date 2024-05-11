package me.isaiah.common.effects;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a type of potion and its effect on an entity.
 */
public class ICPotionEffectType {
    private static final BiMap<Integer, ICPotionEffectType> ID_MAP = HashBiMap.create();

    public int typeId;
    public String key;
    
    /**
     */
    public static final ICPotionEffectType UNKNOWN = getPotionEffectType(0, "UNKNOWN");
    
    /**
     */
    public static final ICPotionEffectType SPEED = getPotionEffectType(1, "speed");

    /**
     */
    public static final ICPotionEffectType SLOWNESS = getPotionEffectType(2, "slowness");

    /**
     */
    public static final ICPotionEffectType HASTE = getPotionEffectType(3, "haste");

    /**
     */
    public static final ICPotionEffectType MINING_FATIGUE = getPotionEffectType(4, "mining_fatigue");

    /**
     */
    public static final ICPotionEffectType STRENGTH = getPotionEffectType(5, "strength");

    /**
     */
    public static final ICPotionEffectType INSTANT_HEALTH = getPotionEffectType(6, "instant_health");

    /**
     */
    public static final ICPotionEffectType INSTANT_DAMAGE = getPotionEffectType(7, "instant_damage");

    /**
     */
    public static final ICPotionEffectType JUMP_BOOST = getPotionEffectType(8, "jump_boost");

    /**
     */
    public static final ICPotionEffectType NAUSEA = getPotionEffectType(9, "nausea");

    /**
     */
    public static final ICPotionEffectType REGENERATION = getPotionEffectType(10, "regeneration");

    /**
     */
    public static final ICPotionEffectType RESISTANCE = getPotionEffectType(11, "resistance");

    /**
     */
    public static final ICPotionEffectType FIRE_RESISTANCE = getPotionEffectType(12, "fire_resistance");

    /**
     */
    public static final ICPotionEffectType WATER_BREATHING = getPotionEffectType(13, "water_breathing");

    /**
     */
    public static final ICPotionEffectType INVISIBILITY = getPotionEffectType(14, "invisibility");

    /**
     */
    public static final ICPotionEffectType BLINDNESS = getPotionEffectType(15, "blindness");

    /**
     */
    public static final ICPotionEffectType NIGHT_VISION = getPotionEffectType(16, "night_vision");

    /**
     */
    public static final ICPotionEffectType HUNGER = getPotionEffectType(17, "hunger");

    /**
     */
    public static final ICPotionEffectType WEAKNESS = getPotionEffectType(18, "weakness");

    /**
     */
    public static final ICPotionEffectType POISON = getPotionEffectType(19, "poison");

    /**
     */
    public static final ICPotionEffectType WITHER = getPotionEffectType(20, "wither");

    /**
     */
    public static final ICPotionEffectType HEALTH_BOOST = getPotionEffectType(21, "health_boost");

    /**
     */
    public static final ICPotionEffectType ABSORPTION = getPotionEffectType(22, "absorption");

    /**
     */
    public static final ICPotionEffectType SATURATION = getPotionEffectType(23, "saturation");

    /**
     */
    public static final ICPotionEffectType GLOWING = getPotionEffectType(24, "glowing");

    /**
     */
    public static final ICPotionEffectType LEVITATION = getPotionEffectType(25, "levitation");

    /**
     */
    public static final ICPotionEffectType LUCK = getPotionEffectType(26, "luck");

    /**
     */
    public static final ICPotionEffectType UNLUCK = getPotionEffectType(27, "unluck");

    /**
     */
    public static final ICPotionEffectType SLOW_FALLING = getPotionEffectType(28, "slow_falling");

    /**
     */
    public static final ICPotionEffectType CONDUIT_POWER = getPotionEffectType(29, "conduit_power");

    /**
     */
    public static final ICPotionEffectType DOLPHINS_GRACE = getPotionEffectType(30, "dolphins_grace");

    /**
     */
    public static final ICPotionEffectType BAD_OMEN = getPotionEffectType(31, "bad_omen");

    /**
     */
    public static final ICPotionEffectType HERO_OF_THE_VILLAGE = getPotionEffectType(32, "hero_of_the_village");

    /**
     */
    public static final ICPotionEffectType DARKNESS = getPotionEffectType(33, "darkness");

    /**
     */
    public static final ICPotionEffectType TRIAL_OMEN = getPotionEffectType(34, "trial_omen");

    /**
     */
    public static final ICPotionEffectType RAID_OMEN = getPotionEffectType(35, "raid_omen");

    /**
     */
    public static final ICPotionEffectType WIND_CHARGED = getPotionEffectType(36, "wind_charged");

    /**
     */
    public static final ICPotionEffectType WEAVING = getPotionEffectType(37, "weaving");

    /**
     */
    public static final ICPotionEffectType OOZING = getPotionEffectType(38, "oozing");

    /**
     */
    public static final ICPotionEffectType INFESTED = getPotionEffectType(39, "infested");

    @NotNull
    private static ICPotionEffectType getPotionEffectType(int typeId, @NotNull String key) {
    	ICPotionEffectType pet = new ICPotionEffectType(typeId, key);
        if (typeId > 0) {
            ID_MAP.put(typeId, pet);
        }
        return pet;
    }
    
    public ICPotionEffectType(int id, String key) {
    	this.typeId = id;
    	this.key = key;
    }
    
    public int get_type_id() {
    	return typeId;
    }

}
