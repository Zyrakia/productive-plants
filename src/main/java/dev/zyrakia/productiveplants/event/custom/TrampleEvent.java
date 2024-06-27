package dev.zyrakia.productiveplants.event.custom;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * A custom event linked to the trampling of a farmland block by landing upon it.
 */
public interface TrampleEvent {

	/**
	 * The trampling event instance.
	 */
	Event<TrampleEvent> EVENT = EventFactory.createArrayBacked(TrampleEvent.class,
			(listeners) -> (World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) -> {
				for (TrampleEvent listener : listeners) {
					boolean res = listener.trample(world, state, pos, entity, fallDistance);
					if (!res) return false;
				}

				return true;
			});

	/**
	 * Executed when a farmland block is trampled.
	 *
	 * @param world        the world of the trample
	 * @param state        the state of the block that was trampled
	 * @param pos          the pos of the block that was trampled
	 * @param entity       the entity that trampled it
	 * @param fallDistance the distance from which the entity fell to trample the block
	 * @return true if to continue with the trampling, false to block it
	 */
	boolean trample(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance);

}
