package net.runelite.client.plugins.MyPlugin;

import net.runelite.api.*;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.gameval.VarPlayerID;
import net.runelite.client.plugins.prayer.PrayerPlugin;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.util.ImageUtil;

import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;

@Slf4j
class MyPluginSceneOverlay extends Overlay {

    private static final BufferedImage HD_FRONT_BAR = ImageUtil.loadImageResource(PrayerPlugin.class, "front.png");
    private static final BufferedImage HD_BACK_BAR = ImageUtil.loadImageResource(PrayerPlugin.class, "back.png");
    private static final Dimension PRAYER_BAR_SIZE = new Dimension(30, 5);

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
        setPriority(PRIORITY_HIGH);
        setPosition(OverlayPosition.DYNAMIC);
    }

    @Override
    public Dimension render(Graphics2D graphics) {

        final int height = client.getLocalPlayer().getLogicalHeight() + 25;
        final LocalPoint locallocation = client.getLocalPlayer().getLocalLocation();
        WorldView wv = client.getWorldView(-1);
        final Point canvasPoint = Perspective.localToCanvas(client, locallocation, wv.getPlane(), height);

        this.drawHpBar(graphics, canvasPoint);
        this.drawMpBar(graphics, canvasPoint);
        this.drawSpecBar(graphics, canvasPoint);

        return null;
    }

    public void drawHpBar(Graphics2D graphics, Point canvasPoint) {
        final int hpX = canvasPoint.getX() - 15;
        final int hpY = canvasPoint.getY();
        final int hpWidth = PRAYER_BAR_SIZE.width;
        final int hpHeight = PRAYER_BAR_SIZE.height;

        final float ratio = (float) client.getBoostedSkillLevel(Skill.HITPOINTS) / client.getRealSkillLevel(Skill.HITPOINTS);
        final int hpFill = (int) Math.ceil(Math.min((hpWidth * ratio), hpWidth));

        graphics.setColor(Color.RED);
        graphics.fillRect(hpX, hpY, hpWidth, hpHeight);
        graphics.setColor(Color.GREEN);
        graphics.fillRect(hpX, hpY, hpFill, hpHeight);
    }

    public void drawMpBar(Graphics2D graphics, Point canvasPoint) {
        final int hpX = canvasPoint.getX() - 15;
        final int hpY = canvasPoint.getY() + (PRAYER_BAR_SIZE.height);
        final int mpWidth = PRAYER_BAR_SIZE.width;
        final int mpHeight = PRAYER_BAR_SIZE.height / 2;

        final float ratio = (float) client.getBoostedSkillLevel(Skill.PRAYER) / client.getRealSkillLevel(Skill.PRAYER);
        final int mpFill = (int) Math.ceil(Math.min((mpWidth * ratio), mpWidth));

        graphics.setColor(Color.BLACK);
        graphics.fillRect(hpX, hpY, mpWidth, mpHeight);
        graphics.setColor(Color.CYAN);
        graphics.fillRect(hpX, hpY, mpFill, mpHeight);
    }

    public void drawSpecBar(Graphics2D graphics, Point canvasPoint) {
        final int specX = canvasPoint.getX() - 15;
        final int specY = canvasPoint.getY() + (PRAYER_BAR_SIZE.height + (PRAYER_BAR_SIZE.height / 2));
        final int specWidth = PRAYER_BAR_SIZE.width;
        final int specHeight = PRAYER_BAR_SIZE.height / 2;

        // Get the varp spec value and calculate the bar width based on its percentage.
        final int specValue = (client.getVarpValue(VarPlayerID.SA_ENERGY) / 10);
        final double specBar = ((double)specValue / 100);
        final int mpFill = (int) Math.ceil(Math.min((specBar * specWidth), specWidth));

        graphics.setColor(Color.BLACK);
        graphics.fillRect(specX, specY, specWidth, specHeight);
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(specX, specY, mpFill, specHeight);
    }




}
