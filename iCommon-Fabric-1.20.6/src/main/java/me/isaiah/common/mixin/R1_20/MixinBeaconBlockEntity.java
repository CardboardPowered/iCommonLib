package me.isaiah.common.mixin.R1_20;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.cmixin.IMixinBeaconBlockEntity;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;

@Mixin(BeaconBlockEntity.class)
public class MixinBeaconBlockEntity implements IMixinBeaconBlockEntity {

	@Shadow
	public RegistryEntry<StatusEffect> primary;
	
	@Shadow
	public RegistryEntry<StatusEffect> secondary;
	
	@Override
	public void IC$set_primary_effect(int effectId) {
		if (effectId == -99) {
			this.primary = null;
		}
		StatusEffect effect = Registries.STATUS_EFFECT.get(effectId);
		this.primary = Registries.STATUS_EFFECT.getEntry(effect);
	}

	@Override
	public void IC$set_secondary_effect(int effectId) {
		if (effectId == -99) {
			this.secondary = null;
		}
		StatusEffect effect = Registries.STATUS_EFFECT.get(effectId);
		this.secondary = Registries.STATUS_EFFECT.getEntry(effect);
	}

}
