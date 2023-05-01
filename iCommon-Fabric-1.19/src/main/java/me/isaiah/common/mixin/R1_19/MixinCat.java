package me.isaiah.common.mixin.R1_19;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.entity.ICat;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.util.registry.Registry;

@Mixin(CatEntity.class)
public class MixinCat implements ICat {
	
	@Override
    public CatType get_cat_type() {
    	CatEntity mc_cat = (CatEntity) (Object) this;
        return CatType.values()[Registry.CAT_VARIANT.getRawId(mc_cat.getVariant())];
    }

    @Override
    public void set_cat_type(CatType type) {
    	CatEntity mc_cat = (CatEntity) (Object) this;
        mc_cat.setVariant(Registry.CAT_VARIANT.get(type.ordinal()));
    }

}