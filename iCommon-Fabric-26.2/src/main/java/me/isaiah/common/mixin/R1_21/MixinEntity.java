package me.isaiah.common.mixin.R1_21;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.cmixin.IMixinEntity;
import me.isaiah.common.entity.IEntity;
import me.isaiah.common.entity.IRemoveReason;
import net.minecraft.commands.CommandSource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;

@Mixin(Entity.class)
public class MixinEntity implements IMixinEntity {

    @Override
    public void Iremove(IRemoveReason r) {
    	Entity thiz = ((Entity)(Object)this);
    	
        switch (r) {
            case DIMENSION_CHANGE:
                removeAfterChangingDimensions();
                break;
            case DISCARDED:
                discard();
                break;
            case KILLED:
            	
            	Level world = thiz.level();
            	if (world instanceof ServerLevel) {
            		kill( (ServerLevel) world );
            	}
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

    @Shadow public void kill(ServerLevel world)  {} // Dimension change
    @Shadow public void discard() {} // Discard
    @Shadow public void removeAfterChangingDimensions() {} // Kill

    @Override
    public void IsendText(Component text, UUID id) {
    	Entity thiz = (Entity) (Object) this;
    	
    	if (thiz instanceof ServerPlayer) {
    		((ServerPlayer) thiz).sendSystemMessage(text);
    	}
    	
    	if (thiz instanceof CommandSource) {
    		((CommandSource) thiz).sendSystemMessage(text);
    	}
    	
    	// entity has no sendMessage in 1.20.4+
        // IgetMCEntity().sendMessage(text);
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
	public boolean IC$has_status_effect(MobEffect effect) {
		Entity thiz = (Entity) (Object) this;
		if (!(thiz instanceof LivingEntity)) {
			ICommonMod.LOGGER.info("ERROR: Entity is not living enitity");
			return false;
		}
		LivingEntity entity = (LivingEntity) thiz;
		Holder<MobEffect> key = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(effect);
        return entity.hasEffect(key);
	}

	@Override
	public void IC$add_status_effect(MobEffect effect, int duration, int amp, boolean ambient, boolean particles) {
		Entity thiz = (Entity) (Object) this;
		if (!(thiz instanceof LivingEntity)) {
			ICommonMod.LOGGER.info("ERROR: Entity is not living enitity");
			return;
		}
		LivingEntity entity = (LivingEntity) thiz;
		Holder<MobEffect> reg = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(effect);
        entity.addEffect(new MobEffectInstance(reg, duration, amp, ambient, particles));

	}

	@Override
	public void IC$remove_status_effect(MobEffect effect) {
		Entity thiz = (Entity) (Object) this;
		if (!(thiz instanceof LivingEntity)) {
			ICommonMod.LOGGER.info("ERROR: Entity is not living enitity");
			return;
		}
		LivingEntity entity = (LivingEntity) thiz;
		Holder<MobEffect> reg = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(effect);
        entity.removeEffect(reg);

	}

	@Override
	public MobEffectInstance IC$get_status_effect(int typeId) {
		Entity thiz = (Entity) (Object) this;
		if (!(thiz instanceof LivingEntity)) {
			ICommonMod.LOGGER.info("ERROR: Entity is not living enitity");
			return null;
		}
		LivingEntity entity = (LivingEntity) thiz;
		
		// StatusEffectInstance handle = entity.getStatusEffect(Registries.STATUS_EFFECT.get(typeId));
		Holder<MobEffect> reg = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(BuiltInRegistries.MOB_EFFECT.byId(typeId));
		MobEffectInstance handle = entity.getEffect(reg);
		return handle;
	}

	@Override
	public int IC$get_status_effect_id(MobEffectInstance handle) {
		MobEffect effect = handle.getEffect().value();
		return BuiltInRegistries.MOB_EFFECT.getId(effect);
	}

	@Override
	public void IC$teleport(ServerLevel world, double x, double y, double z) {
		IC$teleport(x, y, z);
	}

    private void IC$teleport(double destX, double destY, double destZ) {
    	Entity thiz = ((Entity) (Object) this);
        if (thiz.level() instanceof ServerLevel) {
        	BlockPos blockpos = BlockPos.containing(destX, destY, destZ);
            ChunkPos chunkcoordintpair = ChunkPos.containing(blockpos);
            
            thiz.placePortalTicket(blockpos);
            // ((ServerWorld)thiz.getWorld()).getChunkManager().addTicket(ChunkTicketType.POST_TELEPORT, chunkcoordintpair, 0, thiz.getId());
            thiz.level().getChunk(chunkcoordintpair.x(), chunkcoordintpair.z());
            thiz.teleportTo(destX, destY, destZ);
        }
    }
    
	@Override
	public Level ic$getWorld() {
		return ((Entity) (Object) this).level();
	}

}