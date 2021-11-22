package me.isaiah.common.event.world;

import me.isaiah.common.event.Event;
import net.minecraft.world.chunk.WorldChunk;

public class WorldChunkInitEvent extends Event {

    private WorldChunk chunk;

    public WorldChunkInitEvent(WorldChunk chunk) {
        this.chunk = chunk;
    }

    public WorldChunk getWorld() {
        return chunk;
    }

}