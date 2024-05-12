package me.isaiah.common.cmixin;

import com.mojang.authlib.GameProfile;

public interface IMixinSkullBlockEntity {

	/**
	 */
	public GameProfile IC$get_game_profile();

	/**
	 */
	public void IC$set_game_profile(GameProfile profile);
	
}
