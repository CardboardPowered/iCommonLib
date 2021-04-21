package me.isaiah.common.mixin.R1_17;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.cmixin.IMixinEntity;
import me.isaiah.common.cmixin.SupportedVersion;
import me.isaiah.common.entity.IEntity;
import me.isaiah.common.entity.IRemoveReason;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;

@SupportedVersion({"1.17"})
@Mixin(Entity.class)
public class MixinEntity_17 implements IMixinEntity {

    @Override
    public void Iremove(IRemoveReason r) {
        switch (r) {
            case DIMENSION_CHANGE:
                method_30076();
                break;
            case DISCARDED:
                method_31472();
                break;
            case KILLED:
                method_5768();
                break;
            default:
                ICommonMod.LOGGER.warn("Unknown RemoveReason: " + r.toString());
                break;
        }
    }

    private IEntity icommon;

    @Override
    public IEntity getAsICommon() {
        if (null == icommon) icommon = newICommonInstance_InternalOnly();
        return icommon;
    }

    @Shadow(remap = false) public void method_5768()  {} // Dimension change
    @Shadow(remap = false) public void method_31472() {} // Discard
    @Shadow(remap = false) public void method_30076() {} // Kill

    @Override
    public void IsendText(Text text, UUID id) {
        IgetMCEntity().sendSystemMessage(text, id);
    }

    @Override
    public boolean ic_isRemoved() {
        return method_31481();
    }
    
    @Shadow(remap = false)
    public boolean method_31481() {
        return false;
    }

}