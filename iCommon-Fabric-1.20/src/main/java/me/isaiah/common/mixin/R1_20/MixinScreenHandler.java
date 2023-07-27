package me.isaiah.common.mixin.R1_20;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.cmixin.IMixinScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.collection.DefaultedList;

@Mixin(ScreenHandler.class)
public class MixinScreenHandler implements IMixinScreenHandler {

    @Shadow
    public DefaultedList<Slot> slots;

    @SuppressWarnings("unchecked")
    @Override
    public void ic_setSlots(Object o) {
        this.slots = (DefaultedList<Slot>) o;
    }
    
}