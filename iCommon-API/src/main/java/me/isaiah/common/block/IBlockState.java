package me.isaiah.common.block;

import me.isaiah.common.world.IWorld;

public interface IBlockState {

    IWorld getWorld();

    int getX();

    int getY();

    int getZ();

}