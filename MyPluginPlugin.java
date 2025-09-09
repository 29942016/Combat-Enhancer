package net.runelite.client.plugins.MyPlugin;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.ItemSpawned;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
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
    private MyPluginSceneOverlay sceneOverlay;

    @Inject
    private OverlayManager overlayManager;

    @Override
	protected void startUp() throws Exception
	{
        overlayManager.add(overlay);
        overlayManager.add(sceneOverlay);
		log.info("My Plugin started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
        overlayManager.remove(overlay);
        overlayManager.remove(sceneOverlay);
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

    @Inject
    ItemManager itemManager;

    @Subscribe
    public void onItemSpawned(ItemSpawned itemSpawned) {
        log.info("Item spawned");
        Tile tile = itemSpawned.getTile();
        TileItem item = itemSpawned.getItem();
        ItemComposition composition = itemManager.getItemComposition(item.getId());

        final GroundItem groundItem = GroundItem.builder()
                .id(item.getId())
                .location(tile.getWorldLocation())
                .itemId(item.getId())
                .quantity(item.getQuantity())
                .name(composition.getName())
                .ownership(item.getOwnership())
                .isPrivate(item.isPrivate())
                .build();

        log.info(groundItem.print());
        log.info("end");
    }

	@Provides
    MyPluginConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(MyPluginConfig.class);
	}
}
