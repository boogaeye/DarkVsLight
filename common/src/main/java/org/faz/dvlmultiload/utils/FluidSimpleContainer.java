package org.faz.dvlmultiload.utils;

import dev.architectury.fluid.FluidStack;
import net.minecraft.world.SimpleContainer;

import java.util.Optional;

public class FluidSimpleContainer extends SimpleContainer
{
    public Optional<FluidStack> fluid;
    public FluidSimpleContainer(int pSize, Optional<FluidStack> f)
    {
        super(pSize);
        fluid = f;
    }

    public FluidSimpleContainer(Optional<FluidStack> f, net.minecraft.world.item.ItemStack... pItems)
    {
        super(pItems);
        fluid = f;
    }
}
