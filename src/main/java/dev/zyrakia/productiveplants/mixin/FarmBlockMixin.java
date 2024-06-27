package dev.zyrakia.productiveplants.mixin;

import dev.zyrakia.productiveplants.event.custom.TrampleEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for any farmland block in order to detect trampling.
 */
@Mixin(FarmlandBlock.class)
public class FarmBlockMixin extends Block {

	public FarmBlockMixin(Settings settings) {
		super(settings);
	}

	@Inject(method = "onLandedUpon", at = @At("HEAD"), cancellable = true)
	private void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance,
	                          CallbackInfo ci) {
		super.onLandedUpon(world, state, pos, entity, fallDistance);

		boolean res = TrampleEvent.EVENT.invoker().trample(world, state, pos, entity, fallDistance);
		if (!res) ci.cancel();
	}

}
