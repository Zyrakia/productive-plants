package dev.zyrakia.productiveplants.client.blockscanning;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Represents a block that matches the filter during
 * block scanning.
 *
 * @param world    the {@link World} of the match
 * @param state    the {@link BlockState} of the match
 * @param position the {@link BlockPos} of the match
 */
public record BlockScanMatch(World world, BlockState state, BlockPos position) {
}
