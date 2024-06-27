package dev.zyrakia.productiveplants.client.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;

/**
 * Utilities to communicate messages to the current client player.
 */
public class PlayerCommunication {

	/**
	 * Sends a system message in the chat or sets a message on the toolbar.
	 *
	 * @param msg     the message to send
	 * @param toolbar whether the message should be sent in chat or above the toolbar
	 */
	public static void sendMessage(Text msg, boolean toolbar) {
		ClientPlayerEntity player = getPlayer();
		if (player == null) return;

		player.sendMessage(msg, toolbar);
	}

	/**
	 * Sends a system message in the chat.
	 *
	 * @param msg the message to send
	 */
	public static void sendMessage(Text msg) {
		sendMessage(msg, false);
	}

	/**
	 * Returns the current {@link ClientPlayerEntity} for the game instance.
	 *
	 * @return the current player entity
	 */
	private static ClientPlayerEntity getPlayer() {
		return MinecraftClient.getInstance().player;
	}

}
