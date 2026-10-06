package me.isaiah.common.mixin.R1_21;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.cmixin.IMixinWorld;
import me.isaiah.common.event.EventRegistery;
import me.isaiah.common.event.server.ServerWorldInitEvent;
import me.isaiah.common.fabric.FabricServer;
import me.isaiah.common.fabric.FabricWorld;
import me.isaiah.common.world.IWorld;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.IdMap;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.PalettedContainer;
import net.minecraft.world.level.chunk.Strategy;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraft.world.level.storage.WritableLevelData;

@Mixin(Level.class)
public class MixinWorld_18 implements IMixinWorld {

    private IWorld icommon;

    @SuppressWarnings("resource")
    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(WritableLevelData a, ResourceKey<?> b, RegistryAccess rm, Holder<DimensionType> registryEntry, boolean f, boolean g, long h, int i, CallbackInfo ci){
    // public void init(MutableWorldProperties a, RegistryKey<?> b, DynamicRegistryManager rm, RegistryEntry<DimensionType> registryEntry, Supplier<Profiler> profiler, boolean f, boolean g, long h, int i, CallbackInfo ci){
    // public void init(MutableWorldProperties a, RegistryKey<?> b, RegistryEntry<DimensionType> registryEntry, Supplier<Profiler> profiler, boolean f, boolean g, long h, int i, CallbackInfo ci){
    // public void init(MutableWorldProperties a, RegistryKey<?> b, DimensionType d, Supplier<Profiler> e, boolean f, boolean g, long h, CallbackInfo ci){
        if (!((Object)this instanceof ServerLevel)) {
            return;
        }

        ServerLevel nms = ((ServerLevel)(Object)this);
        String name = ((ServerLevelData) nms.getLevelData()).getLevelName();
        if (((FabricServer)ICommonMod.getIServer()).worlds.containsKey(name)) {
            if (nms.dimension() == Level.NETHER) name = name + "_nether";
            if (nms.dimension() == Level.END) name = name + "_the_end";
            
            if (((FabricServer)ICommonMod.getIServer()).worlds.containsKey(name)) {
                // World added by a mod.
                name = nms.dimension().identifier().toDebugFileName();
            }
        }
        ICommonMod.LOGGER.info("Setting IWorld for world \"" + name + "\"");
        this.icommon =  new FabricWorld(name, (Level)(Object)this);
        Identifier id = nms.dimension().identifier();
        ((FabricServer)ICommonMod.getIServer()).world(icommon, name, id);

        // ServerWorldInitEvent
        EventRegistery.invoke(ServerWorldInitEvent.class, new ServerWorldInitEvent(icommon));
    }

    @Override
    public IWorld icommon() {
        return icommon;
    }

    @Override
    public Object I_newBiomeArray(IdMap<Biome> biomes, Level world, ChunkPos pos, BiomeSource biomeSource) {
        return (BiomeManager.NoiseBiomeSource) world.getChunk(pos.x(), pos.z());//new BiomeArray(((ServerWorld)world).getRegistryManager().get(Registry.BIOME_KEY), world, pos, ((ServerWorld)world).getChunkManager().getChunkGenerator().getBiomeSource());
    }

    @Override
    public PalettedContainer<BlockState> I_emptyBlockIDs() {
    	return new PalettedContainer<>(Blocks.AIR.defaultBlockState(), Strategy.createForBlockStates(Block.BLOCK_STATE_REGISTRY));
    }

    @Override
    public Biome I_get_biome_for_noise_gen(int biomeX, int biomeY, int biomeZ) {
        return ((Level)(Object)this).getNoiseBiome(biomeX, biomeY, biomeZ).value();
    }
    
	@Override
	public MapItemSavedData IC$get_map_state(int id) {
		return ((ServerLevel)(Object)this).getMapData(new MapId(id));
	}
	
	@Override
	public boolean icommon$is_the_end() {
		return ((ServerLevel)(Object)this).dimensionTypeRegistration().is(BuiltinDimensionTypes.END);
		// return ((ServerLevel)(Object)this).dimensionTypeRegistration() == BuiltinDimensionTypes.END;
	}

	@Override
	public BlockPos icommon$get_spawn_point() {
		LevelData prop = ((ServerLevel)(Object)this).getLevelData();
		return prop.getRespawnData().pos();
	}

}