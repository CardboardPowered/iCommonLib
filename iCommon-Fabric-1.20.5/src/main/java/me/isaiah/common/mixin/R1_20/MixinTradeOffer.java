package me.isaiah.common.mixin.R1_20;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.cmixin.IMixinTradeOffer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;

@Mixin(TradeOffer.class)
public class MixinTradeOffer implements IMixinTradeOffer {

	@Override
	public ItemStack IC$get_first_buy_itemstack() {
		TradeOffer ofr = (TradeOffer) (Object) this;
		return ofr.getFirstBuyItem().comp_2427();
	}

	@Override
	public ItemStack IC$get_second_buy_itemstack() {
		TradeOffer ofr = (TradeOffer) (Object) this;
		Optional<TradedItem> opt = ofr.getSecondBuyItem();
		
		if (!opt.isPresent()) {
			return null;
		}

		return opt.get().comp_2427();
	}

}