/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 */
package com.mrzak34.thunderhack.module.modules.thread;

import com.mrzak34.thunderhack.manager.EyeOfGod;
import java.io.IOException;
import net.minecraft.class_310;

public class ThreadSendMessage
extends Thread {
    public static class_310 mc = class_310.method_1551();
    String message;

    public ThreadSendMessage(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        try {
            EyeOfGod.sendMessage(this.message);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        super.run();
    }
}

