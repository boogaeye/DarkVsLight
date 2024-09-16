package org.faz.dvlmultiload.registers;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.faz.dvlmultiload.DarkVsLight;

public class ModTags
{
    public static class Blocks
    {
        public static final TagKey<Block> DARKEND_CARVER_REPLACE = tag("darkend_carver_replace");
        public static final TagKey<Block> BASE_STONE_DARKEND = tag("base_stone_darkend");
        public static final TagKey<Block> DARKEND_PORTAL_FRAME_BLOCKS = tag("darkend_portal_frame_blocks");
        public static final TagKey<Block> NEEDS_BLIND_TOOL = tag("needs_blind_tool");

        private static TagKey<Block> tag(String name)
        {
            return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(DarkVsLight.MOD_ID, name));
        }
    }
}
