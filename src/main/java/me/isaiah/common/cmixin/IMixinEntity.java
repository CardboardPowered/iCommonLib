package me.isaiah.common.cmixin;

import java.util.UUID;

import me.isaiah.common.entity.IEntity;
import me.isaiah.common.entity.IRemoveReason;
import me.isaiah.common.fabric.entity.FabricEntity;
import me.isaiah.common.fabric.entity.FabricPlayer;
import me.isaiah.common.fabric.entity.FabricPrimedTnt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.TntEntity;
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

}