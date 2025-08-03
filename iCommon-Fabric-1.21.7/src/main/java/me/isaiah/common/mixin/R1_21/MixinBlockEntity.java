package me.isaiah.common.mixin.R1_21;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.cmixin.IMixinBlockEntity;
import me.isaiah.common.event.EventRegistery;
import me.isaiah.common.event.block.BlockEntityWriteNbtEvent;
import me.isaiah.common.event.entity.BlockEntityLoadEvent;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

@Mixin(BlockEntity.class)
public class MixinBlockEntity implements IMixinBlockEntity {

	// TODO
	
	/*
    @Inject(at = @At("TAIL"), method = "Lnet/minecraft/block/entity/BlockEntity;readData(Lnet/minecraft/storage/ReadView;)V")
    public void loadEnd(ReadView view, CallbackInfo ci) {
        EventRegistery.invoke(BlockEntityLoadEvent.class, 
                new BlockEntityLoadEvent(tag, (BlockEntity)(Object)this));
    }

    @Inject(at = @At("RETURN"), method = "writeIdentifyingData")
    public void saveEnd(NbtCompound tag, CallbackInfo callback) {
        EventRegistery.invoke(BlockEntityWriteNbtEvent.class, 
                new BlockEntityWriteNbtEvent(tag, (BlockEntity)(Object)this));
    }
    */

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
	
	@Override
	@Deprecated
	public void IC$read_nbt(NbtCompound nbt) {
		// readNbt(nbt, ICommonMod.getIServer().getMinecraft().getRegistryManager());
	}
	
	/**
	 * Lnet/minecraft/block/entity/BlockEntity;readNbt(Lnet/minecraft/nbt/NbtCompound;Lnet/minecraft/registry/RegistryWrapper$WrapperLookup;)V
	 * @param nbt
	 * @param lookup
	 */
	// @Shadow
	@Deprecated
	public void readNbt1(NbtCompound nbt, RegistryWrapper.WrapperLookup lookup) {
		// Shadow Method
	}
	
	@Override
	public BlockEntity IC$create_from_nbt(BlockPos pos, BlockState state, NbtCompound nbt) {
		return BlockEntity.createFromNbt(pos, state, nbt, ICommonMod.getIServer().getMinecraft().getRegistryManager());
	}

}