package org.faz.dvlmultiload.fabric.factory;

import dev.architectury.fluid.FluidStack;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.faz.dvlmultiload.Factory.IFluidTank;
import org.faz.dvlmultiload.Factory.IFluidTextureHelper;
import org.faz.dvlmultiload.Factory.IGenericFactory;

public class FabricFluidTextureHelper implements IFluidTextureHelper
{
    @Override
    public TextureAtlasSprite getStillTexture(FluidStack fluidStack) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TextureAtlasSprite getFlowingTexture(FluidStack fluidStack)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getColorTintColor(FluidStack fluidStack)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
