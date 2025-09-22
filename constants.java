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

    private final static int DIVINE_CMB_1_DOSE = 23694;
    private final static int DIVINE_CMB_2_DOSE = 23691;
    private final static int DIVINE_CMB_3_DOSE = 23688;
    private final static int DIVINE_CMB_4_DOSE = 23685;

    public static int[] CombatItems = new int[]{
            DIVINE_CMB_1_DOSE,
            DIVINE_CMB_2_DOSE,
            DIVINE_CMB_3_DOSE,
            DIVINE_CMB_4_DOSE,
    };

    private final static int DIVINE_RANGE_1_DOSE = 23742;
    private final static int DIVINE_RANGE_2_DOSE = 23739;
    private final static int DIVINE_RANGE_3_DOSE = 23736;
    private final static int DIVINE_RANGE_4_DOSE = 23733;

    public static int[] RangeItems = new int[]{
            DIVINE_RANGE_1_DOSE,
            DIVINE_RANGE_2_DOSE,
            DIVINE_RANGE_3_DOSE,
            DIVINE_RANGE_4_DOSE,
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

    public static ArrayList<Notification> InitialiseNotifications(BuffManager buffManager ) {
        ArrayList<Notification> notifications = new ArrayList<Notification>();

        notifications.add(new Notification(
          "T",
                NotificationName.THRALL,
                Color.BLUE,
                NotificationType.SPELL,
                () -> { return false; }
        ));

        notifications.add(new Notification(
                "V",
                NotificationName.VENGEANCE,
                Color.RED,
                NotificationType.SPELL,
                () -> { return false; }
        ));

        notifications.add(new Notification(
                "DC",
                NotificationName.DEATH_CHARGE,
                Color.ORANGE,
                NotificationType.SPELL,
                () -> { return true; }
        ));

        notifications.add(new Notification(
                "M",
                NotificationName.MAGIC_BOOST,
                Color.BLUE,
                NotificationType.COMBAT,
                buffManager::IsMagicBoosted
        ));

        notifications.add(new Notification(
                "C",
                NotificationName.COMBAT_BOOST,
                Color.RED,
                NotificationType.COMBAT,
                buffManager::IsCombatBoosted
        ));

        notifications.add(new Notification(
                "R",
                NotificationName.RANGE_BOOST,
                Color.GREEN,
                NotificationType.COMBAT,
                buffManager::IsRangeBoosted
        ));

        return notifications;
    }

    private constants() {
    }
}
