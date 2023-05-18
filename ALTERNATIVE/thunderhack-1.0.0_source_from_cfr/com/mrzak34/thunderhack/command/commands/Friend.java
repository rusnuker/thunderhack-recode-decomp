/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_124
 */
package com.mrzak34.thunderhack.command.commands;

import com.mrzak34.thunderhack.command.Command;
import com.mrzak34.thunderhack.manager.NotificationManager;
import com.mrzak34.thunderhack.util.FriendUtil;
import net.minecraft.class_124;

public class Friend
extends Command {
    public static class_124 red = class_124.field_1061;
    public static class_124 green = class_124.field_1060;
    public static class_124 bold = class_124.field_1067;
    public static class_124 reset = class_124.field_1070;

    public Friend() {
        super("friend", "To add friends");
    }

    @Override
    public boolean get_message(String[] message) {
        if (message.length == 1) {
            NotificationManager.notif("Add - add friend");
            NotificationManager.notif("Del - delete friend");
            NotificationManager.notif("List - list friends");
            return true;
        }
        if (message.length == 2) {
            if (message[1].equalsIgnoreCase("list")) {
                if (FriendUtil.friends.isEmpty()) {
                    NotificationManager.notif("You appear to have " + red + bold + "no" + reset + " friends :(");
                } else {
                    for (FriendUtil.Friend friend2 : FriendUtil.friends) {
                        NotificationManager.notif(green + bold + friend2.getUsername());
                    }
                }
                return true;
            }
            if (FriendUtil.isFriend(message[1])) {
                NotificationManager.notif("Player " + green + bold + message[1] + reset + " is your friend :D");
                return true;
            }
            NotificationManager.notif("Player " + red + bold + message[1] + reset + " is not your friend :(");
            return true;
        }
        if (message.length >= 3) {
            if (message[1].equalsIgnoreCase("add")) {
                if (FriendUtil.isFriend(message[2])) {
                    NotificationManager.notif("Player " + green + bold + message[2] + reset + " is already your friend :D");
                    return true;
                }
                FriendUtil.Friend f = FriendUtil.get_friend_object(message[2]);
                if (f == null) {
                    NotificationManager.notif("Cannot find " + red + bold + "UUID" + reset + " for that player :(");
                    return true;
                }
                FriendUtil.friends.add(f);
                NotificationManager.notif("Player " + green + bold + message[2] + reset + " is now your friend :D");
                return true;
            }
            if (message[1].equalsIgnoreCase("del") || message[1].equalsIgnoreCase("remove") || message[1].equalsIgnoreCase("delete")) {
                if (!FriendUtil.isFriend(message[2])) {
                    NotificationManager.notif("Player " + red + bold + message[2] + reset + " is already not your friend :/");
                    return true;
                }
                FriendUtil.Friend f = FriendUtil.friends.stream().filter(friend -> friend.getUsername().equalsIgnoreCase(message[2])).findFirst().get();
                FriendUtil.friends.remove(f);
                NotificationManager.notif("Player " + red + bold + message[2] + reset + " is now not your friend :(");
                return true;
            }
        }
        return true;
    }
}

