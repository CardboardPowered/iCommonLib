package me.isaiah.common.event.entity;

import me.isaiah.common.event.Event;
import net.minecraft.nbt.NbtCompound;

public class BlockEntityLoadEvent extends Event {

    private NbtCompound element;
    private Object mc;

    public BlockEntityLoadEvent(NbtCompound element, Object mc) {
        this.element = element;
        this.mc = mc;
    }

    public NbtCompound getElement() {
        return element;
    }

    public Object getMC() {
        return mc;
    }

}