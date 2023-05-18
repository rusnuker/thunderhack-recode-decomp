/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_268
 *  net.minecraft.class_270
 *  net.minecraft.class_5250
 *  net.minecraft.class_640
 */
package com.mrzak34.thunderhack.module.modules.misc;

import com.mrzak34.thunderhack.manager.ConfigManager;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.Setting;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import net.minecraft.class_2561;
import net.minecraft.class_268;
import net.minecraft.class_270;
import net.minecraft.class_5250;
import net.minecraft.class_640;

public class TabScanner
extends Module {
    public static ArrayList<String> logs = new ArrayList();
    private static final String MAIN_FOLDER = "ThunderHack/";
    private static final String TAB_FOLDER = "ThunderHack/tab logs/";

    public TabScanner() {
        super("TabScanner", 0, false, Category.MISC);
        this.addSettings(new Setting[0]);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        logs.clear();
        for (class_640 playerListEntry : Objects.requireNonNull(mc.method_1562()).method_2880()) {
            class_5250 displayName = class_268.method_1142((class_270)TabScanner.mc.field_1687.method_8428().method_1164(playerListEntry.method_2966().getName()), (class_2561)class_2561.method_30163((String)playerListEntry.method_2966().getName()));
            logs.add(playerListEntry.method_2966().getName());
        }
        try {
            TabScanner.saveTabLog();
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.setToggled(false);
    }

    private static void saveTabLog() throws IOException {
        Date today = new Date();
        String file_name = TAB_FOLDER + today.getDate() + "." + today.getTime() + ".txt";
        Path file_path = Paths.get(file_name, new String[0]);
        ConfigManager.delete_file(file_name);
        ConfigManager.verify_file(file_path);
        File file = new File(file_name);
        BufferedWriter br = new BufferedWriter(new FileWriter(file));
        if (logs != null) {
            for (int i = 0; i < logs.size(); ++i) {
                br.write(logs.get(i) + "\r\n");
            }
            br.close();
        }
    }
}

