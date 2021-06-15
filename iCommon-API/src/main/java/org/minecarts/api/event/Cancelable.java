package org.minecarts.api.event;

public interface Cancelable {

    /**
     * Returns whether the given event is canceled
     */
    public boolean isCanceled();

    /**
     * Sets whether the event will occur or not
     */
    public void setCanceled(boolean cancel);

}