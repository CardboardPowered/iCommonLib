package me.isaiah.common;

import com.google.gson.JsonObject;

import net.minecraft.util.JsonHelper;

public class GameVersion {
    
    public static GameVersion INSTANCE;

    private final int protocolVersion;
    private final String releaseTarget;

    public GameVersion(JsonObject jsonObject) {
        this.releaseTarget = JsonHelper.getString(jsonObject, "release_target");
        this.protocolVersion = JsonHelper.getInt(jsonObject, "protocol_version");
    }

    /**
     */
    public String getReleaseTarget() {
        return releaseTarget;
    }

    /**
     */
    public int getProtocolVersion() {
        return protocolVersion;
    }

}
