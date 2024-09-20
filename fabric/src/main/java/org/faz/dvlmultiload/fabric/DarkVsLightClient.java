package org.faz.dvlmultiload.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import org.faz.dvlmultiload.registers.ModBlocks;

@Environment(net.fabricmc.api.EnvType.CLIENT)
public class DarkVsLightClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        System.out.println("Hello Fabric client!");
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLOWING_FRUIT.block.get(), RenderType.cutout());
    }
}
