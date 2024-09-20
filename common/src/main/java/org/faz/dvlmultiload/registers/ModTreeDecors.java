package org.faz.dvlmultiload.registers;

import com.mojang.serialization.Codec;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.faz.dvlmultiload.DarkVsLight;
import org.faz.dvlmultiload.decorators.GlowingFruitDecorator;

import java.util.Optional;

public class ModTreeDecors {
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(DarkVsLight.MOD_ID, Registry.TREE_DECORATOR_TYPE_REGISTRY);

    public static final RegistrySupplier<TreeDecoratorType<?>> GLOW_FRUIT_DECORATOR = TREE_DECORATORS.register("glow_vines", () -> instantiateTreeDecorators(GlowingFruitDecorator.CODEC));

    public static void init() {
        System.out.println("Loading ModTreeDecors...");
        TREE_DECORATORS.register();
        System.out.println("ModTreeDecors loaded!");
    }

    public static TreeDecoratorType<?> instantiateTreeDecorators(Codec codec)
    {
        try
        {
            Class<?> clazz = Class.forName("net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType");
            java.lang.reflect.Constructor field = clazz.getConstructors()[0];
            field.setAccessible(true);
            TreeDecoratorType<?> treeDecoratorType = (TreeDecoratorType<?>) field.newInstance(codec);
            field.setAccessible(false);
            System.out.println("created tree decorator type" + treeDecoratorType);
            return treeDecoratorType;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
