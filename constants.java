package net.runelite.client.plugins.MyPlugin;

import java.awt.*;
import java.util.ArrayList;

public final class constants {
    private final static int IMBUED_HEART = 20724;
    private final static int SATURATED_HEART = 27641;

    public static int[] MagicItems = new int[]{
            IMBUED_HEART,
            SATURATED_HEART
    };

    public static enum NotificationType {
        COMBAT,
        SPELL
    }

    public static enum NotificationName {
        THRALL,
        VENGEANCE,
        DEATH_CHARGE,
        SUPER_COMBAT,
        DIVINE_RANGING,
        IMBUED_HEART
    }

    public static ArrayList<Notification> InitialiseNotifications() {
        ArrayList<Notification> notifications = new ArrayList<Notification>();

        notifications.add(new Notification(
          "T",
                NotificationName.THRALL,
                Color.BLUE,
                NotificationType.SPELL
        ));

        notifications.add(new Notification(
                "V",
                NotificationName.VENGEANCE,
                Color.RED,
                NotificationType.SPELL
        ));

        notifications.add(new Notification(
                "DC",
                NotificationName.DEATH_CHARGE,
                Color.ORANGE,
                NotificationType.SPELL
        ));

        notifications.add(new Notification(
                "H",
                NotificationName.IMBUED_HEART,
                Color.BLUE,
                NotificationType.COMBAT
        ));

        return notifications;
    }

    private constants() {
    }
}
