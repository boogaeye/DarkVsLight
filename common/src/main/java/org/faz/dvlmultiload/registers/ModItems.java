package org.faz.dvlmultiload.registers;

import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.faz.dvlmultiload.DarkVsLight;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(DarkVsLight.MOD_ID, Registry.ITEM_REGISTRY);

    public static void init()
    {
        ITEMS.register();
    }
}
