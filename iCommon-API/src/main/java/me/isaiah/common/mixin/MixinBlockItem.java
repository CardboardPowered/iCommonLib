package me.isaiah.common.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.isaiah.common.cmixin.IMixinEntity;
import me.isaiah.common.cmixin.IMixinWorld;
import me.isaiah.common.entity.IEntity;
import me.isaiah.common.entity.IPlayer;
import me.isaiah.common.event.EventRegistery;
import me.isaiah.common.event.block.BlockItemPlaceEvent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.ActionResult;

@Mixin(BlockItem.class)
public class MixinBlockItem {

    /**
     * {@link BlockItemPlaceEvent}
     */
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemPlacementContext;getBlockPos()Lnet/minecraft/util/math/BlockPos;"),
            method = "place",cancellable = true)
    public void icommon_doBlockItemPlaceEvent(ItemPlacementContext context, CallbackInfoReturnable<ActionResult> info) {

        BlockItemPlaceEvent e = (BlockItemPlaceEvent) EventRegistery.invoke(BlockItemPlaceEvent.class, 
                new BlockItemPlaceEvent(context));
        if (e.isCanceled()) {
            info.setReturnValue(ActionResult.SUCCESS);
            info.cancel();
            return;
        }
    }

}