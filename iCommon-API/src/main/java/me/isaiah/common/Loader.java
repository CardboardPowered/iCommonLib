package me.isaiah.common;

/**
 * The mod loaders iCommon supports running on.
 * 
 */
public enum Loader {

    FABRIC(true),
    FORGE(true),
    SPONGE(false)
    ;

    private boolean supported;

    private Loader(boolean supported) {
        this.supported = supported;
    }

    /**
     * Does iCommon support this loader
     * 
     * @return true - supported.
     * @return false - currently unsupported.
     */
    public boolean isSupported() {
        return supported;
    }

}