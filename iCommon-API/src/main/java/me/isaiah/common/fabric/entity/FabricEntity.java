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
        return getMCEntity().getEntityName();
    }

    @Override
    public Entity getMCEntity() {
        return mc;
    }

    @Override
    public String getDisplayedName() {
        return getMCEntity().getCustomName().asString();
    }

    @Override
    public void setDisplayedName(String str) {
        getMCEntity().setCustomNameVisible(true);
        getMCEntity().setCustomName(new LiteralText(str));
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
        getMCEntity().collidesWith((Entity)e.getMCEntity());
    }

    @Override
    public void teleport(double x, double y, double z) {
        getMCEntity().teleport(x, y, z);
    }

    @Override
    public void teleport(double x, double y, double z, float yaw, float pitch) {
        this.teleport(x, y, z);
        getMCEntity().setYaw(yaw);
        getMCEntity().pitch = pitch;
    }

}