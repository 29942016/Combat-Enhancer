package net.runelite.client.plugins.MyPlugin;

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


    private constants() {
    }
}
