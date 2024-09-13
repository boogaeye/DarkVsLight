package org.faz.dvlmultiload.forge.factory;

import org.faz.dvlmultiload.Factory.IFluidTextureHelper;
import org.faz.dvlmultiload.Factory.IGenericFactory;

public class ForgeFluidTextureFactory implements IGenericFactory<IFluidTextureHelper>
{
    @Override
    public IFluidTextureHelper create()
    {
        return new ForgeFluidTextureHelper();
    }
    @Override
    public IFluidTextureHelper create(int cap)
    {
        throw new IllegalArgumentException("Textures do not have a capacity");
    }
}
