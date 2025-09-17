package net.runelite.client.plugins.MyPlugin;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import java.util.ArrayList;

import static net.runelite.client.plugins.MyPlugin.constants.InitialiseNotifications;


// HP resources
// Interface Styles Addon
// Users\olive\IdeaProjects\runelite\runelite-client\src\main\resources\net\runelite\client\plugins\interfacestyles\2010\healthbar

// Prayer Bar
// Prayer

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

    @Inject
    public ArrayList<Notification> notifications = new ArrayList<Notification>();

    @Override
	protected void startUp() throws Exception
	{
        overlayManager.add(overlay);
        overlayManager.add(sceneOverlay);
        this.notifications = InitialiseNotifications();
		log.info("Bucky's Combat UI Started");
	}

	@Override
	protected void shutDown() throws Exception
	{
        overlayManager.remove(overlay);
        overlayManager.remove(sceneOverlay);
        log.info("Bucky's Combat UI Stopped");
	}

    @Subscribe
    public void onItemContainerChanged(final ItemContainerChanged event) {
        if(event.getContainerId() != net.runelite.api.gameval.InventoryID.INV)
            return;

        // Magic Buff
        final ItemContainer inventory = event.getItemContainer();

        for(Notification n: this.notifications) {
            n.IsAvailable = false;
        }

        for(int itemId: constants.MagicItems) {
            if(inventory.contains(itemId)) {
                Notification magicBoost = this.notifications.stream()
                        .filter(item -> item.Id.equals(constants.NotificationName.MAGIC_BOOST))
                        .findFirst()
                        .orElse(null);
                if(magicBoost != null) {
                    log.info("Has magic item.");
                    magicBoost.IsAvailable = true;
                }
            }
        }
        //TODO
        // Super Combat
        for(int itemId: constants.CombatItems) {
            if(inventory.contains(itemId)) {
                Notification combatBoost = this.notifications.stream()
                        .filter(item -> item.Id.equals(constants.NotificationName.COMBAT_BOOST))
                        .findFirst()
                        .orElse(null);
                if(combatBoost != null) {
                    log.info("Has combat item.");
                    combatBoost.IsAvailable = true;
                }
            }
        }

        // Range
    }

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN) {
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Bucky's Combat UI Loaded", null);
        }
	}

    @Subscribe
    public void onGameTick(GameTick gameTick) {
        // Combat Buff Check
//        final ItemContainer itemContainer = client.getItemContainer(net.runelite.api.gameval.InventoryID.INV);
//
//        if(itemContainer == null)
//            return;
//
//        final Item[] items = itemContainer.getItems();
//
//        for(Item item : items) {
//            final int id = item.getId();
//            // Imbued Heart
//            switch(id) {
//                case 20724: // Imbued Heart
//                    break;
//                case 27641: // Saturated Heart
//                    break;
//            }

//            // Divine Ranging
//            //37951
//            //37944
//            //37975
//            //37966
//        }




        // Spell Check
    }

	@Provides
    MyPluginConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(MyPluginConfig.class);
	}
}
