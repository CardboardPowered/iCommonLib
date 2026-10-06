package me.isaiah.common.mixin.R1_21;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.mojang.authlib.GameProfile;

import me.isaiah.common.cmixin.IMixinSkullBlockEntity;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.level.block.entity.SkullBlockEntity;

@Mixin(SkullBlockEntity.class)
public class MixinSkullBlockEntity implements IMixinSkullBlockEntity {

	@Override
	public GameProfile IC$get_game_profile() {
		return ((SkullBlockEntity)(Object)this).getOwnerProfile().partialProfile();
	}
	
	// Lnet/minecraft/block/entity/SkullBlockEntity;owner:Lnet/minecraft/component/type/ProfileComponent;
	
	@Shadow
	public ResolvableProfile owner;
	
	@Override
	public void IC$set_game_profile(GameProfile profile) {
		this.owner = ResolvableProfile.createResolved(profile);
	}

}