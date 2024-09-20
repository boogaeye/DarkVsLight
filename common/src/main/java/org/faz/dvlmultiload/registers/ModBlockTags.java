package org.faz.dvlmultiload.registers;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;

public class ModBlockTags extends BlockTagsProvider
{

    public ModBlockTags(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void addTags()
    {
        tag(ModTags.Blocks.DARKEND_CARVER_REPLACE).addTag(BlockTags.OVERWORLD_CARVER_REPLACEABLES).addTag(ModTags.Blocks.BASE_STONE_DARKEND).add(ModBlocks.DARK_STONE.block.get()).add(ModBlocks.DARK_STONE.block.get());
        tag(ModTags.Blocks.BASE_STONE_DARKEND).addTag(BlockTags.BASE_STONE_OVERWORLD).add(ModBlocks.DARK_STONE.block.get());
        tag(ModTags.Blocks.DARKEND_PORTAL_FRAME_BLOCKS).add(ModBlocks.DARK_STONE.block.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.DARK_STONE.block.get()).add(ModBlocks.STARTILE_ORE.block.get()).add(ModBlocks.UPGRADE_BLOCK.block.get());
        tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.UPGRADE_BLOCK.block.get());
        tag(BlockTags.CLIMBABLE).add(ModBlocks.GLOWING_FRUIT.block.get());
        //tag(ModTags.Blocks.NEEDS_BLIND_TOOL).add(ModBlocks.StartileOre.get());
    }
}
