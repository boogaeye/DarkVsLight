package org.faz.dvlmultiload.block;

import dev.architectury.registry.menu.MenuRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import org.faz.dvlmultiload.blockentities.UpgradeBlockEntity;
import org.faz.dvlmultiload.registers.ModBlockEntities;
import org.faz.dvlmultiload.screen.UpgradeMenu;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class UpgradeBlock extends Block implements EntityBlock
{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final BooleanProperty COOLDOWN = BooleanProperty.create("cooldown");
    private int tier;
    public UpgradeBlock(int tier)
    {
        super(Properties.of(Material.STONE).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion());
        registerDefaultState(getStateDefinition().any().setValue(FACING, net.minecraft.core.Direction.NORTH).setValue(LIT, false).setValue(COOLDOWN, false));
        this.tier = tier;
    }

    public int getTier()
    {
        return tier;
    }

    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return ModBlockEntities.UpgradeBlockAsEntity.get().create(pos, state);
    }

    public BlockState rotate(BlockState pState, Rotation pRot) {
        return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, LIT, COOLDOWN);
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getClockWise());
    }

    @Override
    public RenderShape getRenderShape(BlockState p_60550_) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (state.getBlock() != newState.getBlock())
        {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof UpgradeBlockEntity)
            {
                UpgradeBlockEntity upgradeBlockEntity = (UpgradeBlockEntity) blockEntity;
                upgradeBlockEntity.drops();
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        return (level1, blockPos, blockState, t) -> {
            if (t instanceof UpgradeBlockEntity upgradeBlockEntity)
            {
                upgradeBlockEntity.tick(state);
            }
        };
    }

    @Nullable
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player ply, InteractionHand hand, BlockHitResult hitRes)
    {
        if (!level.isClientSide())
        {
            BlockEntity ent = level.getBlockEntity(pos);
            if (ent instanceof UpgradeBlockEntity)
            {
                MenuRegistry.openExtendedMenu((ServerPlayer) ply, (UpgradeBlockEntity) ent, new Consumer<FriendlyByteBuf>() {
                    @Override
                    public void accept(FriendlyByteBuf friendlyByteBuf) {
                        friendlyByteBuf.writeBlockPos(pos);
                    }
                });
            }
            else
            {
                throw new IllegalStateException("Lmao imagine not having a container provider");
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }
}
