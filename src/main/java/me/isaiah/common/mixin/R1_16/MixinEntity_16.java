package me.isaiah.common.mixin.R1_16;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.cmixin.IMixinEntity;
import me.isaiah.common.cmixin.SupportedVersion;
import me.isaiah.common.entity.IEntity;
import me.isaiah.common.entity.IRemoveReason;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;

@SupportedVersion({"1.14.4", "1.15.2", "1.16.4", "1.16.5"})
@Mixin(Entity.class)
public class MixinEntity_16 implements IMixinEntity {

    @Override
    public void Iremove(IRemoveReason r) {
        ((Entity)(Object)this).remove();
    }

    private IEntity icommon;

    @Override
    public IEntity getAsICommon() {
        if (null == icommon) icommon = newICommonInstance_InternalOnly();
        return icommon;
    }

    @Override
    public void IsendText(Text text, UUID id) {
        IgetMCEntity().sendSystemMessage(text, id);
    }

    @Override
    public boolean ic_isRemoved() {
        return ((Entity)(Object)this).removed;
    }

}