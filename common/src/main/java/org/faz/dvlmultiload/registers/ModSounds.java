package org.faz.dvlmultiload.registers;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import org.faz.dvlmultiload.DarkVsLight;

public class ModSounds
{
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(DarkVsLight.MOD_ID, Registry.SOUND_EVENT_REGISTRY);

    public static final RegistrySupplier<SoundEvent> DARK_PORTAL = register("block.dark-portal.ambient");

    public static final RegistrySupplier<SoundEvent> UpgradePrestart = register("block.upgrade.prestart");

    public static final RegistrySupplier<SoundEvent> UpgradeStart = register("block.upgrade.start");

    public static final RegistrySupplier<SoundEvent> UpgradeLoop = register("block.upgrade.loop");

    public static final RegistrySupplier<SoundEvent> UpgradeEnd = register("block.upgrade.end");

    public static void init()
    {
        SOUNDS.register();
    }

    public static RegistrySupplier<SoundEvent> register(String name)
    {
        return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(DarkVsLight.MOD_ID, name)));
    }
}
