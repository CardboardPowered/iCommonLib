package me.isaiah.common.mixin.R1_21;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.AttributeMappings;
import me.isaiah.common.cmixin.IMixinEntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;

@Mixin(EntityAttributeModifier.class)
public class MixinEntityAttributeModifier implements IMixinEntityAttributeModifier {

	@Override
	public double IC$get_value() {
		return ((EntityAttributeModifier)(Object)this).value();
	}

	@Override
	public Operation IC$get_operation() {
		return ((EntityAttributeModifier)(Object)this).comp_2450();
	}

	@Override
	public UUID IC$get_uuid() {
		
		return AttributeMappings.id_to_uuid( ((EntityAttributeModifier)(Object)this).comp_2447() );
		
		// return ((EntityAttributeModifier)(Object)this).uuid();
	}
	
}
