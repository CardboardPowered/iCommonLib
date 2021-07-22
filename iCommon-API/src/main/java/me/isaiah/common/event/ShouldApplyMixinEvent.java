package me.isaiah.common.event;

public class ShouldApplyMixinEvent extends Event implements Cancelable {

    private String name;
    private boolean cancel;

    public ShouldApplyMixinEvent(String name) {
        this.name = name;
        this.cancel = false;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isCanceled() {
        return cancel;
    }

    @Override
    public void setCanceled(boolean cancel) {
        this.cancel = cancel;
    }

}
