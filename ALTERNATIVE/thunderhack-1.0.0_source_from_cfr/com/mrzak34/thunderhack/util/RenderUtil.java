/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.GlStateManager$class_4534
 *  com.mojang.blaze3d.platform.GlStateManager$class_4535
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_2561
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_2960
 *  net.minecraft.class_308
 *  net.minecraft.class_310
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597
 *  net.minecraft.class_4597$class_4598
 *  net.minecraft.class_4608
 *  net.minecraft.class_6053
 *  net.minecraft.class_746
 *  net.minecraft.class_757
 *  net.minecraft.class_761
 *  net.minecraft.class_7833
 *  net.minecraft.class_811
 *  net.minecraft.class_898
 *  net.minecraft.class_918
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3f
 *  org.joml.Matrix4f
 *  org.lwjgl.opengl.GL11
 */
package com.mrzak34.thunderhack.util;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mrzak34.thunderhack.mixin.IInGameHud;
import com.mrzak34.thunderhack.util.EntityUtil;
import com.mrzak34.thunderhack.util.Util;
import java.awt.Color;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_2960;
import net.minecraft.class_308;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_6053;
import net.minecraft.class_746;
import net.minecraft.class_757;
import net.minecraft.class_761;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import net.minecraft.class_898;
import net.minecraft.class_918;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

public class RenderUtil
implements Util {
    private static final class_2960 TEXTURE_MAP_BACKGROUND = new class_2960("textures/map/map_background.png");

    public static void drawCircle(Matrix4f matrix, float x, float y, float r, Color colour) {
        RenderUtil.drawHollowCircle(matrix, x, y, r, 0.0f, colour);
    }

    public static void drawHollowCircle(Matrix4f matrix, float x, float y, float r, float ir, Color colour) {
        RenderUtil.drawHollowCircleSegm(matrix, x, y, r, ir, 0.0f, 360.0f, 0.0f, 360.0f, colour);
    }

    public static void drawCircleSegm(Matrix4f matrix, float x, float y, float r, float startDeg, float endDeg, Color colour) {
        RenderUtil.drawHollowCircleSegm(matrix, x, y, r, 0.0f, startDeg, endDeg, startDeg, endDeg, colour);
    }

    public static void drawHollowCircleSegm(Matrix4f matrix, float x, float y, float r, float ir, float innerStartDeg, float innerEndDeg, float outerStartDeg, float outerEndDeg, Color colour) {
        class_287 bufferBuilder = class_289.method_1348().method_1349();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        float ol = outerEndDeg - outerStartDeg;
        float oa = outerStartDeg;
        float il = innerEndDeg - innerStartDeg;
        float ia = innerStartDeg;
        float red = colour.getRed();
        float green = colour.getGreen();
        float blue = colour.getBlue();
        float alpha = colour.getAlpha();
        for (int i = 0; i <= 356; i += 4) {
            float f = (float)i / 360.0f;
            float oSin = (float)(Math.sin(f * ol + oa) * (double)r);
            float oCos = (float)(Math.cos(f * ol + oa) * (double)r);
            float iSin = (float)(Math.sin(f * il + ia) * (double)ir);
            float iCos = (float)(Math.cos(f * il + ia) * (double)ir);
            f = (float)(i + 4) / 360.0f;
            float oSin1 = (float)(Math.sin(f * ol + oa) * (double)r);
            float oCos1 = (float)(Math.cos(f * ol + oa) * (double)r);
            float iSin1 = (float)(Math.sin(f * il + ia) * (double)ir);
            float iCos1 = (float)(Math.cos(f * il + ia) * (double)ir);
            bufferBuilder.method_1328(class_293.class_5596.field_27377, class_290.field_29337);
            bufferBuilder.method_22918(matrix, x + iSin, y - iCos, 0.0f).method_22915(red, green, blue, alpha).method_1344();
            bufferBuilder.method_22918(matrix, x + oSin, y - oCos, 0.0f).method_22915(red, green, blue, alpha).method_1344();
            bufferBuilder.method_22918(matrix, x + oSin1, y - oCos1, 0.0f).method_22915(red, green, blue, alpha).method_1344();
            bufferBuilder.method_22918(matrix, x + iSin1, y - iCos1, 0.0f).method_22915(red, green, blue, alpha).method_1344();
            bufferBuilder.method_1326();
        }
        RenderSystem.disableBlend();
    }

    public static void drawLine(class_4587 stack, class_243 startPos, class_243 endPos, Color color, double lineWidth) {
        float startX = (float)startPos.field_1352;
        float startY = (float)startPos.field_1351;
        float startZ = (float)startPos.field_1350;
        float endX = (float)(endPos.field_1352 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10216());
        float endY = (float)(endPos.field_1351 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10214());
        float endZ = (float)(endPos.field_1350 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10215());
        float x = endX - startX;
        float y = endY - startY;
        float z = endZ - startZ;
        class_289 tessellator = class_289.method_1348();
        class_287 bufferBuilder = tessellator.method_1349();
        Matrix3f normal = stack.method_23760().method_23762();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.disableCull();
        RenderSystem.lineWidth((float)((float)lineWidth));
        RenderSystem.setShader(class_757::method_34535);
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
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableCull();
    }

    public static void drawVignette(float threshold, float power) {
        boolean dif = (float)EntityUtil.getHealth((class_1309)RenderUtil.mc.field_1724) <= threshold;
        float f = Math.abs((dif ? (float)EntityUtil.getHealth((class_1309)RenderUtil.mc.field_1724) / threshold : 1.0f) - 1.0f) * power;
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.blendFuncSeparate((GlStateManager.class_4535)GlStateManager.class_4535.ZERO, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_SRC_COLOR, (GlStateManager.class_4535)GlStateManager.class_4535.ONE, (GlStateManager.class_4534)GlStateManager.class_4534.ZERO);
        RenderSystem.setShaderColor((float)0.0f, (float)f, (float)f, (float)1.0f);
        RenderSystem.setShader(class_757::method_34542);
        RenderSystem.setShaderTexture((int)0, (class_2960)((IInGameHud)RenderUtil.mc.field_1705).getVignette());
        class_289 tessellator = class_289.method_1348();
        class_287 bufferBuilder = tessellator.method_1349();
        bufferBuilder.method_1328(class_293.class_5596.field_27382, class_290.field_1585);
        bufferBuilder.method_22912(0.0, (double)mc.method_22683().method_4502(), -90.0).method_22913(0.0f, 1.0f).method_1344();
        bufferBuilder.method_22912((double)mc.method_22683().method_4486(), (double)mc.method_22683().method_4502(), -90.0).method_22913(1.0f, 1.0f).method_1344();
        bufferBuilder.method_22912((double)mc.method_22683().method_4486(), 0.0, -90.0).method_22913(1.0f, 0.0f).method_1344();
        bufferBuilder.method_22912(0.0, 0.0, -90.0).method_22913(0.0f, 0.0f).method_1344();
        tessellator.method_1350();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.defaultBlendFunc();
    }

    public static void drawString(class_4587 matrices, String text, int x, int y, int color) {
        matrices.method_22903();
        matrices.method_22904((double)x, (double)y, -0.01);
        RenderUtil.mc.field_1772.method_1729(matrices, text, 0.0f, 0.0f, color);
        matrices.method_22909();
    }

    public static void drawText(class_4587 matrices, class_2561 text, int x, int y, int color) {
        matrices.method_46416((float)x, (float)y, 0.0f);
        RenderUtil.mc.field_1772.method_30883(matrices, text, 0.0f, 0.0f, color);
    }

    public static void drawWeb(class_4587 matrices, class_2338 webPos) {
    }

    public static void renderItemStack(class_4587 matrices, class_1799 stack, int x, int y, @Nullable String countLabel) {
        class_746 clientPlayerEntity;
        float f;
        if (stack.method_7960()) {
            return;
        }
        matrices.method_22903();
        RenderUtil.drawItem(matrices, x, y, 16.0, stack);
        if (stack.method_7947() != 1 || countLabel != null) {
            String string = countLabel == null ? String.valueOf(stack.method_7947()) : countLabel;
            matrices.method_22903();
            matrices.method_22904(0.0, 0.0, -0.01);
            RenderUtil.drawString(matrices, string, x + 9 - RenderUtil.mc.field_1772.method_1727(string), y + 2, 0xFFFFFF);
            matrices.method_22909();
        }
        if (stack.method_31578()) {
            int i = stack.method_31579();
            int j = stack.method_31580();
            class_332.method_25294((class_4587)matrices, (int)(x - 6), (int)(y + 5), (int)(x + 7), (int)(y + 7), (int)new Color(0, 0, 0, 255).getRGB());
            class_332.method_25294((class_4587)matrices, (int)(x - 6), (int)(y + 5), (int)(x - 6 + i), (int)(y + 6), (int)new Color(j >> 16 & 0xFF, j >> 8 & 0xFF, j & 0xFF, 255).getRGB());
        }
        float f2 = f = (clientPlayerEntity = class_310.method_1551().field_1724) == null ? 0.0f : clientPlayerEntity.method_7357().method_7905(stack.method_7909(), class_310.method_1551().method_1488());
        if (f > 0.0f) {
            class_332.method_25294((class_4587)matrices, (int)x, (int)(y + class_3532.method_15375((float)(16.0f * (1.0f - f)))), (int)16, (int)class_3532.method_15386((float)(16.0f * f)), (int)new Color(255, 255, 255, 127).getRGB());
        }
        matrices.method_22909();
    }

    public static void drawItem(class_4587 matrices, int x, int y, double scale, class_1799 item) {
        if (item.method_7960()) {
            return;
        }
        matrices.method_22903();
        matrices.method_46416((float)x, (float)y, 0.0f);
        matrices.method_22905((float)scale, (float)scale, 0.001f);
        matrices.method_22907(class_7833.field_40714.rotationDegrees(180.0f));
        mc.method_22940().method_23000().method_22993();
        class_308.method_24210();
        mc.method_1480().method_23178(item, class_811.field_4317, 0xF000F0, class_4608.field_21444, matrices, (class_4597)mc.method_22940().method_23000(), (class_1937)RenderUtil.mc.field_1687, 0);
        mc.method_22940().method_23000().method_22993();
        matrices.method_22909();
    }

    public static void drawSmoothRect(class_4587 matrices, int left, int top, int right, int bottom, int color) {
        class_332.method_25294((class_4587)matrices, (int)left, (int)top, (int)right, (int)bottom, (int)color);
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        class_332.method_25294((class_4587)matrices, (int)(left * 2 - 1), (int)(top * 2), (int)(left * 2), (int)(bottom * 2 - 1), (int)color);
        class_332.method_25294((class_4587)matrices, (int)(left * 2), (int)(top * 2 - 1), (int)(right * 2), (int)(top * 2), (int)color);
        class_332.method_25294((class_4587)matrices, (int)(right * 2), (int)(top * 2), (int)(right * 2 + 1), (int)(bottom * 2 - 1), (int)color);
        class_332.method_25294((class_4587)matrices, (int)(left * 2), (int)(bottom * 2 - 1), (int)(right * 2), (int)(bottom * 2), (int)color);
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
    }

    public static void renderEntity(class_4587 matrices, class_1297 entity, double x, double y, double z) {
        if (RenderUtil.mc.field_1724 == null) {
            return;
        }
        class_308.method_24210();
        matrices.method_22903();
        class_898 entityRenderDispatcher = mc.method_1561();
        entityRenderDispatcher.method_3948(false);
        class_4597.class_4598 immediate = mc.method_22940().method_23000();
        entity.field_6012 = RenderUtil.mc.field_1724.field_6012;
        entity.method_5880(false);
        entityRenderDispatcher.method_3954(entity, x, y + 2.0, z, 0.0f, 1.0f, matrices, (class_4597)immediate, 0xF000F0);
        immediate.method_22993();
        entityRenderDispatcher.method_3948(true);
        matrices.method_22909();
        class_308.method_24211();
    }

    public static void setup3DRender(boolean disableDepth) {
        RenderSystem.enableBlend();
        if (disableDepth) {
            RenderSystem.disableDepthTest();
        }
        RenderSystem.depthMask((boolean)class_310.method_29611());
        RenderSystem.enableCull();
    }

    public static void drawOutline(class_4587 stack, class_238 box, Color c, double lineWidth) {
        float minX = (float)(box.field_1323 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10216());
        float minY = (float)(box.field_1322 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10214());
        float minZ = (float)(box.field_1321 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10215());
        float maxX = (float)(box.field_1320 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10216());
        float maxY = (float)(box.field_1325 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10214());
        float maxZ = (float)(box.field_1324 - RenderUtil.mc.method_1561().field_4686.method_19326().method_10215());
        class_289 tessellator = class_289.method_1348();
        class_287 bufferBuilder = tessellator.method_1349();
        double distance = Math.sqrt(RenderUtil.mc.field_1724.method_5707(box.method_1005()));
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.disableCull();
        RenderSystem.lineWidth((float)((float)(lineWidth * ((double)-0.025f * (distance <= 8.0 ? 1.0 : distance * 0.1)))));
        RenderSystem.setShader(class_757::method_34535);
        RenderSystem.defaultBlendFunc();
        bufferBuilder.method_1328(class_293.class_5596.field_27377, class_290.field_29337);
        class_761.method_22980((class_4587)stack, (class_4588)bufferBuilder, (double)minX, (double)minY, (double)minZ, (double)maxX, (double)maxY, (double)maxZ, (float)((float)c.getRed() / 255.0f), (float)((float)c.getGreen() / 255.0f), (float)((float)c.getBlue() / 255.0f), (float)((float)c.getAlpha() / 255.0f));
        tessellator.method_1350();
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableCull();
    }

    public static void drawSphere(class_4587 matrixStack, float radius, int gradation, Color color, boolean testDepth, class_243 pos) {
        Matrix4f matrix4f = matrixStack.method_23760().method_23761();
        float PI = 3.141592f;
        RenderUtil.setup3DRender(!testDepth);
        float alpha = 0.0f;
        while ((double)alpha < Math.PI) {
            class_287 bufferBuilder = class_289.method_1348().method_1349();
            bufferBuilder.method_1328(class_293.class_5596.field_27382, class_290.field_1576);
            float beta = 0.0f;
            while ((double)beta < 6.314601203754922) {
                float x = (float)(pos.method_10216() + (double)radius * Math.cos(beta) * Math.sin(alpha));
                float y = (float)(pos.method_10214() + (double)radius * Math.sin(beta) * Math.sin(alpha));
                float z = (float)(pos.method_10215() + (double)radius * Math.cos(alpha));
                class_243 renderPos = RenderUtil.getRenderPosition(x, y, z);
                bufferBuilder.method_22918(matrix4f, (float)renderPos.field_1352, (float)renderPos.field_1351, (float)renderPos.field_1350).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).method_1344();
                x = (float)(pos.method_10216() + (double)radius * Math.cos(beta) * Math.sin(alpha + 3.141592f / (float)gradation));
                y = (float)(pos.method_10214() + (double)radius * Math.sin(beta) * Math.sin(alpha + 3.141592f / (float)gradation));
                z = (float)(pos.method_10215() + (double)radius * Math.cos(alpha + 3.141592f / (float)gradation));
                renderPos = RenderUtil.getRenderPosition(x, y, z);
                bufferBuilder.method_22918(matrix4f, (float)renderPos.field_1352, (float)renderPos.field_1351, (float)renderPos.field_1350).method_1336(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).method_1344();
                beta += 3.141592f / (float)gradation;
            }
            bufferBuilder.method_1326();
            alpha += 3.141592f / (float)gradation;
        }
        RenderSystem.disableCull();
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)true);
    }

    public static class_243 getRenderPosition(double x, double y, double z) {
        double minX = x - RenderUtil.mc.method_1561().field_4686.method_19326().field_1352;
        double minY = y - RenderUtil.mc.method_1561().field_4686.method_19326().field_1351;
        double minZ = z - RenderUtil.mc.method_1561().field_4686.method_19326().field_1350;
        return new class_243(minX, minY, minZ);
    }

    public void drawMap(class_327 textRenderer, int x, int y, class_4587 matrices, class_918 itemRenderer, int z, float scale) {
        matrices.method_22903();
        matrices.method_46416((float)x, (float)y, (float)z);
        matrices.method_22905(scale * 2.0f, scale * 2.0f, 0.0f);
        matrices.method_22905(1.125f, 1.125f, 0.0f);
        RenderSystem.setShader(class_757::method_34542);
        RenderSystem.setShaderTexture((int)0, (class_2960)TEXTURE_MAP_BACKGROUND);
        class_332.method_25291((class_4587)matrices, (int)0, (int)0, (int)0, (float)0.0f, (float)0.0f, (int)64, (int)64, (int)64, (int)64);
        matrices.method_22909();
        class_4597.class_4598 consumer = mc.method_22940().method_23000();
        matrices.method_22903();
        matrices.method_46416((float)x, (float)y, (float)z);
        matrices.method_22905(scale, scale, 0.0f);
        matrices.method_46416(8.0f, 8.0f, 0.0f);
        consumer.method_22993();
        matrices.method_22909();
    }

    public static void renderGhost(class_4587 matrices, class_1297 ghost, double x, double y, double z) {
        matrices.method_22903();
        class_308.method_24211();
        GL11.glEnable((int)3553);
        class_898 entityRenderDispatcher = mc.method_1561();
        entityRenderDispatcher.method_3948(false);
        class_4597.class_4598 immediate = mc.method_22940().method_23000();
        try {
            entityRenderDispatcher.method_3954(ghost, x, y + 2.0, z, 0.0f, 1.0f, matrices, (class_4597)immediate, 0xF000F0);
        }
        catch (Exception exception) {
            // empty catch block
        }
        GL11.glDisable((int)3553);
        class_308.method_24210();
        matrices.method_22909();
    }

    protected static void setupAngles(class_1297 entity) {
        float yaw = System.currentTimeMillis() / 10L % 360L;
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

    public static void drawOutlineRect(class_4587 matrices, int x, int y, int width, int height, int color, int lineWidth) {
        class_332.method_25294((class_4587)matrices, (int)(x - lineWidth), (int)(y - lineWidth), (int)(width + lineWidth), (int)y, (int)color);
        class_332.method_25294((class_4587)matrices, (int)(x - lineWidth), (int)height, (int)(width + lineWidth), (int)(height + lineWidth), (int)color);
        class_332.method_25294((class_4587)matrices, (int)(x - lineWidth), (int)y, (int)x, (int)height, (int)color);
        class_332.method_25294((class_4587)matrices, (int)width, (int)y, (int)(width + lineWidth), (int)height, (int)color);
    }

    public static void drawOutlineRectTest(class_4587 matrices, int x, int y, int width, int height, int color, int lineWidth) {
        matrices.method_22903();
        matrices.method_22905(0.05f, 0.05f, 0.05f);
        class_332.method_25294((class_4587)matrices, (int)(x * 20 - lineWidth), (int)(y * 20 - lineWidth), (int)(width * 20 + lineWidth), (int)(y * 20), (int)color);
        class_332.method_25294((class_4587)matrices, (int)(x * 20 - lineWidth), (int)(height * 20), (int)(width * 20 + lineWidth), (int)(height * 20 + lineWidth), (int)color);
        class_332.method_25294((class_4587)matrices, (int)(x * 20 - lineWidth), (int)(y * 20), (int)(x * 20), (int)(height * 20), (int)color);
        class_332.method_25294((class_4587)matrices, (int)(width * 20), (int)(y * 20), (int)(width * 20 + lineWidth), (int)(height * 20), (int)color);
        matrices.method_22909();
    }

    public static class_4587 matrixFrom(class_243 pos) {
        class_4587 matrices = new class_4587();
        class_4184 camera = RenderUtil.mc.field_1773.method_19418();
        matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        matrices.method_22907(class_7833.field_40716.rotationDegrees(camera.method_19330() + 180.0f));
        matrices.method_22904(pos.method_10216() - camera.method_19326().field_1352, pos.method_10214() - camera.method_19326().field_1351, pos.method_10215() - camera.method_19326().field_1350);
        return matrices;
    }

    public static class_243 getInterpolationOffset(class_1297 e) {
        if (class_310.method_1551().method_1493()) {
            return class_243.field_1353;
        }
        double tickDelta = class_310.method_1551().method_1488();
        return new class_243(e.method_23317() - class_3532.method_16436((double)tickDelta, (double)e.field_6038, (double)e.method_23317()), e.method_23318() - class_3532.method_16436((double)tickDelta, (double)e.field_5971, (double)e.method_23318()), e.method_23321() - class_3532.method_16436((double)tickDelta, (double)e.field_5989, (double)e.method_23321()));
    }

    public static class_243 smoothMovement(class_1297 e) {
        return e.method_19538().method_1020(RenderUtil.getInterpolationOffset(e));
    }

    public static void drawTexture(class_2960 icon, int x, int y, int width, int height, class_4587 stack) {
        RenderSystem.setShaderTexture((int)0, (class_2960)icon);
        RenderSystem.texParameter((int)3553, (int)10240, (int)9729);
        RenderSystem.texParameter((int)3553, (int)10241, (int)9987);
        class_332.method_25290((class_4587)stack, (int)x, (int)y, (float)0.0f, (float)0.0f, (int)width, (int)height, (int)width, (int)height);
    }
}

