package me.isaiah.common.mixin.R1_21;

import me.isaiah.common.R117.ICampfireBlockEntity;
import me.isaiah.common.event.EventRegistery;
import me.isaiah.common.event.entity.CampfireBlockEntityCookEvent;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = CampfireBlockEntity.class, priority = 90)
public class MixinCampfireBlockEntity implements ICampfireBlockEntity {

    @Shadow
    public NonNullList<ItemStack> items;

    // TODO: 1.20.5 Update
    
    /**
     * @author Isaiah
     * @reason Fire events
     */
	 // TODO: Update to 1.19.4
    /*@Overwrite
    public static void litServerTick(World world, BlockPos pos, BlockState state, CampfireBlockEntity mc) {
        ICampfireBlockEntity helper = (ICampfireBlockEntity)(Object)mc;
        for (int i = 0; i < mc.getItemsBeingCooked().size(); ++i) {
            ItemStack itemstack = (ItemStack) mc.getItemsBeingCooked().get(i);

            if (!itemstack.isEmpty()) {
                helper.IgetCookingTimes()[i]++;

                if (helper.IgetCookingTimes()[i] >= helper.IgetCookingTotalTimes()[i]) {
                    SimpleInventory inventorysubcontainer = new SimpleInventory(new ItemStack[]{itemstack});
                    ItemStack itemstack1 = (ItemStack) mc.getWorld().getRecipeManager().getFirstMatch(RecipeType.CAMPFIRE_COOKING, inventorysubcontainer, mc.getWorld()).map((recipecampfire) -> {
                        return recipecampfire.value().craft(inventorysubcontainer, world.getRegistryManager());
                    }).orElse(itemstack);
                    BlockPos blockposition = mc.getPos();

                    CampfireBlockEntityCookEvent event = (CampfireBlockEntityCookEvent)EventRegistery.invoke(CampfireBlockEntityCookEvent.class,
                            new CampfireBlockEntityCookEvent(mc.getWorld(), pos, itemstack, itemstack1));

                    if (event.isCanceled()) return;

                    itemstack1 = (ItemStack) event.getResult();

                    ItemScatterer.spawn(mc.getWorld(), (double) blockposition.getX(), (double) blockposition.getY(), (double) blockposition.getZ(), itemstack1);
                    mc.getItemsBeingCooked().set(i, ItemStack.EMPTY);
                    helper.IupdateListeners();
                }
            }
        }

    }*/

    @Shadow
    public int[] cookingProgress;

    @Shadow
    public int[] cookingTime;

    @Shadow
    public void markUpdated() {
    }


    @Override
    public int[] IgetCookingTimes() {
        return cookingProgress;
    }

    @Override
    public int[] IgetCookingTotalTimes() {
        return cookingTime;
    }

    @Override
    public void IupdateListeners() {
        markUpdated();
    }

}
