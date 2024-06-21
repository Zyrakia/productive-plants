package dev.zyrakia.productiveplants.client.blockscanning;

import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;

/**
 * Block filter to include configured crop blocks.
 */
public class CropBlockFilter implements BlockFilter {

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
