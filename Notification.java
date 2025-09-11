package net.runelite.client.plugins.MyPlugin;

import java.awt.*;
import static net.runelite.client.plugins.MyPlugin.constants.*;

public class Notification {
    public String Shorthand;
    public NotificationName Id;
    public Color Color;
    public Boolean IsAvailable;
    public NotificationType Type;

    public Notification(final String shorthand, final NotificationName id, final Color color, NotificationType type) {
        Shorthand = shorthand;
        Id = id;
        Color = color;
        IsAvailable = false;
        Type = type;
    }
}
