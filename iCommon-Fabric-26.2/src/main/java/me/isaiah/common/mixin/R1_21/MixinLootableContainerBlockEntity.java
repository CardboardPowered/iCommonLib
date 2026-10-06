package me.isaiah.common.mixin.R1_21;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.cmixin.IMixinLootableContainerBlockEntity;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.storage.loot.LootTable;

@Mixin(RandomizableContainerBlockEntity.class)
public class MixinLootableContainerBlockEntity implements IMixinLootableContainerBlockEntity {

	@Shadow
	public ResourceKey<LootTable> lootTable;

	@Override
	public Identifier IC$get_loot_table_id() {
		return lootTable.identifier();
	}
	
}
