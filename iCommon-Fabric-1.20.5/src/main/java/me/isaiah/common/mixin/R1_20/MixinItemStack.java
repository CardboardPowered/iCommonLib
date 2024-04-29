package me.isaiah.common.mixin.R1_20;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.cmixin.IMixinItemStack;
import net.minecraft.item.ItemStack;

@Mixin(ItemStack.class)
public class MixinItemStack implements IMixinItemStack {

}
