package me.isaiah.common.mixin.R1_21;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.entity.ICat;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.animal.feline.Cat;
import net.minecraft.world.entity.animal.feline.CatVariant;

@Mixin(Cat.class)
public class MixinCat implements ICat {
	
	@Override
    public CatType get_cat_type() {
    	Cat mc_cat = (Cat) (Object) this;
    	
    	Holder<CatVariant> v = mc_cat.getVariant();

    	return CatType.ALL_BLACK;

        // return CatType.values()[RegistryKeys.CAT_VARIANT.getRawId(mc_cat.getVariant().comp_349())];
    }

    @Override
    public void set_cat_type(CatType type) {
    	Cat mc_cat = (Cat) (Object) this;
    	

    	// TODO
        // mc_cat.setVariant(Registries.CAT_VARIANT.getEntry(type.ordinal()).get());
    }

}