package me.isaiah.common.mixin.R1_21;

import com.mojang.authlib.GameProfile;
import me.isaiah.common.Gamemode;
import me.isaiah.common.cmixin.IMixinEntity;
import me.isaiah.common.entity.IPlayer;
import me.isaiah.common.event.EventRegistery;
import me.isaiah.common.event.entity.player.PlayerGamemodeChangeEvent;
import me.isaiah.common.event.entity.player.ServerPlayerInitEvent;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.c2s.common.SyncedClientOptions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(ServerPlayerEntity.class)
public class MixinPlayer extends MixinEntity {

	@Override
	public void IsendText(Text text, UUID id) {
		// ((ServerPlayerEntity)IgetMCEntity()).sendMessage(text, MessageType.CHAT, null == id ? Util.NIL_UUID : id);
		((ServerPlayerEntity) IgetMCEntity()).sendMessage(text, false);
	}

	@Inject(method = "<init>", at = @At("TAIL"))
	// public void init(MinecraftServer server, ServerWorld world, GameProfile profile, PlayerPublicKey key, CallbackInfo ci) {
	// public void init(MinecraftServer server, ServerWorld world, GameProfile profile, CallbackInfo ci) {
	public void init(MinecraftServer server, ServerWorld world, GameProfile profile, SyncedClientOptions clientOptions, CallbackInfo ci) {
		EventRegistery.invoke(ServerPlayerInitEvent.class, new ServerPlayerInitEvent((IPlayer) ((IMixinEntity) IgetMCEntity()).getAsICommon()));
	}
	
	// Note: 1.20.5=readGameModeNbt, <=1.20.4=setGameMode
	@Inject(at = @At("HEAD"), method = "readGameModeNbt", cancellable = true)
	public void setGameMode(NbtCompound nbt, CallbackInfo ci) {
		net.minecraft.world.GameMode gm = gameModeFromNbt(nbt, "playerGameType");
		GameMode old = ((ServerPlayerEntity) (Object) this).interactionManager.getGameMode();
		if(null == old) {
			return;
		}
		if(gm == old)
			ci.cancel();

		if(null != gm) {
			PlayerGamemodeChangeEvent event = new PlayerGamemodeChangeEvent((IPlayer) ((IMixinEntity) IgetMCEntity()).getAsICommon(), Gamemode.getById(old.getId()), Gamemode.getById(gm.getId()));

			event = (PlayerGamemodeChangeEvent) EventRegistery.invoke(PlayerGamemodeChangeEvent.class, event);
			if(event.isCanceled())
				ci.cancel();
		}
	}

	@Shadow
	private static net.minecraft.world.GameMode gameModeFromNbt(NbtCompound tag, String key) {
		return null;
	}

}
