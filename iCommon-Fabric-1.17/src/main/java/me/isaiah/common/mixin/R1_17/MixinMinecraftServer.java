package me.isaiah.common.mixin.R1_17;

import java.util.Random;
import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;

import com.mojang.authlib.GameProfile;
import com.mojang.serialization.DynamicOps;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.cmixin.IMixinMinecraftServer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.resource.ResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.CommandManager.RegistrationEnvironment;
import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import net.minecraft.util.dynamic.RegistryOps;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.DynamicRegistryManager.Impl;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.gen.GeneratorOptions;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;

@Mixin(MinecraftDedicatedServer.class)
public class MixinMinecraftServer implements IMixinMinecraftServer {

    /*@Override
    public RegistryOps<Object> Iof(DynamicOps<Object> delegate, ResourceManager resourceManager, Impl impl) {
        return RegistryOps.of(delegate,resourceManager,impl);
    }*/

    @Override
    public NoiseChunkGenerator I_createOverworldGenerator() {
        MinecraftServer mc = ICommonMod.getIServer().getMinecraft();
        return GeneratorOptions.createOverworldGenerator(mc.getRegistryManager().get(Registry.BIOME_KEY), mc.getRegistryManager().get(Registry.CHUNK_GENERATOR_SETTINGS_KEY), (new Random()).nextLong());
    }

    @Override
    public ChunkSection newChunkSection(int pos) {
        return new ChunkSection(pos);
    }
    

	@Override
	public UUID get_uuid_from_profile(GameProfile profile) {
		return PlayerEntity.getUuidFromProfile(profile);
	}


	@Override
	public CommandManager new_command_manager(RegistrationEnvironment env) {
		return new CommandManager(env);
	}
	
}