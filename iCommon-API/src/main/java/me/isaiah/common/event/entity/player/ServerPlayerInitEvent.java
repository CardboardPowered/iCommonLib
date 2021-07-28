package me.isaiah.common.event.entity.player;

import me.isaiah.common.entity.IPlayer;
import me.isaiah.common.event.Event;

public class ServerPlayerInitEvent extends Event {

    private IPlayer player;

    public ServerPlayerInitEvent(IPlayer player) {
        this.player = player;
    }

    public IPlayer getPlayer() {
        return player;
    }

}