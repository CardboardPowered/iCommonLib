package me.isaiah.common;

/**
 * The mod loaders iCommon supports running on.
 * 
 */
public enum Loader {

    /**
     * Fabric - fabricmc.net
     */
    FABRIC(true),

    /**
     * Minecraft Forge - minecraftforge.net
     */
    FORGE(true),

    /**
     * Sponge - SpongePowered.org
     */
    SPONGE(false),

    /**
     * Sugarcane - Paper fork
     * @see https://sugarcanemc.org/
     */
    SUGARCANE(false),

    /**
     * Minecraft Vanilla
     */
    NATIVE(false),

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