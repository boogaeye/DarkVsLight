package org.faz.dvlmultiload.utils;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class DvlItemSlot extends Slot
{
    private static Container cont = new SimpleContainer(0);
    private final DvlItemHandler handler;
    private final int slot;

    public DvlItemSlot(DvlItemHandler handler, int slot, int x, int y)
    {
        super(cont, slot, x, y);
        this.handler = handler;
        this.slot = slot;
    }

    @Override
    public ItemStack getItem()
    {
        return handler.getSlot(slot);
    }

    @Override
    public void set(ItemStack stack)
    {
        handler.setSlot(slot, stack);
        setChanged();
    }

    @Override
    public void initialize(ItemStack stack)
    {
        handler.setSlot(slot, stack);
        setChanged();
    }

    @Override
    public boolean mayPickup(Player player)
    {
        return !handler.shrinkSlot(slot, 1, true).isEmpty();
    }

    @Override
    public ItemStack remove(int amount)
    {
        return handler.shrinkSlot(slot, amount, false);
    }
}
