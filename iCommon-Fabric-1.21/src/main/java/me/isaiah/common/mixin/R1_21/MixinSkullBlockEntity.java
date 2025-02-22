package me.isaiah.common.mixin.R1_21;

import org.spongepowered.asm.mixin.Mixin;

import com.mojang.authlib.GameProfile;

import me.isaiah.common.cmixin.IMixinSkullBlockEntity;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.component.type.ProfileComponent;

@Mixin(SkullBlockEntity.class)
public class MixinSkullBlockEntity implements IMixinSkullBlockEntity {

	@Override
	public GameProfile IC$get_game_profile() {
		return ((SkullBlockEntity)(Object)this).getOwner().comp_2413();
	}
	
	@Override
	public void IC$set_game_profile(GameProfile profile) {
		((SkullBlockEntity)(Object)this).setOwner(new ProfileComponent(profile));
	}

}