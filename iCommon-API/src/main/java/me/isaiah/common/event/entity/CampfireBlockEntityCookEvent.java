package me.isaiah.common.event.entity;

import me.isaiah.common.event.Cancelable;
import me.isaiah.common.event.Event;

public class CampfireBlockEntityCookEvent extends Event implements Cancelable {

    public boolean cancel = false;

    private Object world;
    private Object pos;
    private Object stack;
    private Object stack1;

    public CampfireBlockEntityCookEvent(Object world, Object pos, Object itemstack, Object itemstack1) {
        // TODO Create ItemStack API to avoid MC's ItemStack
        this.world = world;
        this.pos = pos;
        this.stack = itemstack;
        this.stack1 = itemstack1; 
    }

    @Override
    public boolean isCanceled() {
        return cancel;
    }

    @Override
    public void setCanceled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * @deprecated Temp method until the Item API is finished. 
     */
    @Deprecated
    public Object[] getMcObjects() {
        return new Object[] {world,pos,stack,stack1};
    }

    public Object getResult() {
        return stack1;
    }

    /**
     * @deprecated Temp method until the Item API is finished. 
     */
    @Deprecated
    public void setResult(Object o) {
        this.stack1 = o;
    }

}
