package me.isaiah.common.cmixin;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.CommandManager.RegistrationEnvironment;
import net.minecraft.village.TradeOffer;
//import net.minecraft.util.registry.DynamicRegistryManager.Impl;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;

public interface IMixinMinecraftServer {

    /**
     * note: unused?
     */
    // public RegistryOps<Object> Iof(DynamicOps<Object> delegate, ResourceManager resourceManager, Impl impl);

    /**
     * 1.17/1.18 safe replacement for GeneratorOptions.createOverworldGenerator
     * 
     * @implNote 1.18 - createOverworldGenerator(DynamicRegistryManager, long)
     * @implNote 1.17 - createOverworldGenerator(Registry<Biome>, Registry<ChunkGeneratorSettings>, long)
     */
    public NoiseChunkGenerator I_createOverworldGenerator();

    /**
     * The constructors for ChunkSection have changed
     * between 1.17 and 1.18. This method provides the
     * 1.17 arguments for 1.18
     * 
     * @implNote 1.17 - ChunkSection(int yOffset)
     * @implNote 1.18 - ChunkSection(int chunkPos, Registry<Biome> biomeRegistry)
     */
    public ChunkSection newChunkSection(int yOffset);

    /**
     * Retrieve UUID from a GameProfile
     * 
     * @implNote 1.18 -
     * @implNote 1.19 -
     */
    public UUID get_uuid_from_profile(GameProfile profile);

    /**
     * Create new instance of CommandManager
     * 
     * @implNote 1.18 - new CommandManager(RegistrationEnvironment)
     * @implNote 1.19 - new CommandManager(RegistrationEnvironment, CommandRegistryAccess)
     */
    public CommandManager new_command_manager(RegistrationEnvironment env);
    
    /**
     */
    public TradeOffer create_new_trade_offer(ItemStack result, int uses, int maxUses, boolean experienceReward, int experience, float priceMultiplier, int demand, int specialPrice);

}