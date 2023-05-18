/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 */
package com.mrzak34.thunderhack.module.modules.render;

import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.settings.ParentSetting;
import net.minecraft.class_2960;

public class Chams
extends Module {
    public BooleanSetting crystals = new BooleanSetting("crystals", true);
    public BooleanSetting crystalsTexture = new BooleanSetting("crystalsTexture", true);
    public NumberSetting crystalsScale = new NumberSetting("crystalsScale", 0.6f, 0.0f, 2.0f, true);
    public NumberSetting crystalsBounce = new NumberSetting("crystalsBounce", 0.6f, 0.0f, 2.0f, true);
    public NumberSetting crystalsRotationSpeed = new NumberSetting("crystalsRotationSpeed", 0.3f, 0.0f, 2.0f, true);
    public NumberSetting crystalsCoreColorRed = new NumberSetting("crystalsCoreColorRed", 135.0f, 0.0f, 255.0f, false);
    public NumberSetting crystalsCoreColorGreen = new NumberSetting("crystalsCoreColorGreen", 0.0f, 0.0f, 255.0f, false);
    public NumberSetting crystalsCoreColorBlue = new NumberSetting("crystalsCoreColorBlue", 255.0f, 0.0f, 255.0f, false);
    public NumberSetting crystalsCoreColorAlpha = new NumberSetting("crystalsCoreColorAlpha", 255.0f, 0.0f, 255.0f, false);
    public ParentSetting renderCore = new ParentSetting("renderCore", false, true, this.crystalsCoreColorRed, this.crystalsCoreColorGreen, this.crystalsCoreColorBlue, this.crystalsCoreColorAlpha);
    public NumberSetting renderFrame1Red = new NumberSetting("renderFrame1Red", 135.0f, 0.0f, 255.0f, false);
    public NumberSetting renderFrame1Green = new NumberSetting("renderFrame1Green", 0.0f, 0.0f, 255.0f, false);
    public NumberSetting renderFrame1Blue = new NumberSetting("renderFrame1Blue", 255.0f, 0.0f, 255.0f, false);
    public NumberSetting renderFrame1Alpha = new NumberSetting("renderFrame1Alpha", 255.0f, 0.0f, 255.0f, false);
    public ParentSetting renderFrame1 = new ParentSetting("renderCore", false, true, this.renderFrame1Red, this.renderFrame1Green, this.renderFrame1Blue, this.renderFrame1Alpha);
    public NumberSetting renderFrame2Red = new NumberSetting("renderFrame2Red", 135.0f, 0.0f, 255.0f, false);
    public NumberSetting renderFrame2Green = new NumberSetting("renderFrame2Green", 0.0f, 0.0f, 255.0f, false);
    public NumberSetting renderFrame2Blue = new NumberSetting("renderFrame2Blue", 255.0f, 0.0f, 255.0f, false);
    public NumberSetting renderFrame2Alpha = new NumberSetting("renderFrame2Alpha", 255.0f, 0.0f, 255.0f, false);
    public ParentSetting renderFrame2 = new ParentSetting("renderCore", false, true, this.renderFrame2Red, this.renderFrame2Green, this.renderFrame2Blue, this.renderFrame2Alpha);
    public static final class_2960 BLANK = new class_2960("textures/blank.png");
    private static Chams INSTANCE = new Chams();

    public Chams() {
        super("Chams", 0, false, Category.RENDER);
        this.addSettings(this.crystals, this.crystalsTexture, this.crystalsScale, this.crystalsBounce, this.crystalsRotationSpeed, this.renderCore, this.renderFrame1, this.renderFrame2);
        this.setInstance();
    }

    public static Chams getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Chams();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

