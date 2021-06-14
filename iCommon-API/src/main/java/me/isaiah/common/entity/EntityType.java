/**
 * iCoreLib - Common Library for developing Minecraft Mods
 * 
 * This is free and unencumbered software released into the public domain.
 * 
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <http://unlicense.org/>
 */
package me.isaiah.common.entity;

public class EntityType {

    public static EntityType UNKNOWN               = new EntityType("null");

    public static EntityType DROPPED_ITEM          = new EntityType("item");
    public static EntityType EXPERIENCE_ORB        = new EntityType("experience_orb");
    public static EntityType AREA_EFFECT_CLOUD     = new EntityType("area_effect_cloud");
    public static EntityType ELDER_GUARDIAN        = new EntityType("elder_guardian");
    public static EntityType WITHER_SKELETON       = new EntityType("wither_skeleton");
    public static EntityType STRAY                 = new EntityType("stray");
    public static EntityType EGG                   = new EntityType("egg");
    public static EntityType LEASH                 = new EntityType("leash_knot");
    public static EntityType PAINTING              = new EntityType("painting");
    public static EntityType ARROW                 = new EntityType("arrow");
    public static EntityType SNOWBALL              = new EntityType("snowball");
    public static EntityType FIREBALL              = new EntityType("fireball");
    public static EntityType SMALL_FIREBALL        = new EntityType("small_fireball");
    public static EntityType ENDER_PEARL           = new EntityType("ender_pearl");
    public static EntityType EYE_OF_ENDER          = new EntityType("eye_of_ender");
    public static EntityType SPLASH_POTION         = new EntityType("potion");
    public static EntityType THROWN_EXP_BOTTLE     = new EntityType("experience_bottle");
    public static EntityType ITEM_FRAME            = new EntityType("item_frame");
    public static EntityType WITHER_SKULL          = new EntityType("wither_skull");
    public static EntityType TNT                   = new EntityType("tnt");
    public static EntityType FALLING_BLOCK         = new EntityType("falling_block");
    public static EntityType FIREWORK              = new EntityType("firework_rocket");
    public static EntityType HUSK                  = new EntityType("husk");
    public static EntityType SPECTRAL_ARROW        = new EntityType("spectral_arrow");
    public static EntityType SHULKER_BULLET        = new EntityType("shulker_bullet");
    public static EntityType DRAGON_FIREBALL       = new EntityType("dragon_fireball");
    public static EntityType ZOMBIE_VILLAGER       = new EntityType("zombie_villager");
    public static EntityType SKELETON_HORSE        = new EntityType("skeleton_horse");
    public static EntityType ZOMBIE_HORSE          = new EntityType("zombie_horse");
    public static EntityType ARMOR_STAND           = new EntityType("armor_stand");
    public static EntityType DONKEY                = new EntityType("donkey");
    public static EntityType MULE                  = new EntityType("mule");
    public static EntityType EVOKER_FANGS          = new EntityType("evoker_fangs");
    public static EntityType EVOKER                = new EntityType("evoker");
    public static EntityType VEX                   = new EntityType("vex");
    public static EntityType VINDICATOR            = new EntityType("vindicator");
    public static EntityType ILLUSIONER            = new EntityType("illusioner");
    public static EntityType MINECART_COMMAND      = new EntityType("command_block_minecart");
    public static EntityType BOAT                  = new EntityType("boat");
    public static EntityType MINECART              = new EntityType("minecart");
    public static EntityType MINECART_CHEST        = new EntityType("chest_minecart");
    public static EntityType MINECART_FURNACE      = new EntityType("furnace_minecart");
    public static EntityType MINECART_TNT          = new EntityType("tnt_minecart");
    public static EntityType MINECART_HOPPER       = new EntityType("hopper_minecart");
    public static EntityType MINECART_MOB_SPAWNER  = new EntityType("spawner_minecart");
    public static EntityType CREEPER               = new EntityType("creeper");
    public static EntityType SKELETON              = new EntityType("skeleton");
    public static EntityType SPIDER                = new EntityType("spider");
    public static EntityType GIANT                 = new EntityType("giant");
    public static EntityType ZOMBIE                = new EntityType("zombie");
    public static EntityType SLIME                 = new EntityType("slime");
    public static EntityType GHAST                 = new EntityType("ghast");
    public static EntityType ZOMBIFIED_PIGLIN      = new EntityType("zombified_piglin");
    public static EntityType ENDERMAN              = new EntityType("enderman");
    public static EntityType CAVE_SPIDER           = new EntityType("cave_spider");
    public static EntityType SILVERFISH            = new EntityType("silverfish");
    public static EntityType BLAZE                 = new EntityType("blaze");
    public static EntityType MAGMA_CUBE            = new EntityType("magma_cube");
    public static EntityType ENDER_DRAGON          = new EntityType("ender_dragon");
    public static EntityType WITHER                = new EntityType("wither");
    public static EntityType BAT                   = new EntityType("bat");
    public static EntityType WITCH                 = new EntityType("witch");
    public static EntityType ENDERMITE             = new EntityType("endermite");
    public static EntityType GUARDIAN              = new EntityType("guardian");
    public static EntityType SHULKER               = new EntityType("shulker");
    public static EntityType PIG                   = new EntityType("pig");
    public static EntityType SHEEP                 = new EntityType("sheep");
    public static EntityType COW                   = new EntityType("cow");
    public static EntityType CHICKEN               = new EntityType("chicken");
    public static EntityType SQUID                 = new EntityType("squid");
    public static EntityType WOLF                  = new EntityType("wolf");
    public static EntityType MUSHROOM_COW          = new EntityType("mooshroom");
    public static EntityType SNOWMAN               = new EntityType("snow_golem");
    public static EntityType OCELOT                = new EntityType("ocelot");
    public static EntityType IRON_GOLEM            = new EntityType("iron_golem");
    public static EntityType HORSE                 = new EntityType("horse");
    public static EntityType RABBIT                = new EntityType("rabbit");
    public static EntityType POLAR_BEAR            = new EntityType("polar_bear");
    public static EntityType LLAMA                 = new EntityType("llama");
    public static EntityType LLAMA_SPIT            = new EntityType("llama_spit");
    public static EntityType PARROT                = new EntityType("parrot");
    public static EntityType VILLAGER              = new EntityType("villager");
    public static EntityType ENDER_CRYSTAL         = new EntityType("end_crystal");
    public static EntityType TURTLE                = new EntityType("turtle");
    public static EntityType PHANTOM               = new EntityType("phantom");
    public static EntityType TRIDENT               = new EntityType("trident");
    public static EntityType COD                   = new EntityType("cod");
    public static EntityType SALMON                = new EntityType("salmon");
    public static EntityType PUFFERFISH            = new EntityType("pufferfish");
    public static EntityType TROPICAL_FISH         = new EntityType("tropical_fish");
    public static EntityType DROWNED               = new EntityType("drowned");
    public static EntityType DOLPHIN               = new EntityType("dolphin");
    public static EntityType CAT                   = new EntityType("cat");
    public static EntityType PANDA                 = new EntityType("panda");
    public static EntityType PILLAGER              = new EntityType("pillager");
    public static EntityType RAVAGER               = new EntityType("ravager");
    public static EntityType TRADER_LLAMA          = new EntityType("trader_llama");
    public static EntityType WANDERING_TRADER      = new EntityType("wandering_trader");
    public static EntityType FOX                   = new EntityType("fox");
    public static EntityType BEE                   = new EntityType("bee");
    public static EntityType HOGLIN                = new EntityType("hoglin");
    public static EntityType PIGLIN                = new EntityType("piglin");
    public static EntityType STRIDER               = new EntityType("strider");
    public static EntityType ZOGLIN                = new EntityType("zoglin");
    public static EntityType PIGLIN_BRUTE          = new EntityType("piglin_brute");
    public static EntityType FISHING_HOOK          = new EntityType("fishing_bobber");
    public static EntityType LIGHTNING             = new EntityType("lightning_bolt");
    public static EntityType PLAYER                = new EntityType("player");

    private String id;
    public EntityType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}