package me.isaiah.common.fabric;

import me.isaiah.common.world.IWorld;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class FabricWorld implements IWorld {

    private World mc;
    private String name;

    public FabricWorld(String name, World world) {
        this.mc = world;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    private GameRules gr() {
        return mc.getGameRules();
    }

    @Override
    public boolean doDaylightCycle() {
        return gr().get(GameRules.DO_DAYLIGHT_CYCLE).get();
    }

    @Override
    public boolean isDay() {
        return mc.isDay();
    }

}