package me.isaiah.common.mixin.R1_21;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.AttributeMappings;
import me.isaiah.common.cmixin.IMixinEntityAttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;

@Mixin(AttributeModifier.class)
public class MixinEntityAttributeModifier implements IMixinEntityAttributeModifier {

	@Override
	public double IC$get_value() {
		return ((AttributeModifier)(Object)this).amount();
	}

	@Override
	public Operation IC$get_operation() {
		return ((AttributeModifier)(Object)this).operation();
	}

	@Override
	public UUID IC$get_uuid() {
		
		return AttributeMappings.id_to_uuid( ((AttributeModifier)(Object)this).id() );
		
		// return ((EntityAttributeModifier)(Object)this).uuid();
	}
	
}
