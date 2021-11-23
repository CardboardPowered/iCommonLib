package me.isaiah.common.cmixin;

import net.minecraft.nbt.NbtCompound;

public interface IMixinBlockEntity {

    public NbtCompound I_createNbtWithIdentifyingData();

}