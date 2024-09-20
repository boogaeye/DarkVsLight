package org.faz.dvlmultiload.registers;

import com.google.common.collect.ImmutableSet;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.faz.dvlmultiload.DarkVsLight;

import java.util.Set;

public class ModPois
{
    public static final DeferredRegister<PoiType> POI = DeferredRegister.create(DarkVsLight.MOD_ID, Registry.POINT_OF_INTEREST_TYPE_REGISTRY);

    public static final RegistrySupplier<PoiType> DARKEND_PORTAL = POI.register("darkend_portal", () -> new PoiType(getBlockStates(ModBlocks.DARKEND_PORTAL.get()), 0, 1));

    public static void init()
    {
        POI.register();
    }

    private static Set<BlockState> getBlockStates(Block block)
    {
        return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
    }
}
