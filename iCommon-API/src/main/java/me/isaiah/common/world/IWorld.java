package me.isaiah.common.world;

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

}