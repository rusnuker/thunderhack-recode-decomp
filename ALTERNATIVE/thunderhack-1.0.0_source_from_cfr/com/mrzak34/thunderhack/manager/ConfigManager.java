/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.reflect.TypeToken
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  com.google.gson.JsonPrimitive
 */
package com.mrzak34.thunderhack.manager;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.mrzak34.thunderhack.Hud.Hud;
import com.mrzak34.thunderhack.Main;
import com.mrzak34.thunderhack.manager.ModuleManager;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.module.modules.misc.AutoSign;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.KeybindSetting;
import com.mrzak34.thunderhack.settings.ModeSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.settings.ParentSetting;
import com.mrzak34.thunderhack.settings.Setting;
import com.mrzak34.thunderhack.util.EnemyUtil;
import com.mrzak34.thunderhack.util.FriendUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;

public class ConfigManager {
    private static final String MAIN_FOLDER = "ThunderHack/";
    private static final Path MAIN_PATH = Paths.get("ThunderHack/", new String[0]);
    private static final String CONFIGS_FOLDER = "ThunderHack/configs/";
    private static final Path CONFIGS_PATH = Paths.get("ThunderHack/configs/", new String[0]);
    private static final String MODULE_FILE = "default.json";
    private static final String MODULE_DIR = "ThunderHack/configs/default.json";
    private static final Path MODULE_PATH = Paths.get("ThunderHack/configs/default.json", new String[0]);
    private static final String HUD_FILE = "hud.json";
    private static final String HUD_DIR = "ThunderHack/hud.json";
    private static final Path HUD_PATH = Paths.get("ThunderHack/hud.json", new String[0]);
    private static final String FRIENDS_FILE = "friends.json";
    private static final String FRIENDS_DIR = "ThunderHack/friends.json";
    private static final String ENEMIES_FILE = "enemies.json";
    private static final String ENEMIES_DIR = "ThunderHack/enemies.json";
    private static final String CLIENT_FILE = "client.json";
    private static final String CLIENT_DIR = "ThunderHack/client.json";
    private static final Path CLIENT_PATH = Paths.get("ThunderHack/client.json", new String[0]);
    private static final String CONSOLE_FOLDER = "ThunderHack/console logs/";
    private static final Path CONSOLE_PATH = Paths.get("ThunderHack/console logs/", new String[0]);
    private static final String TAB_FOLDER = "ThunderHack/tab logs/";
    private static final Path TAB_PATH = Paths.get("ThunderHack/tab logs/", new String[0]);

    private static void saveModules() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser parser = new JsonParser();
        JsonObject main_json = new JsonObject();
        JsonObject main_module = new JsonObject();
        for (Module module : ModuleManager.modules) {
            JsonObject frame_info = new JsonObject();
            JsonObject main_setting = new JsonObject();
            for (Setting setting : module.settings) {
                JsonObject par_setting = new JsonObject();
                if (setting instanceof BooleanSetting) {
                    BooleanSetting bool = (BooleanSetting)setting;
                    main_setting.add(bool.name, (JsonElement)new JsonPrimitive(Boolean.valueOf(bool.isEnabled())));
                }
                if (setting instanceof NumberSetting) {
                    NumberSetting num = (NumberSetting)setting;
                    main_setting.add(num.name, (JsonElement)new JsonPrimitive((Number)num.getValue()));
                }
                if (setting instanceof ModeSetting) {
                    ModeSetting mode = (ModeSetting)setting;
                    main_setting.add(mode.name, (JsonElement)new JsonPrimitive((Number)mode.index));
                }
                if (setting instanceof KeybindSetting) {
                    KeybindSetting bind = (KeybindSetting)setting;
                    main_setting.add(bind.name, (JsonElement)new JsonPrimitive((Number)bind.getKeyCode()));
                }
                if (!(setting instanceof ParentSetting)) continue;
                ParentSetting par = (ParentSetting)setting;
                par_setting.add(setting.name + "_enabled", (JsonElement)new JsonPrimitive(Boolean.valueOf(par.isEnabled())));
                for (Setting parSett : par.settings) {
                    if (parSett instanceof BooleanSetting) {
                        BooleanSetting bool = (BooleanSetting)parSett;
                        par_setting.add(bool.name, (JsonElement)new JsonPrimitive(Boolean.valueOf(bool.isEnabled())));
                    }
                    if (parSett instanceof NumberSetting) {
                        NumberSetting num = (NumberSetting)parSett;
                        par_setting.add(num.name, (JsonElement)new JsonPrimitive((Number)num.getValue()));
                    }
                    if (parSett instanceof ModeSetting) {
                        ModeSetting mode = (ModeSetting)parSett;
                        par_setting.add(mode.name, (JsonElement)new JsonPrimitive((Number)mode.index));
                    }
                    if (!(parSett instanceof KeybindSetting)) continue;
                    KeybindSetting bind = (KeybindSetting)parSett;
                    par_setting.add(bind.name, (JsonElement)new JsonPrimitive((Number)bind.getKeyCode()));
                }
                main_setting.add(par.name, (JsonElement)par_setting);
            }
            frame_info.add("settings", (JsonElement)main_setting);
            frame_info.add(module.getName() + "_enabled", (JsonElement)new JsonPrimitive(Boolean.valueOf(module.isToggled())));
            main_module.add(module.getName(), (JsonElement)frame_info);
        }
        main_json.add("module", (JsonElement)main_module);
        JsonElement pretty_json = parser.parse(main_json.toString());
        String json = gson.toJson(pretty_json);
        ConfigManager.delete_file(MODULE_DIR);
        ConfigManager.verify_file(MODULE_PATH);
        OutputStreamWriter file = new OutputStreamWriter((OutputStream)new FileOutputStream(MODULE_DIR), StandardCharsets.UTF_8);
        file.write(json);
        file.close();
    }

    private static void loadModules() throws IOException {
        InputStream input_stream = Files.newInputStream(MODULE_PATH, new OpenOption[0]);
        JsonObject main_json = new JsonParser().parse((Reader)new InputStreamReader(input_stream)).getAsJsonObject();
        JsonObject main_module = main_json.get("module").getAsJsonObject();
        for (Module module : ModuleManager.modules) {
            try {
                JsonObject module_info = main_module.get(module.getName()).getAsJsonObject();
                for (Setting setting : module.settings) {
                    try {
                        JsonObject setting_info = module_info.get("settings").getAsJsonObject();
                        if (setting instanceof BooleanSetting) {
                            BooleanSetting bool = (BooleanSetting)setting;
                            bool.setEnabled(setting_info.get(bool.name).getAsBoolean());
                        }
                        if (setting instanceof NumberSetting) {
                            NumberSetting num = (NumberSetting)setting;
                            num.value = setting_info.get(num.name).getAsFloat();
                        }
                        if (setting instanceof ModeSetting) {
                            ModeSetting mode = (ModeSetting)setting;
                            mode.index = setting_info.get(mode.name).getAsInt();
                        }
                        if (setting instanceof KeybindSetting) {
                            KeybindSetting bind = (KeybindSetting)setting;
                            bind.code = setting_info.get(bind.name).getAsInt();
                        }
                        if (!(setting instanceof ParentSetting)) continue;
                        ParentSetting par = (ParentSetting)setting;
                        JsonObject parent_info = setting_info.get(par.name).getAsJsonObject();
                        par.setEnabled(parent_info.get(par.name + "_enabled").getAsBoolean());
                        for (Setting setting1 : par.settings) {
                            try {
                                if (setting1 instanceof BooleanSetting) {
                                    BooleanSetting bool = (BooleanSetting)setting1;
                                    bool.setEnabled(parent_info.get(bool.name).getAsBoolean());
                                }
                                if (setting1 instanceof NumberSetting) {
                                    NumberSetting num = (NumberSetting)setting1;
                                    num.value = parent_info.get(num.name).getAsFloat();
                                }
                                if (setting1 instanceof ModeSetting) {
                                    ModeSetting mode = (ModeSetting)setting1;
                                    mode.index = parent_info.get(mode.name).getAsInt();
                                }
                                if (!(setting1 instanceof KeybindSetting)) continue;
                                KeybindSetting bind = (KeybindSetting)setting1;
                                bind.code = parent_info.get(bind.name).getAsInt();
                            }
                            catch (Exception e) {}
                        }
                    }
                    catch (Exception e) {
                    }
                }
                module.setToggled(module_info.get(module.getName() + "_enabled").getAsBoolean());
            }
            catch (Exception e) {}
        }
        input_stream.close();
    }

    private static void saveHuds() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser parser = new JsonParser();
        JsonObject main_json = new JsonObject();
        JsonObject main_hud = new JsonObject();
        for (Hud hud : Main.hudManager.getHudList()) {
            JsonObject frame_info = new JsonObject();
            frame_info.add("enabled", (JsonElement)new JsonPrimitive(Boolean.valueOf(hud.toggled)));
            frame_info.add("x", (JsonElement)new JsonPrimitive((Number)hud.posX));
            frame_info.add("y", (JsonElement)new JsonPrimitive((Number)hud.posY));
            main_hud.add(hud.name, (JsonElement)frame_info);
        }
        main_json.add("huds", (JsonElement)main_hud);
        JsonElement pretty_json = parser.parse(main_json.toString());
        String json = gson.toJson(pretty_json);
        ConfigManager.delete_file(HUD_DIR);
        ConfigManager.verify_file(HUD_PATH);
        OutputStreamWriter file = new OutputStreamWriter((OutputStream)new FileOutputStream(HUD_DIR), StandardCharsets.UTF_8);
        file.write(json);
        file.close();
    }

    private static void loadHuds() throws IOException {
        InputStream input_stream = Files.newInputStream(HUD_PATH, new OpenOption[0]);
        JsonObject main_hud = new JsonParser().parse((Reader)new InputStreamReader(input_stream)).getAsJsonObject();
        JsonObject main_huds = main_hud.get("huds").getAsJsonObject();
        for (Hud hud : Main.hudManager.getHudList()) {
            try {
                JsonObject hud_info = main_huds.get(hud.name).getAsJsonObject();
                hud.setToggled(hud_info.get("enabled").getAsBoolean());
                hud.posX = hud_info.get("x").getAsInt();
                hud.posY = hud_info.get("y").getAsInt();
            }
            catch (Exception e) {}
        }
        input_stream.close();
    }

    private static void saveFriends() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(FriendUtil.friends);
        OutputStreamWriter file = new OutputStreamWriter((OutputStream)new FileOutputStream(FRIENDS_DIR), StandardCharsets.UTF_8);
        file.write(json);
        file.close();
    }

    private static void loadFriends() throws IOException {
        Gson gson = new Gson();
        BufferedReader reader = Files.newBufferedReader(Paths.get(FRIENDS_DIR, new String[0]));
        FriendUtil.friends = (ArrayList)gson.fromJson((Reader)reader, new TypeToken<ArrayList<FriendUtil.Friend>>(){}.getType());
        ((Reader)reader).close();
    }

    private static void saveEnemies() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(EnemyUtil.enemies);
        OutputStreamWriter file = new OutputStreamWriter((OutputStream)new FileOutputStream(ENEMIES_DIR), StandardCharsets.UTF_8);
        file.write(json);
        file.close();
    }

    private static void loadEnemies() throws IOException {
        Gson gson = new Gson();
        BufferedReader reader = Files.newBufferedReader(Paths.get(ENEMIES_DIR, new String[0]));
        EnemyUtil.enemies = (ArrayList)gson.fromJson((Reader)reader, new TypeToken<ArrayList<EnemyUtil.Enemy>>(){}.getType());
        ((Reader)reader).close();
    }

    private static void saveClient() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser parser = new JsonParser();
        JsonObject main_json = new JsonObject();
        JsonObject main_category = new JsonObject();
        for (Category category : Category.values()) {
            try {
                JsonObject frame_info = new JsonObject();
                frame_info.add("showModules", (JsonElement)new JsonPrimitive(Boolean.valueOf(category.showModules)));
                frame_info.add("x", (JsonElement)new JsonPrimitive((Number)category.posX));
                frame_info.add("y", (JsonElement)new JsonPrimitive((Number)category.posY));
                main_category.add(category.name, (JsonElement)frame_info);
            }
            catch (Exception e) {
                // empty catch block
            }
        }
        main_json.add("category", (JsonElement)main_category);
        JsonElement pretty_json = parser.parse(main_json.toString());
        String json = gson.toJson(pretty_json);
        ConfigManager.delete_file(CLIENT_DIR);
        ConfigManager.verify_file(CLIENT_PATH);
        OutputStreamWriter file = new OutputStreamWriter((OutputStream)new FileOutputStream(CLIENT_DIR), StandardCharsets.UTF_8);
        file.write(json);
        file.close();
    }

    private static void loadClient() throws IOException {
        try {
            InputStream input_stream = Files.newInputStream(CLIENT_PATH, new OpenOption[0]);
            JsonObject main_category = new JsonParser().parse((Reader)new InputStreamReader(input_stream)).getAsJsonObject();
            JsonObject main_categorys = main_category.get("category").getAsJsonObject();
            for (Category category : Category.values()) {
                JsonObject hud_info = main_categorys.get(category.name).getAsJsonObject();
                category.showModules = hud_info.get("showModules").getAsBoolean();
                category.posX = hud_info.get("x").getAsInt();
                category.posY = hud_info.get("y").getAsInt();
            }
            input_stream.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static boolean delete_file(String path) throws IOException {
        File f = new File(path);
        return f.delete();
    }

    public static void verify_file(Path path) throws IOException {
        if (!Files.exists(path, new LinkOption[0])) {
            Files.createFile(path, new FileAttribute[0]);
        }
    }

    public static void verify_dir(Path path) throws IOException {
        if (!Files.exists(path, new LinkOption[0])) {
            Files.createDirectory(path, new FileAttribute[0]);
        }
    }

    public static void saveSettings() {
        try {
            ConfigManager.verify_dir(MAIN_PATH);
            ConfigManager.verify_dir(CONFIGS_PATH);
            ConfigManager.verify_dir(CONSOLE_PATH);
            ConfigManager.saveModules();
            ConfigManager.saveHuds();
            ConfigManager.saveFriends();
            ConfigManager.saveEnemies();
            ConfigManager.saveClient();
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    public static void loadSettings() {
        try {
            ConfigManager.verify_dir(AutoSign.SIGN_FOLDER_PATH);
            ConfigManager.verify_dir(TAB_PATH);
            ConfigManager.loadModules();
            ConfigManager.loadHuds();
            ConfigManager.loadFriends();
            ConfigManager.loadEnemies();
            ConfigManager.loadClient();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}

