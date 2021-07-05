package me.isaiah.common.mixin.R1_16;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.cmixin.IMixinScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

@Mixin(ScreenHandler.class)
public class MixinScreenHandler implements IMixinScreenHandler {

    @Shadow
    public List<Slot> slots;

    @SuppressWarnings("unchecked")
    @Override
    public void ic_setSlots(Object o) {
        this.slots = (List<Slot>) o;
    }

}