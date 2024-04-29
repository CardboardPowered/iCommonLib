package me.isaiah.common.cmixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;

/**
 */
public interface IMixinItemStack {

	/**
	 */
	void IC$damage(int amount, LivingEntity entity, Hand hand);
	
	/**
	 */
	void IC$modify_arguments(String arguments);
	
}
