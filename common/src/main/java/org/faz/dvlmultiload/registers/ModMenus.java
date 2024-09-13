package org.faz.dvlmultiload.registers;

import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registries;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.faz.dvlmultiload.DarkVsLight;
import org.faz.dvlmultiload.screen.UpgradeMenu;

public class ModMenus
{
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(DarkVsLight.MOD_ID, Registry.MENU_REGISTRY);

    public static final RegistrySupplier<MenuType<UpgradeMenu>> UPGRADE_MENU = registerMenuType(MenuRegistry.ofExtended(UpgradeMenu::new), "upgrade_menu");

    public static void init()
    {
        MENUS.register();
    }

    private static <T extends AbstractContainerMenu> RegistrySupplier<MenuType<T>> registerMenuType(MenuType<T> registry, String name)
    {
        return MENUS.register(name, () -> registry);
    }
}
