package org.faz.dvlmultiload.network;

import dev.architectury.fluid.FluidStack;
import dev.architectury.networking.NetworkManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

import java.util.function.Supplier;

public class MethodReasonS2CPacket
{
    public final String fluidStack;
    public final BlockPos pos;

    public MethodReasonS2CPacket(String fluid, BlockPos p)
    {
        fluidStack = fluid;
        pos = p;
    }

    public MethodReasonS2CPacket(FriendlyByteBuf buf)
    {
        fluidStack = buf.readUtf();
        pos = buf.readBlockPos();
    }

    public void encode(FriendlyByteBuf buf)
    {
        buf.writeUtf(fluidStack);
        buf.writeBlockPos(pos);
    }

    public void apply(Supplier<NetworkManager.PacketContext> contextSupplier)
    {
        NetworkManager.PacketContext context = contextSupplier.get();
        context.queue(() ->
        {
            if (net.minecraft.client.Minecraft.getInstance().level.getBlockEntity(pos) instanceof org.faz.dvlmultiload.blockentities.UpgradeBlockEntity be)
            {
                be.setMethodReason(fluidStack);

                if (net.minecraft.client.Minecraft.getInstance().player.containerMenu instanceof org.faz.dvlmultiload.screen.UpgradeMenu menu && menu.getBlockEntity().getBlockPos().equals(pos))
                {
                    be.setMethodReason(fluidStack);
                }
            }
        });
    }
}
