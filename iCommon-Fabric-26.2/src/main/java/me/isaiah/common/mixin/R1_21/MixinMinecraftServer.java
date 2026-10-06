package me.isaiah.common.mixin.R1_21;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mojang.authlib.GameProfile;
import com.mojang.serialization.JsonOps;

import me.isaiah.common.ConnectionState;
import me.isaiah.common.ICommonMod;
import me.isaiah.common.cmixin.IMixinMinecraftServer;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.Commands;
import net.minecraft.commands.Commands.CommandSelection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.network.protocol.handshake.ClientIntent;
import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Mixin(DedicatedServer.class)
public class MixinMinecraftServer implements IMixinMinecraftServer {

    // @Override
    public NoiseBasedChunkGenerator I_createOverworldGenerator() {
        MinecraftServer mc = ICommonMod.getIServer().getMinecraft();

        return createOverworldGenerator(mc.registryAccess(), (new Random()).nextLong());
    }

    // TODO: Remove
    @Override
    @Deprecated
    public LevelChunkSection newChunkSection(int pos) {
    	return null;
        // MinecraftServer mc = ICommonMod.getIServer().getMinecraft();
        // return new ChunkSection(mc.getRegistryManager().getOrThrow(RegistryKeys.BIOME));
    }
    
    private static NoiseBasedChunkGenerator createOverworldGenerator(RegistryAccess registryManager, long seed) {
        return createOverworldGenerator(registryManager, seed, true);
    }

    private static NoiseBasedChunkGenerator createOverworldGenerator(RegistryAccess registryManager, long seed, boolean flag) {
        return createGenerator(registryManager, seed, NoiseGeneratorSettings.OVERWORLD, flag);
    }

    private static NoiseBasedChunkGenerator createGenerator(RegistryAccess registryManager, long seed, ResourceKey<NoiseGeneratorSettings> settings) {
        return createGenerator(registryManager, seed, settings, true);
    }
    
    private static NoiseBasedChunkGenerator createGenerator(RegistryAccess registryManager, long seed, ResourceKey<NoiseGeneratorSettings> settings, boolean flag) {
        
		Registry<Biome> iregistry = registryManager.lookupOrThrow(Registries.BIOME);
		
		//RegistryKeys.noise
		
        Registry<StructureSet> iregistry1 = registryManager.lookupOrThrow(Registries.STRUCTURE_SET);
        Registry<NoiseGeneratorSettings> iregistry2 = registryManager.lookupOrThrow(Registries.NOISE_SETTINGS);
        Registry<NormalNoise.NoiseParameters> iregistry3 = registryManager.lookupOrThrow(Registries.NOISE);
        
        //BiomeSource bs = (BiomeSource)MultiNoiseBiomeSource.Preset.OVERWORLD.getBiomeSource(iregistry, flag);

        MinecraftServer mc = ICommonMod.getIServer().getMinecraft();

        BiomeSource bs = mc.getLevel(Level.OVERWORLD).getChunkSource().getGenerator().getBiomeSource();
        
       // new NoiseChunkGenerator(bs, null);
        
        
        //return new NoiseChunkGenerator(iregistry1, iregistry3, bs, iregistry2.getKey(null));
		
		return null;
    }

	// @Override
	public UUID get_uuid_from_profile(GameProfile profile) {
		return profile.id() != null
				? profile.id()
				: UUIDUtil.createOfflinePlayerUUID(profile.name());
	}

	// @Override
	// TODO: currently not used in Cardboard
	public Commands new_command_manager(CommandSelection env) {
		DedicatedServer mc = (DedicatedServer) (Object) this;

		CommandBuildContext ac = Commands.createValidationContext(VanillaRegistries.createLookup());
		return new Commands(env, ac);
	}

	@Override
	public MerchantOffer create_new_trade_offer(ItemStack result, int uses, int maxUses, boolean experienceReward,
			int experience, float priceMultiplier, int demand, int specialPrice) {
		// TODO Auto-generated method stub
		return new net.minecraft.world.item.trading.MerchantOffer(
                new ItemCost(Items.AIR),
                Optional.empty(),
                result,
                uses,
                maxUses,
                experience,
                priceMultiplier,
                demand
        );
	}

	@Override
	public ClientboundUpdateMobEffectPacket new_status_effect_packet(int id, MobEffectInstance effect, boolean bl) {
		return new ClientboundUpdateMobEffectPacket(id, effect, bl);
	}
	
	private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();
	
	@Override
	public Component IC$from_json(String jsonMessage) {

		// return Text.Serialization.fromJson(json, ((MinecraftServer)(Object)this).getRegistryManager());
		
		MinecraftServer mc = (MinecraftServer) (Object) this;
		
		 final JsonElement jsonElement = GSON.fromJson(jsonMessage, JsonElement.class);
	        return jsonElement == null ? null : ComponentSerialization.CODEC.parse(
	            mc.registryAccess().createSerializationContext(JsonOps.INSTANCE), jsonElement
	        ).getOrThrow(JsonParseException::new);
		
	}

	@Override
	public String IC$to_json(Component text) {
		// return Text.Serialization.toJsonString(text, ((MinecraftServer)(Object)this).getRegistryManager());
		MinecraftServer mc = (MinecraftServer) (Object) this;
		
		return GSON.toJson(
				ComponentSerialization.CODEC
	                .encodeStart(mc.registryAccess().createSerializationContext(JsonOps.INSTANCE), text)
	                .getOrThrow(JsonParseException::new)
	        );
		
	}
	
	@Override
	public int IC$get_connection_state(ClientIntentionPacket packet) {
		ClientIntent state = packet.intention();
		switch (state) {
			case LOGIN:
				return ConnectionState.LOGIN;
			case STATUS:
				return ConnectionState.STATUS;
			case TRANSFER:
				return ConnectionState.TRANSFER;
			default:
				break;
		}
		return -2;
	}
	
	@Override
	public BlockEntity IC$create_blockentity_from_nbt(BlockPos pos, BlockState state, CompoundTag nbt) {
		return BlockEntity.loadStatic(pos, state, nbt, ((MinecraftServer)(Object)this).registryAccess());
	}

}
