package net.runelite.client.plugins.MyPlugin;

import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static net.runelite.client.plugins.MyPlugin.constants.*;

public class Notification {
    public String Shorthand;
    public NotificationName Id;
    public Color Color;
    public Boolean IsAvailable;
    public NotificationType Type;
    public Supplier<Boolean> ShouldRender;

    public Notification(final String shorthand, final NotificationName id, final Color color, NotificationType type, Supplier<Boolean> shouldRender) {
        Shorthand = shorthand;
        Id = id;
        Color = color;
        IsAvailable = false;
        Type = type;
        ShouldRender = shouldRender;
    }
}
