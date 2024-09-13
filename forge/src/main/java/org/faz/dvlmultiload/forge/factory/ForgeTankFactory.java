package org.faz.dvlmultiload.forge.factory;

import org.faz.dvlmultiload.Factory.IFluidTank;
import org.faz.dvlmultiload.Factory.IGenericFactory;

public class ForgeTankFactory implements IGenericFactory<IFluidTank>
{
    @Override
    public IFluidTank create()
    {
        return null;
    }
    @Override
    public IFluidTank create(int cap) {
        return new ForgeFluidTank(cap);
    }
}
