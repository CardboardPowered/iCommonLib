package me.isaiah.common.mixin.R1_21;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.cmixin.IMixinItemStack;
import net.minecraft.command.argument.ItemStringReader;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.util.Hand;

@Mixin(ItemStack.class)
public class MixinItemStack implements IMixinItemStack {

	@Override
	public void IC$damage(int amount, LivingEntity entity, Hand hand) {
        ((ItemStack)(Object)this).damage(amount, entity, hand.getEquipmentSlot());
	}

	
	@Override
	public void IC$modify_arguments(String arguments) {
        try {
        	//((ItemStack)(Object)this).setNbt((NbtCompound) StringNbtReader.parse(arguments));
        	
        	((ItemStack)(Object)this).applyUnvalidatedChanges(
        			new ItemStringReader(
        					CommandManager.createRegistryAccess(
        							ICommonMod.getIServer().getMinecraft().getRegistryManager())
        			).consume(new StringReader(arguments)).comp_2439());
        	
        } catch (CommandSyntaxException ex) {
            ICommonMod.LOGGER.error("CommandSyntaxException while modifying arguments", ex);
        }
	}
	
	@Override
	public List<StatusEffectInstance> IC$get_potion_status_effects() {
		return ((ItemStack) (Object) this).getOrDefault(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT).comp_2380();
	}
	
}
