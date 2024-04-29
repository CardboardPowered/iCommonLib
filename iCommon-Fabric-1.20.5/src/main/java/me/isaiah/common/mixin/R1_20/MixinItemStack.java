package me.isaiah.common.mixin.R1_20;



import org.spongepowered.asm.mixin.Mixin;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.cmixin.IMixinItemStack;
import net.minecraft.command.argument.ItemStringReader;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.util.Hand;

@Mixin(ItemStack.class)
public class MixinItemStack implements IMixinItemStack {

	@Override
	public void IC$damage(int amount, LivingEntity entity, Hand hand) {
        ((ItemStack)(Object)this).damage(1, entity, LivingEntity.getSlotForHand(hand));
	}

	
	@Override
	public void IC$modify_arguments(String arguments) {
        try {
        	//((ItemStack)(Object)this).setNbt((NbtCompound) StringNbtReader.parse(arguments));
        	
        	((ItemStack)(Object)this).applyComponentsFrom(
        			new ItemStringReader(
        					CommandManager.createRegistryAccess(
        							ICommonMod.getIServer().getMinecraft().getRegistryManager())
        			).consume(new StringReader(arguments)).comp_2439());

        	
        } catch (CommandSyntaxException ex) {
            ICommonMod.LOGGER.error("CommandSyntaxException while modifying arguments", ex);
        }
	}
	
}
