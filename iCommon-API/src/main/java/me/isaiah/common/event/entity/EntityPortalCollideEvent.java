package me.isaiah.common.event.entity;

import me.isaiah.common.event.Cancelable;
import me.isaiah.common.event.Event;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Represents an Entity walking into a Nether Portal
 * 
 * @implNote net.minecraft.block.NetherPortalBlock#onEntityCollision
 */
public class EntityPortalCollideEvent extends Event implements Cancelable {

	private boolean cancel = false;
	private Entity entity;
	private BlockPos pos;

	public EntityPortalCollideEvent(BlockState state, World world, BlockPos pos, Entity entity) {
		this.entity = entity;
		this.pos = pos;
	}
	
	@Override
	public boolean isCanceled() {
		return this.cancel;
	}

	@Override
	public void setCanceled(boolean cancel) {
		this.cancel = cancel;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public BlockPos getBlockPos() {
		return pos;
	}

}
