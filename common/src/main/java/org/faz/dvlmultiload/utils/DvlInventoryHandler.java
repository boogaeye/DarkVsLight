package org.faz.dvlmultiload.utils;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class DvlInventoryHandler
{
    protected NonNullList<ItemStack> items;

    public DvlInventoryHandler()
    {
        items = NonNullList.withSize(1, ItemStack.EMPTY);
    }

    public DvlInventoryHandler(int size)
    {
        items = NonNullList.withSize(size, ItemStack.EMPTY);
    }

    public void setItem(int slot, ItemStack stack)
    {

        items.set(slot, stack);
    }
}
