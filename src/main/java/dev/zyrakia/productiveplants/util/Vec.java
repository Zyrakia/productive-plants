package dev.zyrakia.productiveplants.util;

import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

/**
 * Utility class related to Minecraft API vectors.
 * {@link Vec3i}, {@link Vec3d}.
 */
public class Vec {

	/**
	 * Truncates a 3 axis double vector into a 3 axis integer vector.
	 *
	 * @param doubleVec the double vector to truncate
	 * @return the truncated integer vector
	 */
	public static Vec3i truncDouble(Vec3d doubleVec) {
		return new Vec3i((int) doubleVec.x, (int) doubleVec.y, (int) doubleVec.z);
	}

}
