package me.isaiah.common.mixin.R1_16;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.cmixin.SupportedVersion;
import net.minecraft.network.MessageType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

@SupportedVersion({"1.16.4", "1.16.5", "1.17"})
@Mixin(ServerPlayerEntity.class)
public class MixinPlayer extends MixinEntity {

    @Override
    public void IsendText(Text text, UUID id) {
        ((ServerPlayerEntity)IgetMCEntity()).sendMessage(text, MessageType.CHAT, null == id ? Util.NIL_UUID : id);
    }

}
