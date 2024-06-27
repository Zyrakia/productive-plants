package dev.zyrakia.productiveplants;

import dev.zyrakia.productiveplants.client.config.ProductivePlantsConfig;
import dev.zyrakia.productiveplants.event.custom.TrampleEvent;
import dev.zyrakia.productiveplants.event.handler.TrampleHandler;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductivePlants implements ModInitializer {

	/**
	 * The human-readable name of this mod.
	 */
	public static final String MOD_NAME = "Productive Plants";

	/**
	 * The global logger used for this mod.
	 */
	public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

	/**
	 * The precise mod ID of this mod.
	 */
	public static final String MOD_ID = "productive-plants";

	/**
	 * Represents the configuration instance.
	 */
	public static final ProductivePlantsConfig CONFIG = ProductivePlantsConfig.HANDLER.instance();

	@Override
	public void onInitialize() {
		TrampleEvent.EVENT.register(new TrampleHandler());
	}

}
