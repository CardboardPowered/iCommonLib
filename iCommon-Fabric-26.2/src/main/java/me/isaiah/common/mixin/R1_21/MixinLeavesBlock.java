package me.isaiah.common.mixin.R1_21;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.isaiah.common.event.EventRegistery;
import me.isaiah.common.event.block.LeavesDecayEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(LeavesBlock.class)
public class MixinLeavesBlock {
    
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/LeavesBlock;dropResources(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V"),
            method = "randomTick", cancellable = true)
    public void cardboard_doLeavesDecayEvent(BlockState state, ServerLevel world, BlockPos pos, RandomSource ra, CallbackInfo ci) {        
    	EventRegistery.invoke(LeavesDecayEvent.class, 
                new LeavesDecayEvent(state, world, pos));
    }

}
