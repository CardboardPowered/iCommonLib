package me.isaiah.common.mixin.R1_21;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.cmixin.IMixinScreenHandler;
import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;

@Mixin(AbstractContainerMenu.class)
public class MixinScreenHandler implements IMixinScreenHandler {

    @Shadow
    public NonNullList<Slot> slots;

    @SuppressWarnings("unchecked")
    @Override
    public void ic_setSlots(Object o) {
        this.slots = (NonNullList<Slot>) o;
    }
    
}