package me.isaiah.common.mixin.R1_16;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.isaiah.common.Gamemode;
import me.isaiah.common.cmixin.IMixinEntity;
import me.isaiah.common.cmixin.SupportedVersion;
import me.isaiah.common.entity.IPlayer;
import me.isaiah.common.event.EventRegistery;
import me.isaiah.common.event.entity.player.PlayerGamemodeChangeEvent;
import net.minecraft.network.MessageType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import net.minecraft.world.GameMode;

@SupportedVersion({"1.16.4", "1.16.5", "1.17"})
@Mixin(ServerPlayerEntity.class)
public class MixinPlayer extends MixinEntity {

    @Override
    public void IsendText(Text text, UUID id) {
        ((ServerPlayerEntity)IgetMCEntity()).sendMessage(text, MessageType.CHAT, null == id ? Util.NIL_UUID : id);
    }

    @Inject(at = @At("HEAD"), method = "setGameMode", cancellable = true)
    public void setGameMode(net.minecraft.world.GameMode gm, CallbackInfo ci) {
        GameMode old = ((ServerPlayerEntity)(Object)this).interactionManager.getGameMode(); 
        if (gm == old)
            ci.cancel();

        if (null != gm) {
            PlayerGamemodeChangeEvent event = new PlayerGamemodeChangeEvent((IPlayer)((IMixinEntity)IgetMCEntity()).getAsICommon(), Gamemode.getById(old.getId()), Gamemode.getById(gm.getId()));

            event = (PlayerGamemodeChangeEvent) EventRegistery.invoke(PlayerGamemodeChangeEvent.class, event);
            if (event.isCanceled())
                ci.cancel();
        }
    }

}