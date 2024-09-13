package org.faz.dvlmultiload.registers;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.faz.dvlmultiload.DarkVsLight;
import org.faz.dvlmultiload.recipetype.UpgradeBlockRecipe;

public class ModRecipes
{
    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(DarkVsLight.MOD_ID, Registry.RECIPE_SERIALIZER_REGISTRY);

    public static final RegistrySupplier<RecipeSerializer<UpgradeBlockRecipe>> UpgradeSerializer = RECIPES.register("upgrading", () -> UpgradeBlockRecipe.Serializer.INSTANCE);

    public static void init()
    {
        RECIPES.register();
    }
}
