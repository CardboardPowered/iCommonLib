package me.isaiah.common.fabric.entity;

import me.isaiah.common.entity.IArmorStand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.decoration.ArmorStandEntity;

public class FabricArmorStandEntity extends FabricAliveEntity implements IArmorStand {

    public FabricArmorStandEntity(Entity mc) {
        super((ArmorStandEntity)mc);
    }

    @Override
    public ArmorStandEntity getMC() {
        return (ArmorStandEntity) mc;
    }

    @Override
    public boolean isMarker() {
        return getMC().isMarker();
    }

    @Override
    public void setMarker(boolean marker) {
        DataTracker dataTracker = getMC().getDataTracker();
        dataTracker.set(ArmorStandEntity.ARMOR_STAND_FLAGS, this.setBitField1(dataTracker.get(ArmorStandEntity.ARMOR_STAND_FLAGS), 16, marker));
    }

    private byte setBitField1(byte value, int bitField, boolean set) {
        value = set ? (byte)(value | bitField) : (byte)(value & ~bitField);
        return value;
    }

}