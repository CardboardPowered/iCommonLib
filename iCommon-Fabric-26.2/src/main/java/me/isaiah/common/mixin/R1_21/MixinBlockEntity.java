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
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

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
    public CompoundTag I_createNbtWithIdentifyingData() {
    	// TODO: 1.20.5
        return ((BlockEntity)(Object)this).saveWithFullMetadata( ICommonMod.getIServer().getMinecraft().registryAccess() );
    }
    
	@Override
	public void IC$add_bee_to_beehive(ServerLevel world, int rand) {
		BlockEntity tileentity = (BlockEntity) (Object) this;
		if (tileentity instanceof BeehiveBlockEntity) {
            BeehiveBlockEntity beehive = (BeehiveBlockEntity) tileentity;
            beehive.storeBee(BeehiveBlockEntity.Occupant.create(rand));
        }
	}
	
	@Override
	@Deprecated
	public void IC$read_nbt(CompoundTag nbt) {
		// readNbt(nbt, ICommonMod.getIServer().getMinecraft().getRegistryManager());
	}
	
	/**
	 * Lnet/minecraft/block/entity/BlockEntity;readNbt(Lnet/minecraft/nbt/NbtCompound;Lnet/minecraft/registry/RegistryWrapper$WrapperLookup;)V
	 * @param nbt
	 * @param lookup
	 */
	// @Shadow
	@Deprecated
	public void readNbt1(CompoundTag nbt, HolderLookup.Provider lookup) {
		// Shadow Method
	}
	
	@Override
	public BlockEntity IC$create_from_nbt(BlockPos pos, BlockState state, CompoundTag nbt) {
		return BlockEntity.loadStatic(pos, state, nbt, ICommonMod.getIServer().getMinecraft().registryAccess());
	}

}