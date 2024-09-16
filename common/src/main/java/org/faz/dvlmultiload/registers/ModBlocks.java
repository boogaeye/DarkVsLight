package org.faz.dvlmultiload.registers;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.Material;
import org.faz.dvlmultiload.DarkVsLight;
import org.faz.dvlmultiload.block.UpgradeBlock;
import org.faz.dvlmultiload.registers.customaddons.DoubleRegister;
import org.jetbrains.annotations.Nullable;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(DarkVsLight.MOD_ID, Registry.BLOCK_REGISTRY);

    //Base Darkend
    public static final DoubleRegister<Block, BlockItem> DARK_STONE = DoubleRegister.createItemFromBlock("dark_stone", () -> new Block(Block.Properties.of(Material.STONE)));
    public static final DoubleRegister<Block, BlockItem> DARK_DIRT = DoubleRegister.createItemFromBlock("dark_dirt", () -> new Block(Block.Properties.of(Material.DIRT)));
    public static final DoubleRegister<Block, BlockItem> DARK_GRASS = DoubleRegister.createItemFromBlock("dark_grass", () -> new Block(Block.Properties.of(Material.GRASS)));
    //

    // Burn Stuff Rename!!!! LEGACY
    public static final DoubleRegister<Block, BlockItem> BURN_GAZE_WOOD = DoubleRegister.createItemFromBlock("burn_gaze_wood", () -> new RotatedPillarBlock(Block.Properties.of(Material.WOOD))); // need logs
    public static final DoubleRegister<Block, BlockItem> BURN_PANE = DoubleRegister.createItemFromBlock("burn_pane", () -> new Block(Block.Properties.of(Material.GLASS)));
    public static final DoubleRegister<Block, BlockItem> BURN_ORE = DoubleRegister.createItemFromBlock("burn_ore", () -> new Block(Block.Properties.of(Material.STONE).requiresCorrectToolForDrops()));
    public static final DoubleRegister<Block, BlockItem> BURN_ORE_SURFACE = DoubleRegister.createItemFromBlock("burn_ore_surface", () -> new Block(Block.Properties.of(Material.STONE).requiresCorrectToolForDrops())); // May Rework

    public static final DoubleRegister<Block, BlockItem> BURN_DIAMOND_INCASED_OBSIDIAN_BLOCK = DoubleRegister.createItemFromBlock("burn_diamond_incased_obsidian_block", () -> new Block(Block.Properties.of(Material.STONE).strength(50.0F, 1200.0F))); // May Rework
    //

    // Blind Stuff Rename!!!! LEGACY
    public static final DoubleRegister<Block, BlockItem> BLIND_ORE = DoubleRegister.createItemFromBlock("blind_ore", () -> new Block(Block.Properties.of(Material.GLASS)));
    public static final DoubleRegister<Block, BlockItem> BLIND_STONE = DoubleRegister.createItemFromBlock("blind_stone", () -> new Block(Block.Properties.of(Material.GLASS)));
    public static final DoubleRegister<Block, BlockItem> BLIND_LOG = DoubleRegister.createItemFromBlock("blind_log", () -> new RotatedPillarBlock(Block.Properties.of(Material.WOOD)));
    public static final DoubleRegister<Block, BlockItem> BLIND_LEAVES = DoubleRegister.createItemFromBlock("blind_leaves", () -> new Block(Block.Properties.of(Material.PLANT)));
    public static final DoubleRegister<Block, BlockItem> BLIND_PLANKS = DoubleRegister.createItemFromBlock("blind_planks", () -> new Block(Block.Properties.of(Material.WOOD)));
    //

    // Startile LEGACY (keep)
    public static final DoubleRegister<Block, BlockItem> STARTILE_ORE = DoubleRegister.createItemFromBlock("startile_ore", () -> new Block(Block.Properties.of(Material.STONE).requiresCorrectToolForDrops()));
    //

    // Glow Stuff Rename!!!! LEGACY
    public static final DoubleRegister<Block, BlockItem> GLOW_LOG = DoubleRegister.createItemFromBlock("glow_log", () -> new RotatedPillarBlock(Block.Properties.of(Material.WOOD)));
    public static final DoubleRegister<Block, BlockItem> GLOW_LEAVES = DoubleRegister.createItemFromBlock("glow_leaves", () -> new Block(Block.Properties.of(Material.PLANT)));
    public static final DoubleRegister<Block, BlockItem> GLOW_PLANKS = DoubleRegister.createItemFromBlock("glow_planks", () -> new Block(Block.Properties.of(Material.WOOD)));
    public static final DoubleRegister<Block, BlockItem> GLOW_SAPLING = DoubleRegister.createItemFromBlock("glow_sapling", () -> new SaplingBlock(new AbstractMegaTreeGrower() {
        @Nullable
        @Override
        protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource random) {
            return null;
        }

        @Nullable
        @Override
        protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean largeHive) {
            return null;
        }
    }, Block.Properties.of(Material.STONE).requiresCorrectToolForDrops())); // implement sapling

    public static final DoubleRegister<Block, BlockItem> GLOWING_FRUIT = DoubleRegister.createItemFromBlock("glowing_fruit", () -> new Block(Block.Properties.of(Material.PLANT)));

    public static final DoubleRegister<Block, BlockItem> GLOW_LOG_DOOR = DoubleRegister.createItemFromBlock("glow_log_door", () -> new DoorBlock(Block.Properties.of(Material.WOOD)));
    public static final DoubleRegister<Block, BlockItem> GLOW_BERRY_BUSH = DoubleRegister.createItemFromBlock("glow_berry_bush", () -> new Block(Block.Properties.of(Material.WOOD)));
    //

    // Upgrade Blocks
    public static final DoubleRegister<Block, BlockItem> UPGRADE_BLOCK = DoubleRegister.createItemFromBlock("upgrade_block", UpgradeBlock::new);
    //

    // MISC rework most likely
    public static final DoubleRegister<Block, BlockItem> STONE_PLATED_DARK_STONE = DoubleRegister.createItemFromBlock("stone_plated_dark_stone", () -> new Block(Block.Properties.of(Material.STONE)));
    public static final DoubleRegister<Block, BlockItem> PURPLE_FIRE = DoubleRegister.createItemFromBlock("purple_fire", () -> new FireBlock(Block.Properties.of(Material.FIRE)));
    //

    // MISC no rework
    public static final DoubleRegister<Block, BlockItem> OBSCURATUS_SAND = DoubleRegister.createItemFromBlock("obscuratus_sand", () -> new SandBlock(0, Block.Properties.of(Material.SAND)));
    //

    public static void init()
    {
        BLOCKS.register();
    }

    public static ResourceLocation location(String name) {
        return new ResourceLocation(DarkVsLight.MOD_ID, name);
    }
}
