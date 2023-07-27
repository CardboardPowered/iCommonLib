package me.isaiah.common.mixin.R1_20;

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
public class MixinEntity implements IMixinEntity {

    @Override
    public void Iremove(IRemoveReason r) {
        switch (r) {
            case DIMENSION_CHANGE:
                removeFromDimension();
                break;
            case DISCARDED:
                discard();
                break;
            case KILLED:
                kill();
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

    @Shadow public void kill()  {} // Dimension change
    @Shadow public void discard() {} // Discard
    @Shadow public void removeFromDimension() {} // Kill

    @Override
    public void IsendText(Text text, UUID id) {
        IgetMCEntity().sendMessage(text);
    }

    @Override
    public boolean ic_isRemoved() {
        return isRemoved();
    }
    
    @Shadow
    public boolean isRemoved() {
        return false;
    }

}