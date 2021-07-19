package me.isaiah.common.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.fabric.FabricServer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.MinecraftDedicatedServer;

@Mixin(MinecraftDedicatedServer.class)
public class MixinMinecraftServer {

    @Inject(at = @At(value = "HEAD"), method = "setupServer()Z")
    public void onServerStart(CallbackInfoReturnable<Boolean> callbackInfo) {
        ICommonMod.LOGGER.info("Setting IServer instance..");
        ICommonMod.set( new FabricServer((MinecraftServer)(Object)this) );
    }

}