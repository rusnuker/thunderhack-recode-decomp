/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_287$class_7433
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_4587
 *  net.minecraft.class_757
 *  net.minecraft.class_7833
 *  org.joml.Matrix4f
 */
package com.mrzak34.thunderhack.Hud.HudElements;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mrzak34.thunderhack.Hud.Hud;
import com.mrzak34.thunderhack.event.events.RenderOverlayEvent;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.util.RotationUtil;
import java.awt.Color;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_4587;
import net.minecraft.class_757;
import net.minecraft.class_7833;
import org.joml.Matrix4f;

public class Radar
extends Hud {
    public static NumberSetting angl = new NumberSetting("angle", 0.0f, 0.0f, 360.0f, false);
    public static NumberSetting radius = new NumberSetting("radius", 30.0f, 0.0f, 50.0f, false);
    public static NumberSetting scale = new NumberSetting("scale", 0.5f, 1.0f, 15.0f, false);
    public static NumberSetting fixangle = new NumberSetting("fix angle", 51.0f, 0.0f, 360.0f, false);

    public Radar() {
        super("Radar", 70, 20, ((int)radius.getValue() + 2) * 2, ((int)radius.getValue() + 2) * 2, false, true);
        this.addSettings(angl, radius, fixangle, scale);
    }

    @Subscribe
    public void onRenderOverlay(RenderOverlayEvent event) {
        for (class_1657 player : Radar.mc.field_1687.method_18456()) {
            if (player == Radar.mc.field_1724) continue;
            float angle = RotationUtil.lookAtEntity((class_1297)Radar.mc.field_1724, (class_1297)player).field_1343 - Radar.mc.field_1724.method_36454();
            Radar.drawArrow(event.getMatrices(), this.posX, this.posY, (float)radius.getValue(), (float)scale.getValue(), angle, new Color(0, 0, 255).brighter(), new Color(255, 0, 255).brighter(), new Color(130, 0, 255).brighter());
        }
    }

    public static void drawArrow(class_4587 matrices, int x, int y, float radius, float scale, float angle, Color colorR, Color colorL, Color colorD) {
        matrices.method_22903();
        matrices.method_46416((float)x, (float)y, 0.0f);
        matrices.method_22905(scale, scale, scale);
        matrices.method_22907(class_7833.field_40718.rotationDegrees(angle));
        Matrix4f matrix4f = matrices.method_23760().method_23761();
        class_287 bufferBuilder = class_289.method_1348().method_1349();
        RenderSystem.enableBlend();
        RenderSystem.setShader(class_757::method_34540);
        bufferBuilder.method_1328(class_293.class_5596.field_27381, class_290.field_1576);
        bufferBuilder.method_22918(matrix4f, 0.0f, -30.0f - radius / scale, 0.0f).method_39415(colorR.getRGB()).method_1344();
        bufferBuilder.method_22918(matrix4f, 0.0f, -10.0f - radius / scale, 0.0f).method_39415(colorR.getRGB()).method_1344();
        bufferBuilder.method_22918(matrix4f, 10.0f, -5.0f - radius / scale, 0.0f).method_39415(colorR.getRGB()).method_1344();
        class_286.method_43433((class_287.class_7433)bufferBuilder.method_1326());
        bufferBuilder.method_1328(class_293.class_5596.field_27381, class_290.field_1576);
        bufferBuilder.method_22918(matrix4f, -10.0f, -5.0f - radius / scale, 0.0f).method_39415(colorL.getRGB()).method_1344();
        bufferBuilder.method_22918(matrix4f, 0.0f, -10.0f - radius / scale, 0.0f).method_39415(colorL.getRGB()).method_1344();
        bufferBuilder.method_22918(matrix4f, 0.0f, -30.0f - radius / scale, 0.0f).method_39415(colorL.getRGB()).method_1344();
        class_286.method_43433((class_287.class_7433)bufferBuilder.method_1326());
        bufferBuilder.method_1328(class_293.class_5596.field_27382, class_290.field_1576);
        bufferBuilder.method_22918(matrix4f, -10.0f, -5.0f - radius / scale, 0.0f).method_39415(colorD.getRGB()).method_1344();
        bufferBuilder.method_22918(matrix4f, 0.0f, 0.0f - radius / scale, 0.0f).method_39415(colorD.getRGB()).method_1344();
        bufferBuilder.method_22918(matrix4f, 10.0f, -5.0f - radius / scale, 0.0f).method_39415(colorD.getRGB()).method_1344();
        bufferBuilder.method_22918(matrix4f, 0.0f, -10.0f - radius / scale, 0.0f).method_39415(colorD.getRGB()).method_1344();
        class_286.method_43433((class_287.class_7433)bufferBuilder.method_1326());
        RenderSystem.disableBlend();
        matrices.method_22909();
    }
}

