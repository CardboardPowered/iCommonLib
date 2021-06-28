package me.isaiah.common.cmixin;

import me.isaiah.common.fabric.FabricWorld;
import me.isaiah.common.world.IBlockState;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public interface IMixinBlockState {

    public default BlockState IgetMC() {
        return (BlockState)(Object)this;
    }

    public IBlockState getAsICommon(FabricWorld w, BlockPos pos);

}