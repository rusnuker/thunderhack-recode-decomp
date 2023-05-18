/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 */
package com.mrzak34.thunderhack.module.modules.misc;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.manager.NotificationManager;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;

public class PacketLogger
extends Module {
    BooleanSetting send = new BooleanSetting("send", false);
    BooleanSetting receive = new BooleanSetting("receive", false);

    public PacketLogger() {
        super("PacketLogger", 0, false, Category.MISC);
        this.addSettings(this.send, this.receive);
    }

    @Subscribe
    public void PacketSend(PacketEvent.Send event) {
        if (this.send.isEnabled()) {
            NotificationManager.notif(event.getPacket().getClass().getName());
        }
    }

    @Subscribe
    public void PacketReceive(PacketEvent.Receive event) {
        if (this.receive.isEnabled()) {
            NotificationManager.notif(event.getPacket().getClass().getName());
        }
    }
}

