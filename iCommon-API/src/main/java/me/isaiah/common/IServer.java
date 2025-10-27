package me.isaiah.common;

import java.util.Collection;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import me.isaiah.common.world.IWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;

public interface IServer {

    /**
     */
    public String getMinecraftVersion();

    /**
     */
    public Collection<IWorld> getWorlds();

    /**
     * Retrives the World given the Level Name
     * (Ex: "world", "world_nether", "world_the_end")
     */
    public IWorld getWorld(String name);
    
    /**
     * Retrives the World given the Registry Name
     * (Ex: "minecraft:overworld", "minecraft:the_end")
     */
	IWorld getWorld(Identifier id);

    /**
     */
    public int getProtocolVersion();

    /**
     * Find the {@link Side} of this server
     * 
     * @return {@link Side#SERVER} - If DedicatedServer
     * @return {@link Side#CLIENT} - If IntegratedServer
     */
    public Side getSide();

    /**
     * Retrives the value of the current mod loader
     * 
     * Current possible values:
     * @return {@link Loader#FABRIC}
     * @return {@link Loader#FORGE}
     */
    public default Loader getLoaderType() {
        return ICommonMod.modloader;
    }

    /**
     * Get the MinecraftServer object
     */
    public MinecraftServer getMinecraft();

    
    /**
     * Get UUID from Profile
     */
    public UUID get_uuid_from_profile(GameProfile profile);

    /**
     */
	TradeOffer create_trade_offer(ItemStack result, int uses, int maxUses, boolean experienceReward, int experience, float priceMultiplier, int demand, int specialPrice);
    
	/**
	 */
	IDatapack get_datapack(ResourcePackProfile handler);
	
}