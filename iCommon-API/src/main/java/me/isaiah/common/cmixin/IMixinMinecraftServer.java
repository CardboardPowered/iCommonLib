package me.isaiah.common.cmixin;

import com.mojang.serialization.DynamicOps;

import net.minecraft.resource.ResourceManager;
import net.minecraft.util.dynamic.RegistryOps;
import net.minecraft.util.registry.DynamicRegistryManager.Impl;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;

public interface IMixinMinecraftServer {

    /**
     */
    public RegistryOps<Object> Iof(DynamicOps<Object> delegate, ResourceManager resourceManager, Impl impl);

    /**
     * 1.17/1.18 safe replacement for GeneratorOptions.createOverworldGenerator
     * 
     * 1.18 - createOverworldGenerator(DynamicRegistryManager, long)
     * 1.17 - createOverworldGenerator(Registry<Biome>, Registry<ChunkGeneratorSettings>, long)
     * 
     * @author Isaiah
     */
    public NoiseChunkGenerator I_createOverworldGenerator();

}