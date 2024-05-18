package me.isaiah.common.fabric;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import me.isaiah.common.GameVersion;
import me.isaiah.common.IDatapack;
import me.isaiah.common.IServer;
import me.isaiah.common.Side;
import me.isaiah.common.cmixin.IMixinMinecraftServer;
import me.isaiah.common.world.IWorld;
import net.minecraft.MinecraftVersion;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.JsonHelper;
import net.minecraft.village.TradeOffer;

public class FabricServer implements IServer {

    private MinecraftServer mc;
    public HashMap<String, IWorld> worlds;

    public FabricServer(MinecraftServer mc) {
        this.mc = mc;
        this.worlds = new HashMap<>();
    }

    public void world(IWorld icommon, String name) {
        worlds.put(name, icommon);
    }

    @Override
    public String getMinecraftVersion() {
        return mc.getVersion();
    }

    @Override
    public Collection<IWorld> getWorlds() {
        return worlds.values();
    }

    @Override
    public IWorld getWorld(String name) {
        return worlds.get(name);
    }

	public static GameVersion getGameVersion() {
        if (null == GameVersion.INSTANCE) {
            GameVersion.INSTANCE = create();
        }
        return GameVersion.INSTANCE;
    }

    /*
     */
    public static GameVersion create() {
        try (InputStream inputStream = MinecraftVersion.class.getResourceAsStream("/version.json");){
            if (inputStream == null) return null;
            try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);){
                return new GameVersion(JsonHelper.deserialize(inputStreamReader));
            }
        } catch (Exception exception) {
            throw new IllegalStateException("Bad version info", exception);
        }
    }

    @Override
    public int getProtocolVersion() {
        return getGameVersion().getProtocolVersion();
    }

    @Override
    public Side getSide() {
        return mc.isDedicated() ? Side.SERVER : Side.CLIENT;
    }

    @Override
    public MinecraftServer getMinecraft() {
        return mc;
    }

    /**
     * See IMixinMinecraftServer.get_uuid_from_profile
     */
	@Override
	public UUID get_uuid_from_profile(GameProfile profile) {
		return ((IMixinMinecraftServer)mc).get_uuid_from_profile(profile);
	}
	
	/**
	 * @see {@link IMixinMinecraftServer.create_new_trade_offer}
	 */
	@Override
	public TradeOffer create_trade_offer(ItemStack result, int uses, int maxUses, boolean experienceReward, int experience, float priceMultiplier, int demand, int specialPrice) {
		return ((IMixinMinecraftServer)mc).create_new_trade_offer(result, uses, maxUses, experienceReward, experience, priceMultiplier, demand, specialPrice);
	}

	@Override
	public IDatapack get_datapack(ResourcePackProfile handler) {
		return new FabricDatapack(handler);
	}

}