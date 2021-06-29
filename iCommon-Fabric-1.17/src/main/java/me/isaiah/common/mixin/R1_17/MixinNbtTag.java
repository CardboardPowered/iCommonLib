package me.isaiah.common.mixin.R1_17;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.nbt.INbtElement;
import net.minecraft.nbt.NbtElement;

/**
 * Note: 
 *   Called 'Tag' in 1.16
 *   Called 'NbtElement' in 1.17
 */
@Mixin(NbtElement.class)
public interface MixinNbtTag extends INbtElement {
}