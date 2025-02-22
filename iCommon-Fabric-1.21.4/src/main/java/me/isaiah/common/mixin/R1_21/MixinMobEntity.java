package me.isaiah.common.mixin.R1_21;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.cmixin.IMixinMobEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

@Mixin(MobEntity.class)
public class MixinMobEntity implements IMixinMobEntity {

	// <=1.20.4: Lnet/minecraft/entity/mob/MobEntity;lootTable:Lnet/minecraft/util/Identifier;
	// >=1.20.5: Lnet/minecraft/entity/mob/MobEntity;lootTable:Lnet/minecraft/registry/RegistryKey;
	
	//@Shadow
	//public RegistryKey<LootTable> lootTable;

	@Shadow
    public Optional<RegistryKey<LootTable>> lootTable; // = Optional.empty();
	
	@Override
	public void IC$set_loot_table(Identifier id) {
		this.lootTable = Optional.of(IC$identifier_to_table(id));
	}

    private RegistryKey<net.minecraft.loot.LootTable> IC$identifier_to_table(Identifier key) {
        return key == null ? null : RegistryKey.of(RegistryKeys.LOOT_TABLE, key);
    }
    
	@Override
	public Identifier IC$get_loot_table_id() {
		MobEntity e = ((MobEntity) (Object) this);
		
        if (lootTable == null || lootTable.isEmpty()) {
            lootTable = Optional.of( e.getLootTableKey().get() );
        }
		return lootTable.get().getValue();
	}

}
