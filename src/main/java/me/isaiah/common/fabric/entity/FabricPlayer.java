package me.isaiah.common.fabric.entity;

import me.isaiah.common.entity.IPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public class FabricPlayer extends FabricEntity implements IPlayer {

    public FabricPlayer(Entity mc) {
        super((PlayerEntity)mc);
    }

    @Override
    public String[] getClientMods() {
        return new String[] {"Minecraft"};
    }

}
