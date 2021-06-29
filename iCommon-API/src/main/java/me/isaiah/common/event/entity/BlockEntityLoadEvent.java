package me.isaiah.common.event.entity;

import me.isaiah.common.event.Event;
import me.isaiah.common.nbt.INbtElement;

public class BlockEntityLoadEvent extends Event {

    private INbtElement element;
    private Object mc;

    public BlockEntityLoadEvent(INbtElement element, Object mc) {
        this.element = element;
        this.mc = mc;
    }

    public INbtElement getElement() {
        return element;
    }

    public Object getMC() {
        return mc;
    }

}