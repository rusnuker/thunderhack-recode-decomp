/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.util;

import com.mrzak34.thunderhack.util.TimeStamp;

public class CrystalTimeStamp
extends TimeStamp {
    private final float damage;
    private final boolean shield;

    public CrystalTimeStamp(float damage, boolean shield) {
        this.damage = damage;
        this.shield = shield;
    }

    public float getDamage() {
        return this.damage;
    }

    public boolean isShield() {
        return this.shield;
    }
}

