package me.isaiah.common.entity;

import me.isaiah.common.ICommandSource;
import me.isaiah.common.world.IWorld;

public interface IEntity extends ICommandSource {

	/**
	 */
	public Object getMC();

	/**
	 */
	public String getName();

	/**
	 */
	@Deprecated
	public void remove(IRemoveReason r);

	/**
	 */
	@Deprecated
	public boolean isRemoved();

	/**
	 */
	public String getDisplayedName();

	/**
	 */
	public void setDisplayedName(String name);

	/**
	 */
	public EntityType getEntityType();

	/**
	 */
	void collidesWith(IEntity e);

	/**
	 */
	void teleport(double x, double y, double z);

	/**
	 */
	void teleport(double x, double y, double z, float yaw, float pitch);

	/**
	 * @see IMixinTameableEntity#set_tamed
	 */
	void set_tamed(boolean tame, boolean updateAttrib);

	/**
	 * Retrives the {@link IWorld} for this Entity.
	 */
	IWorld getIWorld();

}