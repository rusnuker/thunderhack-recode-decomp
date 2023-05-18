/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.class_2338
 *  net.minecraft.class_2596
 *  net.minecraft.class_2877
 *  net.minecraft.class_498
 *  net.minecraft.class_837$class_4702
 */
package com.mrzak34.thunderhack.module.modules.misc;

import com.google.common.eventbus.Subscribe;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mrzak34.thunderhack.event.events.OpenScreenEvent;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.manager.NotificationManager;
import com.mrzak34.thunderhack.mixin.ISignEditScreen;
import com.mrzak34.thunderhack.mixin.IUpdateSignC2SPacket;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.ModeSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import net.minecraft.class_2338;
import net.minecraft.class_2596;
import net.minecraft.class_2877;
import net.minecraft.class_498;
import net.minecraft.class_837;

public class AutoSign
extends Module {
    NumberSetting symbols = new NumberSetting("symbols", 20.0f, 1.0f, 40.0f, false);
    NumberSetting factor = new NumberSetting("factor", 20.0f, 1.0f, 300.0f, false);
    public static ModeSetting mode = new ModeSetting("mode", "lag", "lag", "cleared", "file", "crash");
    public static ModeSetting writemode = new ModeSetting("write mode", "default", "default", "replace", "fg");
    public static String[] text = new String[]{" ", " ", " ", " "};
    String LAG_STRING = "\u8fea\u65af\u5c3c\u4e9a\u514b\u4fc4\u706d\u65af\u8bf6\u54df\u65af\u6cf0\u5434\u5f17\u54c8\u518c\u5207\u6c99\u590f\u56fe\u4f0a\u53ef\u7f57\u80af\u5361\u827e\u4e86\u6069\u54e6\u4f69\u4501\u4601\u4701\u4801\u4901\u4a01\u4b01\u4c01\u4d01\u5501";
    public static final String MAIN_FOLDER = "ThunderHack/";
    public static final String SIGN_FOLDER = "ThunderHack/autosign/";
    public static final Path SIGN_FOLDER_PATH = Paths.get("ThunderHack/autosign/", new String[0]);
    public static final String SIGN_FILE = "sign.json";
    public static final String SIGN_DIR = "ThunderHack/autosign/sign.json";
    public static final Path SIGN_PATH = Paths.get("ThunderHack/autosign/sign.json", new String[0]);
    class_2338 lagPos;
    boolean bypass = false;

    public AutoSign() {
        super("AutoSign", 0, false, Category.MISC);
        this.addSettings(mode, writemode, this.symbols, this.factor);
    }

    @Subscribe
    public void onOpenScreen(OpenScreenEvent event) {
        if (event.screen instanceof class_498) {
            if (mode.getMode().equals("lag")) {
                text = AutoSign.parseToSignString(this.LAG_STRING, (int)this.symbols.getValue());
            }
            if (mode.getMode().equals("file")) {
                try {
                    AutoSign.loadSignText();
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
            if (mode.getMode().equals("cleared")) {
                text = new String[]{"", "", "", ""};
            }
            class_837.class_4702 signModel = ((ISignEditScreen)event.screen).getModel();
            class_2338 signPos = new class_2338((int)signModel.field_21531.field_3657, (int)signModel.field_21531.field_3656, (int)signModel.field_21531.field_3655);
            AutoSign.mc.field_1724.field_3944.method_2883((class_2596)new class_2877(signPos, text[0], text[1], text[2], text[3]));
            if (writemode.getMode().equals("fg")) {
                int i = 0;
                while ((double)i < this.factor.getValue()) {
                    AutoSign.mc.field_1724.field_3944.method_2883((class_2596)new class_2877(signPos, text[0], text[1], text[2], text[3]));
                    ++i;
                }
            }
            if (writemode.getMode().equals("fg")) {
                // empty if block
            }
            event.setCancelled(true);
        }
    }

    @Subscribe
    public void onPacketSend(PacketEvent.Send event) {
        if (event.getPacket() instanceof class_2877) {
            class_2877 packet = (class_2877)event.getPacket();
            if (writemode.getMode().equals("replace")) {
                if (mode.getMode().equals("lag")) {
                    text = AutoSign.parseToSignString(this.LAG_STRING, (int)this.symbols.getValue());
                }
                if (mode.getMode().equals("file")) {
                    try {
                        AutoSign.loadSignText();
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }
                if (mode.getMode().equals("cleared")) {
                    text = new String[]{"", "", "", ""};
                }
                if (mode.getMode().equals("crash")) {
                    this.lagPos = packet.method_12510();
                }
                ((IUpdateSignC2SPacket)packet).setText(text);
            }
            if (writemode.getMode().equals("fg")) {
                this.bypass = true;
            }
        }
    }

    @Override
    public void onTick() {
        super.onTick();
        if (this.lagPos == null || text == null) {
            return;
        }
        int i = 0;
        while ((double)i < this.factor.getValue()) {
            AutoSign.mc.field_1724.field_3944.method_2883((class_2596)new class_2877(this.lagPos, text[0], text[1], text[2], text[3]));
            ++i;
        }
        if (this.bypass) {
            AutoSign.mc.field_1724.field_3944.method_2883((class_2596)new class_2877(this.lagPos, text[0], text[1], text[2], text[3]));
        }
    }

    @Override
    public void onEnable() {
        super.onEnable();
        try {
            AutoSign.loadSignText();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.lagPos = null;
    }

    public static void loadSignText() throws IOException {
        InputStream input_stream = Files.newInputStream(SIGN_PATH, new OpenOption[0]);
        JsonObject main_sign = new JsonParser().parse((Reader)new InputStreamReader(input_stream)).getAsJsonObject();
        for (int i = 0; i < 4; ++i) {
            try {
                AutoSign.text[i] = main_sign.get("text" + i).getAsString();
                NotificationManager.notif("text" + i + ": " + main_sign.get("text" + i).getAsString());
                continue;
            }
            catch (Exception e) {
                // empty catch block
            }
        }
        input_stream.close();
    }

    public static String[] parseToSignString(String str, int symbols) {
        String[] signString = new String[4];
        signString[0] = symbols < 10 ? str.substring(0, symbols) : str.substring(0, 10);
        signString[1] = symbols < 20 ? (symbols < 10 ? "" : str.substring(10, symbols)) : str.substring(10, 20);
        signString[2] = symbols < 30 ? (symbols < 20 ? "" : str.substring(20, symbols)) : str.substring(20, 30);
        signString[3] = symbols < 40 ? (symbols < 30 ? "" : str.substring(30, symbols)) : str.substring(30, 40);
        return signString;
    }
}

