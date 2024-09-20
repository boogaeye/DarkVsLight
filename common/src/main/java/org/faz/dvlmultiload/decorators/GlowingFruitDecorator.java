package org.faz.dvlmultiload.decorators;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.faz.dvlmultiload.registers.ModBlocks;
import org.faz.dvlmultiload.registers.ModTreeDecors;

public class GlowingFruitDecorator extends TreeDecorator
{
    public static final Codec<GlowingFruitDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap((probability) -> {
        return new GlowingFruitDecorator(probability);
    }, (decorator) -> {
        return decorator.probability;
    }).codec();

    private final float probability;

    public GlowingFruitDecorator(float probability)
    {
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return ModTreeDecors.GLOW_FRUIT_DECORATOR.get();
    }

    @Override
    public void place(Context context)
    {
        RandomSource randomSource = context.random();
        context.leaves().forEach((blockPos) -> {
            BlockPos blockPos2;
            if (randomSource.nextFloat() < this.probability) {
                blockPos2 = blockPos.west();
                if (context.isAir(blockPos2)) {
                    addHangingVine(blockPos2, context);
                }
            }

            if (randomSource.nextFloat() < this.probability) {
                blockPos2 = blockPos.east();
                if (context.isAir(blockPos2)) {
                    addHangingVine(blockPos2, context);
                }
            }

            if (randomSource.nextFloat() < this.probability) {
                blockPos2 = blockPos.north();
                if (context.isAir(blockPos2)) {
                    addHangingVine(blockPos2, context);
                }
            }

            if (randomSource.nextFloat() < this.probability) {
                blockPos2 = blockPos.south();
                if (context.isAir(blockPos2)) {
                    addHangingVine(blockPos2, context);
                }
            }

        });
    }

    private static void addHangingVine(BlockPos pos, TreeDecorator.Context context)
    {
        context.setBlock(pos, ModBlocks.GLOWING_FRUIT.block.get().defaultBlockState());
        int i = (int) (context.logs().size() * context.random().nextFloat());

        for(pos = pos.below(); context.isAir(pos) && i > 0; --i) {
            context.setBlock(pos, ModBlocks.GLOWING_FRUIT.block.get().defaultBlockState());
            pos = pos.below();
        }

    }
}
