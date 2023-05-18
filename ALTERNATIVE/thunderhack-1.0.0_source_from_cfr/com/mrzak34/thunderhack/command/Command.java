/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 */
package com.mrzak34.thunderhack.command;

import com.mrzak34.thunderhack.manager.CommandManager;
import net.minecraft.class_310;

public class Command {
    String name;
    String description;
    public static class_310 mc = class_310.method_1551();

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public boolean get_message(String[] message) {
        return false;
    }

    public String get_name() {
        return this.name;
    }

    public String get_description() {
        return this.description;
    }

    public String current_prefix() {
        return CommandManager.get_prefix();
    }
}

