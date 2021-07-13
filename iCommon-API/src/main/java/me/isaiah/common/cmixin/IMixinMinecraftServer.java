package me.isaiah.common.cmixin;

import com.mojang.serialization.DynamicOps;

import net.minecraft.resource.ResourceManager;
import net.minecraft.util.dynamic.RegistryOps;
import net.minecraft.util.registry.DynamicRegistryManager.Impl;

public interface IMixinMinecraftServer {

    public RegistryOps<Object> Iof(DynamicOps<Object> delegate, ResourceManager resourceManager, Impl impl);

}