package dev.zyrakia.productiveplants.client.blockscanning;

import net.minecraft.block.BlockState;

/**
 * Represents a functional interface that is used
 * to filter {@link BlockState} instances while
 * scanning blocks.
 */
@FunctionalInterface
public interface BlockFilter {

	/**
	 * Returns whether the given {@link BlockState} passes
	 * the filter.
	 *
	 * @param state the {@link BlockState} to check
	 * @return true if the state passes, false otherwise
	 */
	public boolean filter(BlockState state);

}
