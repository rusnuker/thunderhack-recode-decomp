//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.render;

import net.minecraft.*;
import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;

public class Chams extends Module
{
    public BooleanSetting crystals;
    public BooleanSetting crystalsTexture;
    public NumberSetting crystalsScale;
    public NumberSetting crystalsBounce;
    public NumberSetting crystalsRotationSpeed;
    public NumberSetting crystalsCoreColorRed;
    public NumberSetting crystalsCoreColorGreen;
    public NumberSetting crystalsCoreColorBlue;
    public NumberSetting crystalsCoreColorAlpha;
    public ParentSetting renderCore;
    public NumberSetting renderFrame1Red;
    public NumberSetting renderFrame1Green;
    public NumberSetting renderFrame1Blue;
    public NumberSetting renderFrame1Alpha;
    public ParentSetting renderFrame1;
    public NumberSetting renderFrame2Red;
    public NumberSetting renderFrame2Green;
    public NumberSetting renderFrame2Blue;
    public NumberSetting renderFrame2Alpha;
    public ParentSetting renderFrame2;
    public static final class_2960 BLANK;
    private static Chams INSTANCE;
    
    public Chams() {
        super("Chams", 0, false, Category.RENDER);
        this.crystals = new BooleanSetting("crystals", true);
        this.crystalsTexture = new BooleanSetting("crystalsTexture", true);
        this.crystalsScale = new NumberSetting("crystalsScale", 0.6f, 0.0f, 2.0f, true);
        this.crystalsBounce = new NumberSetting("crystalsBounce", 0.6f, 0.0f, 2.0f, true);
        this.crystalsRotationSpeed = new NumberSetting("crystalsRotationSpeed", 0.3f, 0.0f, 2.0f, true);
        this.crystalsCoreColorRed = new NumberSetting("crystalsCoreColorRed", 135.0f, 0.0f, 255.0f, false);
        this.crystalsCoreColorGreen = new NumberSetting("crystalsCoreColorGreen", 0.0f, 0.0f, 255.0f, false);
        this.crystalsCoreColorBlue = new NumberSetting("crystalsCoreColorBlue", 255.0f, 0.0f, 255.0f, false);
        this.crystalsCoreColorAlpha = new NumberSetting("crystalsCoreColorAlpha", 255.0f, 0.0f, 255.0f, false);
        this.renderCore = new ParentSetting("renderCore", false, true, new Setting[] { this.crystalsCoreColorRed, this.crystalsCoreColorGreen, this.crystalsCoreColorBlue, this.crystalsCoreColorAlpha });
        this.renderFrame1Red = new NumberSetting("renderFrame1Red", 135.0f, 0.0f, 255.0f, false);
        this.renderFrame1Green = new NumberSetting("renderFrame1Green", 0.0f, 0.0f, 255.0f, false);
        this.renderFrame1Blue = new NumberSetting("renderFrame1Blue", 255.0f, 0.0f, 255.0f, false);
        this.renderFrame1Alpha = new NumberSetting("renderFrame1Alpha", 255.0f, 0.0f, 255.0f, false);
        this.renderFrame1 = new ParentSetting("renderCore", false, true, new Setting[] { this.renderFrame1Red, this.renderFrame1Green, this.renderFrame1Blue, this.renderFrame1Alpha });
        this.renderFrame2Red = new NumberSetting("renderFrame2Red", 135.0f, 0.0f, 255.0f, false);
        this.renderFrame2Green = new NumberSetting("renderFrame2Green", 0.0f, 0.0f, 255.0f, false);
        this.renderFrame2Blue = new NumberSetting("renderFrame2Blue", 255.0f, 0.0f, 255.0f, false);
        this.renderFrame2Alpha = new NumberSetting("renderFrame2Alpha", 255.0f, 0.0f, 255.0f, false);
        this.renderFrame2 = new ParentSetting("renderCore", false, true, new Setting[] { this.renderFrame2Red, this.renderFrame2Green, this.renderFrame2Blue, this.renderFrame2Alpha });
        this.addSettings(new Setting[] { this.crystals, this.crystalsTexture, this.crystalsScale, this.crystalsBounce, this.crystalsRotationSpeed, this.renderCore, this.renderFrame1, this.renderFrame2 });
        this.setInstance();
    }
    
    public static Chams getInstance() {
        if (Chams.INSTANCE == null) {
            Chams.INSTANCE = new Chams();
        }
        return Chams.INSTANCE;
    }
    
    private void setInstance() {
        Chams.INSTANCE = this;
    }
    
    static {
        BLANK = new class_2960("textures/blank.png");
        Chams.INSTANCE = new Chams();
    }
}
