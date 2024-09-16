package org.faz.dvlmultiload.network;

import dev.architectury.fluid.FluidStack;
import dev.architectury.networking.NetworkManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import org.faz.dvlmultiload.client.DvlHud;

import java.util.function.Supplier;

public class DarkendHudSyncS2CPacket
{
    private final int lightLevel;

    public DarkendHudSyncS2CPacket(int light)
    {
        lightLevel = light;
    }

    public DarkendHudSyncS2CPacket(net.minecraft.network.FriendlyByteBuf buf)
    {
        lightLevel = buf.readInt();
    }

    public void encode(FriendlyByteBuf buf)
    {
        buf.writeInt(lightLevel);
    }

    public void apply(Supplier<NetworkManager.PacketContext> contextSupplier)
    {
        NetworkManager.PacketContext context = contextSupplier.get();
        context.queue(() ->
        {
            DvlHud.setLightLevel(lightLevel);
        });
    }
}
