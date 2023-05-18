/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 */
package com.mrzak34.thunderhack.manager;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.command.Command;
import com.mrzak34.thunderhack.command.Commands;
import com.mrzak34.thunderhack.event.events.ChatEvent;
import com.mrzak34.thunderhack.manager.NotificationManager;
import com.mrzak34.thunderhack.util.Util;

public class CommandManager
implements Util {
    public static Commands command_list;

    public CommandManager() {
        command_list = new Commands();
    }

    public static void set_prefix(String new_prefix) {
        command_list.set_prefix(new_prefix);
    }

    public static String get_prefix() {
        return command_list.get_prefix();
    }

    @Subscribe
    public void onChat(ChatEvent event) {
        String message = event.getMessage();
        String[] message_args = command_list.get_message(event.getMessage());
        boolean true_command = false;
        if (message_args.length > 0) {
            event.setCancelled(true);
            for (Command command : Commands.get_pure_command_list()) {
                try {
                    if (!command_list.get_message(event.getMessage())[0].equalsIgnoreCase(command.get_name())) continue;
                    true_command = command.get_message(command_list.get_message(event.getMessage()));
                }
                catch (Exception exception) {}
            }
            if (!true_command && command_list.has_prefix(event.getMessage())) {
                NotificationManager.notif("Try using " + CommandManager.get_prefix() + "help list to see all commands");
                true_command = false;
            }
        }
    }
}

