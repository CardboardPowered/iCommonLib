package me.isaiah.common.mixin.R1_21;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.cmixin.IMixinChestBlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.ViewerCountManager;

/**
 * Unused in next cardboard update
 */
@Mixin(ChestBlockEntity.class)
public class MixinChestBlockEntity implements IMixinChestBlockEntity {

    @Shadow
    private ViewerCountManager stateManager;

    @Override
    @Deprecated
    public int I_getViewCount() {
        return stateManager.getViewerCount();
    }

}
