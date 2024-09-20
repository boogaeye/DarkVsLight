package org.faz.dvlmultiload.forge;

import dev.architectury.event.events.client.ClientLifecycleEvent;
import dev.architectury.platform.forge.EventBuses;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventListener;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.items.ItemStackHandler;
import org.faz.dvlmultiload.DarkVsLight;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.faz.dvlmultiload.Factory.FactoryManager;
import org.faz.dvlmultiload.client.DvlHud;
import org.faz.dvlmultiload.forge.factory.ForgeFluidTextureHelper;
import org.faz.dvlmultiload.registers.ModBlocks;

@Mod(DarkVsLight.MOD_ID)
public class DarkVsLightForge
{
    public DarkVsLightForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(DarkVsLight.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        DarkVsLight.init();
        initFactories();
    }

    @Mod.EventBusSubscriber(modid = DarkVsLight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Common
    {
        @SubscribeEvent
        public static void FMLCommonSetup(FMLCommonSetupEvent event)
        {
            System.out.println("FMLCommonSetup");
            DarkVsLight.common();
        }
    }




    private void initFactories()
    {
        FactoryManager.fluidTankFactory = new org.faz.dvlmultiload.forge.factory.ForgeTankFactory();
        FactoryManager.fluidTextureHelperFactory = new org.faz.dvlmultiload.forge.factory.ForgeFluidTextureFactory();
        System.out.println("Forge Factories initialized");
    }

    @Mod.EventBusSubscriber(modid = DarkVsLight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Client
    {
        @SubscribeEvent
        public static void registerGuiOverlay(RegisterGuiOverlaysEvent event)
        {
            event.registerAboveAll("darkend", (gui, poseStack, partialTicks, mouseX, mouseY) -> {
                DvlHud.render(poseStack, partialTicks);
            });
        }

        @SubscribeEvent
        public static void FMLClientSetup(final FMLClientSetupEvent event)
        {
            System.out.println("FMLClientSetup");
            DarkVsLight.client(Minecraft.getInstance());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.GLOWING_FRUIT.block.get(), RenderType.cutout());
        }
    }
}