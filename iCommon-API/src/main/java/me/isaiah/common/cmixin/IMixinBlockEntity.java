package me.isaiah.common.cmixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public interface IMixinBlockEntity {

    public NbtCompound I_createNbtWithIdentifyingData();

    /**
     */
    public void IC$add_bee_to_beehive(ServerWorld world, int rand);
    
    /**
     */
    public void IC$read_nbt(NbtCompound nbt);
    
    
    /**
     */
    public BlockEntity IC$create_from_nbt(BlockPos pos, BlockState state, NbtCompound nbt);
    
}