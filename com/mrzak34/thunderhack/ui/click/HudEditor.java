//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.ui.click;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.Hud.*;
import java.awt.*;
import net.minecraft.*;
import java.util.*;

public class HudEditor extends class_437
{
    public static class_310 mc;
    int oldposX;
    int oldposY;
    
    public HudEditor() {
        super(class_2561.method_30163("Thunder Hud Gui"));
    }
    
    public void method_25394(final class_4587 matrices, final int mouseX, final int mouseY, final float partialTicks) {
        final class_327 tr = HudEditor.mc.field_1772;
        final int n = 7;
        Objects.requireNonNull(HudEditor.mc.field_1772);
        final int fontPosY = n - 9 / 2;
        if (Module.fullNullCheck()) {
            this.method_25420(matrices);
        }
        for (final Hud hud : HudManager.huds) {
            if (hud.mouseClicked) {
                final Hud hud2 = hud;
                hud2.posX += mouseX - this.oldposX;
                final Hud hud3 = hud;
                hud3.posY += mouseY - this.oldposY;
            }
            class_332.method_25294(matrices, hud.posX, hud.posY, hud.posX + hud.width, hud.posY + hud.height, hud.mouseClicked ? new Color(0, 255, 0).getRGB() : new Color(70, 70, 70).getRGB());
        }
        this.oldposX = mouseX;
        this.oldposY = mouseY;
    }
    
    public boolean method_25402(final double mouseX, final double mouseY, final int button) {
        for (final Hud hud : HudManager.huds) {
            if (mouseX >= hud.posX && mouseX <= hud.posX + this.field_22789 && mouseY >= hud.posY && mouseY <= hud.posY + this.field_22790) {
                if (button == 0) {
                    hud.mouseClicked = true;
                }
                if (button != 1) {
                    continue;
                }
                if (hud.showSettings) {
                    hud.showSettings = false;
                }
                else {
                    hud.showSettings = true;
                }
            }
        }
        return super.method_25402(mouseX, mouseY, button);
    }
    
    public boolean method_25406(final double mouseX, final double mouseY, final int button) {
        for (final Hud hud : HudManager.huds) {
            hud.mouseClicked = false;
        }
        return super.method_25406(mouseX, mouseY, button);
    }
    
    static {
        HudEditor.mc = class_310.method_1551();
    }
}
