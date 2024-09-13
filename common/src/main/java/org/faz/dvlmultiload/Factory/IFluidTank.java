package org.faz.dvlmultiload.Factory;

import dev.architectury.fluid.FluidStack;

import java.util.ArrayList;
import java.util.List;

public interface IFluidTank
{
    final List<IContentsChangedListener> listeners = new ArrayList<>();
    int getFluidAmount();
    int getCapacity();
    void setFluidAmount(int amount);
    void setCapacity(int cap);
    int fill(int amount, boolean simulate);
    int fill(FluidStack stack, boolean simulate);
    int drain(int amount, boolean simulate);
    void setFluid(FluidStack stack);
    FluidStack getFluid();
    default void onContentsChanged() {}

    default void addListener(IContentsChangedListener listener)
    {
        listeners.add(listener);
    }

    default void removeListener(IContentsChangedListener listener)
    {
        listeners.remove(listener);
    }

    default void notifyListeners()
    {
        listeners.forEach(IContentsChangedListener::onContentsChanged);
    }
}
