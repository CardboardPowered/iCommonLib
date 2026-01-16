package me.isaiah.common;

import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.server.world.ServerWorld;

public class LazyBee {

    public static void IC$add_bee_to_beehive(BlockEntity tileentity, ServerWorld world, int rand) {
		if (tileentity instanceof BeehiveBlockEntity) {
            BeehiveBlockEntity beehive = (BeehiveBlockEntity) tileentity;
            BeeEntity bee = new BeeEntity(EntityType.BEE, world);
            
            // BeeEntity bee = EntityType.BEE.create(world);
            
            // BeeEntity bee = LazyBee.get(world);
            
            beehive.tryEnterHive(bee, false, rand);
        }
	}

}