package me.isaiah.common.mixin.R1_20;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.cmixin.IMixinHeightmap;
import net.minecraft.world.Heightmap;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.chunk.Chunk;

@Mixin(Heightmap.class)
public class MixinHeighttmap implements IMixinHeightmap {

    @Override
    public void I_setTo(Chunk chunk, Type type, long[] ls) {
        Heightmap map = (Heightmap) (Object) this;
        map.setTo(chunk, type, ls);
    }

}
