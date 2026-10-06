package me.isaiah.common.mixin.R1_21;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.cmixin.IMixinGlobalPos;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;

@Mixin(GlobalPos.class)
public class MixinGlobalPos implements IMixinGlobalPos {

	@Override
	public BlockPos IC$get_pos() {
		// <=1.20.4 is method_19446 / getPos()
		// >=1.20.5 is comp_2208 / pos()
		return ((GlobalPos)(Object)this).pos();
	}

	@Override
	public Object IC$get_dimension() {
		// <=1.20.4 is method_19442 / getDimension()
		// >=1.20.5 is comp_2207 / dimension()
		return ((GlobalPos)(Object)this).dimension();
	}

}
