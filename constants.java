package net.runelite.client.plugins.MyPlugin;

import java.awt.*;
import java.util.ArrayList;

public final class constants {

    private final static int IMBUED_HEART = 20724;
    private final static int SATURATED_HEART = 27641;

    public static int[] MagicItems = new int[]{
            IMBUED_HEART,
            SATURATED_HEART,
    };

    private final static int DIVINE_CMB1 = 37966;
    private final static int DIVINE_CMB2 = 37975;
    private final static int DIVINE_CMB3 = 37944;
    private final static int DIVINE_CMB4 = 37951;

    public static int[] CombatItems = new int[]{
            DIVINE_CMB1,
            DIVINE_CMB2,
            DIVINE_CMB3,
            DIVINE_CMB4,
    };

    public static enum NotificationType {
        COMBAT,
        SPELL
    }

    public static enum NotificationName {
        THRALL,
        VENGEANCE,
        DEATH_CHARGE,
        COMBAT_BOOST,
        RANGE_BOOST,
        MAGIC_BOOST
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
                "M",
                NotificationName.MAGIC_BOOST,
                Color.BLUE,
                NotificationType.COMBAT
        ));

        notifications.add(new Notification(
                "C",
                NotificationName.COMBAT_BOOST,
                Color.RED,
                NotificationType.COMBAT
        ));

        return notifications;
    }

    private constants() {
    }
}
