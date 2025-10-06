package me.isaiah.common.mixin.R1_21;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.entity.ICat;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.CatVariant;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;

@Mixin(CatEntity.class)
public class MixinCat implements ICat {
	
	@Override
    public CatType get_cat_type() {
    	CatEntity mc_cat = (CatEntity) (Object) this;
    	
    	RegistryEntry<CatVariant> v = mc_cat.getVariant();

    	return CatType.ALL_BLACK;

        // return CatType.values()[RegistryKeys.CAT_VARIANT.getRawId(mc_cat.getVariant().comp_349())];
    }

    @Override
    public void set_cat_type(CatType type) {
    	CatEntity mc_cat = (CatEntity) (Object) this;
    	

    	// TODO
        // mc_cat.setVariant(Registries.CAT_VARIANT.getEntry(type.ordinal()).get());
    }

}