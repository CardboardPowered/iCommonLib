package me.isaiah.common.cmixin;

public interface IMixinTameableEntity {

	/**
	 * convince method for TameableEntity#setTamed
	 * second argument only used for 1.20.5+
	 */
	public void IC$set_tamed(boolean tamed, boolean update_attributes);
	
}
