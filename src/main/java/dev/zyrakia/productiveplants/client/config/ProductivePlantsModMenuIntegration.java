package dev.zyrakia.productiveplants.client.config;

import com.teamresourceful.resourcefulconfig.client.ConfigScreen;
import com.teamresourceful.resourcefulconfig.common.config.ResourcefulConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.zyrakia.productiveplants.ProductivePlants;
import dev.zyrakia.productiveplants.config.ProductivePlantsConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * Integrates the configuration with the {@link ModMenuApi} configuration
 * screen.
 */
@Environment(EnvType.CLIENT)
public class ProductivePlantsModMenuIntegration implements ModMenuApi {

	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return (parent) -> {
			ResourcefulConfig conf = ProductivePlants.CONFIGURATOR.getConfig(ProductivePlantsConfig.class);
			return conf == null ? null : new ConfigScreen(parent, null, conf);
		};
	}

}
