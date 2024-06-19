package dev.zyrakia.productiveplants.client.blockscanning;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * Allows for scanning of blocks within a given region
 * by a filter.
 */
public class RegionBlockScanner {

	private final Vec3i p1;
	private final Vec3i p2;

	/**
	 * Creates a new scanner with the given first corner and second corner.
	 *
	 * @param p1 the first corner of the region
	 * @param p2 the second corner of the region
	 */
	public RegionBlockScanner(Vec3i p1, Vec3i p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	/**
	 * Creates a new scanner that has corners going out in the specified
	 * amounts in each direction.
	 *
	 * @param center the center of the region of the scanner
	 * @param out    the vector determining the distance from the corners
	 * @return the scanner aligning with the region around the center
	 */
	public static RegionBlockScanner fromCenter(Vec3i center, Vec3i out) {
		return new RegionBlockScanner(center.subtract(out), center.add(out));
	}

	/**
	 * Scans for blocks in the region of this scanner that match the
	 * given {@link BlockFilter}.
	 *
	 * @param world  the world to scan
	 * @param filter the filter to scan with
	 * @return a list of matched positions and states
	 */
	public ArrayList<BlockScanMatch> scanWorld(World world, BlockFilter filter) {
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
					if (filter.filter(state))
						result.add(new BlockScanMatch(world, state, pos));
				}
			}
		}

		return result;
	}

}
