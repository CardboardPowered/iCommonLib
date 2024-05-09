package me.isaiah.common.cmixin;

import java.util.UUID;

import net.minecraft.entity.attribute.EntityAttributeModifier;

public interface IMixinEntityAttributeModifier {

    public double IC$get_value();

    public EntityAttributeModifier.Operation IC$get_operation();

    public UUID IC$get_uuid();

}
