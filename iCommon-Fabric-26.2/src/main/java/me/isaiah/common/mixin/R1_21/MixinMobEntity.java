package me.isaiah.common.mixin.R1_21;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.cmixin.IMixinMobEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.storage.loot.LootTable;

@Mixin(Mob.class)
public class MixinMobEntity implements IMixinMobEntity {

	// <=1.20.4: Lnet/minecraft/entity/mob/MobEntity;lootTable:Lnet/minecraft/util/Identifier;
	// >=1.20.5: Lnet/minecraft/entity/mob/MobEntity;lootTable:Lnet/minecraft/registry/RegistryKey;
	
	//@Shadow
	//public RegistryKey<LootTable> lootTable;

	@Shadow
    public Optional<ResourceKey<LootTable>> lootTable; // = Optional.empty();
	
	@Override
	public void IC$set_loot_table(Identifier id) {
		this.lootTable = Optional.of(IC$identifier_to_table(id));
	}

    private ResourceKey<net.minecraft.world.level.storage.loot.LootTable> IC$identifier_to_table(Identifier key) {
        return key == null ? null : ResourceKey.create(Registries.LOOT_TABLE, key);
    }
    
	@Override
	public Identifier IC$get_loot_table_id() {
		Mob e = ((Mob) (Object) this);
		
        if (lootTable == null || lootTable.isEmpty()) {
            lootTable = Optional.of( e.getLootTable().get() );
        }
		return lootTable.get().identifier();
	}

}
