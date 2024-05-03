package me.isaiah.common.cmixin;

import net.minecraft.util.math.BlockPos;

public interface IMixinGlobalPos {

	/**
	 * convince method.
	 * 
	 * <=1.20.4 is method_19446
	 * >=1.20.5 is comp_2208
	 * 
	 */
	public BlockPos IC$get_pos();
	
	/**
	 */
	public Object IC$get_dimension();
	
}
