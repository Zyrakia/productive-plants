package dev.zyrakia.productiveplants.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

/**
 * Utility class related to Minecraft API vectors.
 * {@link Vec3i}, {@link Vec3d}.
 */
public class Vec {

	/**
	 * Returns a new integer vector with the truncated X, Y, Z of the {@link Vec3d}.
	 *
	 * @param vec the double vector to truncate
	 * @return the truncated integer vector
	 */
	public static Vec3i of(Vec3d vec) {
		return new Vec3i((int) vec.x, (int) vec.y, (int) vec.z);
	}

	/**
	 * Returns a new integer vector with the X, Y, Z of {@code n}.
	 *
	 * @param n the value of the vectors axes
	 * @return the vector with all axis set to {@code n}
	 */
	public static Vec3i of(int n) {
		return new Vec3i(n, n, n);
	}

	/**
	 * Returns a new integer vector with the X, Y, Z of the {@link BlockPos}.
	 *
	 * @param pos the {@link BlockPos} to copy into the vector
	 * @return the vector with the same values as the {@link BlockPos}
	 */
	public static Vec3i of(BlockPos pos) {
		return new Vec3i(pos.getX(), pos.getY(), pos.getZ());
	}

	/**
	 * Returns a new double vector with the X, Y, Z of {@code n}.
	 *
	 * @param n the value of the vectors axes
	 * @return the vector with all axis set to {@code n}
	 */
	public static Vec3d of(double n) {
		return new Vec3d(n, n, n);
	}

	/**
	 * Returns a new double vector with the given X, Y, Z of the {@link BlockPos},
	 * but offset by 0.5 in each axis to align with the center of the given block
	 * position.
	 * 
	 * @param pos the {@link BlockPos} to copy into the vector
	 * @return the block-center aligned vector
	 */
	public static Vec3d ofCenter(BlockPos pos) {
		return new Vec3d(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
	}

}
