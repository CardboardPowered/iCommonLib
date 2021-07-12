package me.isaiah.common.cmixin;

import net.minecraft.server.world.ServerWorld;

public interface IMixinChunkGenerator {

    public int IgetSpawnHeight(ServerWorld w);

}