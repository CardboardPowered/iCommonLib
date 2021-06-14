package me.isaiah.common.entity;

public interface IPrimedTnt extends IEntity {

    @Override
    public default EntityType getEntityType() {
        return EntityType.TNT;
    }

    public IEntity getSource();

}