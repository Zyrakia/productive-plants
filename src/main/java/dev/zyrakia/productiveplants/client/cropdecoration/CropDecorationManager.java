package dev.zyrakia.productiveplants.client.cropdecoration;

import dev.zyrakia.productiveplants.config.ProductivePlantsConfig;
import dev.zyrakia.productiveplants.util.Vec;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
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

		Vec3d blockCenter = Vec.ofCenter(pos);

		ParticleEffect effect = ProductivePlantsConfig.getEffect();
		int intensity = ProductivePlantsConfig.maturityEffectIntensity;
		double spread = ProductivePlantsConfig.maturityEffectSpread;

		for (int i = 0; i < intensity; i++) {
			Vec3d particlePos = this.randomOffsetPos(blockCenter, spread);
			world.addParticle(effect, particlePos.x, particlePos.y, particlePos.z, 0, 0, 0);
		}
	}

	/**
	 * Offsets the given integer vector by a random amount between 0 - 1 in each
	 * direction.
	 *
	 * @param pos    the vector to offset
	 * @param spread the maximum spread that will be applied to each axis
	 * @return the offset vector
	 */
	private Vec3d randomOffsetPos(Vec3d pos, double spread) {
		Random r = new Random();

		return pos.add(r.nextDouble(-spread, spread), r.nextDouble(0, spread), r.nextDouble(-spread, spread));
	}

}
