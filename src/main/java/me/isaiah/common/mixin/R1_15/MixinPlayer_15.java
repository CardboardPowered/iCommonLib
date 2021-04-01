package me.isaiah.common.mixin.R1_15;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.cmixin.SupportedVersion;
import me.isaiah.common.mixin.R1_16.MixinEntity_16;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

@SupportedVersion({"1.14.4", "1.15.2"})
@Mixin(ServerPlayerEntity.class)
public class MixinPlayer_15 extends MixinEntity_16 {

    @Override
    public void IsendText(Text text, UUID id) {
        ((ServerPlayerEntity)IgetMCEntity()).sendMessage(text, false);
    }


}
