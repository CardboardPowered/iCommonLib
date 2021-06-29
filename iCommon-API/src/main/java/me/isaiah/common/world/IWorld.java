package me.isaiah.common.world;

import me.isaiah.common.block.IBlockState;

public interface IWorld {

    /**
     * Returns the level name of this world
     * EX: "world", "world_nether", etc..
     */
    public String getName();

    /**
     */
    public boolean isDay();

    /**
     * GameRule
     */
    boolean doDaylightCycle();

    /**
     * Return the count of the loaded chunks.
     */
    int getLoadedChunkCount();
    
    /**
     * Gets the BlockState at the given pos
     *
     * @param x X-value of the block
     * @param y Y-value of the block
     * @param z Z-value of the block
     */
    public IBlockState getBlockState(int x, int y, int z);
    

}