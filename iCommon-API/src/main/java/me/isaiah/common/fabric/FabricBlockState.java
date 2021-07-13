package me.isaiah.common.fabric;

import me.isaiah.common.block.IBlockState;
import me.isaiah.common.world.IWorld;
import net.minecraft.util.math.BlockPos;

public class FabricBlockState implements IBlockState {

    private FabricWorld world;
    private BlockPos pos;

    public FabricBlockState(FabricWorld w, BlockPos pos) {
        this.world = w;
        this.pos = pos;
    }

    @Override
    public IWorld getWorld() {
        return world;
    }

    @Override
    public int getX() {
        return pos.getX();
    }

    @Override
    public int getY() {
        return pos.getY();
    }

    @Override
    public int getZ() {
        return pos.getZ();
    }

}