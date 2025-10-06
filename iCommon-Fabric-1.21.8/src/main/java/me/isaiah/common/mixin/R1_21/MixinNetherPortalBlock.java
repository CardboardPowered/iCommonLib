package me.isaiah.common.mixin.R1_21;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.isaiah.common.cmixin.MixinInfo;
import me.isaiah.common.event.EventRegistery;
import me.isaiah.common.event.entity.EntityPortalCollideEvent;
import net.minecraft.block.BlockState;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@MixinInfo(minVersion = "1.21.5", maxVersion = "FUTURE")
@Mixin(NetherPortalBlock.class)
public class MixinNetherPortalBlock {

	/**
	 * TODO: 1.21.5+ adds extra EntityCollisionHandler after Entity
	 */
	@Inject(at = @At("HEAD"), method = "onEntityCollision", cancellable = true)
	private void onEntityCollision( BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler h, CallbackInfo ci) {
		if (!(entity instanceof ServerPlayerEntity)) {
			return;
		}

		EntityPortalCollideEvent ev = (EntityPortalCollideEvent)
                EventRegistery.invoke(EntityPortalCollideEvent.class, new EntityPortalCollideEvent(state, world, pos, entity));

        if (ev.isCanceled()) {
            ci.cancel();
            return;
        }
	}

}