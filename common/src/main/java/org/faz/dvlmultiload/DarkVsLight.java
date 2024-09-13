package org.faz.dvlmultiload;

import dev.architectury.event.events.client.ClientLifecycleEvent;
import dev.architectury.registry.menu.MenuRegistry;
import net.minecraft.client.Minecraft;
import org.faz.dvlmultiload.registers.*;
import org.faz.dvlmultiload.screen.UpgradeScreen;

public class DarkVsLight
{
	public static final String MOD_ID = "darkvslight";

	public static void init()
	{
		System.out.println("Loading DarkVsLight mod...");
		ModItems.init();
		ModBlocks.init();
		ModBlockEntities.init();
		ModMenus.init();
		ModSounds.init();
		ModRecipes.init();
		ClientLifecycleEvent.CLIENT_SETUP.register(DarkVsLight::client);
		System.out.println("DarkVsLight mod loaded!");
	}

	public static void client(Minecraft minecraft)
	{
		System.out.println("Loading DarkVsLight client...");
		MenuRegistry.registerScreenFactory(ModMenus.UPGRADE_MENU.get(), UpgradeScreen::new);
		System.out.println("DarkVsLight client loaded!");
	}
}
