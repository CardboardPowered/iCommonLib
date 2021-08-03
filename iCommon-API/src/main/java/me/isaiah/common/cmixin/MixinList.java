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
              "    Entity",
              "    Player",
              "    World",
              "    NbtTag",
              "    BlockEntity",
              "    ChestBlockEntity",
              "    CampfireBlockEntity",
              "    ScreenHandler",
              "    ChunkGenerator",
              "    PersistentStateManager",
              "    PlayerManager",
              "    Heighttmap",
             // "    MinecraftServer",
              ""
            );

}