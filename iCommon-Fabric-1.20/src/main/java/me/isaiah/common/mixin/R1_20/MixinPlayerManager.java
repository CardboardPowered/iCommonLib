package me.isaiah.common.mixin.R1_20;

import com.mojang.authlib.GameProfile;
import me.isaiah.common.cmixin.IMixinPlayerManager;
import net.minecraft.network.message.ChatVisibility;
import net.minecraft.network.packet.c2s.common.SyncedClientOptions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerManager.class)
public class MixinPlayerManager implements IMixinPlayerManager {

    @Override
    public ServerPlayerEntity InewPlayer(MinecraftServer server, ServerWorld world, GameProfile profile) {	
        return new ServerPlayerEntity(server, world, profile, new SyncedClientOptions("en-us", 8, ChatVisibility.FULL, true, Byte.MAX_VALUE, Arm.RIGHT, false, true));
    }

}
