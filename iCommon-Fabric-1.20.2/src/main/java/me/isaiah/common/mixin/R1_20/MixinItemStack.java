package me.isaiah.common.mixin.R1_20;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.cmixin.IMixinItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

@Mixin(ItemStack.class)
public class MixinItemStack implements IMixinItemStack {

	@Override
	public void IC$damage(int amount, LivingEntity entity, Hand hand) {
        ((ItemStack)(Object)this).damage(1, entity, (plr1) -> {
        	plr1.sendToolBreakStatus(hand);
        });
	}

	
}
