package me.isaiah.common.mixin.R1_19;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.cmixin.IMixinEntity;
import me.isaiah.common.cmixin.SupportedVersion;
import me.isaiah.common.entity.IEntity;
import me.isaiah.common.entity.IRemoveReason;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
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
    
	@Override
	public boolean IC$has_status_effect(StatusEffect effect) {
		Entity thiz = (Entity) (Object) this;
		if (!(thiz instanceof LivingEntity)) {
			ICommonMod.LOGGER.info("ERROR: Entity is not living enitity");
			return false;
		}
		LivingEntity entity = (LivingEntity) (Object) this;
        return entity.hasStatusEffect(effect);
	}
	
	@Override
	public void IC$add_status_effect(StatusEffect effect, int duration, int amp, boolean ambient, boolean particles) {
		Entity thiz = (Entity) (Object) this;
		if (!(thiz instanceof LivingEntity)) {
			ICommonMod.LOGGER.info("ERROR: Entity is not living enitity");
			return;
		}
		LivingEntity entity = (LivingEntity) thiz;
        entity.addStatusEffect(new StatusEffectInstance(effect, duration, amp, ambient, particles));
	}
	
	@Override
	public void IC$remove_status_effect(StatusEffect effect) {
		Entity thiz = (Entity) (Object) this;
		if (!(thiz instanceof LivingEntity)) {
			ICommonMod.LOGGER.info("ERROR: Entity is not living enitity");
			return;
		}
		LivingEntity entity = (LivingEntity) thiz;
        entity.removeStatusEffect(effect);
	}
	
	@Override
	public StatusEffectInstance IC$get_status_effect(int typeId) {
		Entity thiz = (Entity) (Object) this;
		if (!(thiz instanceof LivingEntity)) {
			ICommonMod.LOGGER.info("ERROR: Entity is not living enitity");
			return null;
		}
		LivingEntity entity = (LivingEntity) thiz;
		StatusEffect effect = Registries.STATUS_EFFECT.get(typeId);
		StatusEffectInstance handle = entity.getStatusEffect(effect);
		return handle;
	}

	@Override
	public int IC$get_status_effect_id(StatusEffectInstance handle) {
		StatusEffect effect = handle.getEffectType();
		return Registries.STATUS_EFFECT.getRawId(effect);
	}
	
	@Override
	public void IC$teleport(ServerWorld world, double x, double y, double z) {
		((Entity) (Object) this).teleport(x, y, z);
	}


}