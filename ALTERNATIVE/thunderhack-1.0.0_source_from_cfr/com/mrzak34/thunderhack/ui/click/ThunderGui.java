/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_437
 *  net.minecraft.class_4587
 *  net.minecraft.class_7833
 */
package com.mrzak34.thunderhack.ui.click;

import com.mrzak34.thunderhack.manager.ModuleManager;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.module.modules.client.ClickGUIModule;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.KeybindSetting;
import com.mrzak34.thunderhack.settings.ModeSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.settings.ParentSetting;
import com.mrzak34.thunderhack.settings.Setting;
import com.mrzak34.thunderhack.util.KeyUtil;
import com.mrzak34.thunderhack.util.RenderUtil;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Objects;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_7833;

public class ThunderGui
extends class_437 {
    public static final class_2960 lightning = new class_2960("textures/lightninglogo.png");
    public static final class_2960 radar = new class_2960("textures/newradar.png");
    public static final class_2960 arrow = new class_2960("textures/arrow.png");
    public static class_310 mc = class_310.method_1551();
    int oldposX;
    int oldposY;

    public ThunderGui() {
        super(class_2561.method_30163((String)"Thunder Gui"));
    }

    public void method_25394(class_4587 matrices, int mouseX, int mouseY, float partialTicks) {
        class_327 tr = ThunderGui.mc.field_1772;
        Objects.requireNonNull(ThunderGui.mc.field_1772);
        int fontPosY = 7 - 9 / 2;
        if (ClickGUIModule.fullNullCheck()) {
            this.method_25420(matrices);
        }
        if (ClickGUIModule.image.isEnabled()) {
            class_2960 image = null;
            switch (ClickGUIModule.mode.getMode()) {
                case "anime": {
                    image = new class_2960("textures/image.png");
                    break;
                }
                case "anime2": {
                    image = new class_2960("textures/image2.png");
                    break;
                }
                case "logo": {
                    image = new class_2960("textures/logo.png");
                    break;
                }
                case "hentai": {
                    image = new class_2960("textures/ssertinop.png");
                }
            }
            if (image != null) {
                RenderUtil.drawTexture(image, (int)(ClickGUIModule.imposX.getValue() + (double)(mouseX / 100)), (int)(ClickGUIModule.imposY.getValue() + (double)(mouseY / 100)), (int)ClickGUIModule.scale.getValue(), (int)ClickGUIModule.scale.getValue(), matrices);
            }
        }
        for (Category category : Category.values()) {
            if (category.mouseClicked) {
                category.posX += mouseX - this.oldposX;
                category.posY += mouseY - this.oldposY;
            }
            class_332.method_25294((class_4587)matrices, (int)category.posX, (int)category.posY, (int)(category.posX + 102), (int)(category.posY + 13), (int)new Color((int)ClickGUIModule.categoryRed.getValue(), (int)ClickGUIModule.categoryGreen.getValue(), (int)ClickGUIModule.categoryBlue.getValue(), (int)ClickGUIModule.categoryAlpha.getValue()).getRGB());
            class_332.method_25303((class_4587)matrices, (class_327)tr, (String)category.name, (int)(ClickGUIModule.icicle.getMode().equals("all") || ClickGUIModule.icicle.getMode().equals("categories") ? category.posX + ThunderGui.icicleFontPos(category.name) : category.posX + 2), (int)(category.posY + fontPosY), (int)-1);
            int posX = category.posX;
            int posY = category.posY + 13;
            for (Module m : ModuleManager.modules) {
                if (m.getCategory() != category || !category.showModules) continue;
                class_332.method_25294((class_4587)matrices, (int)posX, (int)posY, (int)(posX + 102), (int)(posY + 15), (int)new Color((int)ClickGUIModule.backgroundRed.getValue(), (int)ClickGUIModule.backgroundGreen.getValue(), (int)ClickGUIModule.backgroundBlue.getValue(), (int)ClickGUIModule.backgroundAlpha.getValue()).getRGB());
                if (m.isToggled()) {
                    class_332.method_25294((class_4587)matrices, (int)(posX + 1), (int)(posY + 1), (int)(posX + 101), (int)(posY + 14), (int)new Color((int)ClickGUIModule.enabledRed.getValue(), (int)ClickGUIModule.enabledGreen.getValue(), (int)ClickGUIModule.enabledBlue.getValue(), (int)ClickGUIModule.enabledAlpha.getValue()).getRGB());
                } else {
                    class_332.method_25294((class_4587)matrices, (int)(posX + 1), (int)(posY + 1), (int)(posX + 101), (int)(posY + 14), (int)new Color((int)ClickGUIModule.moduleRed.getValue(), (int)ClickGUIModule.moduleGreen.getValue(), (int)ClickGUIModule.moduleBlue.getValue(), (int)ClickGUIModule.moduleAlpha.getValue()).getRGB());
                }
                class_332.method_25303((class_4587)matrices, (class_327)tr, (String)m.getName(), (int)(ClickGUIModule.icicle.getMode().equals("all") || ClickGUIModule.icicle.getMode().equals("modules") ? posX + ThunderGui.icicleFontPos(m.getName()) : posX + 4), (int)(posY + fontPosY), (int)-1);
                if (m.showSettings) {
                    m.progress = (int)((double)m.progress + ClickGUIModule.animationspeed.getValue());
                }
                ThunderGui.drawRadar(posX + 88, posY + 1, matrices, m);
                RenderUtil.drawTexture(lightning, posX + 88, posY + 1, 12, 12, matrices);
                posY += 15;
                if (m.settings.isEmpty()) continue;
                for (Setting setting1 : m.settings) {
                    if (setting1 instanceof BooleanSetting && m.showSettings) {
                        BooleanSetting bool = (BooleanSetting)setting1;
                        class_332.method_25294((class_4587)matrices, (int)posX, (int)posY, (int)(posX + 1), (int)(posY + 15), (int)new Color((int)ClickGUIModule.enabledRed.getValue(), (int)ClickGUIModule.enabledGreen.getValue(), (int)ClickGUIModule.enabledBlue.getValue(), (int)ClickGUIModule.enabledAlpha.getValue()).getRGB());
                        class_332.method_25294((class_4587)matrices, (int)(posX + 1), (int)posY, (int)(posX + 102), (int)(posY + 15), (int)new Color((int)ClickGUIModule.backgroundRed.getValue(), (int)ClickGUIModule.backgroundGreen.getValue(), (int)ClickGUIModule.backgroundBlue.getValue(), (int)ClickGUIModule.backgroundAlpha.getValue()).getRGB());
                        class_332.method_25294((class_4587)matrices, (int)(posX + 2), (int)(posY + 1), (int)(posX + 101), (int)(posY + 14), (int)new Color((int)ClickGUIModule.moduleRed.getValue(), (int)ClickGUIModule.moduleGreen.getValue(), (int)ClickGUIModule.moduleBlue.getValue(), (int)ClickGUIModule.moduleAlpha.getValue()).getRGB());
                        class_332.method_25303((class_4587)matrices, (class_327)tr, (String)bool.name, (int)(posX + 4), (int)(posY + fontPosY), (int)(bool.isEnabled() ? new Color((int)ClickGUIModule.enabledRed.getValue(), (int)ClickGUIModule.enabledGreen.getValue(), (int)ClickGUIModule.enabledBlue.getValue()).getRGB() : -1));
                        if (mouseX >= posX && mouseX <= posX + 100 && mouseY >= posY && mouseY <= posY + 13) {
                            ThunderGui.boolRender(true, posX, posY, bool, matrices);
                        } else {
                            ThunderGui.boolRender(false, posX, posY, bool, matrices);
                        }
                        posY += 15;
                    }
                    if (setting1 instanceof NumberSetting && m.showSettings) {
                        NumberSetting number = (NumberSetting)setting1;
                        int value = (int)((number.value - number.minimum) / (number.maximum - number.minimum) * 95.0f);
                        class_332.method_25294((class_4587)matrices, (int)posX, (int)posY, (int)(posX + 1), (int)(posY + 17), (int)new Color((int)ClickGUIModule.enabledRed.getValue(), (int)ClickGUIModule.enabledGreen.getValue(), (int)ClickGUIModule.enabledBlue.getValue(), (int)ClickGUIModule.enabledAlpha.getValue()).getRGB());
                        class_332.method_25294((class_4587)matrices, (int)(posX + 1), (int)posY, (int)(posX + 102), (int)(posY + 17), (int)new Color((int)ClickGUIModule.backgroundRed.getValue(), (int)ClickGUIModule.backgroundGreen.getValue(), (int)ClickGUIModule.backgroundBlue.getValue(), (int)ClickGUIModule.backgroundAlpha.getValue()).getRGB());
                        class_332.method_25294((class_4587)matrices, (int)(posX + 2), (int)(posY + 1), (int)(posX + 101), (int)(posY + 16), (int)new Color((int)ClickGUIModule.moduleRed.getValue(), (int)ClickGUIModule.moduleGreen.getValue(), (int)ClickGUIModule.moduleBlue.getValue(), (int)ClickGUIModule.moduleAlpha.getValue()).getRGB());
                        class_332.method_25303((class_4587)matrices, (class_327)tr, (String)(number.name + ":"), (int)(posX + 4), (int)(posY + 2), (int)-1);
                        class_332.method_25294((class_4587)matrices, (int)(posX + 96 - ThunderGui.mc.field_1772.method_1727(number.editing ? number.shadowValue : String.valueOf(number.fraction ? Float.valueOf(number.value) : new DecimalFormat("#0").format(number.value)))), (int)(posY + 2), (int)(posX + 98), (int)(posY + 11), (int)(number.editing ? new Color((int)ClickGUIModule.enabledRed.getValue(), (int)ClickGUIModule.enabledGreen.getValue(), (int)ClickGUIModule.enabledBlue.getValue(), (int)ClickGUIModule.enabledAlpha.getValue()).getRGB() : new Color(141, 141, 141, 200).getRGB()));
                        class_332.method_25303((class_4587)matrices, (class_327)tr, (String)(number.editing ? number.shadowValue : (number.fraction ? String.valueOf(number.value) : new DecimalFormat("#0").format(number.value))), (int)(posX + 97 - ThunderGui.mc.field_1772.method_1727(number.editing ? number.shadowValue : String.valueOf(number.fraction ? Float.valueOf(number.value) : new DecimalFormat("#0").format(number.value)))), (int)(posY + 2), (int)-1);
                        Objects.requireNonNull(ThunderGui.mc.field_1772);
                        Objects.requireNonNull(ThunderGui.mc.field_1772);
                        class_332.method_25294((class_4587)matrices, (int)(posX + 4), (int)(posY + 9 + 3), (int)(posX + 99), (int)(posY + 9 + 5), (int)new Color(30, 30, 30).getRGB());
                        Objects.requireNonNull(ThunderGui.mc.field_1772);
                        Objects.requireNonNull(ThunderGui.mc.field_1772);
                        class_332.method_25294((class_4587)matrices, (int)(posX + 4), (int)(posY + 9 + 3), (int)(posX + value), (int)(posY + 9 + 5), (int)new Color(0, 255, 0).getRGB());
                        if (mouseX >= posX + 2 && mouseX <= posX + 101 && mouseY >= posY + 1 && mouseY <= posY + 16) {
                            if (number.mouseClicked) {
                                number.value = number.fraction ? ThunderGui.parseFloat((float)(mouseX - posX - 2) * (number.maximum - number.minimum) / 99.0f + number.minimum).floatValue() : (float)((int)Float.parseFloat(new DecimalFormat("#0").format((float)(mouseX - posX - 2) * (number.maximum - number.minimum) / 99.0f + number.minimum)));
                            }
                        } else {
                            number.mouseClicked = false;
                        }
                        posY += 17;
                    }
                    if (setting1 instanceof ModeSetting && m.showSettings) {
                        ModeSetting mode = (ModeSetting)setting1;
                        class_332.method_25294((class_4587)matrices, (int)posX, (int)posY, (int)(posX + 1), (int)(posY + 15), (int)new Color((int)ClickGUIModule.enabledRed.getValue(), (int)ClickGUIModule.enabledGreen.getValue(), (int)ClickGUIModule.enabledBlue.getValue(), (int)ClickGUIModule.enabledAlpha.getValue()).getRGB());
                        class_332.method_25294((class_4587)matrices, (int)(posX + 1), (int)posY, (int)(posX + 102), (int)(posY + 15), (int)new Color((int)ClickGUIModule.backgroundRed.getValue(), (int)ClickGUIModule.backgroundGreen.getValue(), (int)ClickGUIModule.backgroundBlue.getValue(), (int)ClickGUIModule.backgroundAlpha.getValue()).getRGB());
                        class_332.method_25294((class_4587)matrices, (int)(posX + 2), (int)(posY + 1), (int)(posX + 101), (int)(posY + 14), (int)new Color((int)ClickGUIModule.moduleRed.getValue(), (int)ClickGUIModule.moduleGreen.getValue(), (int)ClickGUIModule.moduleBlue.getValue(), (int)ClickGUIModule.moduleAlpha.getValue()).getRGB());
                        class_332.method_25303((class_4587)matrices, (class_327)tr, (String)(mode.name + ":" + mode.getMode()), (int)(posX + 4), (int)(posY + fontPosY), (int)-1);
                        posY += 15;
                    }
                    if (setting1 instanceof KeybindSetting && m.showSettings) {
                        KeybindSetting keybind = (KeybindSetting)setting1;
                        class_332.method_25294((class_4587)matrices, (int)posX, (int)posY, (int)(posX + 1), (int)(posY + 15), (int)new Color((int)ClickGUIModule.enabledRed.getValue(), (int)ClickGUIModule.enabledGreen.getValue(), (int)ClickGUIModule.enabledBlue.getValue(), (int)ClickGUIModule.enabledAlpha.getValue()).getRGB());
                        class_332.method_25294((class_4587)matrices, (int)(posX + 1), (int)posY, (int)(posX + 102), (int)(posY + 15), (int)new Color((int)ClickGUIModule.backgroundRed.getValue(), (int)ClickGUIModule.backgroundGreen.getValue(), (int)ClickGUIModule.backgroundBlue.getValue(), (int)ClickGUIModule.backgroundAlpha.getValue()).getRGB());
                        class_332.method_25294((class_4587)matrices, (int)(posX + 2), (int)(posY + 1), (int)(posX + 101), (int)(posY + 14), (int)new Color((int)ClickGUIModule.moduleRed.getValue(), (int)ClickGUIModule.moduleGreen.getValue(), (int)ClickGUIModule.moduleBlue.getValue(), (int)ClickGUIModule.moduleAlpha.getValue()).getRGB());
                        class_332.method_25303((class_4587)matrices, (class_327)tr, (String)"hold:", (int)(posX + 91 - ThunderGui.mc.field_1772.method_1727("hold:")), (int)(posY + fontPosY), (int)-1);
                        class_332.method_25294((class_4587)matrices, (int)(posX + 92), (int)(posY + 3), (int)(posX + 99), (int)(posY + 10), (int)new Color(141, 141, 141, 200).getRGB());
                        if (keybind.keyDown) {
                            class_332.method_25294((class_4587)matrices, (int)(posX + 92), (int)(posY + 3), (int)(posX + 99), (int)(posY + 10), (int)new Color(0, 255, 0).getRGB());
                        }
                        class_332.method_25303((class_4587)matrices, (class_327)tr, (String)(keybind.binding ? "..." : KeyUtil.getKeyName(keybind.code)), (int)(posX + 4), (int)(posY + fontPosY), (int)-1);
                        posY += 15;
                    }
                    if (!(setting1 instanceof ParentSetting) || !m.showSettings) continue;
                    ParentSetting par = (ParentSetting)setting1;
                    class_332.method_25294((class_4587)matrices, (int)posX, (int)posY, (int)(posX + 1), (int)(posY + 15), (int)new Color((int)ClickGUIModule.enabledRed.getValue(), (int)ClickGUIModule.enabledGreen.getValue(), (int)ClickGUIModule.enabledBlue.getValue(), (int)ClickGUIModule.enabledAlpha.getValue()).getRGB());
                    class_332.method_25294((class_4587)matrices, (int)(posX + 1), (int)posY, (int)(posX + 102), (int)(posY + 15), (int)new Color((int)ClickGUIModule.backgroundRed.getValue(), (int)ClickGUIModule.backgroundGreen.getValue(), (int)ClickGUIModule.backgroundBlue.getValue(), (int)ClickGUIModule.backgroundAlpha.getValue()).getRGB());
                    class_332.method_25294((class_4587)matrices, (int)(posX + 2), (int)(posY + 1), (int)(posX + 101), (int)(posY + 14), (int)new Color((int)ClickGUIModule.moduleRed.getValue(), (int)ClickGUIModule.moduleGreen.getValue(), (int)ClickGUIModule.moduleBlue.getValue(), (int)ClickGUIModule.moduleAlpha.getValue()).getRGB());
                    if (par.bool) {
                        class_332.method_25294((class_4587)matrices, (int)(posX + 92), (int)(posY + 3), (int)(posX + 99), (int)(posY + 10), (int)new Color(141, 141, 141, 200).getRGB());
                        if (par.isEnabled()) {
                            class_332.method_25294((class_4587)matrices, (int)(posX + 92), (int)(posY + 3), (int)(posX + 99), (int)(posY + 10), (int)new Color(0, 255, 0).getRGB());
                        }
                    }
                    ThunderGui.parRender(matrices, posX, posY, par);
                    class_332.method_25303((class_4587)matrices, (class_327)tr, (String)par.name, (int)(posX + 13), (int)(posY + fontPosY), (int)-1);
                    posY += 15;
                    if (!par.showSettings) continue;
                    for (Setting setting2 : par.settings) {
                        if (setting2 instanceof BooleanSetting && m.showSettings) {
                            BooleanSetting bool = (BooleanSetting)setting2;
                            class_332.method_25294((class_4587)matrices, (int)posX, (int)posY, (int)(posX + 1), (int)(posY + 15), (int)new Color((int)ClickGUIModule.enabledRed.getValue(), (int)ClickGUIModule.enabledGreen.getValue(), (int)ClickGUIModule.enabledBlue.getValue(), (int)ClickGUIModule.enabledAlpha.getValue()).getRGB());
                            class_332.method_25294((class_4587)matrices, (int)(posX + 1), (int)posY, (int)(posX + 102), (int)(posY + 15), (int)new Color((int)ClickGUIModule.backgroundRed.getValue(), (int)ClickGUIModule.backgroundGreen.getValue(), (int)ClickGUIModule.backgroundBlue.getValue(), (int)ClickGUIModule.backgroundAlpha.getValue()).getRGB());
                            class_332.method_25294((class_4587)matrices, (int)(posX + 2), (int)posY, (int)(posX + 3), (int)(posY + 15), (int)new Color((int)ClickGUIModule.enabledRed.getValue(), (int)ClickGUIModule.enabledGreen.getValue(), (int)ClickGUIModule.enabledBlue.getValue(), (int)ClickGUIModule.enabledAlpha.getValue()).getRGB());
                            class_332.method_25294((class_4587)matrices, (int)(posX + 4), (int)(posY + 1), (int)(posX + 101), (int)(posY + 14), (int)new Color((int)ClickGUIModule.moduleRed.getValue(), (int)ClickGUIModule.moduleGreen.getValue(), (int)ClickGUIModule.moduleBlue.getValue(), (int)ClickGUIModule.moduleAlpha.getValue()).getRGB());
                            class_332.method_25303((class_4587)matrices, (class_327)tr, (String)bool.name, (int)(posX + 6), (int)(posY + fontPosY), (int)(bool.isEnabled() ? new Color((int)ClickGUIModule.enabledRed.getValue(), (int)ClickGUIModule.enabledGreen.getValue(), (int)ClickGUIModule.enabledBlue.getValue()).getRGB() : -1));
                            if (mouseX >= posX && mouseX <= posX + 100 && mouseY >= posY && mouseY <= posY + 13) {
                                ThunderGui.boolRender(true, posX, posY, bool, matrices);
                            } else {
                                ThunderGui.boolRender(false, posX, posY, bool, matrices);
                            }
                            posY += 15;
                        }
                        if (setting2 instanceof NumberSetting && m.showSettings) {
                            NumberSetting number = (NumberSetting)setting2;
                            int value = (int)((number.value - number.minimum) / (number.maximum - number.minimum) * 95.0f);
                            class_332.method_25294((class_4587)matrices, (int)posX, (int)posY, (int)(posX + 1), (int)(posY + 17), (int)new Color((int)ClickGUIModule.enabledRed.getValue(), (int)ClickGUIModule.enabledGreen.getValue(), (int)ClickGUIModule.enabledBlue.getValue(), (int)ClickGUIModule.enabledAlpha.getValue()).getRGB());
                            class_332.method_25294((class_4587)matrices, (int)(posX + 1), (int)posY, (int)(posX + 102), (int)(posY + 17), (int)new Color((int)ClickGUIModule.backgroundRed.getValue(), (int)ClickGUIModule.backgroundGreen.getValue(), (int)ClickGUIModule.backgroundBlue.getValue(), (int)ClickGUIModule.backgroundAlpha.getValue()).getRGB());
                            class_332.method_25294((class_4587)matrices, (int)(posX + 2), (int)(posY + 1), (int)(posX + 101), (int)(posY + 16), (int)new Color((int)ClickGUIModule.moduleRed.getValue(), (int)ClickGUIModule.moduleGreen.getValue(), (int)ClickGUIModule.moduleBlue.getValue(), (int)ClickGUIModule.moduleAlpha.getValue()).getRGB());
                            class_332.method_25303((class_4587)matrices, (class_327)tr, (String)(number.name + ":"), (int)(posX + 4), (int)(posY + 2), (int)-1);
                            class_332.method_25294((class_4587)matrices, (int)(posX + 96 - ThunderGui.mc.field_1772.method_1727(number.editing ? number.shadowValue : String.valueOf(number.fraction ? Float.valueOf(number.value) : new DecimalFormat("#0").format(number.value)))), (int)(posY + 2), (int)(posX + 98), (int)(posY + 11), (int)(number.editing ? new Color((int)ClickGUIModule.enabledRed.getValue(), (int)ClickGUIModule.enabledGreen.getValue(), (int)ClickGUIModule.enabledBlue.getValue(), (int)ClickGUIModule.enabledAlpha.getValue()).getRGB() : new Color(141, 141, 141, 200).getRGB()));
                            class_332.method_25303((class_4587)matrices, (class_327)tr, (String)(number.editing ? number.shadowValue : (number.fraction ? String.valueOf(number.value) : new DecimalFormat("#0").format(number.value))), (int)(posX + 97 - ThunderGui.mc.field_1772.method_1727(number.editing ? number.shadowValue : String.valueOf(number.fraction ? Float.valueOf(number.value) : new DecimalFormat("#0").format(number.value)))), (int)(posY + 2), (int)-1);
                            Objects.requireNonNull(ThunderGui.mc.field_1772);
                            Objects.requireNonNull(ThunderGui.mc.field_1772);
                            class_332.method_25294((class_4587)matrices, (int)(posX + 4), (int)(posY + 9 + 3), (int)(posX + 99), (int)(posY + 9 + 5), (int)new Color(30, 30, 30).getRGB());
                            Objects.requireNonNull(ThunderGui.mc.field_1772);
                            Objects.requireNonNull(ThunderGui.mc.field_1772);
                            class_332.method_25294((class_4587)matrices, (int)(posX + 4), (int)(posY + 9 + 3), (int)(posX + value), (int)(posY + 9 + 5), (int)new Color(0, 255, 0).getRGB());
                            if (mouseX >= posX + 2 && mouseX <= posX + 101 && mouseY >= posY + 1 && mouseY <= posY + 16) {
                                if (number.mouseClicked) {
                                    number.value = number.fraction ? ThunderGui.parseFloat((float)(mouseX - posX - 2) * (number.maximum - number.minimum) / 99.0f + number.minimum).floatValue() : (float)((int)Float.parseFloat(new DecimalFormat("#0").format((float)(mouseX - posX - 2) * (number.maximum - number.minimum) / 99.0f + number.minimum)));
                                }
                            } else {
                                number.mouseClicked = false;
                            }
                            posY += 17;
                        }
                        if (setting2 instanceof ModeSetting && m.showSettings) {
                            ModeSetting mode = (ModeSetting)setting2;
                            class_332.method_25294((class_4587)matrices, (int)posX, (int)posY, (int)(posX + 1), (int)(posY + 15), (int)new Color((int)ClickGUIModule.enabledRed.getValue(), (int)ClickGUIModule.enabledGreen.getValue(), (int)ClickGUIModule.enabledBlue.getValue(), (int)ClickGUIModule.enabledAlpha.getValue()).getRGB());
                            class_332.method_25294((class_4587)matrices, (int)(posX + 1), (int)posY, (int)(posX + 102), (int)(posY + 15), (int)new Color((int)ClickGUIModule.backgroundRed.getValue(), (int)ClickGUIModule.backgroundGreen.getValue(), (int)ClickGUIModule.backgroundBlue.getValue(), (int)ClickGUIModule.backgroundAlpha.getValue()).getRGB());
                            class_332.method_25294((class_4587)matrices, (int)(posX + 2), (int)posY, (int)(posX + 3), (int)(posY + 15), (int)new Color((int)ClickGUIModule.enabledRed.getValue(), (int)ClickGUIModule.enabledGreen.getValue(), (int)ClickGUIModule.enabledBlue.getValue(), (int)ClickGUIModule.enabledAlpha.getValue()).getRGB());
                            class_332.method_25294((class_4587)matrices, (int)(posX + 4), (int)(posY + 1), (int)(posX + 101), (int)(posY + 14), (int)new Color((int)ClickGUIModule.moduleRed.getValue(), (int)ClickGUIModule.moduleGreen.getValue(), (int)ClickGUIModule.moduleBlue.getValue(), (int)ClickGUIModule.moduleAlpha.getValue()).getRGB());
                            class_332.method_25303((class_4587)matrices, (class_327)tr, (String)(mode.name + ":" + mode.getMode()), (int)(posX + 6), (int)(posY + fontPosY), (int)-1);
                            posY += 15;
                        }
                        if (!(setting2 instanceof KeybindSetting) || !m.showSettings) continue;
                        KeybindSetting keybind = (KeybindSetting)setting2;
                        class_332.method_25294((class_4587)matrices, (int)posX, (int)posY, (int)(posX + 1), (int)(posY + 15), (int)new Color((int)ClickGUIModule.enabledRed.getValue(), (int)ClickGUIModule.enabledGreen.getValue(), (int)ClickGUIModule.enabledBlue.getValue(), (int)ClickGUIModule.enabledAlpha.getValue()).getRGB());
                        class_332.method_25294((class_4587)matrices, (int)(posX + 1), (int)posY, (int)(posX + 102), (int)(posY + 15), (int)new Color((int)ClickGUIModule.backgroundRed.getValue(), (int)ClickGUIModule.backgroundGreen.getValue(), (int)ClickGUIModule.backgroundBlue.getValue(), (int)ClickGUIModule.backgroundAlpha.getValue()).getRGB());
                        class_332.method_25294((class_4587)matrices, (int)(posX + 2), (int)posY, (int)(posX + 3), (int)(posY + 15), (int)new Color((int)ClickGUIModule.enabledRed.getValue(), (int)ClickGUIModule.enabledGreen.getValue(), (int)ClickGUIModule.enabledBlue.getValue(), (int)ClickGUIModule.enabledAlpha.getValue()).getRGB());
                        class_332.method_25294((class_4587)matrices, (int)(posX + 2), (int)(posY + 1), (int)(posX + 101), (int)(posY + 14), (int)new Color((int)ClickGUIModule.moduleRed.getValue(), (int)ClickGUIModule.moduleGreen.getValue(), (int)ClickGUIModule.moduleBlue.getValue(), (int)ClickGUIModule.moduleAlpha.getValue()).getRGB());
                        class_332.method_25303((class_4587)matrices, (class_327)tr, (String)"hold:", (int)(posX + 91 - ThunderGui.mc.field_1772.method_1727("hold:")), (int)(posY + fontPosY), (int)-1);
                        class_332.method_25294((class_4587)matrices, (int)(posX + 92), (int)(posY + 3), (int)(posX + 99), (int)(posY + 10), (int)new Color(141, 141, 141, 200).getRGB());
                        if (keybind.keyDown) {
                            class_332.method_25294((class_4587)matrices, (int)(posX + 92), (int)(posY + 3), (int)(posX + 99), (int)(posY + 10), (int)new Color(0, 255, 0).getRGB());
                        }
                        class_332.method_25303((class_4587)matrices, (class_327)tr, (String)(keybind.binding ? "..." : KeyUtil.getKeyName(keybind.code)), (int)(posX + 6), (int)(posY + fontPosY), (int)-1);
                        posY += 15;
                    }
                }
            }
        }
        this.oldposX = mouseX;
        this.oldposY = mouseY;
    }

    public static void drawRadar(int x, int y, class_4587 matrices, Module module) {
        matrices.method_22903();
        matrices.method_46416((float)(x + 6), (float)(y + 6), 0.0f);
        matrices.method_22907(class_7833.field_40718.rotationDegrees((float)module.progress));
        RenderUtil.drawTexture(radar, -6, -6, 12, 12, matrices);
        matrices.method_22909();
    }

    public static void parRender(class_4587 matrices, int posX, int posY, ParentSetting setting) {
        if (setting.showSettings) {
            if (setting.progress < 90) {
                setting.progress += 5;
            }
        } else if (setting.progress > 0) {
            setting.progress -= 5;
        }
        matrices.method_22903();
        matrices.method_46416((float)(posX + 8), (float)(posY + 7), 0.0f);
        matrices.method_22907(class_7833.field_40718.rotationDegrees((float)setting.progress));
        RenderUtil.drawTexture(arrow, -4, -4, 8, 8, matrices);
        matrices.method_22909();
    }

    public static void boolRender(boolean intoMouse, int posX, int posY, BooleanSetting setting, class_4587 matrices) {
        boolean intoMouse2 = false;
        if (intoMouse == intoMouse2) {
            setting.loadingTimer.reset();
        }
        if (!setting.loadingTimer.passedMs(150L)) {
            if (setting.enabled) {
                class_332.method_25294((class_4587)matrices, (int)((int)((long)(posX + 97) - setting.loadingTimer.getPassedTimeMs() / 15L)), (int)(posY + 2), (int)(posX + 97), (int)(posY + 11), (int)new Color(0, 255, 0).getRGB());
            } else {
                class_332.method_25294((class_4587)matrices, (int)((int)((long)(posX + 97) - setting.loadingTimer.getPassedTimeMs() / 15L)), (int)(posY + 2), (int)(posX + 97), (int)(posY + 11), (int)new Color(255, 0, 0).getRGB());
            }
        } else if (setting.enabled) {
            class_332.method_25294((class_4587)matrices, (int)(posX + 97 - ThunderGui.mc.field_1772.method_1727(setting.enabled ? "on" : "off") - 2), (int)(posY + 2), (int)(posX + 97), (int)(posY + 11), (int)new Color(0, 255, 0).getRGB());
        } else {
            class_332.method_25294((class_4587)matrices, (int)(posX + 97 - ThunderGui.mc.field_1772.method_1727(setting.enabled ? "on" : "off") - 2), (int)(posY + 2), (int)(posX + 97), (int)(posY + 11), (int)new Color(255, 0, 0).getRGB());
        }
        if (setting.loadingTimer.passedMs(150L)) {
            class_332.method_25303((class_4587)matrices, (class_327)ThunderGui.mc.field_1772, (String)(setting.enabled ? "on" : "off"), (int)(posX + 96 - ThunderGui.mc.field_1772.method_1727(setting.enabled ? "on" : "off")), (int)(posY + 3), (int)-1);
        }
        intoMouse2 = intoMouse;
    }

    public boolean method_25401(double mouseX, double mouseY, double amount) {
        for (Category category : Category.values()) {
            if (amount > 0.0) {
                category.posY = (int)((double)category.posY + ClickGUIModule.scrollspeed.getValue());
            }
            if (!(amount < 0.0)) continue;
            category.posY = (int)((double)category.posY - ClickGUIModule.scrollspeed.getValue());
        }
        return super.method_25401(mouseX, mouseY, amount);
    }

    public boolean method_25402(double mouseX, double mouseY, int button) {
        for (Category category : Category.values()) {
            if (mouseX >= (double)category.posX && mouseX <= (double)(category.posX + 100) && mouseY >= (double)category.posY && mouseY <= (double)(category.posY + 13)) {
                if (button == 0) {
                    category.mouseClicked = true;
                }
                if (button == 1) {
                    category.showModules = !category.showModules;
                }
            }
            int posX = category.posX;
            int posY = category.posY + 13;
            for (Module m : ModuleManager.modules) {
                if (m.getCategory() != category || !category.showModules) continue;
                if (mouseX >= (double)(posX + 1) && mouseX <= (double)(posX + 101) && mouseY >= (double)(posY + 1) && mouseY <= (double)(posY + 14)) {
                    if (button == 0) {
                        m.toggle();
                    }
                    if (button == 1) {
                        m.showSettings = !m.showSettings;
                    }
                }
                posY += 15;
                for (Setting setting1 : m.settings) {
                    block48: {
                        NumberSetting number;
                        block49: {
                            block50: {
                                if (setting1 instanceof BooleanSetting && m.showSettings) {
                                    BooleanSetting bool = (BooleanSetting)setting1;
                                    if (mouseX >= (double)(posX + 2) && mouseX <= (double)(posX + 101) && mouseY >= (double)(posY + 1) && mouseY <= (double)(posY + 14) && button == 0) {
                                        bool.toggle();
                                        bool.loadingTimer.reset();
                                    }
                                    posY += 15;
                                }
                                if (!(setting1 instanceof NumberSetting) || !m.showSettings) break block48;
                                number = (NumberSetting)setting1;
                                if (!(mouseX >= (double)(posX + 2)) || !(mouseX <= (double)(posX + 101)) || !(mouseY >= (double)(posY + 1)) || !(mouseY <= (double)(posY + 16))) break block49;
                                String string = number.editing ? number.shadowValue : String.valueOf(number.value);
                                if (!(mouseX >= (double)(posX + 96 - ThunderGui.mc.field_1772.method_1727(string))) || !(mouseX <= (double)(posX + 101)) || !(mouseY >= (double)(posY + 2))) break block50;
                                Objects.requireNonNull(ThunderGui.mc.field_1772);
                                if (mouseY <= (double)(posY + 2 + 9)) break block49;
                            }
                            if (button == 0) {
                                number.mouseClicked = true;
                            }
                        }
                        String string = number.editing ? number.shadowValue : String.valueOf(number.value);
                        if (mouseX >= (double)(posX + 96 - ThunderGui.mc.field_1772.method_1727(string)) && mouseX <= (double)(posX + 101) && mouseY >= (double)(posY + 2)) {
                            Objects.requireNonNull(ThunderGui.mc.field_1772);
                            if (mouseY <= (double)(posY + 2 + 9)) {
                                number.editing = true;
                                number.shadowValue = String.valueOf(number.value);
                            }
                        }
                        posY += 17;
                    }
                    if (setting1 instanceof ModeSetting && m.showSettings) {
                        ModeSetting mode = (ModeSetting)setting1;
                        if (mouseX >= (double)(posX + 2) && mouseX <= (double)(posX + 101) && mouseY >= (double)(posY + 1) && mouseY <= (double)(posY + 14)) {
                            if (button == 0) {
                                mode.cycle();
                            }
                            if (button == 1) {
                                mode.revcycle();
                            }
                        }
                        posY += 15;
                    }
                    if (setting1 instanceof KeybindSetting && m.showSettings) {
                        KeybindSetting keybind = (KeybindSetting)setting1;
                        if (mouseX >= (double)(posX + 2) && mouseX <= (double)(posX + 101) && mouseY >= (double)(posY + 1) && mouseY <= (double)(posY + 14) && (!(mouseX >= (double)(posX + 92)) || !(mouseX <= (double)(posX + 99)) || !(mouseY >= (double)(posY + 3)) || !(mouseY <= (double)(posY + 10)))) {
                            if (button == 0) {
                                keybind.binding = true;
                            } else if (button == 1) {
                                keybind.code = 0;
                                keybind.binding = false;
                            }
                        }
                        if (mouseX >= (double)(posX + 92) && mouseX <= (double)(posX + 99) && mouseY >= (double)(posY + 3) && mouseY <= (double)(posY + 10) && button == 0) {
                            keybind.keyDown = !keybind.keyDown;
                        }
                        posY += 15;
                    }
                    if (!(setting1 instanceof ParentSetting) || !m.showSettings) continue;
                    ParentSetting par = (ParentSetting)setting1;
                    if (mouseX >= (double)(posX + 2) && mouseX <= (double)(posX + 101) && mouseY >= (double)(posY + 1) && mouseY <= (double)(posY + 14)) {
                        if (button == 1) {
                            par.showSettings = !par.showSettings;
                        }
                        if (par.bool && button == 0) {
                            par.toggle();
                        }
                    }
                    posY += 15;
                    if (!par.showSettings) continue;
                    for (Setting setting2 : par.settings) {
                        block51: {
                            NumberSetting number;
                            block52: {
                                block53: {
                                    if (setting2 instanceof BooleanSetting && m.showSettings) {
                                        BooleanSetting bool = (BooleanSetting)setting2;
                                        if (mouseX >= (double)(posX + 2) && mouseX <= (double)(posX + 101) && mouseY >= (double)(posY + 1) && mouseY <= (double)(posY + 14) && button == 0) {
                                            bool.toggle();
                                            bool.loadingTimer.reset();
                                        }
                                        posY += 15;
                                    }
                                    if (!(setting2 instanceof NumberSetting) || !m.showSettings) break block51;
                                    number = (NumberSetting)setting2;
                                    if (!(mouseX >= (double)(posX + 2)) || !(mouseX <= (double)(posX + 101)) || !(mouseY >= (double)(posY + 1)) || !(mouseY <= (double)(posY + 16))) break block52;
                                    String string = number.editing ? number.shadowValue : String.valueOf(number.value);
                                    if (!(mouseX >= (double)(posX + 96 - ThunderGui.mc.field_1772.method_1727(string))) || !(mouseX <= (double)(posX + 101)) || !(mouseY >= (double)(posY + 2))) break block53;
                                    Objects.requireNonNull(ThunderGui.mc.field_1772);
                                    if (mouseY <= (double)(posY + 2 + 9)) break block52;
                                }
                                if (button == 0) {
                                    number.mouseClicked = true;
                                }
                            }
                            String string = number.editing ? number.shadowValue : String.valueOf(number.value);
                            if (mouseX >= (double)(posX + 96 - ThunderGui.mc.field_1772.method_1727(string)) && mouseX <= (double)(posX + 101) && mouseY >= (double)(posY + 2)) {
                                Objects.requireNonNull(ThunderGui.mc.field_1772);
                                if (mouseY <= (double)(posY + 2 + 9)) {
                                    number.editing = true;
                                    number.shadowValue = String.valueOf(number.value);
                                }
                            }
                            posY += 17;
                        }
                        if (setting2 instanceof ModeSetting && m.showSettings) {
                            ModeSetting mode = (ModeSetting)setting2;
                            if (mouseX >= (double)(posX + 2) && mouseX <= (double)(posX + 101) && mouseY >= (double)(posY + 1) && mouseY <= (double)(posY + 14)) {
                                if (button == 0) {
                                    mode.cycle();
                                }
                                if (button == 1) {
                                    mode.revcycle();
                                }
                            }
                            posY += 15;
                        }
                        if (!(setting2 instanceof KeybindSetting) || !m.showSettings) continue;
                        KeybindSetting keybind = (KeybindSetting)setting2;
                        if (mouseX >= (double)(posX + 2) && mouseX <= (double)(posX + 101) && mouseY >= (double)(posY + 1) && mouseY <= (double)(posY + 14) && (!(mouseX >= (double)(posX + 92)) || !(mouseX <= (double)(posX + 99)) || !(mouseY >= (double)(posY + 3)) || !(mouseY <= (double)(posY + 10)))) {
                            if (button == 0) {
                                keybind.binding = true;
                            } else if (button == 1) {
                                keybind.code = 0;
                                keybind.binding = false;
                            }
                        }
                        if (mouseX >= (double)(posX + 92) && mouseX <= (double)(posX + 99) && mouseY >= (double)(posY + 3) && mouseY <= (double)(posY + 10) && button == 0) {
                            keybind.keyDown = !keybind.keyDown;
                        }
                        posY += 15;
                    }
                }
            }
        }
        return super.method_25402(mouseX, mouseY, button);
    }

    public boolean method_25406(double mouseX, double mouseY, int button) {
        for (Category category : Category.values()) {
            category.mouseClicked = false;
        }
        for (Module m : ModuleManager.modules) {
            for (Setting setting : m.settings) {
                if (setting instanceof NumberSetting) {
                    ((NumberSetting)setting).mouseClicked = false;
                }
                if (!(setting instanceof ParentSetting)) continue;
                for (Setting setting2 : ((ParentSetting)setting).settings) {
                    if (!(setting2 instanceof NumberSetting)) continue;
                    ((NumberSetting)setting2).mouseClicked = false;
                }
            }
        }
        return super.method_25406(mouseX, mouseY, button);
    }

    public boolean method_25404(int keyCode, int scanCode, int modifiers) {
        for (Module m : ModuleManager.modules) {
            for (Setting setting : m.settings) {
                if (setting instanceof KeybindSetting && m.showSettings) {
                    KeybindSetting keybind = (KeybindSetting)setting;
                    if (keybind.binding) {
                        keybind.code = keyCode;
                        keybind.binding = false;
                    }
                }
                if (setting instanceof NumberSetting && m.showSettings) {
                    NumberSetting number = (NumberSetting)setting;
                    String value = "";
                    if (number.editing) {
                        if (keyCode == 259 && number.shadowValue.length() >= 1) {
                            number.shadowValue = value = number.shadowValue.substring(0, number.shadowValue.length() - 1);
                        }
                        if (keyCode == 257) {
                            try {
                                number.value = Float.parseFloat(number.shadowValue);
                            }
                            catch (NumberFormatException e) {
                                System.out.println("corupted value " + value);
                            }
                            number.editing = false;
                        }
                        if (keyCode != 259 && keyCode != 256 && keyCode != 257) {
                            number.shadowValue = number.shadowValue + KeyUtil.getKeyName(keyCode);
                        }
                    }
                }
                if (!(setting instanceof ParentSetting) || !m.showSettings) continue;
                ParentSetting par = (ParentSetting)setting;
                for (Setting setting2 : par.settings) {
                    if (setting2 instanceof KeybindSetting && m.showSettings) {
                        KeybindSetting keybind = (KeybindSetting)setting2;
                        if (keybind.binding) {
                            keybind.code = keyCode;
                            keybind.binding = false;
                        }
                    }
                    if (!(setting2 instanceof NumberSetting) || !m.showSettings) continue;
                    NumberSetting number = (NumberSetting)setting2;
                    String value = "";
                    if (!number.editing) continue;
                    if (keyCode == 259 && number.shadowValue.length() >= 1) {
                        number.shadowValue = value = number.shadowValue.substring(0, number.shadowValue.length() - 1);
                    }
                    if (keyCode == 257) {
                        try {
                            number.value = number.fraction ? Float.parseFloat(number.shadowValue) : ThunderGui.parseInt(Float.parseFloat(number.shadowValue)).floatValue();
                        }
                        catch (NumberFormatException var14) {
                            System.out.println("corupted value " + value);
                        }
                        number.editing = false;
                    }
                    if (keyCode == 259 || keyCode == 256 || keyCode == 257) continue;
                    number.shadowValue = number.shadowValue + KeyUtil.getKeyName(keyCode);
                }
            }
        }
        return super.method_25404(keyCode, scanCode, modifiers);
    }

    public boolean method_25421() {
        return false;
    }

    public static int icicleFontPos(String name) {
        int pos = 50 - ThunderGui.mc.field_1772.method_1727(name) / 2;
        return pos;
    }

    public static Float parseFloat(float str) {
        String[] str1 = String.valueOf(str).split("\\.");
        return Float.valueOf(str1[0] + "." + str1[1].substring(0, 1));
    }

    public static Float parseInt(float str) {
        String[] str1 = String.valueOf(str).split("\\.");
        return Float.valueOf(str1[0]);
    }

    public static float calculateRotation(float var0) {
        float f;
        var0 %= 360.0f;
        if (f >= 180.0f) {
            var0 -= 360.0f;
        }
        if (var0 < -180.0f) {
            var0 += 360.0f;
        }
        return var0;
    }
}

