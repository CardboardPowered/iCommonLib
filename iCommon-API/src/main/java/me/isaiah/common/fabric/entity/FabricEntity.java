package me.isaiah.common.fabric.entity;

import java.util.UUID;

import me.isaiah.common.cmixin.IMixinEntity;
import me.isaiah.common.entity.EntityType;
import me.isaiah.common.entity.IEntity;
import me.isaiah.common.entity.IRemoveReason;
import net.minecraft.entity.Entity;
import net.minecraft.text.LiteralText;

public class FabricEntity implements IEntity {

    public Entity mc;
    public FabricEntity(Entity mc) {
        this.mc = mc;
    }

    @Override
    public String getName() {
        return getMC().getEntityName();
    }

    @Override
    public Entity getMC() {
        return mc;
    }

    @Override
    public String getDisplayedName() {
        return getMC().getCustomName().asString();
    }

    @Override
    public void setDisplayedName(String str) {
        getMC().setCustomNameVisible(true);
        getMC().setCustomName(new LiteralText(str));
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.UNKNOWN;
    }

    @Override
    public void remove(IRemoveReason r) {
        ((IMixinEntity)mc).Iremove(r);
    }

    @Override
    public void message(String msg) {
        ((IMixinEntity)mc).IsendText(new LiteralText(msg), UUID.randomUUID());
    }

    @Override
    public boolean isRemoved() {
        return ((IMixinEntity)mc).ic_isRemoved();
    }

    @Override
    public void collidesWith(IEntity e) {
        getMC().collidesWith((Entity)e.getMC());
    }

    @Override
    public void teleport(double x, double y, double z) {
        getMC().teleport(x, y, z);
    }

    @Override
    public void teleport(double x, double y, double z, float yaw, float pitch) {
        this.teleport(x, y, z);
        getMC().yaw = (yaw);
        // TODO 1.17 getMC().pitch = pitch;
    }

}