package dev.zyrakia.productiveplants.client.crop;

import dev.zyrakia.productiveplants.client.blockscanning.BlockFilter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;

/**
 * Wrapper around "crop" blocks to standardize
 * getting age properties.
 */
public class AgeableBlock {

	/**
	 * The {@link BlockState} of this {@link AgeableBlock}.
	 */
	private BlockState state;

	/**
	 * Creates a new {@link AgeableBlock} wrapper around the given
	 * state.
	 *
	 * @param state the {@link BlockState} to wrap
	 */
	public AgeableBlock(BlockState state) {
		this.state = state;
	}

	/**
	 * Returns the current age of this block, if
	 * this block is not supported, `0` will be returned.
	 *
	 * @return the current age
	 */
	public int getAge() {
		Block block = this.state.getBlock();
		if (block instanceof CropBlock cropBlock) {
			return cropBlock.getAge(this.state);
		}

		return 0;
	}

	/**
	 * Returns the maximum age of this block, if
	 * this block is not supported, `-1` will be returned.
	 *
	 * @return the maximum age
	 */
	public int getMaxAge() {
		Block block = this.state.getBlock();
		if (block instanceof CropBlock cropBlock) {
			return cropBlock.getMaxAge();
		}

		return -1;
	}

	/**
	 * Block filter to include configured crop blocks.
	 */
	public static class SupportedFilter implements BlockFilter {

		/**
		 * Returns true if the given {@link BlockState} is
		 * linked ot a crop that should be detected by this mod.
		 *
		 * @param state the {@link BlockState} to check
		 * @return whether the state is ac rop
		 */
		@Override
		public boolean filter(BlockState state) {
			return state.getBlock() instanceof CropBlock;
		}
	}

}
