package me.isaiah.common.mixin.R1_19;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.cmixin.IMixinLootableContainerBlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.util.Identifier;

@Mixin(LootableContainerBlockEntity.class)
public class MixinLootableContainerBlockEntity implements IMixinLootableContainerBlockEntity {

	@Shadow
	public Identifier lootTableId;

	@Override
	public Identifier IC$get_loot_table_id() {
		return lootTableId;
	}
	
}
