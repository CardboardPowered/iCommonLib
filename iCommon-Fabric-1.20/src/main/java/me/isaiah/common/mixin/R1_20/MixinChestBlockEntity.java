package me.isaiah.common.mixin.R1_20;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.cmixin.IMixinChestBlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.ViewerCountManager;

@Mixin(ChestBlockEntity.class)
public class MixinChestBlockEntity implements IMixinChestBlockEntity {

    @Shadow
    private ViewerCountManager stateManager;

    @Override
    public int I_getViewCount() {
        return stateManager.getViewerCount();
    }

}
