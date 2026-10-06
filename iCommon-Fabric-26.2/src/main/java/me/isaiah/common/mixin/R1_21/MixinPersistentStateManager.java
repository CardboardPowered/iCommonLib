package me.isaiah.common.mixin.R1_21;

import me.isaiah.common.cmixin.IMixinPersistentStateManager;
import net.minecraft.world.level.storage.SavedDataStorage;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SavedDataStorage.class)
public abstract class MixinPersistentStateManager implements IMixinPersistentStateManager {
  
	// TODO
	/*
	@Shadow public abstract PersistentState get(PersistentState.Type<ForcedChunkState> par1, String par2);

    @Override
    public ForcedChunkState Iget() {
        return (ForcedChunkState) get(ForcedChunkState.getPersistentStateType(), "chunks");
    }
    */

}
