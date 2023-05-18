/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.EventBus
 *  net.fabricmc.api.ModInitializer
 *  net.minecraft.class_2960
 *  net.minecraft.class_310
 *  net.minecraft.class_437
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.mrzak34.thunderhack;

import com.google.common.eventbus.EventBus;
import com.mrzak34.thunderhack.Hud.HudManager;
import com.mrzak34.thunderhack.manager.CommandManager;
import com.mrzak34.thunderhack.manager.ConfigManager;
import com.mrzak34.thunderhack.manager.EyeOfGod;
import com.mrzak34.thunderhack.manager.ModuleManager;
import com.mrzak34.thunderhack.manager.RotationManager;
import com.mrzak34.thunderhack.manager.TotemPopManager;
import com.mrzak34.thunderhack.module.Module;
import meteordevelopment.orbit.listeners.RpcListener;
import net.fabricmc.api.ModInitializer;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_437;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main
implements ModInitializer {
    public static final Main INSTANCE = new Main();
    public static EventBus EVENT_BUS = new EventBus();
    public static ModuleManager moduleManager;
    public static HudManager hudManager;
    public static TotemPopManager totemPopManager;
    public static RotationManager rotationManager;
    public static CommandManager commandManager;
    public static EyeOfGod eyeOfGod;
    public static RpcListener rpcListener;
    public static final Logger LOGGER;
    public static final String VERSION = "1.0";
    public static class_437 currentScreen;
    public static float TICK_TIMER;
    private class_310 mc = class_310.method_1551();

    public static ModuleManager getModules() {
        return moduleManager;
    }

    public static ModuleManager getParents() {
        return moduleManager;
    }

    public void onInitialize() {
        LOGGER.info("ThunderHack Loading");
        moduleManager = new ModuleManager();
        hudManager = new HudManager();
        totemPopManager = new TotemPopManager();
        rotationManager = new RotationManager();
        commandManager = new CommandManager();
        rpcListener = new RpcListener();
        eyeOfGod = new EyeOfGod();
        EVENT_BUS.register((Object)totemPopManager);
        EVENT_BUS.register((Object)rotationManager);
        EVENT_BUS.register((Object)commandManager);
        EVENT_BUS.register((Object)eyeOfGod);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        RpcListener.search();
        ConfigManager.loadSettings();
    }

    public static class_2960 identifier(String path) {
        return new class_2960("fabricloader", path);
    }

    public void KeyPress(int key, int action) {
        if (this.mc.field_1755 != null || Module.fullNullCheck()) {
            return;
        }
        if (action == 1) {
            if (key <= 0) {
                return;
            }
            for (Module m : ModuleManager.modules) {
                if (m.getKey() != key || key <= 0) continue;
                m.toggle();
            }
        }
    }

    public void onTick() {
        for (Module m : moduleManager.getModuleList()) {
            if (!m.toggled || Module.fullNullCheck()) continue;
            m.onTick();
        }
    }

    static {
        LOGGER = LoggerFactory.getLogger(Main.class);
        currentScreen = null;
        TICK_TIMER = 1.0f;
    }
}

