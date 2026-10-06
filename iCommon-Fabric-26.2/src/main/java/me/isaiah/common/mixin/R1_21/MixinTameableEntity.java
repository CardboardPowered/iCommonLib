package me.isaiah.common.mixin.R1_21;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.cmixin.IMixinTameableEntity;
import net.minecraft.world.entity.TamableAnimal;

@Mixin(TamableAnimal.class)
public class MixinTameableEntity implements IMixinTameableEntity {

	@Override
	public void IC$set_tamed(boolean tamed, boolean update_attributes) {
		((TamableAnimal)(Object)this).setTame(tamed, update_attributes);
	}

}
