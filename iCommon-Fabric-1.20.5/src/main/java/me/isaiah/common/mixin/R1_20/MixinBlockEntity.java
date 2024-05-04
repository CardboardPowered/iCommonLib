package me.isaiah.common.mixin.R1_20;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.cmixin.IMixinBlockEntity;
import me.isaiah.common.event.EventRegistery;
import me.isaiah.common.event.block.BlockEntityWriteNbtEvent;
import me.isaiah.common.event.entity.BlockEntityLoadEvent;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;

@Mixin(BlockEntity.class)
public class MixinBlockEntity implements IMixinBlockEntity {

    @Inject(at = @At("TAIL"), method = "readNbt")
    public void loadEnd(NbtCompound tag, CallbackInfo ci) {
        EventRegistery.invoke(BlockEntityLoadEvent.class, 
                new BlockEntityLoadEvent(tag, (BlockEntity)(Object)this));
    }

    @Inject(at = @At("RETURN"), method = "writeIdentifyingData")
    public void saveEnd(NbtCompound tag, CallbackInfo callback) {
        EventRegistery.invoke(BlockEntityWriteNbtEvent.class, 
                new BlockEntityWriteNbtEvent(tag, (BlockEntity)(Object)this));
    }

    @Override
    public NbtCompound I_createNbtWithIdentifyingData() {
    	// TODO: 1.20.5
        return ((BlockEntity)(Object)this).createNbtWithIdentifyingData( ICommonMod.getIServer().getMinecraft().getRegistryManager() );
    }
    
	@Override
	public void IC$add_bee_to_beehive(ServerWorld world, int rand) {
		BlockEntity tileentity = (BlockEntity) (Object) this;
		if (tileentity instanceof BeehiveBlockEntity) {
            BeehiveBlockEntity beehive = (BeehiveBlockEntity) tileentity;
            beehive.addBee(BeehiveBlockEntity.BeeData.create(rand));
        }
	}

}