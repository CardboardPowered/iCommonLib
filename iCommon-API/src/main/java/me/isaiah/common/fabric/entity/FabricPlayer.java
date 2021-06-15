package me.isaiah.common.fabric.entity;

import me.isaiah.common.entity.IPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class FabricPlayer extends FabricAliveEntity implements IPlayer {

    public FabricPlayer(Entity mc) {
        super((PlayerEntity)mc);
    }

    @Override
    public String[] getClientMods() {
        return new String[] {"Minecraft"};
    }

    @Override
    public PlayerEntity getMC() {
        return (PlayerEntity) mc;
    }

    @Override
    public boolean isCreativeMode() {
        return getMC().isCreative();
    }

}
