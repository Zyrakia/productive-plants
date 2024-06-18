package dev.zyrakia.productiveplants.client.blockscanning;

import net.minecraft.block.BlockState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

import java.util.ArrayList;

public class RegionBlockScanner {

	private final Vec3i p1;
	private final Vec3i p2;

	public RegionBlockScanner(Vec3i p1, Vec3i p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public static RegionBlockScanner fromCenter(Vec3i center, int xzOut, int yOut) {
		Vec3i out = new Vec3i(xzOut, yOut, xzOut);
		return new RegionBlockScanner(center.subtract(out), center.add(out));
	}

	public ArrayList<BlockScanMatch> scanWorld(ClientWorld world, BlockFilter filter) {
		int initX = p1.getX();
		int initY = p1.getY();
		int initZ = p1.getZ();

		int endX = p2.getX();
		int endY = p2.getY();
		int endZ = p2.getZ();

		ArrayList<BlockScanMatch> result = new ArrayList<>();

		for (int x = initX; x <= endX; x++) {
			for (int y = initY; y <= endY; y++) {
				for (int z = initZ; z <= endZ; z++) {
					BlockPos pos = new BlockPos(x, y, z);
					BlockState state = world.getBlockState(pos);
					if (filter.filter(state)) result.add(new BlockScanMatch(state, pos));
				}
			}
		}

		return result;
	}

}
