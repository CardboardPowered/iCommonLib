package me.isaiah.common.mixin.R1_17;

import org.spongepowered.asm.mixin.Mixin;

import com.mojang.serialization.DynamicOps;

import me.isaiah.common.cmixin.IMixinMinecraftServer;
import net.minecraft.resource.ResourceManager;
import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import net.minecraft.util.dynamic.RegistryOps;
import net.minecraft.util.registry.DynamicRegistryManager.Impl;

@Mixin(MinecraftDedicatedServer.class)
public class MixinMinecraftServer implements IMixinMinecraftServer {

    @Override
    public RegistryOps<Object> Iof(DynamicOps<Object> delegate, ResourceManager resourceManager, Impl impl) {
        return RegistryOps.of(delegate,resourceManager,impl);
    }

}