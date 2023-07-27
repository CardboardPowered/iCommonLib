package me.isaiah.common.mixin.R1_20;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.cmixin.IMixinPersistentStateManager;
import net.minecraft.world.ForcedChunkState;
import net.minecraft.world.PersistentStateManager;

@Mixin(PersistentStateManager.class)
public class MixinPersistentStateManager implements IMixinPersistentStateManager {

    @Override
    public ForcedChunkState Iget() {
        return ((PersistentStateManager)(Object)this).get(ForcedChunkState::fromNbt, "chunks");
    }

}