package org.faz.dvlmultiload.fabric.factory;

import org.faz.dvlmultiload.Factory.IFluidTextureHelper;
import org.faz.dvlmultiload.Factory.IGenericFactory;

public class FabricFluidTextureFactory implements IGenericFactory<IFluidTextureHelper>
{
    @Override
    public IFluidTextureHelper create()
    {
        return new FabricFluidTextureHelper();
    }
    @Override
    public IFluidTextureHelper create(int cap)
    {
        throw new IllegalArgumentException("Textures do not have a capacity");
    }
}
