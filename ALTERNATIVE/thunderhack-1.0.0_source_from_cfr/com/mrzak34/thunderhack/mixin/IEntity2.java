/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1299
 */
package com.mrzak34.thunderhack.mixin;

import com.mrzak34.thunderhack.util.TimerUtil;
import net.minecraft.class_1299;

public interface IEntity2 {
    public boolean inWeb();

    public class_1299 getType();

    public long getDeathTime();

    public boolean isPseudoDead();

    public void setPseudoDead(boolean var1);

    public TimerUtil getPseudoTime();

    public long getTimeStamp();

    public void setDummy(boolean var1);

    public long getOldServerPosX();

    public long getOldServerPosY();

    public long getOldServerPosZ();

    public void setOldServerPos(long var1, long var3, long var5);
}

