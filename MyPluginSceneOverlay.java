package net.runelite.client.plugins.MyPlugin;

import net.runelite.api.Actor;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import java.awt.*;

@Slf4j
class MyPluginSceneOverlay extends Overlay {

    private final Client client;
    private final MyPluginPlugin plugin;
    private final MyPluginConfig config;

    @Inject
    private MyPluginSceneOverlay(final Client client, final MyPluginConfig config, final MyPluginPlugin plugin) {
        this.client = client;
        this.config = config;
        this.plugin = plugin;
        setLayer(OverlayLayer.ABOVE_SCENE);
        setPosition(OverlayPosition.DYNAMIC);
    }

    @Override
    public Dimension render(Graphics2D graphics) {

        Shape testShape = client.getLocalPlayer().getConvexHull();

        if(testShape == null) {
            return null;
        }

        Color color = Color.GREEN;
        graphics.setColor(color);
        graphics.draw(testShape);
        graphics.fill(testShape);

        return null;
    }
}
