package org.faz.dvlmultiload.registers;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import org.faz.dvlmultiload.DarkVsLight;

public class ModDimensions
{
    public static final ResourceKey<Level> DARKEND = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(DarkVsLight.MOD_ID, "darkend"));
}
