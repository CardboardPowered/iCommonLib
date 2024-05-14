package me.isaiah.common.mixin.R1_20;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.cmixin.IMixinItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.potion.PotionUtil;
import net.minecraft.util.Hand;

@Mixin(ItemStack.class)
public class MixinItemStack implements IMixinItemStack {

	@Override
	public void IC$damage(int amount, LivingEntity entity, Hand hand) {
        ((ItemStack)(Object)this).damage(1, entity, (plr1) -> {
        	plr1.sendToolBreakStatus(hand);
        });
	}

	@Override
	public void IC$modify_arguments(String arguments) {
        try {
        	((ItemStack)(Object)this).setNbt((NbtCompound) StringNbtReader.parse(arguments));
        } catch (CommandSyntaxException ex) {
            ICommonMod.LOGGER.error("CommandSyntaxException while modifying arguments", ex);
        }
	}
	
	@Override
	public List<StatusEffectInstance> IC$get_potion_status_effects() {
		return PotionUtil.getPotionEffects(((ItemStack) (Object) this));
	}

}
