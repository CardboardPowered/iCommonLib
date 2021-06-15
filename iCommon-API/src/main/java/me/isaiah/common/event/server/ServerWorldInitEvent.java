package me.isaiah.common.event.server;

import me.isaiah.common.world.IWorld;

public class ServerWorldInitEvent extends ServerEvent {

    private IWorld world;

    public ServerWorldInitEvent(IWorld world) {
        this.world = world;
    }

    public IWorld getWorld() {
        return world;
    }

}