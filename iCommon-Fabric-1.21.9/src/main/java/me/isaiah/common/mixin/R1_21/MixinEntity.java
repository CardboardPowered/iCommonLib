package me.isaiah.common.mixin.R1_21;

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
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

@SupportedVersion({"1.17"})
@Mixin(Entity.class)
public class MixinEntity implements IMixinEntity {

    @Override
    public void Iremove(IRemoveReason r) {
    	Entity thiz = ((Entity)(Object)this);
    	
        switch (r) {
            case DIMENSION_CHANGE:
                removeFromDimension();
                break;
            case DISCARDED:
                discard();
                break;
            case KILLED:
            	
            	World world = thiz.getEntityWorld();
            	if (world instanceof ServerWorld) {
            		kill( (ServerWorld) world );
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

    @Shadow public void kill(ServerWorld world)  {} // Dimension change
    @Shadow public void discard() {} // Discard
    @Shadow public void removeFromDimension() {} // Kill

    @Override
    public void IsendText(Text text, UUID id) {
    	Entity thiz = (Entity) (Object) this;
    	
    	if (thiz instanceof ServerPlayerEntity) {
    		((ServerPlayerEntity) thiz).sendMessage(text);
    	}
    	
    	if (thiz instanceof CommandOutput) {
    		((CommandOutput) thiz).sendMessage(text);
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
	public boolean IC$has_status_effect(StatusEffect effect) {
		Entity thiz = (Entity) (Object) this;
		if (!(thiz instanceof LivingEntity)) {
			ICommonMod.LOGGER.info("ERROR: Entity is not living enitity");
			return false;
		}
		LivingEntity entity = (LivingEntity) thiz;
		RegistryEntry<StatusEffect> key = Registries.STATUS_EFFECT.getEntry(effect);
        return entity.hasStatusEffect(key);
	}

	@Override
	public void IC$add_status_effect(StatusEffect effect, int duration, int amp, boolean ambient, boolean particles) {
		Entity thiz = (Entity) (Object) this;
		if (!(thiz instanceof LivingEntity)) {
			ICommonMod.LOGGER.info("ERROR: Entity is not living enitity");
			return;
		}
		LivingEntity entity = (LivingEntity) thiz;
		RegistryEntry<StatusEffect> reg = Registries.STATUS_EFFECT.getEntry(effect);
        entity.addStatusEffect(new StatusEffectInstance(reg, duration, amp, ambient, particles));

	}

	@Override
	public void IC$remove_status_effect(StatusEffect effect) {
		Entity thiz = (Entity) (Object) this;
		if (!(thiz instanceof LivingEntity)) {
			ICommonMod.LOGGER.info("ERROR: Entity is not living enitity");
			return;
		}
		LivingEntity entity = (LivingEntity) thiz;
		RegistryEntry<StatusEffect> reg = Registries.STATUS_EFFECT.getEntry(effect);
        entity.removeStatusEffect(reg);

	}

	@Override
	public StatusEffectInstance IC$get_status_effect(int typeId) {
		Entity thiz = (Entity) (Object) this;
		if (!(thiz instanceof LivingEntity)) {
			ICommonMod.LOGGER.info("ERROR: Entity is not living enitity");
			return null;
		}
		LivingEntity entity = (LivingEntity) thiz;
		
		// StatusEffectInstance handle = entity.getStatusEffect(Registries.STATUS_EFFECT.get(typeId));
		RegistryEntry<StatusEffect> reg = Registries.STATUS_EFFECT.getEntry(Registries.STATUS_EFFECT.get(typeId));
		StatusEffectInstance handle = entity.getStatusEffect(reg);
		return handle;
	}

	@Override
	public int IC$get_status_effect_id(StatusEffectInstance handle) {
		StatusEffect effect = handle.getEffectType().comp_349();
		return Registries.STATUS_EFFECT.getRawId(effect);
	}

	@Override
	public void IC$teleport(ServerWorld world, double x, double y, double z) {
		IC$teleport(x, y, z);
	}

    private void IC$teleport(double destX, double destY, double destZ) {
    	Entity thiz = ((Entity) (Object) this);
        if (thiz.getEntityWorld() instanceof ServerWorld) {
        	BlockPos blockpos = BlockPos.ofFloored(destX, destY, destZ);
            ChunkPos chunkcoordintpair = new ChunkPos(blockpos);
            
            thiz.addPortalChunkTicketAt(blockpos);
            // ((ServerWorld)thiz.getWorld()).getChunkManager().addTicket(ChunkTicketType.POST_TELEPORT, chunkcoordintpair, 0, thiz.getId());
            thiz.getEntityWorld().getChunk(chunkcoordintpair.x, chunkcoordintpair.z);
            thiz.requestTeleport(destX, destY, destZ);
        }
    }

}