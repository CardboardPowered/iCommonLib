package me.isaiah.common.entity;

import me.isaiah.common.ICommandSource;

public interface IEntity extends ICommandSource {

    public Object     getMC();

    public String     getName();

    public void       remove(IRemoveReason r);

    public boolean    isRemoved();

    public String     getDisplayedName();

    public void       setDisplayedName(String name);

    public EntityType getEntityType();

    void collidesWith(IEntity e);

    void teleport(double x, double y, double z);

    void teleport(double x, double y, double z, float yaw, float pitch);

}