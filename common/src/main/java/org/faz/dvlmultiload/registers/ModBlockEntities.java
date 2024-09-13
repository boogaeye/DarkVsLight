package org.faz.dvlmultiload.registers;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.faz.dvlmultiload.DarkVsLight;
import org.faz.dvlmultiload.blockentities.UpgradeBlockEntity;

public class ModBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(DarkVsLight.MOD_ID, Registry.BLOCK_ENTITY_TYPE_REGISTRY);

    public static final RegistrySupplier<BlockEntityType<UpgradeBlockEntity>> UpgradeBlockAsEntity = BLOCK_ENTITIES.register("upgrade_block", () -> (BlockEntityType.Builder.of(UpgradeBlockEntity::new, ModBlocks.UPGRADE_BLOCK.block.get()).build(null)));

    public static void init()
    {
        BLOCK_ENTITIES.register();
    }
}
