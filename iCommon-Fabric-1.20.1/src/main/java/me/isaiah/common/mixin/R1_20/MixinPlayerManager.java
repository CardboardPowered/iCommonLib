package me.isaiah.common.mixin.R1_20;

import org.spongepowered.asm.mixin.Mixin;

import com.mojang.authlib.GameProfile;

import me.isaiah.common.cmixin.IMixinPlayerManager;
import net.minecraft.network.encryption.PlayerPublicKey;
import net.minecraft.network.encryption.SignatureVerifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

@Mixin(PlayerManager.class)
public class MixinPlayerManager implements IMixinPlayerManager {

    @Override
    public ServerPlayerEntity InewPlayer(MinecraftServer server, ServerWorld world, GameProfile profile) {	
        return new ServerPlayerEntity(server, world, profile);
    }

}