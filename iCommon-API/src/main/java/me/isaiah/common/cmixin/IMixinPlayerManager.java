package me.isaiah.common.cmixin;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public interface IMixinPlayerManager {

    public ServerPlayerEntity InewPlayer(MinecraftServer server, ServerWorld world, GameProfile profile);

}