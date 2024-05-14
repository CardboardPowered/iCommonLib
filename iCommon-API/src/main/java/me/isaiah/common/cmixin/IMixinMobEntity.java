package me.isaiah.common.cmixin;

import net.minecraft.util.Identifier;

public interface IMixinMobEntity {

	/**
	 */
	void IC$set_loot_table(Identifier id);
	
	/**
	 */
	Identifier IC$get_loot_table_id();

}
