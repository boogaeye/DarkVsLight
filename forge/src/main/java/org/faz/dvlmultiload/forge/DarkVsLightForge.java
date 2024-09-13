package org.faz.dvlmultiload.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.items.ItemStackHandler;
import org.faz.dvlmultiload.DarkVsLight;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.faz.dvlmultiload.Factory.FactoryManager;
import org.faz.dvlmultiload.forge.factory.ForgeFluidTextureHelper;

@Mod(DarkVsLight.MOD_ID)
public class DarkVsLightForge {
    public DarkVsLightForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(DarkVsLight.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        DarkVsLight.init();
        initFactories();
    }

    private void initFactories()
    {
        FactoryManager.fluidTankFactory = new org.faz.dvlmultiload.forge.factory.ForgeTankFactory();
        FactoryManager.fluidTextureHelperFactory = new org.faz.dvlmultiload.forge.factory.ForgeFluidTextureFactory();
        System.out.println("Forge Factories initialized");
    }
}