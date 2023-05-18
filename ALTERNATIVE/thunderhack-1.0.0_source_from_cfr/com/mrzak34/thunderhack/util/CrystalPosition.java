/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2338
 */
package com.mrzak34.thunderhack.util;

import net.minecraft.class_2338;

public final class CrystalPosition {
    private final class_2338 position;
    private final double targetDamage;
    private final double selfDamage;

    public CrystalPosition(class_2338 position, double targetDamage, double selfDamage) {
        this.position = position;
        this.targetDamage = targetDamage;
        this.selfDamage = selfDamage;
    }

    public class_2338 getPosition() {
        return this.position;
    }

    public double getTargetDamage() {
        return this.targetDamage;
    }

    public double getSelfDamage() {
        return this.selfDamage;
    }
}

