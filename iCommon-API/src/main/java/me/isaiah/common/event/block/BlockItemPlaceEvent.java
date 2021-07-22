package me.isaiah.common.event.block;

import me.isaiah.common.cmixin.IMixinEntity;
import me.isaiah.common.cmixin.IMixinWorld;
import me.isaiah.common.entity.IPlayer;
import me.isaiah.common.event.Cancelable;
import me.isaiah.common.event.Event;
import me.isaiah.common.world.IWorld;
import net.minecraft.item.ItemPlacementContext;

public class BlockItemPlaceEvent extends Event implements Cancelable {

    private boolean no;
    private ItemPlacementContext context;

    public BlockItemPlaceEvent(ItemPlacementContext context) {
        this.no = false;
        this.context = context;
    }

    @Override
    public boolean isCanceled() {
        return no;
    }

    @Override
    public void setCanceled(boolean cancel) {
        this.no = cancel;
    }

    public IPlayer getPlayer() {
        return (IPlayer) ((IMixinEntity)context.getPlayer()).getAsICommon();
    }

    public IWorld getWorld() {
        return ((IMixinWorld)context.getWorld()).icommon();
    }

    public ItemPlacementContext mc() {
        return context;
    }

}