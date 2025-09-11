package net.runelite.client.plugins.MyPlugin;

import java.awt.*;

public class Notification {
    public String Shorthand;
    public String FullName;
    public Color Color;
    public Boolean IsAvailable;

    public Notification(final String shorthand, final String fullName, final Color color) {
        Shorthand = shorthand;
        FullName = fullName;
        Color = color;
        IsAvailable = false;
    }
}
