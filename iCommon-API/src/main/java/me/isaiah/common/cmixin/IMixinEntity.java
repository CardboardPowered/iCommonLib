package me.isaiah.common.cmixin;

import java.util.UUID;

import me.isaiah.common.entity.IEntity;
import me.isaiah.common.entity.IRemoveReason;
import me.isaiah.common.fabric.entity.FabricArmorStandEntity;
import me.isaiah.common.fabric.entity.FabricEntity;
import me.isaiah.common.fabric.entity.FabricPlayer;
import me.isaiah.common.fabric.entity.FabricPrimedTnt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public interface IMixinEntity {

    public default Entity IgetMCEntity() {
        return (Entity)(Object)this;
    }

    public IEntity getAsICommon();

    public default IEntity newICommonInstance_InternalOnly() {
        Entity mc = IgetMCEntity();
        if (mc instanceof PlayerEntity) {
            if (mc instanceof ServerPlayerEntity) {  }
            else {  } // TODO
            return new FabricPlayer(mc);
        }
        else if (mc instanceof TntEntity) { return new FabricPrimedTnt(mc); }
        else if (mc instanceof ArmorStandEntity) {
            return new FabricArmorStandEntity(mc);
        }
        return new FabricEntity(mc);
    }

    /**
     * @reason 1.16 & 1.17 differ in entity removal
     */
    public void Iremove(IRemoveReason r);

    /**
     * @reason 1.16 requires an UUID to be sent
     */
    public void IsendText(Text text, UUID id);

    /**
     * 1.16 - removed
     * 1.17 - isRemoved()
     */
    public boolean ic_isRemoved();

    /**
     */
    public boolean IC$has_status_effect(StatusEffect effect);
    
    /**
     */
    public void IC$add_status_effect(StatusEffect effect, int duration, int amp, boolean ambient, boolean particles);
    

    /**
     */
    public void IC$remove_status_effect(StatusEffect effect);
    
    /**
     */
    public StatusEffectInstance IC$get_status_effect(int type);
    
    /**
     */
    public int IC$get_status_effect_id(StatusEffectInstance effect);

}