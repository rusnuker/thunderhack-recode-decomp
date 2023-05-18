/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonParser
 *  com.mojang.util.UUIDTypeAdapter
 *  net.minecraft.class_640
 */
package com.mrzak34.thunderhack.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.util.UUIDTypeAdapter;
import com.mrzak34.thunderhack.util.Util;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import net.minecraft.class_640;

public class EnemyUtil
implements Util {
    public static ArrayList<Enemy> enemies = new ArrayList();

    public static boolean isEnemy(String name) {
        return enemies.stream().anyMatch(enemy -> enemy.username.equalsIgnoreCase(name));
    }

    public static Enemy get_enemy_object(String name) {
        ArrayList infoMap = new ArrayList(EnemyUtil.mc.field_1724.field_3944.method_2880());
        class_640 profile = infoMap.stream().filter(networkPlayerInfo -> networkPlayerInfo.method_2966().getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        if (profile == null) {
            JsonElement element;
            String s = EnemyUtil.request_ids("[\"" + name + "\"]");
            if (s != null && !s.isEmpty() && (element = new JsonParser().parse(s)).getAsJsonArray().size() != 0) {
                try {
                    String id = element.getAsJsonArray().get(0).getAsJsonObject().get("id").getAsString();
                    String username = element.getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();
                    return new Enemy(username, UUIDTypeAdapter.fromString((String)id));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        return new Enemy(profile.method_2966().getName(), profile.method_2966().getId());
    }

    private static String request_ids(String data) {
        try {
            String query = "https://api.mojang.com/profiles/minecraft";
            URL url = new URL(query);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            os.write(data.getBytes("UTF-8"));
            os.close();
            BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
            String res = EnemyUtil.convertStreamToString(in);
            ((InputStream)in).close();
            conn.disconnect();
            return res;
        }
        catch (Exception e) {
            return null;
        }
    }

    private static String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        String r = s.hasNext() ? s.next() : "/";
        return r;
    }

    public static class Enemy {
        String username;
        UUID uuid;

        public Enemy(String username, UUID uuid) {
            this.username = username;
            this.uuid = uuid;
        }

        public String getUsername() {
            return this.username;
        }
    }
}

