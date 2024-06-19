package dev.zyrakia.productiveplants.client.event.handler;

import dev.zyrakia.productiveplants.client.blockscanning.BlockFilter;
import dev.zyrakia.productiveplants.client.blockscanning.BlockScanMatch;
import dev.zyrakia.productiveplants.client.blockscanning.RegionBlockScanner;
import dev.zyrakia.productiveplants.client.cropdecoration.CropDecorationManager;
import dev.zyrakia.productiveplants.util.Vec;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class CropSearchTickHandler implements ClientTickEvents.EndWorldTick {

	/**
	 * The decoration manager to play effects at a certain position.
	 */
	public static final CropDecorationManager DECO_MANAGER = new CropDecorationManager();

	/**
	 * The tick gap between crop searches. Based off of 20tps.
	 */
	private static final int SEARCH_EVERY_TICKS = 10;

	/**
	 * The filter function used for block scanning.
	 */
	private static final BlockFilter CROP_FILTER = (BlockState state) -> state.getBlock() instanceof CropBlock;

	private int tickCounter = 0;

	@Override
	public void onEndTick(ClientWorld world) {
		tickCounter++;
		if (tickCounter < SEARCH_EVERY_TICKS)
			return;

		this.tickCounter = 0;
		this.performCropSearch(world);
	}

	public void performCropSearch(World world) {
		MinecraftClient client = MinecraftClient.getInstance();
		ClientPlayerEntity player = client.player;
		if (player == null)
			return;

		BlockPos currentPos = player.getBlockPos();
		RegionBlockScanner scanner = RegionBlockScanner.fromCenter(Vec.of(currentPos), 10, 5);

		ArrayList<BlockScanMatch> cropMatches = scanner.scanWorld(world, CROP_FILTER);
		cropMatches.forEach(this::handleCropMatch);
	}

	private void handleCropMatch(BlockScanMatch cropMatch) {
		BlockState cropState = cropMatch.state();
		CropBlock cropBlock = (CropBlock) cropState.getBlock();

		int age = cropBlock.getAge(cropState);
		if (age == cropBlock.getMaxAge())
			DECO_MANAGER.playEffectAt(cropMatch.world(), cropMatch.position());
	}

}
