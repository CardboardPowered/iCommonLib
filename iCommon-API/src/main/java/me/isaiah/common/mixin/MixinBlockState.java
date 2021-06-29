package me.isaiah.common.mixin;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.cmixin.IMixinBlockState;
import me.isaiah.common.fabric.FabricBlockState;
import me.isaiah.common.fabric.FabricWorld;
import me.isaiah.common.block.IBlockState;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

@Mixin(BlockState.class)
public class MixinBlockState implements IMixinBlockState {

    public IBlockState icommon;

    @Override
    public IBlockState getAsICommon(FabricWorld w, BlockPos pos) {
        if (null == icommon) {
            icommon = new FabricBlockState(w, pos);
        }
        return icommon;
    }

}