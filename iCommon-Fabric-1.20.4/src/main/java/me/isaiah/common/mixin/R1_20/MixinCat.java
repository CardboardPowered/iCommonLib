package me.isaiah.common.mixin.R1_20;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.entity.ICat;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.registry.Registries;

@Mixin(CatEntity.class)
public class MixinCat implements ICat {
	
	@Override
    public CatType get_cat_type() {
    	CatEntity mc_cat = (CatEntity) (Object) this;
        return CatType.values()[Registries.CAT_VARIANT.getRawId(mc_cat.getVariant())];
    }

    @Override
    public void set_cat_type(CatType type) {
    	CatEntity mc_cat = (CatEntity) (Object) this;
        mc_cat.setVariant(Registries.CAT_VARIANT.get(type.ordinal()));
    }

}