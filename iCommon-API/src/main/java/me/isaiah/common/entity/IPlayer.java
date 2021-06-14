package me.isaiah.common.entity;

public interface IPlayer extends IEntity {

    /**
     * Retrieves a list of mods installed on the client.<br>
     * Requires iCommon to be installed on the client for communication.
     */
    public String[] getClientMods();

    @Override
    public default EntityType getEntityType() {
        return EntityType.PLAYER;
    }

    /**
     * Is player in Creative mode or not
     */
    boolean isCreativeMode();

}