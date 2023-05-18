//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.Hud.HudElements;

import com.mrzak34.thunderhack.Hud.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.event.events.*;
import com.mrzak34.thunderhack.util.*;
import java.awt.*;
import java.util.*;
import com.google.common.eventbus.*;
import com.mojang.blaze3d.systems.*;
import java.util.function.*;
import org.joml.*;
import net.minecraft.*;

public class Radar extends Hud
{
    public static NumberSetting angl;
    public static NumberSetting radius;
    public static NumberSetting scale;
    public static NumberSetting fixangle;
    
    public Radar() {
        super("Radar", 70, 20, ((int)Radar.radius.getValue() + 2) * 2, ((int)Radar.radius.getValue() + 2) * 2, false, true);
        this.addSettings(new Setting[] { Radar.angl, Radar.radius, Radar.fixangle, Radar.scale });
    }
    
    @Subscribe
    public void onRenderOverlay(final RenderOverlayEvent event) {
        for (final class_1657 player : Radar.mc.field_1687.method_18456()) {
            if (player == Radar.mc.field_1724) {
                continue;
            }
            final float angle = RotationUtil.lookAtEntity((class_1297)Radar.mc.field_1724, (class_1297)player).field_1343 - Radar.mc.field_1724.method_36454();
            drawArrow(event.getMatrices(), this.posX, this.posY, (float)Radar.radius.getValue(), (float)Radar.scale.getValue(), angle, new Color(0, 0, 255).brighter(), new Color(255, 0, 255).brighter(), new Color(130, 0, 255).brighter());
        }
    }
    
    public static void drawArrow(final class_4587 matrices, final int x, final int y, final float radius, final float scale, final float angle, final Color colorR, final Color colorL, final Color colorD) {
        matrices.method_22903();
        matrices.method_46416((float)x, (float)y, 0.0f);
        matrices.method_22905(scale, scale, scale);
        matrices.method_22907(class_7833.field_40718.rotationDegrees(angle));
        final Matrix4f matrix4f = matrices.method_23760().method_23761();
        final class_287 bufferBuilder = class_289.method_1348().method_1349();
        RenderSystem.enableBlend();
        RenderSystem.setShader((Supplier)class_757::method_34540);
        bufferBuilder.method_1328(class_293.class_5596.field_27381, class_290.field_1576);
        bufferBuilder.method_22918(matrix4f, 0.0f, -30.0f - radius / scale, 0.0f).method_39415(colorR.getRGB()).method_1344();
        bufferBuilder.method_22918(matrix4f, 0.0f, -10.0f - radius / scale, 0.0f).method_39415(colorR.getRGB()).method_1344();
        bufferBuilder.method_22918(matrix4f, 10.0f, -5.0f - radius / scale, 0.0f).method_39415(colorR.getRGB()).method_1344();
        class_286.method_43433(bufferBuilder.method_1326());
        bufferBuilder.method_1328(class_293.class_5596.field_27381, class_290.field_1576);
        bufferBuilder.method_22918(matrix4f, -10.0f, -5.0f - radius / scale, 0.0f).method_39415(colorL.getRGB()).method_1344();
        bufferBuilder.method_22918(matrix4f, 0.0f, -10.0f - radius / scale, 0.0f).method_39415(colorL.getRGB()).method_1344();
        bufferBuilder.method_22918(matrix4f, 0.0f, -30.0f - radius / scale, 0.0f).method_39415(colorL.getRGB()).method_1344();
        class_286.method_43433(bufferBuilder.method_1326());
        bufferBuilder.method_1328(class_293.class_5596.field_27382, class_290.field_1576);
        bufferBuilder.method_22918(matrix4f, -10.0f, -5.0f - radius / scale, 0.0f).method_39415(colorD.getRGB()).method_1344();
        bufferBuilder.method_22918(matrix4f, 0.0f, 0.0f - radius / scale, 0.0f).method_39415(colorD.getRGB()).method_1344();
        bufferBuilder.method_22918(matrix4f, 10.0f, -5.0f - radius / scale, 0.0f).method_39415(colorD.getRGB()).method_1344();
        bufferBuilder.method_22918(matrix4f, 0.0f, -10.0f - radius / scale, 0.0f).method_39415(colorD.getRGB()).method_1344();
        class_286.method_43433(bufferBuilder.method_1326());
        RenderSystem.disableBlend();
        matrices.method_22909();
    }
    
    static {
        Radar.angl = new NumberSetting("angle", 0.0f, 0.0f, 360.0f, false);
        Radar.radius = new NumberSetting("radius", 30.0f, 0.0f, 50.0f, false);
        Radar.scale = new NumberSetting("scale", 0.5f, 1.0f, 15.0f, false);
        Radar.fixangle = new NumberSetting("fix angle", 51.0f, 0.0f, 360.0f, false);
    }
}
