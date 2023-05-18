/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 */
package com.mrzak34.thunderhack.module.modules.misc;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.MouseButtonEvent;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.Setting;
import com.mrzak34.thunderhack.util.FriendUtil;
import net.minecraft.class_1297;
import net.minecraft.class_1657;

public class MiddleClickFriends
extends Module {
    public MiddleClickFriends() {
        super("MiddleClickFriends", 0, false, Category.MISC);
        this.addSettings(new Setting[0]);
    }

    @Subscribe
    public void onMouseButton(MouseButtonEvent event) {
        class_1297 class_12972;
        if (event.getAction() == 0 || event.getButton() != 2 || MiddleClickFriends.mc.field_1755 != null || MiddleClickFriends.mc.field_1692 == null || !((class_12972 = MiddleClickFriends.mc.field_1692) instanceof class_1657)) {
            return;
        }
        class_1657 player = (class_1657)class_12972;
        if (FriendUtil.isFriend(player.method_5477().getString())) {
            f = FriendUtil.friends.stream().filter(friend -> friend.getUsername().equalsIgnoreCase(player.method_5477().getString())).findFirst().get();
            FriendUtil.friends.remove(f);
        } else {
            f = FriendUtil.get_friend_object(player.method_5477().getString());
            FriendUtil.friends.add(f);
        }
    }
}

