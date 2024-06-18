package dev.zyrakia.productiveplants.client.blockscanning;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

/**
 * Represents a block that matches the filter during
 * block scanning.
 *
 * @param state    the {@link BlockState} of the match
 * @param position the {@link BlockPos} of the match
 */
public record BlockScanMatch(BlockState state, BlockPos position) { }
