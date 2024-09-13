package org.faz.dvlmultiload.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.architectury.fluid.FluidStack;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.nbt.CompoundTag;

import java.util.Optional;

public class FluidJsonUtil
{
    public static Codec<FluidStack> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Registry.FLUID.byNameCodec().fieldOf("FluidName").forGetter(FluidStack::getFluid),
            Codec.LONG.fieldOf("Amount").forGetter(FluidStack::getAmount)
    ).apply(instance, FluidStack::create));
    public static FluidStack readFluid(JsonObject json)
    {
        return CODEC.decode(JsonOps.INSTANCE, json).result().orElseThrow().getFirst();
    }

    public static JsonElement toJson(FluidStack stack)
    {
        return CODEC.encodeStart(JsonOps.INSTANCE, stack).result().orElseThrow();
    }
}
