package org.faz.dvlmultiload.utils;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ModdedSlot extends Slot
{
    private Collection<Item> validItems;
    public ModdedSlot(Container container, int ind, int x, int y)
    {
        super(container, ind, x, y);
        validItems = new ArrayList<>();
    }

    public void addValidItem(Item stack)
    {
        validItems.add(stack);
    }

    public void addAllOfTagItem(ResourceLocation tag)
    {
        ResourceKey<Item> key = ResourceKey.create(Registry.ITEM_REGISTRY, tag);
        var items = Registry.ITEM.getHolder(key)
                .stream()
                .filter(item -> item.is(tag))
                .map(i -> i.value())
                .collect(Collectors.toList());
        validItems.addAll(items);
    }

    @Override
    public boolean mayPlace(ItemStack stack)
    {
        if (validItems.isEmpty()) return true;
        return validItems.contains(stack.getItem());
    }
}
