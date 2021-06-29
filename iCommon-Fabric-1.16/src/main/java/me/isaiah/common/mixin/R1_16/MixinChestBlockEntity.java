package me.isaiah.common.mixin.R1_16;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.cmixin.IMixinChestBlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;

@Mixin(ChestBlockEntity.class)
public class MixinChestBlockEntity implements IMixinChestBlockEntity {   

    @Shadow
    public int viewerCount;

    @Override
    public int I_getViewCount() {
        return viewerCount;
    }

}
