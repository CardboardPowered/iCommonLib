package me.isaiah.common.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.server.MinecraftServer;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {

	/*
	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;initServer()Z"), method = "runServer")
	private void beforeSetupServer(CallbackInfo info) {
		System.out.println("HELLO FROM MixinMinecraftServer!!!!!!!!!!!!!!!!");
		// ServerLifecycleEvents.SERVER_STARTING.invoker().onServerStarting((MinecraftServer) (Object) this);
	}*/
	
	/*
    @Inject(at = @At(value = "HEAD"), method = "initServer()Z")
    public void icommonlib_onServerStart(CallbackInfoReturnable<Boolean> callbackInfo) {
    	System.out.println("HELLO FROM MixinMinecraftServer!!!!!!!!!!!!!!!!");
    	// icommon$LOGGER.info("Setting IServer instance..");
        // ICommonMod.set( new FabricServer((MinecraftServer)(Object)this) );
    }*/

}