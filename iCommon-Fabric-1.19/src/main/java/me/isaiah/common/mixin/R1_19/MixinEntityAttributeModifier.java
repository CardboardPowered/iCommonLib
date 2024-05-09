package me.isaiah.common.mixin.R1_19;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.cmixin.IMixinEntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;

@Mixin(EntityAttributeModifier.class)
public class MixinEntityAttributeModifier implements IMixinEntityAttributeModifier {

	@Override
	public double IC$get_value() {
		return ((EntityAttributeModifier)(Object)this).getValue();
	}

	@Override
	public Operation IC$get_operation() {
		return ((EntityAttributeModifier)(Object)this).getOperation();
	}

	@Override
	public UUID IC$get_uuid() {
		return ((EntityAttributeModifier)(Object)this).getId();
	}
	
}
