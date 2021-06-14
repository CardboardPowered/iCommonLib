package me.isaiah.common.entity;

/**
 * A reason for an Entity's removal.
 * 
 * @since MC1.17. will be ignored in 1.16 and below.
 */
public enum IRemoveReason {

    KILLED               (true,  false),
    DISCARDED            (true,  false),
    DIMENSION_CHANGE     (false, false),
    ;

    public final boolean shouldDestroy;
    public final boolean shouldSave;

    private IRemoveReason(boolean destroy, boolean save) {
        this.shouldDestroy = destroy;
        this.shouldSave = save;
    }

}