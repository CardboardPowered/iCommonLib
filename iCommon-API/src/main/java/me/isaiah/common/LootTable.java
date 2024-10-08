package me.isaiah.common;

import java.util.Collection;
import java.util.Random;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

/**
 */
public interface LootTable {

    /**
     * Returns a mutable list of loot generated by this LootTable.
     */
    @NotNull
    Collection<ItemStack> populateLoot(@Nullable Random random, @NotNull LootInfo context);

    /**
     * Attempt to fill an inventory with this LootTable's loot.
     */
    void fillInventory(@NotNull Inventory inventory, @Nullable Random random, @NotNull LootInfo context);

    /**
     */
    String get_key();

}
