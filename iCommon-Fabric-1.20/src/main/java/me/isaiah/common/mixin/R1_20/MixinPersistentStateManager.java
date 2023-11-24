package me.isaiah.common.mixin.R1_20;

import me.isaiah.common.cmixin.IMixinPersistentStateManager;
import net.minecraft.world.ForcedChunkState;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PersistentStateManager.class)
public abstract class MixinPersistentStateManager implements IMixinPersistentStateManager {
    @Shadow public abstract PersistentState get(PersistentState.Type<ForcedChunkState> par1, String par2);

    @Override
    public ForcedChunkState Iget() {
        return (ForcedChunkState) get(ForcedChunkState.getPersistentStateType(), "chunks");
    }

}
