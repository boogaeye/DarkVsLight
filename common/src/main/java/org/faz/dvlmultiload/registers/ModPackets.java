package org.faz.dvlmultiload.registers;

import dev.architectury.networking.NetworkChannel;
import dev.architectury.networking.NetworkManager;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import org.faz.dvlmultiload.DarkVsLight;
import org.faz.dvlmultiload.blockentities.UpgradeBlockEntity;
import org.faz.dvlmultiload.network.DarkendHudSyncS2CPacket;
import org.faz.dvlmultiload.network.FluidSyncS2CPacket;
import org.faz.dvlmultiload.network.MethodReasonS2CPacket;
import org.faz.dvlmultiload.screen.UpgradeMenu;

public class ModPackets
{
    public static final NetworkChannel CHANNEL = NetworkChannel.create(new ResourceLocation(DarkVsLight.MOD_ID, "main"));
    public static final ResourceLocation SYNC_FLUIDS = new ResourceLocation(DarkVsLight.MOD_ID, "sync_fluids");

    public static final ResourceLocation SYNC_ENERGY = new ResourceLocation(DarkVsLight.MOD_ID, "sync_energy");

    public static final ResourceLocation SYNC_METHOD_REASON = new ResourceLocation(DarkVsLight.MOD_ID, "sync_method_reason");

    public static final ResourceLocation SYNC_DARKEND_HUD = new ResourceLocation(DarkVsLight.MOD_ID, "sync_darkend_hud");

    public static void init()
    {
        CHANNEL.register(FluidSyncS2CPacket.class, FluidSyncS2CPacket::encode, FluidSyncS2CPacket::new, FluidSyncS2CPacket::apply);
        CHANNEL.register(MethodReasonS2CPacket.class, MethodReasonS2CPacket::encode, MethodReasonS2CPacket::new, MethodReasonS2CPacket::apply);
        CHANNEL.register(DarkendHudSyncS2CPacket.class, DarkendHudSyncS2CPacket::encode, DarkendHudSyncS2CPacket::new, DarkendHudSyncS2CPacket::apply);
        initPackets();
    }

    public static void initClient()
    {
        initPacketsClient();
        System.out.println("Client packets initialized");
    }

    public static void initPackets()
    {

    }

    public static void initPacketsClient()
    {
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, SYNC_FLUIDS, (packetContext, packetBuffer) -> {
            FluidSyncS2CPacket packet = new FluidSyncS2CPacket(packetContext);
            packet.apply(() -> packetBuffer);
        });

        NetworkManager.registerReceiver(NetworkManager.Side.S2C, SYNC_METHOD_REASON, (packetContext, packetBuffer) -> {
            MethodReasonS2CPacket packet = new MethodReasonS2CPacket(packetContext);
            packet.apply(() -> packetBuffer);
        });

        NetworkManager.registerReceiver(NetworkManager.Side.S2C, SYNC_DARKEND_HUD, (packetContext, packetBuffer) -> {
            DarkendHudSyncS2CPacket packet = new DarkendHudSyncS2CPacket(packetContext);
            packet.apply(() -> packetBuffer);
        });
    }
}
