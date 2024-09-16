package org.faz.dvlmultiload.mixin;

import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import org.faz.dvlmultiload.network.DarkendHudSyncS2CPacket;
import org.faz.dvlmultiload.registers.ModPackets;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
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
    }
}
