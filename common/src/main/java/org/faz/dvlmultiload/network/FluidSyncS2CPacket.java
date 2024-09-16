package org.faz.dvlmultiload.network;

import dev.architectury.fluid.FluidStack;
import dev.architectury.networking.NetworkManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

import java.util.function.Supplier;

public class FluidSyncS2CPacket
{
    public final FluidStack fluidStack;
    public final BlockPos pos;

    public FluidSyncS2CPacket(FluidStack fluid, BlockPos p)
    {
        fluidStack = fluid;
        pos = p;
    }

    public FluidSyncS2CPacket(net.minecraft.network.FriendlyByteBuf buf)
    {
        fluidStack = FluidStack.read(buf);
        pos = buf.readBlockPos();
    }

    public void encode(FriendlyByteBuf buf)
    {
        fluidStack.write(buf);
        buf.writeBlockPos(pos);
    }

    public void apply(Supplier<NetworkManager.PacketContext> contextSupplier)
    {
        NetworkManager.PacketContext context = contextSupplier.get();
        context.queue(() ->
        {
            if (net.minecraft.client.Minecraft.getInstance().level.getBlockEntity(pos) instanceof org.faz.dvlmultiload.blockentities.UpgradeBlockEntity be)
            {
                be.setFluid(fluidStack);
                if (net.minecraft.client.Minecraft.getInstance().player.containerMenu instanceof org.faz.dvlmultiload.screen.UpgradeMenu menu && menu.getBlockEntity().getBlockPos().equals(pos))
                {
                    menu.setFluid(this.fluidStack);
                }
            }
        });
    }
}
