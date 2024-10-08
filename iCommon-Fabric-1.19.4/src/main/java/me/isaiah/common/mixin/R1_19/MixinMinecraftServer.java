package me.isaiah.common.mixin.R1_19;

import java.util.Random;
import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;

import com.mojang.authlib.GameProfile;

import me.isaiah.common.ConnectionState;
import me.isaiah.common.ICommonMod;
import me.isaiah.common.cmixin.IMixinMinecraftServer;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.NetworkState;
import net.minecraft.network.packet.c2s.handshake.HandshakeC2SPacket;
import net.minecraft.network.packet.s2c.play.EntityStatusEffectS2CPacket;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.CommandManager.RegistrationEnvironment;
import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import net.minecraft.structure.StructureSet;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.village.TradeOffer;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;

@Mixin(MinecraftDedicatedServer.class)
public class MixinMinecraftServer implements IMixinMinecraftServer {

    // @Override
    public NoiseChunkGenerator I_createOverworldGenerator() {
        MinecraftServer mc = ICommonMod.getIServer().getMinecraft();

        return createOverworldGenerator(mc.getRegistryManager(), (new Random()).nextLong());
    }

    // @Override
    public ChunkSection newChunkSection(int pos) {
        MinecraftServer mc = ICommonMod.getIServer().getMinecraft();
        return new ChunkSection(pos, mc.getRegistryManager().get(RegistryKeys.BIOME));
    }
    
    private static NoiseChunkGenerator createOverworldGenerator(DynamicRegistryManager registryManager, long seed) {
        return createOverworldGenerator(registryManager, seed, true);
    }

    private static NoiseChunkGenerator createOverworldGenerator(DynamicRegistryManager registryManager, long seed, boolean flag) {
        return createGenerator(registryManager, seed, ChunkGeneratorSettings.OVERWORLD, flag);
    }

    private static NoiseChunkGenerator createGenerator(DynamicRegistryManager registryManager, long seed, RegistryKey<ChunkGeneratorSettings> settings) {
        return createGenerator(registryManager, seed, settings, true);
    }
    
    private static NoiseChunkGenerator createGenerator(DynamicRegistryManager registryManager, long seed, RegistryKey<ChunkGeneratorSettings> settings, boolean flag) {
        
		Registry<Biome> iregistry = registryManager.get(RegistryKeys.BIOME);
		
		//RegistryKeys.noise
		
        Registry<StructureSet> iregistry1 = registryManager.get(RegistryKeys.STRUCTURE_SET);
        Registry<ChunkGeneratorSettings> iregistry2 = registryManager.get(RegistryKeys.CHUNK_GENERATOR_SETTINGS);
        Registry<DoublePerlinNoiseSampler.NoiseParameters> iregistry3 = registryManager.get(RegistryKeys.NOISE_PARAMETERS);
        
        //BiomeSource bs = (BiomeSource)MultiNoiseBiomeSource.Preset.OVERWORLD.getBiomeSource(iregistry, flag);

        MinecraftServer mc = ICommonMod.getIServer().getMinecraft();

        BiomeSource bs = mc.getWorld(World.OVERWORLD).getChunkManager().getChunkGenerator().getBiomeSource();
        
       // new NoiseChunkGenerator(bs, null);
        
        
        //return new NoiseChunkGenerator(iregistry1, iregistry3, bs, iregistry2.getKey(null));
		
		return null;
    }

	// @Override
	public UUID get_uuid_from_profile(GameProfile profile) {
		return Uuids.getUuidFromProfile(profile);
	}

	// @Override
	// TODO: currently not used in Cardboard
	public CommandManager new_command_manager(RegistrationEnvironment env) {
		MinecraftDedicatedServer mc = (MinecraftDedicatedServer) (Object) this;

		CommandRegistryAccess ac = CommandManager.createRegistryAccess(BuiltinRegistries.createWrapperLookup());
		return new CommandManager(env, ac);
	}
	
	@Override
	public TradeOffer create_new_trade_offer(ItemStack result, int uses, int maxUses, boolean experienceReward,
			int experience, float priceMultiplier, int demand, int specialPrice) {
		return new net.minecraft.village.TradeOffer(
        		net.minecraft.item.ItemStack.EMPTY,
        		net.minecraft.item.ItemStack.EMPTY,
                result,
                uses,
                maxUses,
                experience,
                priceMultiplier);
	}
	
	@Override
	public EntityStatusEffectS2CPacket new_status_effect_packet(int id, StatusEffectInstance effect, boolean bl) {
		return new EntityStatusEffectS2CPacket(id, effect);
	}

	@Override
	public Text IC$from_json(String json) {
		return Text.Serializer.fromJson(json);
	}

	@Override
	public String IC$to_json(Text text) {
		return Text.Serializer.toJson(text);
	}
	
	@Override
	public int IC$get_connection_state(HandshakeC2SPacket packet) {
		NetworkState state = packet.getIntendedState();
		switch (state) {
			case HANDSHAKING:
				return ConnectionState.HANDSHAKING;
			case LOGIN:
				return ConnectionState.LOGIN;
			case PLAY:
				return ConnectionState.PLAY;
			case STATUS:
				return ConnectionState.STATUS;
			default:
				break;
		}
		return -2;
	}
	
	@Override
	public BlockEntity IC$create_blockentity_from_nbt(BlockPos pos, BlockState state, NbtCompound nbt) {
		return BlockEntity.createFromNbt(pos, state, nbt);
	}


}