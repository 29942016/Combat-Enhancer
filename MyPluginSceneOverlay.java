package net.runelite.client.plugins.MyPlugin;

import net.runelite.api.Actor;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Tile;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import java.awt.*;
import java.awt.geom.Rectangle2D;

@Slf4j
class MyPluginSceneOverlay extends Overlay {

    private final Client client;
    private final MyPluginPlugin plugin;
    private final MyPluginConfig config;
    private final BuffManager buffManager;

    @Inject
    private MyPluginSceneOverlay(final Client client, final MyPluginConfig config, final MyPluginPlugin plugin, BuffManager buffManager) {
        this.client = client;
        this.config = config;
        this.plugin = plugin;
        this.buffManager = buffManager;

        setLayer(OverlayLayer.ABOVE_SCENE);
        setPosition(OverlayPosition.DYNAMIC);
    }

    @Override
    public Dimension render(Graphics2D graphics) {

        Shape testShape = client.getLocalPlayer().getConvexHull();
        Polygon tile = client.getLocalPlayer().getCanvasTilePoly();
        Polygon tileRoof = client.getLocalPlayer().getCanvasTilePoly();

        final int height = client.getLocalPlayer().getLogicalHeight();

        if(testShape == null) {
            return null;
        }


        double x = tile.getBounds().getCenterX();
        double y = tile.getBounds().getCenterY();


        tileRoof.translate(0, -(height));

        graphics.setColor(Color.GREEN);
        graphics.drawPolygon(tile);
        graphics.setColor(Color.MAGENTA);
        graphics.drawPolygon(tileRoof);

        graphics.setColor(Color.RED);
        graphics.drawString("X",(int)x, (int)y);

        return null;
    }
}
