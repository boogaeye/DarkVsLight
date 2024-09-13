package org.faz.dvlmultiload.forge.factory;

import dev.architectury.fluid.FluidStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import org.faz.dvlmultiload.Factory.IFluidTextureHelper;

public class ForgeFluidTextureHelper implements IFluidTextureHelper
{
    @Override
    public TextureAtlasSprite getStillTexture(FluidStack fluidStack)
    {
        Fluid fluid = fluidStack.getFluid();

        IClientFluidTypeExtensions renderProperties = IClientFluidTypeExtensions.of(fluid);
        ResourceLocation fluidStill = renderProperties.getStillTexture(new net.minecraftforge.fluids.FluidStack(fluidStack.getFluid(), 1));

        Minecraft minecraft = Minecraft.getInstance();
        return minecraft.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(fluidStill);
    }

    @Override
    public TextureAtlasSprite getFlowingTexture(FluidStack fluidStack)
    {
        Fluid fluid = fluidStack.getFluid();

        IClientFluidTypeExtensions renderProperties = IClientFluidTypeExtensions.of(fluid);
        ResourceLocation fluidStill = renderProperties.getFlowingTexture(new net.minecraftforge.fluids.FluidStack(fluidStack.getFluid(), 1));

        Minecraft minecraft = Minecraft.getInstance();
        return minecraft.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(fluidStill);
    }

    @Override
    public int getColorTintColor(FluidStack fluidStack)
    {
        net.minecraftforge.fluids.FluidStack forgeStack = new net.minecraftforge.fluids.FluidStack(fluidStack.getFluid(), 1);
        Fluid fluid = fluidStack.getFluid();
        IClientFluidTypeExtensions renderProperties = IClientFluidTypeExtensions.of(fluid);
        return renderProperties.getTintColor(forgeStack);
    }
}
