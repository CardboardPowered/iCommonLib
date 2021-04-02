package me.isaiah.common.fabric.entity;

import me.isaiah.common.cmixin.IMixinEntity;
import me.isaiah.common.entity.IEntity;
import me.isaiah.common.entity.IPrimedTnt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;

public class FabricPrimedTnt extends FabricEntity implements IPrimedTnt {

    public FabricPrimedTnt(Entity mc) {
        super((TntEntity)mc);
    }

    @Override
    public TntEntity getMCEntity() {
        return (TntEntity) mc;
    }

    @Override
    public IEntity getSource() {
        LivingEntity source = getMCEntity().getCausingEntity();
        return (source != null) ? ((IMixinEntity)source).getAsICommon() : null;
    }

}
