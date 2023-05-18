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
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.KeybindSetting;
import com.mrzak34.thunderhack.settings.ModeSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.settings.ParentSetting;
import com.mrzak34.thunderhack.settings.Setting;
import com.mrzak34.thunderhack.util.EnemyUtil;
import com.mrzak34.thunderhack.util.FriendUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
import java.util.Objects;

public class ConfigManagerOld {
    private static final String MAIN_FOLDER = "ThunderHack/";
    private static final String CONFIGS_FOLDER = "ThunderHack/configs/";
    private static String ACTIVE_CONFIG_FOLDER = "ThunderHack/configs/default/";
    private static final String CLIENT_FILE = "client.json";
    private static final String CONFIG_FILE = "config.txt";
    private static final String EZ_FILE = "ez.txt";
    private static final String ENEMIES_FILE = "enemies.json";
    private static final String FRIENDS_FILE = "friends.json";
    private static final String HUD_FILE = "hud.json";
    private static final String CLIENT_DIR = "ThunderHack/client.json";
    private static final String CONFIG_DIR = "ThunderHack/config.txt";
    private static final String EZ_DIR = "ThunderHack/ez.txt";
    private static final String ENEMIES_DIR = "ThunderHack/enemies.json";
    private static final String FRIENDS_DIR = "ThunderHack/friends.json";
    private static final String HUD_DIR = "ThunderHack/hud.json";
    private String CURRENT_CONFIG_DIR = "ThunderHack/ThunderHack/configs/" + ACTIVE_CONFIG_FOLDER;
    private static final Path MAIN_FOLDER_PATH = Paths.get("ThunderHack/", new String[0]);
    private static final Path CONFIGS_FOLDER_PATH = Paths.get("ThunderHack/configs/", new String[0]);
    private static Path ACTIVE_CONFIG_FOLDER_PATH = Paths.get(ACTIVE_CONFIG_FOLDER, new String[0]);
    private static final Path CLIENT_PATH = Paths.get("ThunderHack/client.json", new String[0]);
    private static final Path CONFIG_PATH = Paths.get("ThunderHack/config.txt", new String[0]);
    private static final Path EZ_PATH = Paths.get("ThunderHack/ez.txt", new String[0]);
    private static final Path ENEMIES_PATH = Paths.get("ThunderHack/enemies.json", new String[0]);
    private static final Path FRIENDS_PATH = Paths.get("ThunderHack/friends.json", new String[0]);
    private static final Path HUD_PATH = Paths.get("ThunderHack/hud.json", new String[0]);
    private Path CURRENT_CONFIG_PATH = Paths.get(this.CURRENT_CONFIG_DIR, new String[0]);

    private static void save_ezmessage() throws IOException {
        FileWriter writer = new FileWriter(EZ_DIR);
        writer.close();
    }

    private static void load_ezmessage() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (String s : Files.readAllLines(EZ_PATH)) {
            sb.append(s);
        }
    }

    private static void save_friends() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(FriendUtil.friends);
        OutputStreamWriter file = new OutputStreamWriter((OutputStream)new FileOutputStream(FRIENDS_DIR), StandardCharsets.UTF_8);
        file.write(json);
        file.close();
    }

    private static void load_friends() throws IOException {
        Gson gson = new Gson();
        BufferedReader reader = Files.newBufferedReader(Paths.get(FRIENDS_DIR, new String[0]));
        FriendUtil.friends = (ArrayList)gson.fromJson((Reader)reader, new TypeToken<ArrayList<FriendUtil.Friend>>(){}.getType());
        ((Reader)reader).close();
    }

    private static void save_enemies() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(EnemyUtil.enemies);
        OutputStreamWriter file = new OutputStreamWriter((OutputStream)new FileOutputStream(ENEMIES_DIR), StandardCharsets.UTF_8);
        file.write(json);
        file.close();
    }

    private static void load_enemies() throws IOException {
        Gson gson = new Gson();
        BufferedReader reader = Files.newBufferedReader(Paths.get(ENEMIES_DIR, new String[0]));
        EnemyUtil.enemies = (ArrayList)gson.fromJson((Reader)reader, new TypeToken<ArrayList<EnemyUtil.Enemy>>(){}.getType());
        ((Reader)reader).close();
    }

    private static void save_hacks() throws IOException {
        for (Module module : ModuleManager.modules) {
            String file_name = ACTIVE_CONFIG_FOLDER + module.getName() + ".txt";
            Path file_path = Paths.get(file_name, new String[0]);
            ConfigManagerOld.delete_file(file_name);
            ConfigManagerOld.verify_file(file_path);
            File file = new File(file_name);
            BufferedWriter br = new BufferedWriter(new FileWriter(file));
            for (Setting setting : module.settings) {
                if (setting instanceof BooleanSetting) {
                    BooleanSetting bool = (BooleanSetting)setting;
                    br.write(bool.name + ":" + bool.enabled + "\r\n");
                    continue;
                }
                if (setting instanceof NumberSetting) {
                    NumberSetting number = (NumberSetting)setting;
                    br.write(number.name + ":" + number.value + "\r\n");
                    continue;
                }
                if (setting instanceof ModeSetting) {
                    ModeSetting mode = (ModeSetting)setting;
                    br.write(mode.name + ":" + mode.index + "\r\n");
                    continue;
                }
                if (setting instanceof KeybindSetting) {
                    KeybindSetting key = (KeybindSetting)setting;
                    br.write(key.name + ":" + key.code + "\r\n");
                    continue;
                }
                if (!(setting instanceof ParentSetting)) continue;
                ParentSetting par = (ParentSetting)setting;
                br.write(par.name + ":" + par.enabled + "\r\n");
                for (Setting setting2 : par.settings) {
                    if (setting2 instanceof BooleanSetting) {
                        BooleanSetting bool = (BooleanSetting)setting2;
                        br.write(bool.name + ":" + bool.enabled + "\r\n");
                        continue;
                    }
                    if (setting2 instanceof NumberSetting) {
                        NumberSetting number = (NumberSetting)setting2;
                        br.write(number.name + ":" + number.value + "\r\n");
                        continue;
                    }
                    if (setting2 instanceof ModeSetting) {
                        ModeSetting mode = (ModeSetting)setting2;
                        br.write(mode.name + ":" + mode.index + "\r\n");
                        continue;
                    }
                    if (!(setting2 instanceof KeybindSetting)) continue;
                    KeybindSetting key = (KeybindSetting)setting2;
                    br.write(key.name + ":" + key.code + "\r\n");
                }
            }
            br.write("enabled:" + module.toggled + "\r\n");
            br.close();
        }
    }

    private static void load_hacks() throws IOException {
        for (Module module : ModuleManager.modules) {
            String line;
            String file_name = ACTIVE_CONFIG_FOLDER + module.name + ".txt";
            File file = new File(file_name);
            FileInputStream fi_stream = new FileInputStream(file.getAbsolutePath());
            DataInputStream di_stream = new DataInputStream(fi_stream);
            BufferedReader br = new BufferedReader(new InputStreamReader(di_stream));
            ArrayList<String> bugged_lines = new ArrayList<String>();
            while ((line = br.readLine()) != null) {
                try {
                    String colune = line.trim();
                    String tag = colune.split(":")[0];
                    String value = colune.split(":")[1];
                    try {
                        for (Setting setting : module.settings) {
                            if (!Objects.equals(setting.name, tag)) continue;
                            if (setting instanceof BooleanSetting) {
                                BooleanSetting bool = (BooleanSetting)setting;
                                bool.setEnabled(Boolean.parseBoolean(value));
                                continue;
                            }
                            if (setting instanceof NumberSetting) {
                                NumberSetting number = (NumberSetting)setting;
                                number.value = Float.parseFloat(value);
                                continue;
                            }
                            if (setting instanceof ModeSetting) {
                                ModeSetting mode = (ModeSetting)setting;
                                mode.index = Integer.parseInt(value);
                                continue;
                            }
                            if (setting instanceof KeybindSetting) {
                                KeybindSetting key = (KeybindSetting)setting;
                                key.code = Integer.parseInt(value);
                                continue;
                            }
                            if (!(setting instanceof ParentSetting)) continue;
                            ParentSetting par = (ParentSetting)setting;
                            par.setEnabled(Boolean.parseBoolean(value));
                            try {
                                for (Setting setting2 : par.settings) {
                                    if (!Objects.equals(setting2.name, tag)) continue;
                                    if (setting2 instanceof BooleanSetting) {
                                        BooleanSetting bool = (BooleanSetting)setting2;
                                        bool.setEnabled(Boolean.parseBoolean(value));
                                        continue;
                                    }
                                    if (setting2 instanceof NumberSetting) {
                                        NumberSetting number = (NumberSetting)setting2;
                                        number.value = Float.parseFloat(value);
                                        continue;
                                    }
                                    if (setting2 instanceof ModeSetting) {
                                        ModeSetting mode = (ModeSetting)setting2;
                                        mode.index = Integer.parseInt(value);
                                        continue;
                                    }
                                    if (!(setting2 instanceof KeybindSetting)) continue;
                                    KeybindSetting key = (KeybindSetting)setting2;
                                    key.code = Integer.parseInt(value);
                                }
                            }
                            catch (Exception e) {
                                bugged_lines.add(colune);
                            }
                        }
                        module.setToggled(Boolean.parseBoolean(value));
                    }
                    catch (Exception e) {
                        bugged_lines.add(colune);
                    }
                }
                catch (Exception exception) {}
            }
            br.close();
        }
    }

    private static void save_client() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser parser = new JsonParser();
        JsonObject main_json = new JsonObject();
        JsonObject main_category = new JsonObject();
        for (Category category : Category.values()) {
            JsonObject frame_info = new JsonObject();
            frame_info.add("name", (JsonElement)new JsonPrimitive(category.name));
            frame_info.add("showModules", (JsonElement)new JsonPrimitive(Boolean.valueOf(category.showModules)));
            frame_info.add("x", (JsonElement)new JsonPrimitive((Number)category.posX));
            frame_info.add("y", (JsonElement)new JsonPrimitive((Number)category.posY));
            main_category.add(category.name, (JsonElement)frame_info);
        }
        main_json.add("category", (JsonElement)main_category);
        JsonElement pretty_json = parser.parse(main_json.toString());
        String json = gson.toJson(pretty_json);
        ConfigManagerOld.delete_file(CLIENT_DIR);
        ConfigManagerOld.verify_file(CLIENT_PATH);
        OutputStreamWriter file = new OutputStreamWriter((OutputStream)new FileOutputStream(CLIENT_DIR), StandardCharsets.UTF_8);
        file.write(json);
        file.close();
    }

    private static void load_client() throws IOException {
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

    private static void save_hud() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser parser = new JsonParser();
        JsonObject main_json = new JsonObject();
        JsonObject main_hud = new JsonObject();
        for (Hud hud : Main.hudManager.getHudList()) {
            JsonObject frame_info = new JsonObject();
            frame_info.add("name", (JsonElement)new JsonPrimitive(hud.name));
            frame_info.add("enabled", (JsonElement)new JsonPrimitive(Boolean.valueOf(hud.toggled)));
            frame_info.add("x", (JsonElement)new JsonPrimitive((Number)hud.posX));
            frame_info.add("y", (JsonElement)new JsonPrimitive((Number)hud.posY));
            main_hud.add(hud.name, (JsonElement)frame_info);
        }
        main_json.add("hud", (JsonElement)main_hud);
        JsonElement pretty_json = parser.parse(main_json.toString());
        String json = gson.toJson(pretty_json);
        ConfigManagerOld.delete_file(HUD_DIR);
        ConfigManagerOld.verify_file(HUD_PATH);
        OutputStreamWriter file = new OutputStreamWriter((OutputStream)new FileOutputStream(HUD_DIR), StandardCharsets.UTF_8);
        file.write(json);
        file.close();
    }

    private static void load_hud() throws IOException {
        try {
            InputStream input_stream = Files.newInputStream(HUD_PATH, new OpenOption[0]);
            JsonObject main_hud = new JsonParser().parse((Reader)new InputStreamReader(input_stream)).getAsJsonObject();
            JsonObject main_huds = main_hud.get("hud").getAsJsonObject();
            for (Hud hud : Main.hudManager.getHudList()) {
                JsonObject hud_info = main_huds.get(hud.name).getAsJsonObject();
                hud.setToggled(hud_info.get("enabled").getAsBoolean());
                hud.posX = hud_info.get("x").getAsInt();
                hud.posY = hud_info.get("y").getAsInt();
            }
            input_stream.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void save_settings() {
        try {
            ConfigManagerOld.verify_dir(MAIN_FOLDER_PATH);
            ConfigManagerOld.verify_dir(CONFIGS_FOLDER_PATH);
            ConfigManagerOld.verify_dir(ACTIVE_CONFIG_FOLDER_PATH);
            ConfigManagerOld.save_hacks();
            ConfigManagerOld.save_friends();
            ConfigManagerOld.save_enemies();
            ConfigManagerOld.save_client();
            ConfigManagerOld.save_ezmessage();
            ConfigManagerOld.save_hud();
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    public static void load_settings() {
        try {
            ConfigManagerOld.load_client();
            ConfigManagerOld.load_enemies();
            ConfigManagerOld.load_ezmessage();
            ConfigManagerOld.load_friends();
            ConfigManagerOld.load_hacks();
            ConfigManagerOld.load_hud();
        }
        catch (IOException iOException) {
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
}

