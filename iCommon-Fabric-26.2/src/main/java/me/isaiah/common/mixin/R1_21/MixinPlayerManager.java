package me.isaiah.common.mixin.R1_21;

import com.mojang.authlib.GameProfile;
import me.isaiah.common.cmixin.IMixinPlayerManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerList.class)
public class MixinPlayerManager implements IMixinPlayerManager {

    @Override
    @Deprecated
    public ServerPlayer InewPlayer(MinecraftServer server, ServerLevel world, GameProfile profile) {	
        return new ServerPlayer(server, world, profile, ClientInformation.createDefault());
    }

}
