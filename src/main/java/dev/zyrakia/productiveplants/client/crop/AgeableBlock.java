package dev.zyrakia.productiveplants.client.crop;

import dev.zyrakia.productiveplants.client.blockscanning.BlockFilter;
import dev.zyrakia.productiveplants.config.ProductivePlantsConfig;
import net.minecraft.block.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Wrapper around "crop" blocks to standardize getting age properties.
 */
public class AgeableBlock {

	/**
	 * The {@link BlockState} of this {@link AgeableBlock}.
	 */
	private final BlockState state;

	/**
	 * Creates a new {@link AgeableBlock} wrapper around the given state.
	 *
	 * @param state the {@link BlockState} to wrap
	 */
	public AgeableBlock(BlockState state) {
		this.state = state;
	}

	/**
	 * Returns the current age of this block, if this block is not supported, `0`
	 * will be returned.
	 *
	 * @return the current age
	 */
	public int getAge() {
		Block block = this.state.getBlock();
		if (block instanceof CropBlock cropBlock) {
			return cropBlock.getAge(this.state);
		} else if (block instanceof NetherWartBlock) {
			return this.state.get(NetherWartBlock.AGE);
		} else if (block instanceof AttachedStemBlock) {
			return 1;
		}

		return 0;
	}

	/**
	 * Returns the maximum age of this block, if this block is not supported, `-1`
	 * will be returned.
	 *
	 * @return the maximum age
	 */
	public int getMaxAge() {
		Block block = this.state.getBlock();
		if (block instanceof CropBlock cropBlock) {
			return cropBlock.getMaxAge();
		} else if (block instanceof NetherWartBlock) {
			return NetherWartBlock.field_31199;
		} else if (block instanceof AttachedStemBlock) {
			return 1;
		}

		return -1;
	}

	/**
	 * Block filter to include configured crop blocks.
	 */
	public static class SupportedFilter implements BlockFilter {

		/**
		 * The set of block classes that are currently implemented within the
		 * {@link AgeableBlock} class.
		 */
		private final Set<Class<? extends Block>> supportedBlocks = new HashSet<>();

		public SupportedFilter() {
			if (ProductivePlantsConfig.SupportedPlants.regularCrops)
				this.supportedBlocks.add(CropBlock.class);
			if (ProductivePlantsConfig.SupportedPlants.netherWarts)
				this.supportedBlocks.add(NetherWartBlock.class);
			if (ProductivePlantsConfig.SupportedPlants.attachedStems)
				this.supportedBlocks.add(AttachedStemBlock.class);
		}

		/**
		 * Returns true if the given {@link BlockState} is linked ot a crop that should
		 * be detected by this mod.
		 *
		 * @param state the {@link BlockState} to check
		 * @return whether the state is ac rop
		 */
		@Override
		public boolean filter(BlockState state) {
			Block block = state.getBlock();
			return supportedBlocks.stream().anyMatch((parent) -> parent.isAssignableFrom(block.getClass()));
		}
	}

}
