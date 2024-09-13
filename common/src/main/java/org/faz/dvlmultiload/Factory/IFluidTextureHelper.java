package org.faz.dvlmultiload.Factory;

import dev.architectury.fluid.FluidStack;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public interface IFluidTextureHelper
{
    public TextureAtlasSprite getStillTexture(FluidStack fluidStack);

    public TextureAtlasSprite getFlowingTexture(FluidStack fluidStack);

    public int getColorTintColor(FluidStack fluidStack);
}
