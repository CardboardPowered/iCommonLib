package net.fabricmc.api;

/**
 * A mod initializer.
 */
@FunctionalInterface
public interface ModInitializer {

	void onInitialize();

}