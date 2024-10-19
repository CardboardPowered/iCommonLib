package me.isaiah.common.mixin.R1_21;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.cmixin.IMixinLootableContainerBlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

@Mixin(LootableContainerBlockEntity.class)
public class MixinLootableContainerBlockEntity implements IMixinLootableContainerBlockEntity {

	@Shadow
	public RegistryKey<LootTable> lootTable;

	@Override
	public Identifier IC$get_loot_table_id() {
		return lootTable.getValue();
	}
	
}
