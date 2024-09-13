package org.faz.dvlmultiload.fabric;

import org.faz.dvlmultiload.DarkVsLight;
import net.fabricmc.api.ModInitializer;
import org.faz.dvlmultiload.Factory.FactoryManager;

public class DarkVsLightFabric implements ModInitializer {
    @Override
    public void onInitialize()
    {
        DarkVsLight.init();
        initFactories();
    }

    private void initFactories()
    {
        FactoryManager.fluidTankFactory = new org.faz.dvlmultiload.fabric.factory.FabricTankFactory();
        FactoryManager.fluidTextureHelperFactory = new org.faz.dvlmultiload.fabric.factory.FabricFluidTextureFactory();
        System.out.println("Fabric Factories initialized");
    }
}