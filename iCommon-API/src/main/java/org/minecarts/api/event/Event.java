package org.minecarts.api.event;

public abstract class Event {

    private boolean async;

    public Event() {
        this.async = false;
    }

    public Event(boolean async) {
        this.async = async;
    }

    public boolean isAsync() {
        return async;
    }

    /**
     * Method has no effect on existing listener calls.
     * @return The new updated value of async
     */
    public boolean setAsync(boolean val) {
        return (async = val);
    }

}