package dev.zyrakia.productiveplants.client.event.handler;

import dev.zyrakia.productiveplants.client.ProductivePlantsClient;
import dev.zyrakia.productiveplants.client.blockscanning.BlockScanMatch;
import dev.zyrakia.productiveplants.client.blockscanning.CropBlockFilter;
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
import net.minecraft.util.math.Vec3i;
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
	 * The size of the block scanning region from the player.
	 */
	private static final Vec3i SCAN_REGION = new Vec3i(10, 5, 10);

	private int tickCounter = 0;

	@Override
	public void onEndTick(ClientWorld world) {
		tickCounter++;
		if (tickCounter < SEARCH_EVERY_TICKS)
			return;

		this.tickCounter = 0;
		this.performCropSearch(world);
	}

	/**
	 * Uses a {@link RegionBlockScanner} to scan for blocks around
	 * the player.
	 *
	 * @param world the world that should be scanned
	 */
	public void performCropSearch(World world) {
		if (!ProductivePlantsClient.getConfig().maturityEffect.effectEnabled)
			return;

		MinecraftClient client = MinecraftClient.getInstance();
		ClientPlayerEntity player = client.player;
		if (player == null)
			return;

		BlockPos currentPos = player.getBlockPos();
		RegionBlockScanner scanner = RegionBlockScanner.fromCenter(Vec.of(currentPos), SCAN_REGION);

		ArrayList<BlockScanMatch> cropMatches = scanner.scanWorld(world, new CropBlockFilter());
		cropMatches.forEach(this::handleCropMatch);
	}

	/**
	 * Decorates the given {@link BlockScanMatch}, assuming
	 * that it is associated with a valid {@link CropBlock}.
	 *
	 * @param cropMatch the {@link BlockScanMatch} associated to a {@link CropBlock}
	 */
	private void handleCropMatch(BlockScanMatch cropMatch) {
		BlockState cropState = cropMatch.state();
		CropBlock cropBlock = (CropBlock) cropState.getBlock();

		int age = cropBlock.getAge(cropState);
		if (age == cropBlock.getMaxAge())
			DECO_MANAGER.playEffectAt(cropMatch.world(), cropMatch.position());
	}

}
