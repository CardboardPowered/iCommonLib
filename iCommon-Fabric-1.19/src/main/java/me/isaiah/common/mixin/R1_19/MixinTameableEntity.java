package me.isaiah.common.mixin.R1_19;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.cmixin.IMixinTameableEntity;
import net.minecraft.entity.passive.TameableEntity;

@Mixin(TameableEntity.class)
public class MixinTameableEntity implements IMixinTameableEntity {

	@Override
	public void IC$set_tamed(boolean tamed, boolean update_attributes) {
		((TameableEntity)(Object)this).setTamed(tamed);
	}

}
