package me.isaiah.common.mixin.R1_16;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.isaiah.common.event.EventRegistery;
import me.isaiah.common.event.entity.BlockEntityLoadEvent;
import me.isaiah.common.nbt.INbtElement;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;

@Mixin(BlockEntity.class)
public class MixinBlockEntity {

    @Inject(at = @At("TAIL"), method = "fromTag")
    public void loadEnd(BlockState state, CompoundTag tag, CallbackInfo ci) {
        EventRegistery.invoke(BlockEntityLoadEvent.class, 
                new BlockEntityLoadEvent((INbtElement) tag, (BlockEntity)(Object)this));
    }

}