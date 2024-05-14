package me.isaiah.common.cmixin;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
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
	
	/**
	 */
	public List<StatusEffectInstance> IC$get_potion_status_effects();

}
