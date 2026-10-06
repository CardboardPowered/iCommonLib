package me.isaiah.common.mixin.R1_21;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.cmixin.IMixinItemStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.item.ItemParser;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;

@Mixin(ItemStack.class)
public class MixinItemStack implements IMixinItemStack {

	@Override
	public void IC$damage(int amount, LivingEntity entity, InteractionHand hand) {
        ((ItemStack)(Object)this).hurtAndBreak(amount, entity, hand.asEquipmentSlot());
	}

	
	@Override
	public void IC$modify_arguments(String arguments) {
        try {
        	//((ItemStack)(Object)this).setNbt((NbtCompound) StringNbtReader.parse(arguments));
        	
        	((ItemStack)(Object)this).applyComponents(
        			new ItemParser(
        					Commands.createValidationContext(
        							ICommonMod.getIServer().getMinecraft().registryAccess())
        			).parse(new StringReader(arguments)).components());
        	
        } catch (CommandSyntaxException ex) {
            ICommonMod.LOGGER.error("CommandSyntaxException while modifying arguments", ex);
        }
	}
	
	@Override
	public List<MobEffectInstance> IC$get_potion_status_effects() {
		return ((ItemStack) (Object) this).getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).customEffects();
	}
	
}
