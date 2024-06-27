package dev.zyrakia.productiveplants.client.cropdecoration;

import dev.zyrakia.productiveplants.ProductivePlants;
import dev.zyrakia.productiveplants.util.Vec;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Allows for playing of the crop decoration at any {@link BlockPos}.
 */
public class CropDecorationManager {

	/**
	 * Plays the crop decoration particle at the given position in the given world.
	 *
	 * @param world the world to play the decoration in
	 * @param pos   the position to play at
	 */
	public void playEffectAt(World world, BlockPos pos) {
		Vec3d particlePos = this.randomOffsetPos(pos);

		ParticleEffect effect = ProductivePlants.CONFIG.getEffect();
		world.addParticle(effect, particlePos.x, particlePos.y, particlePos.z, 0, 0, 0);
	}

	/**
	 * Offsets the given integer vector by a random amount between 0 - 1 in each direction.
	 *
	 * @param pos the vector to offset
	 * @return the offset vector
	 */
	private Vec3d randomOffsetPos(Vec3i pos) {
		Random r = new Random();

		final double minOffset = 0.25;
		final double maxOffset = 0.75;

		return Vec3d.of(pos)
				.add(r.nextDouble(minOffset, maxOffset), r.nextDouble(minOffset, maxOffset),
						r.nextDouble(minOffset, maxOffset));
	}

	/**
	 * Offsets the given block position by a random amount between 0 - 1 in each direction.
	 *
	 * @param pos the block position to offset
	 * @return the offset vector
	 */
	private Vec3d randomOffsetPos(BlockPos pos) {
		return this.randomOffsetPos(Vec.of(pos));
	}

}
