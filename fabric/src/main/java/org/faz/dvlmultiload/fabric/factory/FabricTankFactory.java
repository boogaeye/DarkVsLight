package org.faz.dvlmultiload.fabric.factory;

import org.faz.dvlmultiload.Factory.IFluidTank;
import org.faz.dvlmultiload.Factory.IGenericFactory;

public class FabricTankFactory implements IGenericFactory<IFluidTank>
{
    @Override
    public IFluidTank create()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public IFluidTank create(int cap)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
