package baguchan.frostrealm.client.overlay;

import baguchan.frostrealm.capability.FrostLivingCapability;
import baguchan.frostrealm.registry.FrostAttachs;
import baguchan.frostrealm.registry.FrostBlocks;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public class FrostPortalOverlay implements LayeredDraw.Layer {
    @Override
    public void render(GuiGraphics poseStack, float partialTick) {
        Minecraft mc = Minecraft.getInstance();
        FrostLivingCapability frostLivingCapability = mc.player.getData(FrostAttachs.FROST_LIVING);
        renderTofuPortalOverlay(poseStack, mc, partialTick, poseStack.guiWidth(), poseStack.guiHeight(), frostLivingCapability);

    }

    private void renderTofuPortalOverlay(GuiGraphics poseStack, Minecraft mc, float partialTick, int width, int height, FrostLivingCapability handler) {
        float timeInPortal = handler.getPrevPortalAnimTime() + (handler.getPortalAnimTime() - handler.getPrevPortalAnimTime()) * partialTick;
        if (timeInPortal > 0.0F) {
            if (timeInPortal < 1.0F) {
                timeInPortal = timeInPortal * timeInPortal;
                timeInPortal = timeInPortal * timeInPortal;
                timeInPortal = timeInPortal * 0.8F + 0.2F;
            }

            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, timeInPortal);
            RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            TextureAtlasSprite textureatlassprite = mc.getBlockRenderer().getBlockModelShaper().getParticleIcon(FrostBlocks.FROST_PORTAL.get().defaultBlockState());
            float f = textureatlassprite.getU0();
            float f1 = textureatlassprite.getV0();
            float f2 = textureatlassprite.getU1();
            float f3 = textureatlassprite.getV1();
            Tesselator tesselator = Tesselator.getInstance();
            BufferBuilder bufferbuilder = tesselator.getBuilder();
            bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
            bufferbuilder.vertex(0.0D, (double) height, -90.0D).uv(f, f3).endVertex();
            bufferbuilder.vertex((double) width, (double) height, -90.0D).uv(f2, f3).endVertex();
            bufferbuilder.vertex((double) width, 0.0D, -90.0D).uv(f2, f1).endVertex();
            bufferbuilder.vertex(0.0D, 0.0D, -90.0D).uv(f, f1).endVertex();
            tesselator.end();
            RenderSystem.disableBlend();
            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}