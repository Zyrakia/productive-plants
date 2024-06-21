package dev.zyrakia.productiveplants.client.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * Integrates the {@link AutoConfig} configuration with
 * the {@link ModMenuApi} configuration screen.
 */
@Environment(EnvType.CLIENT)
public class ProductivePlantsModMenuIntegration implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return parent -> AutoConfig.getConfigScreen(ProductivePlantsConfig.class, parent).get();
	}
}
