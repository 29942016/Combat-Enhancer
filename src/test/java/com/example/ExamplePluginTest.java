package net.runelite.client.plugins.MyPlugin.src.test.java.com.example;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;
import net.runelite.client.plugins.MyPlugin.MyPluginPlugin;

public class ExamplePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(MyPluginPlugin.class);
		RuneLite.main(args);
	}
}