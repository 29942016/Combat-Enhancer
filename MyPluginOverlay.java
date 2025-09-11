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

    private final Client client;
    private final MyPluginPlugin plugin;
    private final MyPluginConfig config;
    private final BuffManager buffManager;

    @Inject
    private MyPluginOverlay(Client client, MyPluginPlugin plugin, MyPluginConfig config, BuffManager buffManager) {
        this.client = client;
        this.plugin = plugin;
        this.config = config;
        this.buffManager = buffManager;

        addMenuEntry(MenuAction.RUNELITE_OVERLAY_CONFIG, OPTION_CONFIGURE, "Overlay");
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        final Boolean isRangePotted = buffManager.IsRangeBoosted();
        final Boolean isMagePotted = buffManager.IsMagicBoosted();
        final Boolean isCombatPotted = buffManager.isCombatBoosted();



        panelComponent.getChildren().add(TitleComponent.builder().text("Mage: " + isMagePotted).color(isMagePotted ? Color.GREEN : Color.RED).build());
        panelComponent.getChildren().add(TitleComponent.builder().text("Range: " + isRangePotted).color(isRangePotted ? Color.GREEN : Color.RED).build());
        panelComponent.getChildren().add(TitleComponent.builder().text("Combat: " + isCombatPotted).color(isCombatPotted? Color.GREEN : Color.RED).build());
        return super.render(graphics);
    }
}
