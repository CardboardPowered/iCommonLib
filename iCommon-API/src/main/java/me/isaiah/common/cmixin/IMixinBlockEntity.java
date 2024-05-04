package me.isaiah.common.cmixin;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;

public interface IMixinBlockEntity {

    public NbtCompound I_createNbtWithIdentifyingData();

    /**
     */
    public void IC$add_bee_to_beehive(ServerWorld world, int rand);
    
}