package me.isaiah.common.mixin.R1_18;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.entity.ICat;
import net.minecraft.entity.passive.CatEntity;

@Mixin(CatEntity.class)
public class MixinCat implements ICat {

	@Override
    public CatType get_cat_type() {
    	CatEntity mc_cat = (CatEntity) (Object) this;
        return CatType.values()[mc_cat.getCatType()];
    }

    @Override
    public void set_cat_type(CatType type) {
    	CatEntity mc_cat = (CatEntity) (Object) this;
        mc_cat.setCatType(type.ordinal());
    }

}