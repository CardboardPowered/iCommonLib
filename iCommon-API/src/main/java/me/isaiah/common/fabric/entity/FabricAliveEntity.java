package me.isaiah.common.fabric.entity;

import me.isaiah.common.entity.IAliveEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class FabricAliveEntity extends FabricEntity implements IAliveEntity {

    public FabricAliveEntity(Entity mc) {
        super((LivingEntity)mc);
    }

    @Override
    public LivingEntity getMC() {
        return (LivingEntity) mc;
    }

    @Override
    public void setHealth(float health) {
        getMC().setHealth(health);
    }

}
