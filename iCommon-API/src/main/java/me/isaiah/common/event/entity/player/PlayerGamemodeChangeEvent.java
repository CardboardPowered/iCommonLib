package me.isaiah.common.event.entity.player;

import me.isaiah.common.Gamemode;
import me.isaiah.common.entity.IPlayer;
import me.isaiah.common.event.Cancelable;
import me.isaiah.common.event.Event;

public class PlayerGamemodeChangeEvent extends Event implements Cancelable {

    private boolean cancel = false;
    private IPlayer player;
    private Gamemode from;
    private Gamemode to;

    public PlayerGamemodeChangeEvent(IPlayer player, Gamemode from, Gamemode to) {
        this.player = player;
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean isCanceled() {
        return cancel;
    }

    @Override
    public void setCanceled(boolean cancel) {
        this.cancel = cancel;
    }

    public Gamemode getPreviousGamemode() {
        return from;
    }

    public Gamemode getNewGamemode() {
        return to;
    }

    public IPlayer getPlayer() {
        return player;
    }

}