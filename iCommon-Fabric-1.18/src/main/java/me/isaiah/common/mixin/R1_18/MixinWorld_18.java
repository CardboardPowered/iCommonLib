package me.isaiah.common.mixin.R1_18;

import java.util.function.Supplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.cmixin.IMixinWorld;
import me.isaiah.common.cmixin.SupportedVersion;
import me.isaiah.common.event.EventRegistery;
import me.isaiah.common.event.server.ServerWorldInitEvent;
import me.isaiah.common.fabric.FabricServer;
import me.isaiah.common.fabric.FabricWorld;
import me.isaiah.common.world.IWorld;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.collection.IndexedIterable;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.MutableWorldProperties;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.level.ServerWorldProperties;

@SupportedVersion({"1.18"}) // TODO: Test 1.15
@Mixin(World.class)
public class MixinWorld_18 implements IMixinWorld {

    private IWorld icommon;

    @SuppressWarnings("resource")
    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(MutableWorldProperties a, RegistryKey<?> b, DimensionType d, Supplier<Profiler> e, boolean f, boolean g, long h, CallbackInfo ci){
        if (!((Object)this instanceof ServerWorld)) {
            return;
        }
        
        ICommonMod.LOGGER.info("DEBUG: World Init tail.");

        ServerWorld nms = ((ServerWorld)(Object)this);
        String name = ((ServerWorldProperties) nms.getLevelProperties()).getLevelName();
        if (((FabricServer)ICommonMod.getIServer()).worlds.containsKey(name)) {
            if (nms.getRegistryKey() == World.NETHER) name = name + "_nether";
            if (nms.getRegistryKey() == World.END) name = name + "_the_end";
            
            if (((FabricServer)ICommonMod.getIServer()).worlds.containsKey(name)) {
                // World added by a mod.
                name = nms.getRegistryKey().getValue().toUnderscoreSeparatedString();
            }
        }
        ICommonMod.LOGGER.info("Setting IWorld for world \"" + name + "\"");
        this.icommon =  new FabricWorld(name, (World)(Object)this);
        ((FabricServer)ICommonMod.getIServer()).world(icommon, name);

        // ServerWorldInitEvent
        EventRegistery.invoke(ServerWorldInitEvent.class, new ServerWorldInitEvent(icommon));
    }

    @Override
    public IWorld icommon() {
        return icommon;
    }

    @Override
    public Object I_newBiomeArray(IndexedIterable<Biome> biomes, World world, ChunkPos pos, BiomeSource biomeSource) {
        return (BiomeAccess.Storage) world.getChunk(pos.x, pos.z);//new BiomeArray(((ServerWorld)world).getRegistryManager().get(Registry.BIOME_KEY), world, pos, ((ServerWorld)world).getChunkManager().getChunkGenerator().getBiomeSource());
    }

}