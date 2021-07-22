package me.isaiah.common.mixin.R1_16;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.event.EventRegistery;
import me.isaiah.common.event.entity.CampfireBlockEntityCookEvent;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

@Mixin(CampfireBlockEntity.class)
public class MixinCampfireBlockEntity {

    @Shadow
    public DefaultedList<ItemStack> itemsBeingCooked;

    @Shadow
    public int[] cookingTimes;

    @Shadow
    public int[] cookingTotalTimes;

    @Shadow
    public void updateListeners() {
    }

    /**
     * @author Isaiah
     * @reason Fire event.
     */
    @Overwrite
    public void updateItemsBeingCooked() {
        CampfireBlockEntity mc = (CampfireBlockEntity)(Object)this;
        for (int i = 0; i < this.itemsBeingCooked.size(); ++i) {
            ItemStack itemstack = (ItemStack) this.itemsBeingCooked.get(i);

            if (!itemstack.isEmpty()) {
                cookingTimes[i]++;

                if (cookingTimes[i] >= cookingTotalTimes[i]) {
                    SimpleInventory subcontainer = new SimpleInventory(new ItemStack[]{itemstack});
                    ItemStack itemstack1 = (ItemStack) mc.getWorld().getRecipeManager().getFirstMatch(RecipeType.CAMPFIRE_COOKING, subcontainer, 
                            mc.getWorld()).map((recipecampfire) -> { return recipecampfire.craft(subcontainer);
                    }).orElse(itemstack);
                    BlockPos pos = mc.getPos();

                    CampfireBlockEntityCookEvent event = (CampfireBlockEntityCookEvent)EventRegistery.invoke(CampfireBlockEntityCookEvent.class,
                            new CampfireBlockEntityCookEvent(mc.getWorld(), pos, itemstack, itemstack1));

                    if (event.isCanceled()) return;

                    itemstack1 = (ItemStack) event.getResult();

                    ItemScatterer.spawn(mc.getWorld(), (double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), itemstack1);
                    this.itemsBeingCooked.set(i, ItemStack.EMPTY);
                    updateListeners();
                }
            }
        }

    }

}