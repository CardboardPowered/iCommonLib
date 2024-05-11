package me.isaiah.common.mixin.R1_20;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.isaiah.common.cmixin.IMixinBlockEntity;
import me.isaiah.common.event.EventRegistery;
import me.isaiah.common.event.block.BlockEntityWriteNbtEvent;
import me.isaiah.common.event.entity.BlockEntityLoadEvent;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

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
        return ((BlockEntity)(Object)this).createNbtWithIdentifyingData();
    }
    
	@Override
	public void IC$add_bee_to_beehive(ServerWorld world, int rand) {
		BlockEntity tileentity = (BlockEntity) (Object) this;
		if (tileentity instanceof BeehiveBlockEntity) {
            BeehiveBlockEntity beehive = (BeehiveBlockEntity) tileentity;
            BeeEntity bee = new BeeEntity(EntityType.BEE, world);
            beehive.tryEnterHive(bee, false, rand);
        }
	}
	
	@Override
	public void IC$read_nbt(NbtCompound nbt) {
		BlockEntity be = (BlockEntity) (Object) this;
		be.readNbt(nbt);
	}
	
	@Override
	public BlockEntity IC$create_from_nbt(BlockPos pos, BlockState state, NbtCompound nbt) {
		return BlockEntity.createFromNbt(pos, state, nbt);
	}

}