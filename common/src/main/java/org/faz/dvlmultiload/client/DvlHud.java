package org.faz.dvlmultiload.client;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import org.faz.dvlmultiload.DarkVsLight;

public class DvlHud
{
    private static final ResourceLocation FILLED_DARKEND = new ResourceLocation(DarkVsLight.MOD_ID, "textures/darkingberry.png");

    private static final ResourceLocation EMPTY_DARKEND = new ResourceLocation(DarkVsLight.MOD_ID, "textures/darkingberrybg.png");

    private static final ResourceLocation LIGHT_GAUGE = new ResourceLocation(DarkVsLight.MOD_ID, "textures/glowamount.png");

    private static int lightLevel = 8;

    public static void setLightLevel(int light)
    {
        lightLevel = light;
    }

    public static void render(PoseStack poseStack, float tickDelta)
    {
        Minecraft client = Minecraft.getInstance();

        int x = client.getWindow().getGuiScaledWidth() / 2;
        int y = client.getWindow().getGuiScaledHeight();
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, LIGHT_GAUGE);
        GuiComponent.blit(poseStack, 9, y - 45, 90 - (Math.min((lightLevel / 2), 6) * 15), 0, 15, 15, 112, 16);
        RenderSystem.setShaderTexture(0, EMPTY_DARKEND);
        for (int i = 0; i < 10; i++){
            GuiComponent.blit(poseStack, 9 + (i * 9), y - 18, 0, 0, 9, 9, 9, 9);
        }
        RenderSystem.setShaderTexture(0, FILLED_DARKEND);
        //for (int i = 0; i < ClientDarkendData.getPlayerDarkend() / 2; i++){
        //    GuiComponent.blit(poseStack, x - 94 + (i * 9), y - 54, 0, 0, 12, 12, 12, 12);
        //}
        // Later
    }
}
