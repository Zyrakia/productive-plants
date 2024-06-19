package dev.zyrakia.productiveplants.client.cropdecoration;

import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Allows for
 */
public class CropDecorationManager {

	private final DefaultParticleType particle = ParticleTypes.HAPPY_VILLAGER;

	public void playEffectAt(World world, BlockPos pos) {
		Vec3d particlePos = this.randomOffsetPos(pos);
		world.addParticle(this.particle, particlePos.getX(), particlePos.getY(), particlePos.getZ(), 0, 0, 0);
	}

	private Vec3d randomOffsetPos(Vec3i pos) {
		Random r = new Random();
		return Vec3d.of(pos).add(r.nextDouble(), r.nextDouble(), r.nextDouble());
	}

	private Vec3d randomOffsetPos(BlockPos pos) {
		return this.randomOffsetPos(new Vec3i(pos.getX(), pos.getY(), pos.getZ()));
	}

}
