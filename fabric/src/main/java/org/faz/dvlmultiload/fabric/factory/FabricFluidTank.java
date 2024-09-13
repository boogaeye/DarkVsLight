package org.faz.dvlmultiload.fabric.factory;
import org.faz.dvlmultiload.Factory.IFluidTank;

public class FabricFluidTank implements IFluidTank
{
    //public final FluidTank tank;

    public FabricFluidTank(int cap)
    {
        //this.tank = new FluidTank(cap);
    }

    @Override
    public int getFluidAmount()
    {
        throw new UnsupportedOperationException("Not supported yet.");
        //return tank.getFluidAmount();
    }

    @Override
    public int getCapacity()
    {
        throw new UnsupportedOperationException("Not supported yet.");
        //return tank.getCapacity();
    }

    @Override
    public void setFluidAmount(int amount)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCapacity(int cap)
    {
        throw new UnsupportedOperationException("Not supported yet.");
        //tank.setCapacity(cap);
    }

    @Override
    public int fill(int amount, boolean simulate)
    {
        throw new UnsupportedOperationException("Not supported yet.");
        //return tank.fill(new FluidStack(tank.getFluid(), amount), (simulate ? IFluidHandler.FluidAction.SIMULATE : IFluidHandler.FluidAction.EXECUTE));
    }

    @Override
    public int fill(dev.architectury.fluid.FluidStack stack, boolean simulate)
    {
        throw new UnsupportedOperationException("Not supported yet.");
        //return tank.fill(new FluidStack(stack.getFluid(), (int)stack.getAmount()), (simulate ? IFluidHandler.FluidAction.SIMULATE : IFluidHandler.FluidAction.EXECUTE));
    }

    @Override
    public int drain(int amount, boolean simulate)
    {
        throw new UnsupportedOperationException("Not supported yet.");
        //return 0;
    }

    @Override
    public void setFluid(dev.architectury.fluid.FluidStack stack)
    {
        throw new UnsupportedOperationException("Not supported yet.");
        //tank.setFluid(new FluidStack(stack.getFluid(), (int)stack.getAmount()));
    }

    @Override
    public dev.architectury.fluid.FluidStack getFluid()
    {
        throw new UnsupportedOperationException("Not supported yet.");
        //return dev.architectury.fluid.FluidStack.create(tank.getFluid().getRawFluid(), tank.getFluidAmount());
    }
}
