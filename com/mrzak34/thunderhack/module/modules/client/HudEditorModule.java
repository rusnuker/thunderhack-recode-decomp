//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.client;

import com.mrzak34.thunderhack.ui.click.*;
import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import net.minecraft.*;
import com.mrzak34.thunderhack.*;

public class HudEditorModule extends Module
{
    public static ModeSetting mode;
    public static NumberSetting imposX;
    public static NumberSetting imposY;
    public static NumberSetting scale;
    public static ParentSetting image;
    public static ModeSetting icicle;
    public static NumberSetting categoryRed;
    public static NumberSetting categoryGreen;
    public static NumberSetting categoryBlue;
    public static NumberSetting categoryAlpha;
    public static ParentSetting category;
    public static NumberSetting moduleRed;
    public static NumberSetting moduleGreen;
    public static NumberSetting moduleBlue;
    public static NumberSetting moduleAlpha;
    public static ParentSetting module;
    public static NumberSetting backgroundRed;
    public static NumberSetting backgroundGreen;
    public static NumberSetting backgroundBlue;
    public static NumberSetting backgroundAlpha;
    public static ParentSetting background;
    public static NumberSetting enabledRed;
    public static NumberSetting enabledGreen;
    public static NumberSetting enabledBlue;
    public static NumberSetting enabledAlpha;
    public static ParentSetting enabledColor;
    public static NumberSetting gradientRed;
    public static NumberSetting gradientGreen;
    public static NumberSetting gradientBlue;
    public static NumberSetting gradientAlpha;
    public static NumberSetting gradientRad;
    public static ParentSetting gradient;
    public static NumberSetting scrollspeed;
    public HudEditor hudEditor;
    private static HudEditorModule INSTANCE;
    
    public HudEditorModule() {
        super("HudEditor", 0, false, Category.CLIENT);
        this.hudEditor = new HudEditor();
        this.addSettings(new Setting[] { HudEditorModule.icicle, HudEditorModule.image, HudEditorModule.category, HudEditorModule.module, HudEditorModule.background, HudEditorModule.enabledColor, HudEditorModule.gradient, HudEditorModule.scrollspeed });
        this.setInstance();
    }
    
    public static HudEditorModule getInstance() {
        if (HudEditorModule.INSTANCE == null) {
            HudEditorModule.INSTANCE = new HudEditorModule();
        }
        return HudEditorModule.INSTANCE;
    }
    
    private void setInstance() {
        HudEditorModule.INSTANCE = this;
    }
    
    public void onEnable() {
        super.onEnable();
        if (!(HudEditorModule.mc.field_1755 instanceof class_442)) {
            HudEditorModule.mc.method_1507((class_437)this.hudEditor);
        }
        else {
            Main.currentScreen = this.hudEditor;
        }
        System.out.println("ClickGUI enabled");
        this.setToggled(false);
    }
    
    static {
        HudEditorModule.mode = new ModeSetting("mode", "logo", new String[] { "anime", "logo", "anime2", "hentai" });
        HudEditorModule.imposX = new NumberSetting("imposX", 400.0f, 0.0f, 1024.0f, false);
        HudEditorModule.imposY = new NumberSetting("imposY", 0.0f, 0.0f, 1024.0f, false);
        HudEditorModule.scale = new NumberSetting("scale", 910.0f, 0.0f, 1024.0f, false);
        HudEditorModule.image = new ParentSetting("image", false, true, new Setting[] { HudEditorModule.mode, HudEditorModule.imposX, HudEditorModule.imposY, HudEditorModule.scale });
        HudEditorModule.icicle = new ModeSetting("icicle", "modules", new String[] { "modules", "categories", "all" });
        HudEditorModule.categoryRed = new NumberSetting("categoryRed", 0.0f, 0.0f, 255.0f, false);
        HudEditorModule.categoryGreen = new NumberSetting("categoryGreen", 255.0f, 0.0f, 255.0f, false);
        HudEditorModule.categoryBlue = new NumberSetting("categoryBlue", 175.0f, 0.0f, 255.0f, false);
        HudEditorModule.categoryAlpha = new NumberSetting("categoryAlpha", 255.0f, 0.0f, 255.0f, false);
        HudEditorModule.category = new ParentSetting("category", false, false, new Setting[] { HudEditorModule.categoryRed, HudEditorModule.categoryGreen, HudEditorModule.categoryBlue, HudEditorModule.categoryAlpha });
        HudEditorModule.moduleRed = new NumberSetting("moduleRed", 0.0f, 0.0f, 255.0f, false);
        HudEditorModule.moduleGreen = new NumberSetting("moduleGreen", 16.0f, 0.0f, 255.0f, false);
        HudEditorModule.moduleBlue = new NumberSetting("moduleBlue", 17.0f, 0.0f, 255.0f, false);
        HudEditorModule.moduleAlpha = new NumberSetting("moduleAlpha", 100.0f, 0.0f, 255.0f, false);
        HudEditorModule.module = new ParentSetting("module", false, false, new Setting[] { HudEditorModule.moduleRed, HudEditorModule.moduleGreen, HudEditorModule.moduleBlue, HudEditorModule.moduleAlpha });
        HudEditorModule.backgroundRed = new NumberSetting("backgroundRed", 0.0f, 0.0f, 255.0f, false);
        HudEditorModule.backgroundGreen = new NumberSetting("backgroundGreen", 16.0f, 0.0f, 255.0f, false);
        HudEditorModule.backgroundBlue = new NumberSetting("backgroundBlue", 17.0f, 0.0f, 255.0f, false);
        HudEditorModule.backgroundAlpha = new NumberSetting("backgroundAlpha", 200.0f, 0.0f, 255.0f, false);
        HudEditorModule.background = new ParentSetting("background", false, false, new Setting[] { HudEditorModule.backgroundRed, HudEditorModule.backgroundGreen, HudEditorModule.backgroundBlue, HudEditorModule.backgroundAlpha });
        HudEditorModule.enabledRed = new NumberSetting("enabledRed", 0.0f, 0.0f, 255.0f, false);
        HudEditorModule.enabledGreen = new NumberSetting("enabledGreen", 255.0f, 0.0f, 255.0f, false);
        HudEditorModule.enabledBlue = new NumberSetting("enabledBlue", 175.0f, 0.0f, 255.0f, false);
        HudEditorModule.enabledAlpha = new NumberSetting("enabledAlpha", 200.0f, 0.0f, 255.0f, false);
        HudEditorModule.enabledColor = new ParentSetting("enabledColor", false, false, new Setting[] { HudEditorModule.enabledRed, HudEditorModule.enabledGreen, HudEditorModule.enabledBlue, HudEditorModule.enabledAlpha });
        HudEditorModule.gradientRed = new NumberSetting("gradientRed", 0.0f, 0.0f, 255.0f, false);
        HudEditorModule.gradientGreen = new NumberSetting("gradientGreen", 16.0f, 0.0f, 255.0f, false);
        HudEditorModule.gradientBlue = new NumberSetting("gradientBlue", 17.0f, 0.0f, 255.0f, false);
        HudEditorModule.gradientAlpha = new NumberSetting("gradientAlpha", 200.0f, 0.0f, 255.0f, false);
        HudEditorModule.gradientRad = new NumberSetting("gradientRad", 5.0f, 1.0f, 15.0f, false);
        HudEditorModule.gradient = new ParentSetting("gradient", false, true, new Setting[] { HudEditorModule.gradientRed, HudEditorModule.gradientGreen, HudEditorModule.gradientBlue, HudEditorModule.gradientAlpha, HudEditorModule.gradientRad });
        HudEditorModule.scrollspeed = new NumberSetting("scroll speed", 5.0f, 1.0f, 10.0f, false);
        HudEditorModule.INSTANCE = new HudEditorModule();
    }
}
