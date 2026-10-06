package me.isaiah.common.mixin.R1_21;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.isaiah.common.cmixin.MixinInfo;
import me.isaiah.common.event.EventRegistery;
import me.isaiah.common.event.entity.EntityPortalCollideEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

@MixinInfo(minVersion = "1.21.10", maxVersion = "FUTURE")
@Mixin(NetherPortalBlock.class)
public class MixinNetherPortalBlock_10 {

	/**
	 * 1.21.5+ adds extra EntityCollisionHandler after Entity
	 * 1.21.10 adds an extra boolean
	 * 
	 * You'd think Mixin would be smart enough to handle this.
	 */
	@Inject(at = @At("HEAD"), method = "entityInside(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/InsideBlockEffectApplier;Z)V", cancellable = true)
	private void onEntityCollision_21_10( BlockState state, Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier h, boolean bool, CallbackInfo ci) {

		if (!(entity instanceof ServerPlayer)) {
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