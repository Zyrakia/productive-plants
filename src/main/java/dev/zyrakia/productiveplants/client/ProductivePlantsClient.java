package dev.zyrakia.productiveplants.client;

import dev.zyrakia.productiveplants.client.event.handler.CropSearchTickHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

/**
 * Client loader for this Fabric mod.
 */
public class ProductivePlantsClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ClientTickEvents.END_WORLD_TICK.register(new CropSearchTickHandler());
	}
	
}
