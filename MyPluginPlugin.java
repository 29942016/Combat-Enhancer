package net.runelite.client.plugins.MyPlugin;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import java.awt.*;

@Slf4j
@PluginDescriptor(
	name = "MyPlugin"
)
public class MyPluginPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private MyPluginConfig config;

    @Inject
    private MyPluginOverlay overlay;

    @Inject
    private OverlayManager overlayManager;


    @Override
	protected void startUp() throws Exception
	{
        overlayManager.add(overlay);
		log.info("My Plugin started! Hotchange 2");
	}

	@Override
	protected void shutDown() throws Exception
	{
        overlayManager.remove(overlay);
		log.info("My Plugin stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);

		}
	}

    @Subscribe
    public void onGameTick(GameTick gameTick) {
        Player player = client.getLocalPlayer();
    }

	@Provides
    MyPluginConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(MyPluginConfig.class);
	}
}
