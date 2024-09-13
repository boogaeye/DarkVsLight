package org.faz.dvlmultiload.forge.factory;

import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.faz.dvlmultiload.Factory.IFluidTank;

import java.util.function.Supplier;

public class ForgeFluidTank implements IFluidTank
{
    public final FluidTank tank;

    public ForgeFluidTank(int cap)
    {
        this.tank = new FluidTank(cap){
            @Override
            protected void onContentsChanged()
            {
                notifyListeners();
            }
        };
    }

    @Override
    public int getFluidAmount() {
        return tank.getFluidAmount();
    }

    @Override
    public int getCapacity() {
        return tank.getCapacity();
    }

    @Override
    public void setFluidAmount(int amount)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCapacity(int cap)
    {
        tank.setCapacity(cap);
    }

    @Override
    public int fill(int amount, boolean simulate)
    {
        return tank.fill(new FluidStack(tank.getFluid(), amount), (simulate ? IFluidHandler.FluidAction.SIMULATE : IFluidHandler.FluidAction.EXECUTE));
    }

    @Override
    public int fill(dev.architectury.fluid.FluidStack stack, boolean simulate)
    {
        return tank.fill(new FluidStack(stack.getFluid(), (int)stack.getAmount()), (simulate ? IFluidHandler.FluidAction.SIMULATE : IFluidHandler.FluidAction.EXECUTE));
    }

    @Override
    public int drain(int amount, boolean simulate) {
        return 0;
    }

    @Override
    public void setFluid(dev.architectury.fluid.FluidStack stack) {
        tank.setFluid(new FluidStack(stack.getFluid(), (int)stack.getAmount()));
    }

    @Override
    public dev.architectury.fluid.FluidStack getFluid()
    {
        return dev.architectury.fluid.FluidStack.create(tank.getFluid().getRawFluid(), tank.getFluidAmount());
    }
}
