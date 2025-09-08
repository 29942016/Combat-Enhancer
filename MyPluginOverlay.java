package net.runelite.client.plugins.MyPlugin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import static net.runelite.client.ui.overlay.OverlayManager.OPTION_CONFIGURE;

public class MyPluginOverlay extends OverlayPanel {
    private static final String HEADER = "My Plugin Header";

    private final Client client;
    private final MyPluginPlugin plugin;
    private final MyPluginConfig config;

    @Inject
    private MyPluginOverlay(Client client, MyPluginPlugin plugin, MyPluginConfig config) {
        this.client = client;
        this.plugin = plugin;
        this.config = config;
        addMenuEntry(MenuAction.RUNELITE_OVERLAY_CONFIG, OPTION_CONFIGURE, "Overlay");
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        panelComponent.getChildren().add(TitleComponent.builder().text("TEST 123").color(Color.GREEN).build());
        return super.render(graphics);
    }
}
