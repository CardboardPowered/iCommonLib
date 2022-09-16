package me.isaiah.common.mixin.R1_18;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.isaiah.common.event.EventRegistery;
import me.isaiah.common.event.block.LeavesDecayEvent;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

@Mixin(LeavesBlock.class)
public class MixinLeavesBlock {
    
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/block/LeavesBlock;dropStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V"),
            method = "randomTick", cancellable = true)
    public void cardboard_doLeavesDecayEvent(BlockState state, ServerWorld world, BlockPos pos, Random ra, CallbackInfo ci) {        
    	LeavesDecayEvent ev = (LeavesDecayEvent) EventRegistery.invoke(LeavesDecayEvent.class, 
                new LeavesDecayEvent(state, world, pos));
    	
    	if (ev.isCanceled()) {
    		ci.cancel();
    		return;
    	}
    }

}
