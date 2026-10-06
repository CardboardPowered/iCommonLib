package me.isaiah.common.mixin.R1_21;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.cmixin.IMixinBeaconBlockEntity;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;

@Mixin(BeaconBlockEntity.class)
public class MixinBeaconBlockEntity implements IMixinBeaconBlockEntity {

	@Shadow
	public Holder<MobEffect> primaryPower;
	
	@Shadow
	public Holder<MobEffect> secondaryPower;
	
	@Override
	public void IC$set_primary_effect(int effectId) {
		if (effectId == -99) {
			this.primaryPower = null;
		}
		MobEffect effect = BuiltInRegistries.MOB_EFFECT.byId(effectId);
		this.primaryPower = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(effect);
	}

	@Override
	public void IC$set_secondary_effect(int effectId) {
		if (effectId == -99) {
			this.secondaryPower = null;
		}
		MobEffect effect = BuiltInRegistries.MOB_EFFECT.byId(effectId);
		this.secondaryPower = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(effect);
	}

}
