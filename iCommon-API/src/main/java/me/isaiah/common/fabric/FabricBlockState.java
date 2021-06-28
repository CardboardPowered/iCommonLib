package me.isaiah.common.fabric;

import me.isaiah.common.world.IBlockState;
import net.minecraft.util.math.BlockPos;

public class FabricBlockState implements IBlockState {

    private FabricWorld world;
    private BlockPos pos;

    public FabricBlockState(FabricWorld w, BlockPos pos) {
        this.world = w;
        this.pos = pos;
    }

}