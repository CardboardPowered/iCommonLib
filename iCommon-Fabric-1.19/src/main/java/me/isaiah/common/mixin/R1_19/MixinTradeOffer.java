package me.isaiah.common.mixin.R1_19;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.cmixin.IMixinTradeOffer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;

@Mixin(TradeOffer.class)
public class MixinTradeOffer implements IMixinTradeOffer {

	// Lnet/minecraft/village/TradeOffer;firstBuyItem:Lnet/minecraft/item/ItemStack;
	@Shadow
	public ItemStack firstBuyItem;

	// Lnet/minecraft/village/TradeOffer;secondBuyItem:Lnet/minecraft/item/ItemStack;
	@Shadow
	public ItemStack secondBuyItem;
	
	@Override
	public ItemStack IC$get_first_buy_itemstack() {
		return firstBuyItem;
	}

	@Override
	public ItemStack IC$get_second_buy_itemstack() {
		if (null == secondBuyItem) {
			return null;
		}
		return secondBuyItem;
	}

}