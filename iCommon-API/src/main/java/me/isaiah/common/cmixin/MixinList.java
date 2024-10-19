package me.isaiah.common.cmixin;

import java.util.Arrays;
import java.util.List;

public class MixinList {
    
    public static List<String> list = Arrays.asList(
              "# iCommon Mixin List",
              "# This is where Mixins are defined",
              "# Use this instead of mixins.json",
              "",
              "PREFIX=Mixin",
              "MCVER=ALL",
              "    MinecraftServer",
              "    BlockState",
              "    BlockItem",
              "",
              "MCVER=1.16,1.17",
              "    World",
              "MCVER=1.16,1.17,1.18,1.19,1.20,1.21",
              "    MinecraftServer",
              "    Entity",
              "    Player",
              "    BlockEntity",
              "    ChestBlockEntity",
              "    CampfireBlockEntity",
              "    ScreenHandler",
              "    ChunkGenerator",
              "    PersistentStateManager",
              "    PlayerManager",
              "    Heighttmap",
              "    LeavesBlock",
              "    Cat",
              "    ItemStack",
              "    TameableEntity",
              "    GlobalPos",
              "    LootableContainerBlockEntity",
              "    EntityAttributeModifier",
              "    TradeOffer",
              "    SkullBlockEntity",
              "    BeaconBlockEntity",
              "    MobEntity",
              "MCVER=1.18,1.19,1.20,1.21",
              "    World_18"
            );

}