package me.isaiah.common;

import java.util.Collection;

import me.isaiah.common.world.IWorld;

public interface IServer {

    /**
     */
    public String getMinecraftVersion();

    /**
     */
    public Collection<IWorld> getWorlds();

    /**
     */
    public IWorld getWorld(String name);

}