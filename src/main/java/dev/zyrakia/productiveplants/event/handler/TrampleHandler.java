package dev.zyrakia.productiveplants.event.handler;

import dev.zyrakia.productiveplants.ProductivePlants;
import dev.zyrakia.productiveplants.event.custom.TrampleEvent;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Responsible for preventing the trampling of farmland blocks.
 */
public class TrampleHandler implements TrampleEvent {

	@Override
	public boolean trample(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		return ProductivePlants.CONFIG.serverAllowTrampling;
	}

}
