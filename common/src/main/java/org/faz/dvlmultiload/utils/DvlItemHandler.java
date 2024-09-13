package org.faz.dvlmultiload.utils;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;

public class DvlItemHandler
{
    private NonNullList<ItemStack> stacks;

    public DvlItemHandler()
    {
        this.stacks = NonNullList.withSize(1, ItemStack.EMPTY);
    }

    public DvlItemHandler(int size)
    {
        this.stacks = NonNullList.withSize(size, ItemStack.EMPTY);
    }

    public ItemStack getSlot(int slot)
    {
        return this.stacks.get(slot);
    }

    public void setSlot(int slot, ItemStack stack)
    {
        this.stacks.set(slot, stack);
    }

    public CompoundTag serialize()
    {
        ListTag tag = new ListTag();
        for (int i = 0; i < this.stacks.size(); i++)
        {
            if (!this.stacks.get(i).isEmpty())
            {
                CompoundTag itemTag = new CompoundTag();
                itemTag.putByte("Slot", (byte)i);
                this.stacks.get(i).save(itemTag);
                tag.add(itemTag);
            }
        }
        CompoundTag compound = new CompoundTag();
        compound.put("Items", tag);
        compound.putInt("Size", this.stacks.size());
        return compound;
    }

    public void deserialize(CompoundTag nbt)
    {
        ListTag tag = nbt.getList("Items", 10);
        for (int i = 0; i < tag.size(); i++)
        {
            CompoundTag itemTag = tag.getCompound(i);
            int slot = itemTag.getByte("Slot") & 255;
            if (slot >= 0 && slot < this.stacks.size())
            {
                this.stacks.set(slot, ItemStack.of(itemTag));
            }
        }
    }
}
