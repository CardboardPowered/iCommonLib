package me.isaiah.common.mixin.R1_21;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.cmixin.IMixinTradeOffer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

@Mixin(MerchantOffer.class)
public class MixinTradeOffer implements IMixinTradeOffer {

	@Shadow
	public ItemCost baseCostA;

	@Shadow
	public Optional<ItemCost> costB;
	
	@Override
	public ItemStack IC$get_first_buy_itemstack() {
		MerchantOffer ofr = (MerchantOffer) (Object) this;
		return ofr.getItemCostA().itemStack();
	}

	@Override
	public ItemStack IC$get_second_buy_itemstack() {
		MerchantOffer ofr = (MerchantOffer) (Object) this;
		Optional<ItemCost> opt = ofr.getItemCostB();
		
		if (!opt.isPresent()) {
			return null;
		}

		return opt.get().itemStack();
	}
	
	@Override
	public void IC$set_first_buy_itemstack(ItemStack stack) {
        // TODO
		// this.firstBuyItem = new TradedItem(stack.getRegistryEntry(), stack.getCount(), ComponentPredicate.of(stack.getComponents()), stack);

	}

	@Override
	public void IC$set_second_buy_itemstack(ItemStack stack) {
        // TODO
		// this.secondBuyItem = Optional.of(new TradedItem(stack.getRegistryEntry(), stack.getCount(), ComponentPredicate.of(stack.getComponents()), stack));

	}

}