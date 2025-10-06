package me.isaiah.common.mixin.R1_19;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.cmixin.IMixinBeaconBlockEntity;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.registry.Registry;

@Mixin(BeaconBlockEntity.class)
public class MixinBeaconBlockEntity implements IMixinBeaconBlockEntity {

	@Shadow
	public StatusEffect primary;
	
	@Shadow
	public StatusEffect secondary;
	
	@Override
	public void IC$set_primary_effect(int effectId) {
		if (effectId == -99) {
			this.primary = null;
		}
		this.primary = Registry.STATUS_EFFECT.get(effectId);
	}

	@Override
	public void IC$set_secondary_effect(int effectId) {
		if (effectId == -99) {
			this.secondary = null;
		}
		this.secondary = Registry.STATUS_EFFECT.get(effectId);
	}

}
