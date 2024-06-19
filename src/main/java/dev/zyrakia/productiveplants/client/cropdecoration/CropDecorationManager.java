package dev.zyrakia.productiveplants.client.cropdecoration;

import dev.zyrakia.productiveplants.util.Vec;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
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
	 * The particle that is used for all spawned decorations.
	 */
	private final ParticleEffect particle = ParticleTypes.HAPPY_VILLAGER;

	/**
	 * Plays the crop decoration particle at the given position
	 * in the given world.
	 *
	 * @param world the world to play the decoration in
	 * @param pos   the position to play at
	 */
	public void playEffectAt(World world, BlockPos pos) {
		Vec3d particlePos = this.randomOffsetPos(pos);
		world.addParticle(this.particle, particlePos.x, particlePos.y, particlePos.z, 0, 0, 0);
	}

	/**
	 * Offsets the given integer vector by a random amount between 0 - 1
	 * in each direction.
	 *
	 * @param pos the vector to offset
	 * @return the offset vector
	 */
	private Vec3d randomOffsetPos(Vec3i pos) {
		Random r = new Random();
		return Vec3d.of(pos).add(r.nextDouble(), r.nextDouble(), r.nextDouble());
	}

	/**
	 * Offsets the given block position by a random amount between 0 - 1
	 * in each direction.
	 *
	 * @param pos the block position to offset
	 * @return the offset vector
	 */
	private Vec3d randomOffsetPos(BlockPos pos) {
		return this.randomOffsetPos(Vec.of(pos));
	}

}
