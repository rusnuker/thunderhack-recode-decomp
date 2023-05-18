//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.util;

import java.awt.*;
import com.mojang.blaze3d.systems.*;
import java.util.function.*;
import org.joml.*;
import com.mojang.blaze3d.platform.*;
import com.mrzak34.thunderhack.mixin.*;
import org.jetbrains.annotations.*;
import org.lwjgl.opengl.*;
import net.minecraft.*;

public class RenderUtil implements Util
{
    private static final class_2960 TEXTURE_MAP_BACKGROUND;
    
    public static void drawCircle(final Matrix4f matrix, final float x, final float y, final float r, final Color colour) {
        drawHollowCircle(matrix, x, y, r, 0.0f, colour);
    }
    
    public static void drawHollowCircle(final Matrix4f matrix, final float x, final float y, final float r, final float ir, final Color colour) {
        drawHollowCircleSegm(matrix, x, y, r, ir, 0.0f, 360.0f, 0.0f, 360.0f, colour);
    }
    
    public static void drawCircleSegm(final Matrix4f matrix, final float x, final float y, final float r, final float startDeg, final float endDeg, final Color colour) {
        drawHollowCircleSegm(matrix, x, y, r, 0.0f, startDeg, endDeg, startDeg, endDeg, colour);
    }
    
    public static void drawHollowCircleSegm(final Matrix4f matrix, final float x, final float y, final float r, final float ir, final float innerStartDeg, final float innerEndDeg, final float outerStartDeg, final float outerEndDeg, final Color colour) {
        final class_287 bufferBuilder = class_289.method_1348().method_1349();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        final float ol = outerEndDeg - outerStartDeg;
        final float oa = outerStartDeg;
        final float il = innerEndDeg - innerStartDeg;
        final float ia = innerStartDeg;
        final float red = (float)colour.getRed();
        final float green = (float)colour.getGreen();
        final float blue = (float)colour.getBlue();
        final float alpha = (float)colour.getAlpha();
        for (int i = 0; i <= 356; i += 4) {
            float f = i / 360.0f;
            final float oSin = (float)(Math.sin(f * ol + oa) * r);
            final float oCos = (float)(Math.cos(f * ol + oa) * r);
            final float iSin = (float)(Math.sin(f * il + ia) * ir);
            final float iCos = (float)(Math.cos(f * il + ia) * ir);
            f = (i + 4) / 360.0f;
            final float oSin2 = (float)(Math.sin(f * ol + oa) * r);
            final float oCos2 = (float)(Math.cos(f * ol + oa) * r);
            final float iSin2 = (float)(Math.sin(f * il + ia) * ir);
            final float iCos2 = (float)(Math.cos(f * il + ia) * ir);
            bufferBuilder.method_1328(class_293.class_5596.field_27377, class_290.field_29337);
            bufferBuilder.method_22918(matrix, x + iSin, y - iCos, 0.0f).method_22915(red, green, blue, alpha).method_1344();
            bufferBuilder.method_22918(matrix, x + oSin, y - oCos, 0.0f).method_22915(red, green, blue, alpha).method_1344();
            bufferBuilder.method_22918(matrix, x + oSin2, y - oCos2, 0.0f).method_22915(red, green, blue, alpha).method_1344();
            bufferBuilder.method_22918(matrix, x + iSin2, y - iCos2, 0.0f).method_22915(red, green, blue, alpha).method_1344();
            bufferBuilder.method_1326();
        }
        RenderSystem.disableBlend();
    }
    
    public static void drawLine(final class_4587 stack, final class_243 startPos, final class_243 endPos, final Color color, final double lineWidth) {
        final float startX = (float)startPos.field_1352;
        final float startY = (float)startPos.field_1351;
        final float startZ = (float)startPos.field_1350;
        final float endX = (float)(endPos.field_1352 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10216());
        final float endY = (float)(endPos.field_1351 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10214());
        final float endZ = (float)(endPos.field_1350 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10215());
        final float x = endX - startX;
        final float y = endY - startY;
        final float z = endZ - startZ;
        final class_289 tessellator = class_289.method_1348();
        final class_287 bufferBuilder = tessellator.method_1349();
        final Matrix3f normal = stack.method_23760().method_23762();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.disableCull();
        RenderSystem.lineWidth((float)lineWidth);
        RenderSystem.setShader((Supplier)class_757::method_34535);
        RenderSystem.defaultBlendFunc();
        bufferBuilder.method_1328(class_293.class_5596.field_27377, class_290.field_29337);
        bufferBuilder.method_22918(stack.method_23760().method_23761(), startX, startY, startZ).method_39415(color.getRGB()).method_23763(normal, 1.0f, 0.0f, 0.0f).method_1344();
        bufferBuilder.method_22918(stack.method_23760().method_23761(), endX, endY, endZ).method_39415(color.getRGB()).method_23763(normal, 1.0f, 0.0f, 0.0f).method_1344();
        bufferBuilder.method_22918(stack.method_23760().method_23761(), startX, startY, startZ).method_39415(color.getRGB()).method_23763(normal, 0.0f, 1.0f, 0.0f).method_1344();
        bufferBuilder.method_22918(stack.method_23760().method_23761(), endX, endY, endZ).method_39415(color.getRGB()).method_23763(normal, 0.0f, 1.0f, 0.0f).method_1344();
        bufferBuilder.method_22918(stack.method_23760().method_23761(), startX, startY, startZ).method_39415(color.getRGB()).method_23763(normal, 0.0f, 0.0f, 1.0f).method_1344();
        bufferBuilder.method_22918(stack.method_23760().method_23761(), endX, endY, endZ).method_39415(color.getRGB()).method_23763(normal, 0.0f, 0.0f, 1.0f).method_1344();
        tessellator.method_1350();
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);
        RenderSystem.enableCull();
    }
    
    public static void drawVignette(final float threshold, final float power) {
        final boolean dif = EntityUtil.getHealth((class_1309)RenderUtil.mc.field_1724) <= threshold;
        final float f = Math.abs((dif ? (EntityUtil.getHealth((class_1309)RenderUtil.mc.field_1724) / threshold) : 1.0f) - 1.0f) * power;
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.blendFuncSeparate(GlStateManager.class_4535.ZERO, GlStateManager.class_4534.ONE_MINUS_SRC_COLOR, GlStateManager.class_4535.ONE, GlStateManager.class_4534.ZERO);
        RenderSystem.setShaderColor(0.0f, f, f, 1.0f);
        RenderSystem.setShader((Supplier)class_757::method_34542);
        RenderSystem.setShaderTexture(0, ((IInGameHud)RenderUtil.mc.field_1705).getVignette());
        final class_289 tessellator = class_289.method_1348();
        final class_287 bufferBuilder = tessellator.method_1349();
        bufferBuilder.method_1328(class_293.class_5596.field_27382, class_290.field_1585);
        bufferBuilder.method_22912(0.0, (double)RenderUtil.mc.method_22683().method_4502(), -90.0).method_22913(0.0f, 1.0f).method_1344();
        bufferBuilder.method_22912((double)RenderUtil.mc.method_22683().method_4486(), (double)RenderUtil.mc.method_22683().method_4502(), -90.0).method_22913(1.0f, 1.0f).method_1344();
        bufferBuilder.method_22912((double)RenderUtil.mc.method_22683().method_4486(), 0.0, -90.0).method_22913(1.0f, 0.0f).method_1344();
        bufferBuilder.method_22912(0.0, 0.0, -90.0).method_22913(0.0f, 0.0f).method_1344();
        tessellator.method_1350();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.defaultBlendFunc();
    }
    
    public static void drawString(final class_4587 matrices, final String text, final int x, final int y, final int color) {
        matrices.method_22903();
        matrices.method_22904((double)x, (double)y, -0.01);
        RenderUtil.mc.field_1772.method_1729(matrices, text, 0.0f, 0.0f, color);
        matrices.method_22909();
    }
    
    public static void drawText(final class_4587 matrices, final class_2561 text, final int x, final int y, final int color) {
        matrices.method_46416((float)x, (float)y, 0.0f);
        RenderUtil.mc.field_1772.method_30883(matrices, text, 0.0f, 0.0f, color);
    }
    
    public static void drawWeb(final class_4587 matrices, final class_2338 webPos) {
    }
    
    public static void renderItemStack(final class_4587 matrices, final class_1799 stack, final int x, final int y, @Nullable final String countLabel) {
        if (stack.method_7960()) {
            return;
        }
        matrices.method_22903();
        drawItem(matrices, x, y, 16.0, stack);
        if (stack.method_7947() != 1 || countLabel != null) {
            final String string = (countLabel == null) ? String.valueOf(stack.method_7947()) : countLabel;
            matrices.method_22903();
            matrices.method_22904(0.0, 0.0, -0.01);
            drawString(matrices, string, x + 9 - RenderUtil.mc.field_1772.method_1727(string), y + 2, 16777215);
            matrices.method_22909();
        }
        if (stack.method_31578()) {
            final int i = stack.method_31579();
            final int j = stack.method_31580();
            class_332.method_25294(matrices, x - 6, y + 5, x + 7, y + 7, new Color(0, 0, 0, 255).getRGB());
            class_332.method_25294(matrices, x - 6, y + 5, x - 6 + i, y + 6, new Color(j >> 16 & 0xFF, j >> 8 & 0xFF, j & 0xFF, 255).getRGB());
        }
        final class_746 clientPlayerEntity = class_310.method_1551().field_1724;
        final float f = (clientPlayerEntity == null) ? 0.0f : clientPlayerEntity.method_7357().method_7905(stack.method_7909(), class_310.method_1551().method_1488());
        if (f > 0.0f) {
            class_332.method_25294(matrices, x, y + class_3532.method_15375(16.0f * (1.0f - f)), 16, class_3532.method_15386(16.0f * f), new Color(255, 255, 255, 127).getRGB());
        }
        matrices.method_22909();
    }
    
    public static void drawItem(final class_4587 matrices, final int x, final int y, final double scale, final class_1799 item) {
        if (item.method_7960()) {
            return;
        }
        matrices.method_22903();
        matrices.method_46416((float)x, (float)y, 0.0f);
        matrices.method_22905((float)scale, (float)scale, 0.001f);
        matrices.method_22907(class_7833.field_40714.rotationDegrees(180.0f));
        RenderUtil.mc.method_22940().method_23000().method_22993();
        class_308.method_24210();
        RenderUtil.mc.method_1480().method_23178(item, class_811.field_4317, 15728880, class_4608.field_21444, matrices, (class_4597)RenderUtil.mc.method_22940().method_23000(), (class_1937)RenderUtil.mc.field_1687, 0);
        RenderUtil.mc.method_22940().method_23000().method_22993();
        matrices.method_22909();
    }
    
    public static void drawSmoothRect(final class_4587 matrices, final int left, final int top, final int right, final int bottom, final int color) {
        class_332.method_25294(matrices, left, top, right, bottom, color);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        class_332.method_25294(matrices, left * 2 - 1, top * 2, left * 2, bottom * 2 - 1, color);
        class_332.method_25294(matrices, left * 2, top * 2 - 1, right * 2, top * 2, color);
        class_332.method_25294(matrices, right * 2, top * 2, right * 2 + 1, bottom * 2 - 1, color);
        class_332.method_25294(matrices, left * 2, bottom * 2 - 1, right * 2, bottom * 2, color);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
    }
    
    public static void renderEntity(final class_4587 matrices, final class_1297 entity, final double x, final double y, final double z) {
        if (RenderUtil.mc.field_1724 == null) {
            return;
        }
        class_308.method_24210();
        matrices.method_22903();
        final class_898 entityRenderDispatcher = RenderUtil.mc.method_1561();
        entityRenderDispatcher.method_3948(false);
        final class_4597.class_4598 immediate = RenderUtil.mc.method_22940().method_23000();
        entity.field_6012 = RenderUtil.mc.field_1724.field_6012;
        entity.method_5880(false);
        entityRenderDispatcher.method_3954(entity, x, y + 2.0, z, 0.0f, 1.0f, matrices, (class_4597)immediate, 15728880);
        immediate.method_22993();
        entityRenderDispatcher.method_3948(true);
        matrices.method_22909();
        class_308.method_24211();
    }
    
    public static void setup3DRender(final boolean disableDepth) {
        RenderSystem.enableBlend();
        if (disableDepth) {
            RenderSystem.disableDepthTest();
        }
        RenderSystem.depthMask(class_310.method_29611());
        RenderSystem.enableCull();
    }
    
    public static void drawOutline(final class_4587 stack, final class_238 box, final Color c, final double lineWidth) {
        final float minX = (float)(box.field_1323 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10216());
        final float minY = (float)(box.field_1322 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10214());
        final float minZ = (float)(box.field_1321 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10215());
        final float maxX = (float)(box.field_1320 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10216());
        final float maxY = (float)(box.field_1325 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10214());
        final float maxZ = (float)(box.field_1324 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10215());
        final class_289 tessellator = class_289.method_1348();
        final class_287 bufferBuilder = tessellator.method_1349();
        final double distance = Math.sqrt(RenderUtil.mc.field_1724.method_5707(box.method_1005()));
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.disableCull();
        RenderSystem.lineWidth((float)(lineWidth * (-0.02500000037252903 * ((distance <= 8.0) ? 1.0 : (distance * 0.1)))));
        RenderSystem.setShader((Supplier)class_757::method_34535);
        RenderSystem.defaultBlendFunc();
        bufferBuilder.method_1328(class_293.class_5596.field_27377, class_290.field_29337);
        class_761.method_22980(stack, (class_4588)bufferBuilder, (double)minX, (double)minY, (double)minZ, (double)maxX, (double)maxY, (double)maxZ, c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f);
        tessellator.method_1350();
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);
        RenderSystem.enableCull();
    }
    
    public static void drawSphere(final class_4587 matrixStack, final float radius, final int gradation, final Color color, final boolean testDepth, final class_243 pos) {
        final Matrix4f matrix4f = matrixStack.method_23760().method_23761();
        final float PI = 3.141592f;
        setup3DRender(!testDepth);
        for (float alpha = 0.0f; alpha < 3.141592653589793; alpha += 3.141592f / gradation) {
            final class_287 bufferBuilder = class_289.method_1348().method_1349();
            bufferBuilder.method_1328(class_293.class_5596.field_27382, class_290.field_1576);
            for (float beta = 0.0f; beta < 6.314601203754922; beta += 3.141592f / gradation) {
                float x = (float)(pos.method_10216() + radius * Math.cos(beta) * Math.sin(alpha));
                float y = (float)(pos.method_10214() + radius * Math.sin(beta) * Math.sin(alpha));
                float z = (float)(pos.method_10215() + radius * Math.cos(alpha));
                class_243 renderPos = getRenderPosition(x, y, z);
                bufferBuilder.method_22918(matrix4f, (float)renderPos.field_1352, (float)renderPos.field_1351, (float)renderPos.field_1350).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).method_1344();
                x = (float)(pos.method_10216() + radius * Math.cos(beta) * Math.sin(alpha + 3.141592f / gradation));
                y = (float)(pos.method_10214() + radius * Math.sin(beta) * Math.sin(alpha + 3.141592f / gradation));
                z = (float)(pos.method_10215() + radius * Math.cos(alpha + 3.141592f / gradation));
                renderPos = getRenderPosition(x, y, z);
                bufferBuilder.method_22918(matrix4f, (float)renderPos.field_1352, (float)renderPos.field_1351, (float)renderPos.field_1350).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).method_1344();
            }
            bufferBuilder.method_1326();
        }
        RenderSystem.disableCull();
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);
    }
    
    public static class_243 getRenderPosition(final double x, final double y, final double z) {
        final double minX = x - RenderUtil.mc.method_1561().field_4686.method_19326().field_1352;
        final double minY = y - RenderUtil.mc.method_1561().field_4686.method_19326().field_1351;
        final double minZ = z - RenderUtil.mc.method_1561().field_4686.method_19326().field_1350;
        return new class_243(minX, minY, minZ);
    }
    
    public void drawMap(final class_327 textRenderer, final int x, final int y, final class_4587 matrices, final class_918 itemRenderer, final int z, final float scale) {
        matrices.method_22903();
        matrices.method_46416((float)x, (float)y, (float)z);
        matrices.method_22905(scale * 2.0f, scale * 2.0f, 0.0f);
        matrices.method_22905(1.125f, 1.125f, 0.0f);
        RenderSystem.setShader((Supplier)class_757::method_34542);
        RenderSystem.setShaderTexture(0, RenderUtil.TEXTURE_MAP_BACKGROUND);
        class_332.method_25291(matrices, 0, 0, 0, 0.0f, 0.0f, 64, 64, 64, 64);
        matrices.method_22909();
        final class_4597.class_4598 consumer = RenderUtil.mc.method_22940().method_23000();
        matrices.method_22903();
        matrices.method_46416((float)x, (float)y, (float)z);
        matrices.method_22905(scale, scale, 0.0f);
        matrices.method_46416(8.0f, 8.0f, 0.0f);
        consumer.method_22993();
        matrices.method_22909();
    }
    
    public static void renderGhost(final class_4587 matrices, final class_1297 ghost, final double x, final double y, final double z) {
        matrices.method_22903();
        class_308.method_24211();
        GL11.glEnable(3553);
        final class_898 entityRenderDispatcher = RenderUtil.mc.method_1561();
        entityRenderDispatcher.method_3948(false);
        final class_4597.class_4598 immediate = RenderUtil.mc.method_22940().method_23000();
        try {
            entityRenderDispatcher.method_3954(ghost, x, y + 2.0, z, 0.0f, 1.0f, matrices, (class_4597)immediate, 15728880);
        }
        catch (Exception ex) {}
        GL11.glDisable(3553);
        class_308.method_24210();
        matrices.method_22909();
    }
    
    protected static void setupAngles(final class_1297 entity) {
        final float yaw = (float)(System.currentTimeMillis() / 10L % 360L);
        entity.method_36456(yaw);
        entity.method_5847(yaw);
        entity.method_36457(0.0f);
        if (entity instanceof class_1309) {
            if (entity instanceof class_6053) {
                ((class_1309)entity).field_6241 = yaw;
            }
            ((class_1309)entity).field_6283 = yaw;
        }
    }
    
    public static void drawOutlineRect(final class_4587 matrices, final int x, final int y, final int width, final int height, final int color, final int lineWidth) {
        class_332.method_25294(matrices, x - lineWidth, y - lineWidth, width + lineWidth, y, color);
        class_332.method_25294(matrices, x - lineWidth, height, width + lineWidth, height + lineWidth, color);
        class_332.method_25294(matrices, x - lineWidth, y, x, height, color);
        class_332.method_25294(matrices, width, y, width + lineWidth, height, color);
    }
    
    public static void drawOutlineRectTest(final class_4587 matrices, final int x, final int y, final int width, final int height, final int color, final int lineWidth) {
        matrices.method_22903();
        matrices.method_22905(0.05f, 0.05f, 0.05f);
        class_332.method_25294(matrices, x * 20 - lineWidth, y * 20 - lineWidth, width * 20 + lineWidth, y * 20, color);
        class_332.method_25294(matrices, x * 20 - lineWidth, height * 20, width * 20 + lineWidth, height * 20 + lineWidth, color);
        class_332.method_25294(matrices, x * 20 - lineWidth, y * 20, x * 20, height * 20, color);
        class_332.method_25294(matrices, width * 20, y * 20, width * 20 + lineWidth, height * 20, color);
        matrices.method_22909();
    }
    
    public static class_4587 matrixFrom(final class_243 pos) {
        final class_4587 matrices = new class_4587();
        final class_4184 camera = RenderUtil.mc.field_1773.method_19418();
        matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0f));
        matrices.method_22904(pos.method_10216() - camera.method_19326().field_1352, pos.method_10214() - camera.method_19326().field_1351, pos.method_10215() - camera.method_19326().field_1350);
        return matrices;
    }
    
    public static class_243 getInterpolationOffset(final class_1297 e) {
        if (class_310.method_1551().method_1493()) {
            return class_243.field_1353;
        }
        final double tickDelta = class_310.method_1551().method_1488();
        return new class_243(e.method_23317() - class_3532.method_16436(tickDelta, e.field_6038, e.method_23317()), e.method_23318() - class_3532.method_16436(tickDelta, e.field_5971, e.method_23318()), e.method_23321() - class_3532.method_16436(tickDelta, e.field_5989, e.method_23321()));
    }
    
    public static class_243 smoothMovement(final class_1297 e) {
        return e.method_19538().method_1020(getInterpolationOffset(e));
    }
    
    public static void drawTexture(final class_2960 icon, final int x, final int y, final int width, final int height, final class_4587 stack) {
        RenderSystem.setShaderTexture(0, icon);
        RenderSystem.texParameter(3553, 10240, 9729);
        RenderSystem.texParameter(3553, 10241, 9987);
        class_332.method_25290(stack, x, y, 0.0f, 0.0f, width, height, width, height);
    }
    
    static {
        TEXTURE_MAP_BACKGROUND = new class_2960("textures/map/map_background.png");
    }
}
