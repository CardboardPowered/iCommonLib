package me.isaiah.common.cmixin;

import net.minecraft.world.Heightmap;
import net.minecraft.world.chunk.Chunk;

public interface IMixinHeightmap {

    /**
     * Method for {@link Heightmap#setTo}
     * 
     * @implNote 1.16: setTo(long[]);
     * @implNote 1.17: setTo(Chunk, Type, long[])
     */
    public void I_setTo(Chunk chunk, Heightmap.Type type, long[] ls);

}