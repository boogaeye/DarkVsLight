package org.faz.dvlmultiload.mixin;

import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.minecraft.BlockUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.phys.Vec3;
import org.faz.dvlmultiload.block.ClimbableBlock;
import org.faz.dvlmultiload.block.DarkendPortalBlock;
import org.faz.dvlmultiload.network.DarkendHudSyncS2CPacket;
import org.faz.dvlmultiload.registers.ModDimensions;
import org.faz.dvlmultiload.registers.ModPackets;
import org.faz.dvlmultiload.teleporters.DarkendTeleporter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(value = ServerPlayer.class)
public abstract class PlayerMixin
{
    @Shadow public abstract Level getLevel();

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci)
    {
        if (!Minecraft.getInstance().isLocalServer()) return;

        LivingEntity ply = ((LivingEntity) (Object) this);
        if (ply.tickCount % 10 == 0)
        {
            int skyLight = getLevel().getBrightness(LightLayer.SKY, ply.getOnPos().above()) - ply.getLevel().getSkyDarken();
            int blockLight = getLevel().getBrightness(LightLayer.BLOCK, ply.getOnPos().above());
            int lightLvl = Math.max(skyLight, blockLight);
            DarkendHudSyncS2CPacket packet = new DarkendHudSyncS2CPacket(lightLvl);
            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
            packet.encode(buf);
            NetworkManager.sendToPlayer((ServerPlayer) ply, ModPackets.SYNC_DARKEND_HUD, buf);
        }

        if (ply.getBlockStateOn().getBlock() instanceof ClimbableBlock climbableBlock)
        {
            ply.fallDistance = 0;
            ply.flyDist = 0;
            ply.resetFallDistance();
            if (isPlayerMovingVert(ply) && ply.tickCount % 5 == 0)
            {
                getLevel().playSound(null, ply.blockPosition(), climbableBlock.getClimbSound(), ply.getSoundSource(), 1.0F, 1.0F);
            }
        }
    }

    @Inject(method = "findDimensionEntryPoint", at = @At("HEAD"), cancellable = true)
    private void findDimensionEntryPoint(ServerLevel destination, CallbackInfoReturnable<PortalInfo> cir)
    {
        if (destination.dimension() == ModDimensions.DARKEND)
        {
            Entity entity = (Entity) (Object) this;
            DarkendTeleporter teleporter = new DarkendTeleporter(destination);
            BlockPos pos = entity.blockPosition();
            int y = entity.blockPosition().getY();
            BlockPos multiPos = pos.multiply(16).atY(y);

            Optional<BlockUtil.FoundRectangle> rectangle = teleporter.findOrBuildPortal(entity, multiPos);
            Vec3 vec = new Vec3(rectangle.get().minCorner.getX(), rectangle.get().minCorner.getY(), rectangle.get().minCorner.getZ());
            if (rectangle.isPresent())
            {
                System.out.println("Found portal");
                PortalInfo info = new PortalInfo(vec, Vec3.ZERO, entity.getXRot(), entity.getYRot());
                cir.setReturnValue(info);
            }
            else
            {
                System.out.println("No portal found");
            }
        }
    }

    private boolean isPlayerMovingVert(LivingEntity ply)
    {
        Vec3 motion = ply.getDeltaMovement();
        double y = motion.y;
        return y >= 0.1 || y <= -0.1;
    }
}
