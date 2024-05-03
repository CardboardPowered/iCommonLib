package me.isaiah.common.fabric.entity;

import java.util.UUID;

import me.isaiah.common.cmixin.IMixinEntity;
import me.isaiah.common.cmixin.IMixinTameableEntity;
import me.isaiah.common.entity.EntityType;
import me.isaiah.common.entity.IEntity;
import me.isaiah.common.entity.IRemoveReason;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;

public class FabricEntity implements IEntity {

    public Entity mc;
    public FabricEntity(Entity mc) {
        this.mc = mc;
    }

    @Override
    public String getName() {
    	// TODO: 1.20.5
        return getMC().getName().getString();
    }

    @Override
    public Entity getMC() {
        return mc;
    }

    @Override
    public String getDisplayedName() {
        return getMC().getCustomName().getString();
    }

    @Override
    public void setDisplayedName(String str) {
        getMC().setCustomNameVisible(true);
        getMC().setCustomName(Text.of(str));
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
        ((IMixinEntity)mc).IsendText(Text.of(msg), UUID.randomUUID());
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
        getMC().setBodyYaw(yaw);
        // TODO 1.17 getMC().pitch = pitch;
    }
    
    @Override
    public void set_tamed(boolean tame, boolean updateAttrib) {
    	if (mc instanceof IMixinTameableEntity) {
    		((IMixinTameableEntity)mc).IC$set_tamed(tame, updateAttrib);
    	} else {
    		// Not TameableEntity
    	}
    }

}