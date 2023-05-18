/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_310
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_437
 *  net.minecraft.class_4587
 */
package com.mrzak34.thunderhack.ui.click;

import com.mrzak34.thunderhack.Hud.Hud;
import com.mrzak34.thunderhack.Hud.HudManager;
import com.mrzak34.thunderhack.module.Module;
import java.awt.Color;
import java.util.Objects;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_4587;

public class HudEditor
extends class_437 {
    public static class_310 mc = class_310.method_1551();
    int oldposX;
    int oldposY;

    public HudEditor() {
        super(class_2561.method_30163((String)"Thunder Hud Gui"));
    }

    public void method_25394(class_4587 matrices, int mouseX, int mouseY, float partialTicks) {
        class_327 tr = HudEditor.mc.field_1772;
        Objects.requireNonNull(HudEditor.mc.field_1772);
        int fontPosY = 7 - 9 / 2;
        if (Module.fullNullCheck()) {
            this.method_25420(matrices);
        }
        for (Hud hud : HudManager.huds) {
            if (hud.mouseClicked) {
                hud.posX += mouseX - this.oldposX;
                hud.posY += mouseY - this.oldposY;
            }
            class_332.method_25294((class_4587)matrices, (int)hud.posX, (int)hud.posY, (int)(hud.posX + hud.width), (int)(hud.posY + hud.height), (int)(hud.mouseClicked ? new Color(0, 255, 0).getRGB() : new Color(70, 70, 70).getRGB()));
        }
        this.oldposX = mouseX;
        this.oldposY = mouseY;
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        for (Hud hud : HudManager.huds) {
            if (!(mouseX >= (double)hud.posX) || !(mouseX <= (double)(hud.posX + this.field_22789)) || !(mouseY >= (double)hud.posY) || !(mouseY <= (double)(hud.posY + this.field_22790))) continue;
            if (button == 0) {
                hud.mouseClicked = true;
            }
            if (button != 1) continue;
            if (hud.showSettings) {
                hud.showSettings = false;
                continue;
            }
            hud.showSettings = true;
        }
        return super.method_25402(mouseX, mouseY, button);
    }

    public boolean method_25406(double mouseX, double mouseY, int button) {
        for (Hud hud : HudManager.huds) {
            hud.mouseClicked = false;
        }
        return super.method_25406(mouseX, mouseY, button);
    }
}

