package org.faz.dvlmultiload.registers.customaddons;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.faz.dvlmultiload.registers.ModBlocks;
import org.faz.dvlmultiload.registers.ModItems;

import java.util.function.Supplier;

public class DoubleRegister<B, I>
{
    public final RegistrySupplier<B> block;
    public final RegistrySupplier<I> item;

    public DoubleRegister(RegistrySupplier<B> block, RegistrySupplier<I> item)
    {
        this.block = block;
        this.item = item;
    }

    public static DoubleRegister createItemFromBlock(String name, Supplier<Block> block)
    {
        RegistrySupplier<Block> blockSupplier = ModBlocks.BLOCKS.register(name, block);
        RegistrySupplier<Item> itemSupplier = ModItems.ITEMS.register(name, () -> new BlockItem(blockSupplier.get(), new Item.Properties()));
        return new DoubleRegister<>(blockSupplier, itemSupplier);
    }
}
