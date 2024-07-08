package dev.zyrakia.productiveplants.config;

import com.teamresourceful.resourcefulconfig.common.annotations.Category;
import com.teamresourceful.resourcefulconfig.common.annotations.Comment;
import com.teamresourceful.resourcefulconfig.common.annotations.Config;
import com.teamresourceful.resourcefulconfig.common.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.common.annotations.ConfigSeparator;
import com.teamresourceful.resourcefulconfig.common.config.EntryType;
import com.teamresourceful.resourcefulconfig.web.annotations.WebInfo;

import dev.zyrakia.productiveplants.client.util.PlayerCommunication;
import net.minecraft.block.CropBlock;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

/**
 * This is the utility configuration class for this mod.
 */
@Config(value = "productive-plants")
@WebInfo()
public final class ProductivePlantsConfig {

	private static final String DEFAULT_EFFECT = "minecraft:happy_villager";

	/**
	 * Determines whether the mature plant indication effect is enabled.
	 */
	@ConfigSeparator(translation = "Maturity Indication")
	@ConfigEntry(id = "maturity-effect-enabled", type = EntryType.BOOLEAN, translation = "Maturity Effect")
	public static boolean maturityEffectEnabled = true;

	/**
	 * Determines which particle effect is used for the mature plant indication
	 * effect.
	 */
	@ConfigEntry(id = "maturity-effect-identifier", type = EntryType.STRING, translation = "Effect Identifier")
	@Comment(value = "This should be the identifier of a Minecraft or mod particle effect.")
	public static String maturityEffectIdentifier = "minecraft:happy_villager";

	/**
	 * Determines whether immature plants can be harvested.
	 */
	@ConfigSeparator(translation = "Other Features")
	@ConfigEntry(id = "block-immature-harvesting", type = EntryType.BOOLEAN, translation = "Block Immature Harvesting")
	@Comment(value = "This is always temporarily disabled while sneaking.")
	public static boolean blockImmatureHarvest = true;

	/**
	 * Determines whether farmland can be trampled by landing on it.
	 */
	@ConfigEntry(id = "block-trampling", type = EntryType.BOOLEAN, translation = "Block Farmland Trampling")
	@Comment(value = "This setting must be enabled on the server to take effect.")
	public static boolean blockTrampling = true;

	/**
	 * This is the configuration category to determine which plant blocks are
	 * supported by this mod.
	 */
	@Category(id = "supported-plants", translation = "Supported Plants")
	public static final class SupportedPlants {

		/**
		 * Determines whether regular {@link CropBlock} blocks are supported.
		 */
		@ConfigSeparator(translation = "Supported Plants", tooltip = "These are all the plants that are detected by this mod for maturity indication and other features.")
		@ConfigEntry(id = "support-regular-crops", type = EntryType.BOOLEAN, translation = "Regular Crops")
		@Comment("This includes regular Minecraft or mod provided crop blocks, such as wheat and carrots.")
		public static boolean regularCrops = true;

		/**
		 * Determines whether nether warts are supported, because they are not regular
		 * {@link CropBlock} instances.
		 */
		@ConfigEntry(id = "support-nether-warts", type = EntryType.BOOLEAN, translation = "Nether Warts")
		public static boolean netherWarts = true;

		/**
		 * Determines whether attached stems are supported.
		 */
		@ConfigEntry(id = "support-attached-stems", type = EntryType.BOOLEAN, translation = "Attached Stems")
		@Comment("This includes the stems of blocks such as melons or pumpkins.")
		public static boolean attachedStems = false;

	}

	/**
	 * Returns the currently configured effect ID cast into a
	 * {@link ParticleEffect}.
	 *
	 * @return the currently configured particle effect
	 */
	public static ParticleEffect getEffect() {
		String formattedName = maturityEffectIdentifier.toLowerCase().replace(" ", "_");
		Identifier effectIdentifier = Identifier.tryParse(formattedName);

		if (effectIdentifier != null) {
			ParticleType<?> particleType = Registries.PARTICLE_TYPE.get(effectIdentifier);
			if (particleType instanceof ParticleEffect) {
				maturityEffectIdentifier = formattedName;
				return (ParticleEffect) particleType;
			}
		}

		Text errorMessage = Text.literal("")
				.append("Effect identifier \"")
				.append(Text.literal(maturityEffectIdentifier).formatted(Formatting.WHITE, Formatting.BOLD))
				.append("\" is invalid and has been reset to the default value.")
				.formatted(Formatting.GOLD);
		PlayerCommunication.sendMessage(errorMessage);

		maturityEffectIdentifier = DEFAULT_EFFECT;

		return getEffect();
	}

}
