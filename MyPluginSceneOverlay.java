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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
class MyPluginSceneOverlay extends Overlay {

    private static final BufferedImage HD_FRONT_BAR = ImageUtil.loadImageResource(PrayerPlugin.class, "front.png");
    private static final BufferedImage HD_BACK_BAR = ImageUtil.loadImageResource(PrayerPlugin.class, "back.png");
    private static final Dimension PRAYER_BAR_SIZE = new Dimension(30, 5);

    private final Client client;
    private final MyPluginPlugin plugin;
    private final MyPluginConfig config;
    private final BuffManager buffManager;

    private final ArrayList<Notification> notifierBuffs = new ArrayList<Notification>();
    private final ArrayList<Notification> notifierSpells = new ArrayList<Notification>();

    @Inject
    private MyPluginSceneOverlay(final Client client, final MyPluginConfig config, final MyPluginPlugin plugin, BuffManager buffManager) {
        this.client = client;
        this.config = config;
        this.plugin = plugin;
        this.buffManager = buffManager;

        notifierSpells.add(new Notification(" T", "Thralls", Color.BLUE));
        notifierSpells.add(new Notification("V", "Vengeance", Color.RED));
        notifierSpells.add(new Notification("DC", "Death Charge", Color.BLUE));

        notifierBuffs.add(new Notification("C", "Super Combat", Color.RED));
        notifierBuffs.add(new Notification("R", "Divine Ranging", Color.GREEN));
        notifierBuffs.add(new Notification("M", "Imbued Heart", Color.BLUE));


        setLayer(OverlayLayer.ABOVE_SCENE);
        setPriority(PRIORITY_HIGH);
        setPosition(OverlayPosition.DYNAMIC);
    }


    final int barWidth = PRAYER_BAR_SIZE.width;
    final int barHeight = PRAYER_BAR_SIZE.height;

    @Override
    public Dimension render(Graphics2D graphics) {

        final int height = client.getLocalPlayer().getLogicalHeight() + 25;
        final LocalPoint locallocation = client.getLocalPlayer().getLocalLocation();
        WorldView wv = client.getWorldView(-1);
        final Point canvasPoint = Perspective.localToCanvas(client, locallocation, wv.getPlane(), height);

        // Bar Offsets
        final Point anchorPosition = new Point(canvasPoint.getX() - 15, canvasPoint.getY());

        // Anchor everything relative to the HP bar
        // this.drawAnchorPoint(graphics, anchorPosition);
        this.drawHpBar(graphics, anchorPosition);
        this.drawMpBar(graphics, anchorPosition);
        this.drawSpecBar(graphics, anchorPosition);
        this.drawThrallPanel(graphics, anchorPosition);

        return null;
    }

    public void drawAnchorPoint(Graphics2D graphics, Point anchorPoint) {
        graphics.setColor(Color.RED);
        graphics.drawString(".", anchorPoint.getX(), anchorPoint.getY());
    }

    public void drawHpBar(Graphics2D graphics, Point anchorPoint) {
        final float ratio = (float) client.getBoostedSkillLevel(Skill.HITPOINTS) / client.getRealSkillLevel(Skill.HITPOINTS);
        final int hpFill = (int) Math.ceil(Math.min((barWidth * ratio), barWidth));

        graphics.setColor(Color.RED);
        graphics.fillRect(anchorPoint.getX(), anchorPoint.getY(), barWidth, barHeight);
        graphics.setColor(Color.GREEN);
        graphics.fillRect(anchorPoint.getX(), anchorPoint.getY(), hpFill, barHeight);
    }

    public void drawMpBar(Graphics2D graphics, Point anchorPoint) {
        final float ratio = (float) client.getBoostedSkillLevel(Skill.PRAYER) / client.getRealSkillLevel(Skill.PRAYER);
        final int mpFill = (int) Math.ceil(Math.min((barWidth * ratio), barWidth));
        final int yOffset = anchorPoint.getY() + barHeight;

        graphics.setColor(Color.BLACK);
        graphics.fillRect(anchorPoint.getX(), yOffset, barWidth, barHeight / 2);
        graphics.setColor(Color.CYAN);
        graphics.fillRect(anchorPoint.getX(), yOffset, mpFill, barHeight / 2);
    }

    public void drawSpecBar(Graphics2D graphics, Point anchorPoint) {
        // Get the varp spec value and calculate the bar width based on its percentage.
        final int specValue = (client.getVarpValue(VarPlayerID.SA_ENERGY) / 10);
        final double specBar = ((double)specValue / 100);
        final int mpFill = (int) Math.ceil(Math.min((specBar * barWidth), barWidth));

        final int yOffset = anchorPoint.getY() + (barHeight + (barHeight / 2));

        graphics.setColor(Color.BLACK);
        graphics.fillRect(anchorPoint.getX(), yOffset, barWidth, barHeight / 2);
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(anchorPoint.getX(), yOffset, mpFill, barHeight / 2);
    }

    // Notifiers
    // T - Thrall
    // V - Venge
    // C - SCB
    // M - Heart
    // R - Divine Ranging
    public void drawThrallPanel(Graphics2D graphics, Point anchorPoint) {

        graphics.setColor(Color.BLUE);
        final Font fontName = graphics.getFont();
        final Font f = new Font(fontName.getName(), fontName.getStyle(), 12);
        graphics.setFont(f);

        int index = 1;
        for(var item: notifierBuffs) {
            graphics.setColor(item.Color);
            graphics.drawString(item.Shorthand, anchorPoint.getX() - 15, anchorPoint.getY() + (index * 8));
            index++;
        }

        index = 1;
        for(var item: notifierSpells) {
            graphics.setColor(item.Color);
            graphics.drawString(item.Shorthand, anchorPoint.getX() + barWidth + 7, anchorPoint.getY() + (index * 8));
            index++;
        }
    }




}
