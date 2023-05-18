/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  org.jetbrains.annotations.NotNull
 */
package com.mrzak34.thunderhack.Account;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.jetbrains.annotations.NotNull;

public class Auth {
    private static final String CLIENT_ID = "54fd49e4-2103-4044-9603-2b028c814ec3";
    private static final String REDIRECT_URI = "http://localhost:59125";
    private static final boolean BLIND_SSL;
    private static final boolean NO_CUSTOM_SSL;
    private static final SSLContext FIXED_CONTEXT;

    public static  @NotNull Map.Entry<@NotNull String, @NotNull String> codeToToken(@NotNull String code) throws Exception {
        HttpsURLConnection conn = (HttpsURLConnection)new URL("https://login.live.com/oauth20_token.srf").openConnection();
        if (FIXED_CONTEXT != null) {
            conn.setSSLSocketFactory(FIXED_CONTEXT.getSocketFactory());
        }
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(15000);
        conn.setReadTimeout(15000);
        conn.setDoOutput(true);
        try (OutputStream out = conn.getOutputStream();){
            AbstractMap.SimpleImmutableEntry<String, String> simpleImmutableEntry;
            out.write(("client_id=" + URLEncoder.encode(CLIENT_ID, "UTF-8") + "&code=" + URLEncoder.encode(code, "UTF-8") + "&grant_type=authorization_code&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, "UTF-8") + "&scope=XboxLive.signin%20XboxLive.offline_access").getBytes(StandardCharsets.UTF_8));
            if (conn.getResponseCode() < 200 || conn.getResponseCode() > 299) {
                try {
                    BufferedReader err = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8));
                    try {
                        throw new IllegalArgumentException("codeToToken response: " + conn.getResponseCode() + ", data: " + err.lines().collect(Collectors.joining("\n")));
                    }
                    catch (Throwable throwable) {
                        try {
                            err.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                        throw throwable;
                    }
                }
                catch (Throwable t) {
                    throw new IllegalArgumentException("codeToToken response: " + conn.getResponseCode(), t);
                }
            }
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));){
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonObject resp = (JsonObject)gson.fromJson(in.lines().collect(Collectors.joining("\n")), JsonObject.class);
                simpleImmutableEntry = new AbstractMap.SimpleImmutableEntry<String, String>(resp.get("access_token").getAsString(), resp.get("refresh_token").getAsString());
            }
            return simpleImmutableEntry;
        }
    }

    public static  @NotNull Map.Entry<@NotNull String, @NotNull String> refreshToken(@NotNull String refreshToken) throws Exception {
        HttpsURLConnection conn = (HttpsURLConnection)new URL("https://login.live.com/oauth20_token.srf").openConnection();
        if (FIXED_CONTEXT != null) {
            conn.setSSLSocketFactory(FIXED_CONTEXT.getSocketFactory());
        }
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(15000);
        conn.setReadTimeout(15000);
        conn.setDoOutput(true);
        try (OutputStream out = conn.getOutputStream();){
            AbstractMap.SimpleImmutableEntry<String, String> simpleImmutableEntry;
            out.write(("client_id=" + URLEncoder.encode(CLIENT_ID, "UTF-8") + "&refresh_token=" + URLEncoder.encode(refreshToken, "UTF-8") + "&grant_type=refresh_token&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, "UTF-8") + "&scope=XboxLive.signin%20XboxLive.offline_access").getBytes(StandardCharsets.UTF_8));
            if (conn.getResponseCode() < 200 || conn.getResponseCode() > 299) {
                try {
                    BufferedReader err = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8));
                    try {
                        throw new IllegalArgumentException("refreshToken response: " + conn.getResponseCode() + ", data: " + err.lines().collect(Collectors.joining("\n")));
                    }
                    catch (Throwable throwable) {
                        try {
                            err.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                        throw throwable;
                    }
                }
                catch (Throwable t) {
                    throw new IllegalArgumentException("refreshToken response: " + conn.getResponseCode(), t);
                }
            }
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));){
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonObject resp = (JsonObject)gson.fromJson(in.lines().collect(Collectors.joining("\n")), JsonObject.class);
                simpleImmutableEntry = new AbstractMap.SimpleImmutableEntry<String, String>(resp.get("access_token").getAsString(), resp.get("refresh_token").getAsString());
            }
            return simpleImmutableEntry;
        }
    }

    @NotNull
    public static String authXBL(@NotNull String authToken) throws Exception {
        HttpsURLConnection conn = (HttpsURLConnection)new URL("https://user.auth.xboxlive.com/user/authenticate").openConnection();
        if (FIXED_CONTEXT != null) {
            conn.setSSLSocketFactory(FIXED_CONTEXT.getSocketFactory());
        }
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(15000);
        conn.setReadTimeout(15000);
        conn.setDoOutput(true);
        try (OutputStream out = conn.getOutputStream();){
            String string;
            JsonObject req = new JsonObject();
            JsonObject reqProps = new JsonObject();
            reqProps.addProperty("AuthMethod", "RPS");
            reqProps.addProperty("SiteName", "user.auth.xboxlive.com");
            reqProps.addProperty("RpsTicket", "d=" + authToken);
            req.add("Properties", (JsonElement)reqProps);
            req.addProperty("RelyingParty", "http://auth.xboxlive.com");
            req.addProperty("TokenType", "JWT");
            out.write(req.toString().getBytes(StandardCharsets.UTF_8));
            if (conn.getResponseCode() < 200 || conn.getResponseCode() > 299) {
                try {
                    BufferedReader err = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8));
                    try {
                        throw new IllegalArgumentException("authXBL response: " + conn.getResponseCode() + ", data: " + err.lines().collect(Collectors.joining("\n")));
                    }
                    catch (Throwable throwable) {
                        try {
                            err.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                        throw throwable;
                    }
                }
                catch (Throwable t) {
                    throw new IllegalArgumentException("authXBL response: " + conn.getResponseCode(), t);
                }
            }
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));){
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonObject resp = (JsonObject)gson.fromJson(in.lines().collect(Collectors.joining("\n")), JsonObject.class);
                string = resp.get("Token").getAsString();
            }
            return string;
        }
    }

    public static  @NotNull Map.Entry<@NotNull String, @NotNull String> authXSTS(@NotNull String xblToken) throws Exception {
        HttpsURLConnection conn = (HttpsURLConnection)new URL("https://xsts.auth.xboxlive.com/xsts/authorize").openConnection();
        if (FIXED_CONTEXT != null) {
            conn.setSSLSocketFactory(FIXED_CONTEXT.getSocketFactory());
        }
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(15000);
        conn.setReadTimeout(15000);
        conn.setDoOutput(true);
        try (OutputStream out = conn.getOutputStream();){
            AbstractMap.SimpleImmutableEntry<String, String> simpleImmutableEntry;
            JsonObject req = new JsonObject();
            JsonObject reqProps = new JsonObject();
            JsonArray userTokens = new JsonArray();
            userTokens.add(xblToken);
            reqProps.add("UserTokens", (JsonElement)userTokens);
            reqProps.addProperty("SandboxId", "RETAIL");
            req.add("Properties", (JsonElement)reqProps);
            req.addProperty("RelyingParty", "rp://api.minecraftservices.com/");
            req.addProperty("TokenType", "JWT");
            out.write(req.toString().getBytes(StandardCharsets.UTF_8));
            if (conn.getResponseCode() < 200 || conn.getResponseCode() > 299) {
                try {
                    BufferedReader err = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8));
                    try {
                        throw new IllegalArgumentException("authXSTS response: " + conn.getResponseCode() + ", data: " + err.lines().collect(Collectors.joining("\n")));
                    }
                    catch (Throwable throwable) {
                        try {
                            err.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                        throw throwable;
                    }
                }
                catch (Throwable t) {
                    throw new IllegalArgumentException("authXSTS response: " + conn.getResponseCode(), t);
                }
            }
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));){
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonObject resp = (JsonObject)gson.fromJson(in.lines().collect(Collectors.joining("\n")), JsonObject.class);
                simpleImmutableEntry = new AbstractMap.SimpleImmutableEntry<String, String>(resp.get("Token").getAsString(), resp.getAsJsonObject("DisplayClaims").getAsJsonArray("xui").get(0).getAsJsonObject().get("uhs").getAsString());
            }
            return simpleImmutableEntry;
        }
    }

    @NotNull
    public static String authMinecraft(@NotNull String userHash, @NotNull String xstsToken) throws Exception {
        HttpsURLConnection conn = (HttpsURLConnection)new URL("https://api.minecraftservices.com/authentication/login_with_xbox").openConnection();
        if (FIXED_CONTEXT != null) {
            conn.setSSLSocketFactory(FIXED_CONTEXT.getSocketFactory());
        }
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(15000);
        conn.setReadTimeout(15000);
        conn.setDoOutput(true);
        try (OutputStream out = conn.getOutputStream();){
            String string;
            JsonObject req = new JsonObject();
            req.addProperty("identityToken", "XBL3.0 x=" + userHash + ";" + xstsToken);
            out.write(req.toString().getBytes(StandardCharsets.UTF_8));
            if (conn.getResponseCode() < 200 || conn.getResponseCode() > 299) {
                try {
                    BufferedReader err = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8));
                    try {
                        throw new IllegalArgumentException("authMinecraft response: " + conn.getResponseCode() + ", data: " + err.lines().collect(Collectors.joining("\n")));
                    }
                    catch (Throwable throwable) {
                        try {
                            err.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                        throw throwable;
                    }
                }
                catch (Throwable t) {
                    throw new IllegalArgumentException("authMinecraft response: " + conn.getResponseCode(), t);
                }
            }
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));){
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonObject resp = (JsonObject)gson.fromJson(in.lines().collect(Collectors.joining("\n")), JsonObject.class);
                string = resp.get("access_token").getAsString();
            }
            return string;
        }
    }

    public static  @NotNull Map.Entry<@NotNull UUID, @NotNull String> getProfile(@NotNull String accessToken) throws Exception {
        HttpURLConnection conn = (HttpURLConnection)new URL("https://api.minecraftservices.com/minecraft/profile").openConnection();
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
        conn.setConnectTimeout(15000);
        conn.setReadTimeout(15000);
        if (conn.getResponseCode() < 200 || conn.getResponseCode() > 299) {
            try {
                BufferedReader err = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8));
                try {
                    throw new IllegalArgumentException("getProfile response: " + conn.getResponseCode() + ", data: " + err.lines().collect(Collectors.joining("\n")));
                }
                catch (Throwable throwable) {
                    try {
                        err.close();
                    }
                    catch (Throwable throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                    throw throwable;
                }
            }
            catch (Throwable t) {
                throw new IllegalArgumentException("getProfile response: " + conn.getResponseCode(), t);
            }
        }
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject resp = (JsonObject)gson.fromJson(in.lines().collect(Collectors.joining("\n")), JsonObject.class);
            AbstractMap.SimpleImmutableEntry<UUID, String> simpleImmutableEntry = new AbstractMap.SimpleImmutableEntry<UUID, String>(UUID.fromString(resp.get("id").getAsString().replaceFirst("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5")), resp.get("name").getAsString());
            return simpleImmutableEntry;
        }
    }

    @NotNull
    public static UUID resolveUUID(@NotNull String name) {
        UUID uUID;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        InputStreamReader in = new InputStreamReader(new URL("https://api.mojang.com/users/profiles/minecraft/" + name).openStream(), StandardCharsets.UTF_8);
        try {
            UUID uuid;
            uUID = uuid = UUID.fromString(((JsonObject)gson.fromJson((Reader)in, JsonObject.class)).get("id").getAsString().replaceFirst("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5"));
        }
        catch (Throwable uuid) {
            try {
                try {
                    in.close();
                }
                catch (Throwable throwable) {
                    uuid.addSuppressed(throwable);
                }
                throw uuid;
            }
            catch (Throwable ignored) {
                UUID uuid2 = UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(StandardCharsets.UTF_8));
                return uuid2;
            }
        }
        in.close();
        return uUID;
    }

    static {
        SSLContext ctx;
        block10: {
            BLIND_SSL = Boolean.getBoolean("ias.blindSSL");
            NO_CUSTOM_SSL = Boolean.getBoolean("ias.noCustomSSL");
            ctx = null;
            try {
                if (BLIND_SSL) {
                    System.out.println("========== IAS: WARNING ==========");
                    System.out.println("You've enabled 'ias.blindSSL' property.");
                    System.out.println("(probably via JVM-argument '-Dias.blindSSL=true')");
                    System.out.println("While this may fix some SSL problems, it's UNSAFE!");
                    System.out.println("Do NOT use this option as a 'permanent solution to all problems',");
                    System.out.println("nag the mod authors if any problems arrive:");
                    System.out.println("https://github.com/The-Fireplace-Minecraft-Mods/In-Game-Account-Switcher/issues");
                    System.out.println("========== IAS: WARNING ==========");
                    X509TrustManager blindManager = new X509TrustManager(){

                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    };
                    ctx = SSLContext.getInstance("TLS");
                    ctx.init(null, new TrustManager[]{blindManager}, new SecureRandom());
                    System.out.println("Blindly skipping SSL checks. (behavior: 'ias.blindSSL' property)");
                    break block10;
                }
                if (!NO_CUSTOM_SSL) {
                    KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
                    try (InputStream in = Auth.class.getResourceAsStream("/iasjavafix.jks");){
                        ks.load(in, "iasjavafix".toCharArray());
                    }
                    TrustManagerFactory customTmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                    customTmf.init(ks);
                    TrustManagerFactory defaultTmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                    defaultTmf.init((KeyStore)null);
                    final ArrayList managers = new ArrayList();
                    managers.addAll(Arrays.stream(customTmf.getTrustManagers()).filter(tm -> tm instanceof X509TrustManager).map(tm -> (X509TrustManager)tm).collect(Collectors.toList()));
                    managers.addAll(Arrays.stream(defaultTmf.getTrustManagers()).filter(tm -> tm instanceof X509TrustManager).map(tm -> (X509TrustManager)tm).collect(Collectors.toList()));
                    X509TrustManager multiManager = new X509TrustManager(){

                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                            CertificateException wrapper = new CertificateException("Unable to validate via any trust manager.");
                            for (X509TrustManager manager : managers) {
                                try {
                                    manager.checkClientTrusted(chain, authType);
                                    return;
                                }
                                catch (Throwable t) {
                                    wrapper.addSuppressed(t);
                                }
                            }
                            throw wrapper;
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                            CertificateException wrapper = new CertificateException("Unable to validate via any trust manager.");
                            for (X509TrustManager manager : managers) {
                                try {
                                    manager.checkServerTrusted(chain, authType);
                                    return;
                                }
                                catch (Throwable t) {
                                    wrapper.addSuppressed(t);
                                }
                            }
                            throw wrapper;
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            ArrayList<X509Certificate> certificates = new ArrayList<X509Certificate>();
                            for (X509TrustManager manager : managers) {
                                certificates.addAll(Arrays.asList(manager.getAcceptedIssuers()));
                            }
                            return certificates.toArray(new X509Certificate[0]);
                        }
                    };
                    ctx = SSLContext.getInstance("TLS");
                    ctx.init(null, new TrustManager[]{multiManager}, new SecureRandom());
                    System.out.println("Using shared SSL context. (behavior: default; custom + default certificates)");
                    break block10;
                }
                System.out.println("Not editing SSL context. (behavior: 'ias.noCustomSSL' property)");
            }
            catch (Throwable t) {
                System.out.println("Unable to init SSL context.");
            }
        }
        FIXED_CONTEXT = ctx;
    }
}

