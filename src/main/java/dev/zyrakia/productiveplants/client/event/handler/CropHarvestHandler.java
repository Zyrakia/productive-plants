package dev.zyrakia.productiveplants.client.event.handler;

import dev.zyrakia.productiveplants.client.ProductivePlantsClient;
import dev.zyrakia.productiveplants.client.blockscanning.BlockFilter;
import dev.zyrakia.productiveplants.client.crop.AgeableBlock;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class CropHarvestHandler implements AttackBlockCallback {
	private static final BlockFilter CROP_FILTER = new AgeableBlock.SupportedFilter();


	@Override
	public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockPos pos, Direction direction) {
		if (player.isSneaking() || player.isCreative())
			return ActionResult.PASS;

		BlockState state = world.getBlockState(pos);
		if (!CROP_FILTER.filter(state) || ProductivePlantsClient.getConfig().allowImmatureHarvest)
			return ActionResult.PASS;

		AgeableBlock block = new AgeableBlock(state);
		return block.getAge() == block.getMaxAge() ? ActionResult.PASS : ActionResult.FAIL;
	}
}
