/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_437
 *  net.minecraft.class_442
 */
package com.mrzak34.thunderhack.module.modules.client;

import com.mrzak34.thunderhack.Main;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.ModeSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.settings.ParentSetting;
import com.mrzak34.thunderhack.ui.click.HudEditor;
import net.minecraft.class_437;
import net.minecraft.class_442;

public class HudEditorModule
extends Module {
    public static ModeSetting mode = new ModeSetting("mode", "logo", "anime", "logo", "anime2", "hentai");
    public static NumberSetting imposX = new NumberSetting("imposX", 400.0f, 0.0f, 1024.0f, false);
    public static NumberSetting imposY = new NumberSetting("imposY", 0.0f, 0.0f, 1024.0f, false);
    public static NumberSetting scale = new NumberSetting("scale", 910.0f, 0.0f, 1024.0f, false);
    public static ParentSetting image = new ParentSetting("image", false, true, mode, imposX, imposY, scale);
    public static ModeSetting icicle = new ModeSetting("icicle", "modules", "modules", "categories", "all");
    public static NumberSetting categoryRed = new NumberSetting("categoryRed", 0.0f, 0.0f, 255.0f, false);
    public static NumberSetting categoryGreen = new NumberSetting("categoryGreen", 255.0f, 0.0f, 255.0f, false);
    public static NumberSetting categoryBlue = new NumberSetting("categoryBlue", 175.0f, 0.0f, 255.0f, false);
    public static NumberSetting categoryAlpha = new NumberSetting("categoryAlpha", 255.0f, 0.0f, 255.0f, false);
    public static ParentSetting category = new ParentSetting("category", false, false, categoryRed, categoryGreen, categoryBlue, categoryAlpha);
    public static NumberSetting moduleRed = new NumberSetting("moduleRed", 0.0f, 0.0f, 255.0f, false);
    public static NumberSetting moduleGreen = new NumberSetting("moduleGreen", 16.0f, 0.0f, 255.0f, false);
    public static NumberSetting moduleBlue = new NumberSetting("moduleBlue", 17.0f, 0.0f, 255.0f, false);
    public static NumberSetting moduleAlpha = new NumberSetting("moduleAlpha", 100.0f, 0.0f, 255.0f, false);
    public static ParentSetting module = new ParentSetting("module", false, false, moduleRed, moduleGreen, moduleBlue, moduleAlpha);
    public static NumberSetting backgroundRed = new NumberSetting("backgroundRed", 0.0f, 0.0f, 255.0f, false);
    public static NumberSetting backgroundGreen = new NumberSetting("backgroundGreen", 16.0f, 0.0f, 255.0f, false);
    public static NumberSetting backgroundBlue = new NumberSetting("backgroundBlue", 17.0f, 0.0f, 255.0f, false);
    public static NumberSetting backgroundAlpha = new NumberSetting("backgroundAlpha", 200.0f, 0.0f, 255.0f, false);
    public static ParentSetting background = new ParentSetting("background", false, false, backgroundRed, backgroundGreen, backgroundBlue, backgroundAlpha);
    public static NumberSetting enabledRed = new NumberSetting("enabledRed", 0.0f, 0.0f, 255.0f, false);
    public static NumberSetting enabledGreen = new NumberSetting("enabledGreen", 255.0f, 0.0f, 255.0f, false);
    public static NumberSetting enabledBlue = new NumberSetting("enabledBlue", 175.0f, 0.0f, 255.0f, false);
    public static NumberSetting enabledAlpha = new NumberSetting("enabledAlpha", 200.0f, 0.0f, 255.0f, false);
    public static ParentSetting enabledColor = new ParentSetting("enabledColor", false, false, enabledRed, enabledGreen, enabledBlue, enabledAlpha);
    public static NumberSetting gradientRed = new NumberSetting("gradientRed", 0.0f, 0.0f, 255.0f, false);
    public static NumberSetting gradientGreen = new NumberSetting("gradientGreen", 16.0f, 0.0f, 255.0f, false);
    public static NumberSetting gradientBlue = new NumberSetting("gradientBlue", 17.0f, 0.0f, 255.0f, false);
    public static NumberSetting gradientAlpha = new NumberSetting("gradientAlpha", 200.0f, 0.0f, 255.0f, false);
    public static NumberSetting gradientRad = new NumberSetting("gradientRad", 5.0f, 1.0f, 15.0f, false);
    public static ParentSetting gradient = new ParentSetting("gradient", false, true, gradientRed, gradientGreen, gradientBlue, gradientAlpha, gradientRad);
    public static NumberSetting scrollspeed = new NumberSetting("scroll speed", 5.0f, 1.0f, 10.0f, false);
    public HudEditor hudEditor = new HudEditor();
    private static HudEditorModule INSTANCE = new HudEditorModule();

    public HudEditorModule() {
        super("HudEditor", 0, false, Category.CLIENT);
        this.addSettings(icicle, image, category, module, background, enabledColor, gradient, scrollspeed);
        this.setInstance();
    }

    public static HudEditorModule getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HudEditorModule();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (!(HudEditorModule.mc.field_1755 instanceof class_442)) {
            mc.method_1507((class_437)this.hudEditor);
        } else {
            Main.currentScreen = this.hudEditor;
        }
        System.out.println("ClickGUI enabled");
        this.setToggled(false);
    }
}

