package me.isaiah.common.mixin.R1_17;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.isaiah.common.cmixin.IMixinBlockEntity;
import me.isaiah.common.event.EventRegistery;
import me.isaiah.common.event.block.BlockEntityWriteNbtEvent;
import me.isaiah.common.event.entity.BlockEntityLoadEvent;
import me.isaiah.common.nbt.INbtElement;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;

@Mixin(BlockEntity.class)
public class MixinBlockEntity implements IMixinBlockEntity {

    @Inject(at = @At("TAIL"), method = "readNbt")
    public void loadEnd(NbtCompound tag, CallbackInfo ci) {
        EventRegistery.invoke(BlockEntityLoadEvent.class, 
                new BlockEntityLoadEvent((INbtElement) tag, (BlockEntity)(Object)this));
    }

    @Inject(at = @At("RETURN"), method = "writeIdentifyingData")
    public void saveEnd(NbtCompound tag, @SuppressWarnings("rawtypes") CallbackInfoReturnable callback) {
        EventRegistery.invoke(BlockEntityWriteNbtEvent.class, 
                new BlockEntityWriteNbtEvent((INbtElement) tag, (BlockEntity)(Object)this));
    }

    @Override
    public NbtCompound I_createNbtWithIdentifyingData() {
        return ((BlockEntity)(Object)this).writeNbt(new NbtCompound());
    }

}