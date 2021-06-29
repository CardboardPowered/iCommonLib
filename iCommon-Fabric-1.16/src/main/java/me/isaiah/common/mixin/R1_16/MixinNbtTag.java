package me.isaiah.common.mixin.R1_16;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.nbt.INbtElement;
import net.minecraft.nbt.Tag;

/**
 * Note: 
 *   Called 'Tag' in 1.16
 *   Called 'NbtElement' in 1.17
 */
@Mixin(Tag.class)
public interface MixinNbtTag extends INbtElement {
}