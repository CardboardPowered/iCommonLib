package me.isaiah.common.cmixin;

import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;

/**
 */
public interface IMixinTradeOffer {

	/**
	 */
	// public TradeOffer IC$new_trade_offer();
	
	/**
	 */
	// public void IC$set_experience_reward(boolean val);
	
	
	/**
	 */
	// public void IC$set_villager_experience(int val);
	 
	
	/**
	 */
	// public void IC$set_price_multiplier(float val);

	/**
	 */
	public ItemStack IC$get_first_buy_itemstack();
	
	/**
	 * @return ItemStack or {@code null} if
	 * secondBuyItem is not present.
	 */
	public ItemStack IC$get_second_buy_itemstack();
	
}
