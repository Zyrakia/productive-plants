package dev.zyrakia.productiveplants.client.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.DropdownStringControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.zyrakia.productiveplants.ProductivePlants;
import dev.zyrakia.productiveplants.client.util.PlayerCommunication;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.stream.Collectors;

public class ProductivePlantsConfig {

	/**
	 * YACL {@link ConfigClassHandler} for this class.
	 */
	public static final ConfigClassHandler<ProductivePlantsConfig> HANDLER = ConfigClassHandler.createBuilder(
					ProductivePlantsConfig.class)
			.id(new Identifier(ProductivePlants.MOD_ID, "config"))
			.serializer(config -> GsonConfigSerializerBuilder.create(config)
					.setPath(FabricLoader.getInstance().getConfigDir().resolve(ProductivePlants.MOD_ID + ".json5"))
					.setJson5(true)
					.build())
			.build();

	/**
	 * Default value for {@link ProductivePlantsConfig#maturityDecorationEnabled}.
	 */
	private static final boolean MATURITY_DECORATION_ENABLED = true;

	/**
	 * Default value for {@link ProductivePlantsConfig#allowImmatureHarvest}.
	 */
	private static final boolean ALLOW_IMMATURE_HARVEST = true;

	/**
	 * Default value for {@link ProductivePlantsConfig#serverAllowTrampling}.
	 */
	private static final boolean SERVER_ALLOW_TRAMPLING = false;

	/**
	 * Default value for {@link ProductivePlantsConfig#decorateRegularCrops}.
	 */
	private static final boolean DECORATE_REGULAR_CROPS = true;

	/**
	 * Default value for {@link ProductivePlantsConfig#decorateNetherWarts}.
	 */
	private static final boolean DECORATE_NETHER_WARTS = true;

	/**
	 * Default value for {@link ProductivePlantsConfig#decorateAttachedStems}.
	 */
	private static final boolean DECORATE_ATTACHED_STEMS = false;


	/**
	 * Default value for {@link ProductivePlantsConfig#decorationEffectIdentifier}.
	 */
	private static final String DECORATION_EFFECT_IDENTIFIER = "minecraft:happy_villager";

	static {
		HANDLER.load();
	}

	@SerialEntry
	public boolean serverAllowTrampling = SERVER_ALLOW_TRAMPLING;

	/**
	 * Whether harvesting of immature plant is disabled.
	 */
	@SerialEntry
	public boolean allowImmatureHarvest = ALLOW_IMMATURE_HARVEST;

	/**
	 * Whether the maturity effect is enabled.
	 */
	@SerialEntry
	public boolean maturityDecorationEnabled = MATURITY_DECORATION_ENABLED;

	/**
	 * Whether regular crops ({@link net.minecraft.block.CropBlock} extending blocks) are observed by the mod.
	 */
	@SerialEntry
	public boolean decorateRegularCrops = DECORATE_REGULAR_CROPS;

	/**
	 * Whether nether warts are observed by the mod.
	 */
	@SerialEntry
	public boolean decorateNetherWarts = DECORATE_NETHER_WARTS;

	/**
	 * Whether attached stems ({@link net.minecraft.block.AttachedStemBlock} extending blocks) are observed by the mod.
	 */
	@SerialEntry
	public boolean decorateAttachedStems = DECORATE_ATTACHED_STEMS;

	/**
	 * The identifier of the effect that will be used.
	 */
	@SerialEntry
	public String decorationEffectIdentifier = DECORATION_EFFECT_IDENTIFIER;

	/**
	 * Builds a GUI screen to configure available options with an integration such as
	 * {@link com.terraformersmc.modmenu.ModMenu}.
	 *
	 * @param parent the parent screen
	 * @return the generated config screen
	 */
	public Screen createGui(Screen parent) {
		boolean isServer = MinecraftClient.getInstance().isIntegratedServerRunning();

		return YetAnotherConfigLib.createBuilder()
				.title(Text.of(ProductivePlants.MOD_NAME))
				.category(ConfigCategory.createBuilder()
						.name(Text.of("✨ " + ProductivePlants.MOD_NAME + " ✨"))
						.group(OptionGroup.createBuilder()
								.name(Text.of("Features"))
								.description(OptionDescription.of(
										Text.of("In this section are options to toggle the features of this mod " +
												"includes.")))
								.option(Option.<Boolean>createBuilder()
										.name(Text.of("Maturity Indication Effect"))
										.description(OptionDescription.of(
												Text.of("Determines whether particle effects are played when a " +
														"supported crop " + "has grown to maturity.")))
										.binding(MATURITY_DECORATION_ENABLED, () -> maturityDecorationEnabled,
												(v) -> maturityDecorationEnabled = v)
										.controller(TickBoxControllerBuilder::create)
										.build())
								.option(Option.<Boolean>createBuilder()
										.name(Text.of("Allow Immature Harvesting"))
										.description(OptionDescription.of(
												Text.of("Determines whether immature plants can be harvested. Always " + "allowed " + "temporarily while sneaking.")))
										.binding(ALLOW_IMMATURE_HARVEST, () -> allowImmatureHarvest,
												(v) -> allowImmatureHarvest = v)
										.controller(TickBoxControllerBuilder::create)
										.build())
								.optionIf(isServer, Option.<Boolean>createBuilder()
										.name(Text.of("Allow Cropland Trampling"))
										.description(OptionDescription.of(
												Text.of("Determines whether cropland can be trampled " + "by " +
														"landing on it" + ".")))
										.binding(SERVER_ALLOW_TRAMPLING, () -> serverAllowTrampling,
												(v) -> serverAllowTrampling = v)
										.controller(TickBoxControllerBuilder::create)
										.build())
								.build())
						.group(OptionGroup.createBuilder()
								.name(Text.of("Maturity Effect"))
								.collapsed(true)
								.description(OptionDescription.of(
										Text.of("In this section are options to change the appearance and behaviour " + "of" + " the " + "effect that appears when crops have reached maturity" + ".")))
								.option(Option.<String>createBuilder()
										.name(Text.of("Effect Identifier"))
										.description(OptionDescription.of(
												Text.of("This is the Minecraft identifier of the effect that will " +
														"play" + " when a " + "crop is mature.")))
										.binding(DECORATION_EFFECT_IDENTIFIER, () -> decorationEffectIdentifier,
												(v) -> decorationEffectIdentifier = v)
										.controller((v) -> DropdownStringControllerBuilder.create(v)
												.allowAnyValue(true)
												.allowEmptyValue(false)
												.values(getEffectNames()))
										.build())
								.build())
						.group(OptionGroup.createBuilder()
								.name(Text.of("Supported Plants"))
								.collapsed(true)
								.description(OptionDescription.of(
										Text.of("In this section are options to toggle the different plants that this "
												+ "mod " + "supports.")))
								.option(Option.<Boolean>createBuilder()
										.name(Text.of("Regular Crops"))
										.description(OptionDescription.of(
												Text.of("This will include all general crop blocks.")))
										.binding(DECORATE_REGULAR_CROPS, () -> decorateRegularCrops,
												(v) -> decorateRegularCrops = v)
										.controller(TickBoxControllerBuilder::create)
										.build())
								.option(Option.<Boolean>createBuilder()
										.name(Text.of("Nether Warts"))
										.binding(DECORATE_NETHER_WARTS, () -> decorateNetherWarts,
												(v) -> decorateNetherWarts = v)
										.controller(TickBoxControllerBuilder::create)
										.build())
								.option(Option.<Boolean>createBuilder()
										.name(Text.of("Attached Stems"))
										.description(OptionDescription.of(
												Text.of("This includes the stems of blocks such as melons or pumpkins" + ".")))
										.binding(DECORATE_ATTACHED_STEMS, () -> decorateAttachedStems,
												(v) -> decorateAttachedStems = v)
										.controller(TickBoxControllerBuilder::create)
										.build())
								.build())
						.build())
				.save(ProductivePlantsConfig.HANDLER::save)
				.build()
				.generateScreen(parent);
	}

	/**
	 * Returns the currently configured effect ID cast into a {@link ParticleEffect}.
	 *
	 * @return the currently configured particle effect
	 */
	public ParticleEffect getEffect() {
		String formattedName = this.decorationEffectIdentifier.toLowerCase().replace(" ", "_");
		Identifier effectIdentifier = Identifier.tryParse(formattedName);

		if (effectIdentifier != null) {
			ParticleType<?> particleType = Registries.PARTICLE_TYPE.get(effectIdentifier);
			if (particleType instanceof ParticleEffect) {
				this.decorationEffectIdentifier = formattedName;
				return (ParticleEffect) particleType;
			}
		}

		Text errorMessage = Text.literal("")
				.append("Effect identifier \"")
				.append(Text.literal(this.decorationEffectIdentifier).formatted(Formatting.WHITE, Formatting.BOLD))
				.append("\" is invalid and has been reset to the default value.")
				.formatted(Formatting.GOLD);
		PlayerCommunication.sendMessage(errorMessage);

		this.decorationEffectIdentifier = DECORATION_EFFECT_IDENTIFIER;

		return getEffect();
	}

	/**
	 * Returns a list of possible effect names based off of registered particle types.
	 *
	 * @return the list of names
	 */
	public List<String> getEffectNames() {
		return Registries.PARTICLE_TYPE.stream()
				.filter((v) -> v instanceof ParticleEffect)
				.map((v) -> ((ParticleEffect) v).asString())
				.collect(Collectors.toList());
	}

}
