package me.isaiah.common.cmixin;

import me.isaiah.common.world.IWorld;
import net.minecraft.util.collection.IndexedIterable;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeArray;
import net.minecraft.world.biome.source.BiomeSource;

public interface IMixinWorld {

    public IWorld icommon();

    /**
     * Creates a new instance of {@link BiomeArray}
     * 
     * @implNote 1.16  - new BiomeArray(IndexedIterable, ChunkPos, BiomeSource)
     * @implNote 1.17+ - new BiomeArray(IndexedIterable, World, ChunkPos, BiomeSource)
     */
    public BiomeArray I_newBiomeArray(IndexedIterable<Biome> biomes, World world, ChunkPos chunkPos, BiomeSource biomeSource);

}