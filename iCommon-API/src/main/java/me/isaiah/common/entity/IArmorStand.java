package me.isaiah.common.entity;

public interface IArmorStand extends IAliveEntity {

    @Override
    public default EntityType getEntityType() {
        return EntityType.ARMOR_STAND;
    }

    /**
     * Get the marker value of this ArmorStand.
     * 
     * @return true - if this ArmorStand is a marker, false otherwise
     */
    public boolean isMarker();

    /**
     * Set this ArmorStand marker property
     * 
     * @param value - true=marker, false=not marker
     */
    void setMarker(boolean value);

}