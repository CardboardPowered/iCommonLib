package me.isaiah.common.mixin.R1_16;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.cmixin.IMixinChunkGenerator;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;

@Mixin(ChunkGenerator.class)
public class MixinChunkGenerator implements IMixinChunkGenerator {

    @Override
    public int IgetSpawnHeight(ServerWorld w) {
        return getSpawnHeight();
    }
    
    @Shadow
    public int getSpawnHeight() {return 0;}

}
