package me.isaiah.common.mixin.R1_21;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.cmixin.IMixinHeightmap;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.Heightmap.Types;

@Mixin(Heightmap.class)
public class MixinHeighttmap implements IMixinHeightmap {

    @Override
    public void I_setTo(ChunkAccess chunk, Types type, long[] ls) {
        Heightmap map = (Heightmap) (Object) this;
        map.setRawData(chunk, type, ls);
    }

}
