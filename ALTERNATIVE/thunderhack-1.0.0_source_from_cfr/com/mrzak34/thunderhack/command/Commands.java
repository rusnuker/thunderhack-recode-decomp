/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.command;

import com.mrzak34.thunderhack.command.Command;
import com.mrzak34.thunderhack.command.ThunderString;
import com.mrzak34.thunderhack.command.commands.Friend;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Commands {
    public static ArrayList<Command> command_list = new ArrayList();
    static HashMap<String, Command> list_command = new HashMap();
    public static final ThunderString prefix = new ThunderString("Prefix", "Prefix", ".");

    public Commands() {
        Commands.add_command(new Friend());
        command_list.sort(Comparator.comparing(Command::get_name));
    }

    public static void add_command(Command command) {
        command_list.add(command);
        list_command.put(command.get_name().toLowerCase(), command);
    }

    public String[] get_message(String message) {
        String[] arguments = new String[]{};
        if (this.has_prefix(message)) {
            arguments = message.replaceFirst(prefix.get_value(), "").split(" ");
        }
        return arguments;
    }

    public boolean has_prefix(String message) {
        return message.startsWith(prefix.get_value());
    }

    public void set_prefix(String new_prefix) {
        prefix.set_value(new_prefix);
    }

    public String get_prefix() {
        return prefix.get_value();
    }

    public static ArrayList<Command> get_pure_command_list() {
        return command_list;
    }

    public static Command get_command_with_name(String name) {
        return list_command.get(name.toLowerCase());
    }
}

