package me.isaiah.common.mixin.R1_21;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.cmixin.IMixinChestBlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;

/**
 * Unused in next cardboard update
 */
@Mixin(ChestBlockEntity.class)
public class MixinChestBlockEntity implements IMixinChestBlockEntity {

    @Shadow
    private ContainerOpenersCounter openersCounter;

    @Override
    @Deprecated
    public int I_getViewCount() {
        return openersCounter.getOpenerCount();
    }

}
