package org.faz.dvlmultiload.utils;

import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.portal.PortalInfo;

import java.util.Optional;
import java.util.function.Function;

public interface ITeleporter
{
    Optional<BlockUtil.FoundRectangle> findPortal(BlockPos pos);

    Optional<BlockUtil.FoundRectangle> buildPortal(BlockPos pos, Direction.Axis axis);

    PortalInfo recievePortalInfo(Entity entity, ServerLevel level, PortalInfo info);

    Optional<BlockUtil.FoundRectangle> findOrBuildPortal(Entity ent, BlockPos pos);

    default Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw, Function<Boolean, Entity> repositionEntity)
    {
        return repositionEntity.apply(true);
    }
}
