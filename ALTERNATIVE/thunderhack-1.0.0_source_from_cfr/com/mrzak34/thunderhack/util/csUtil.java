/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1263
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_636
 */
package com.mrzak34.thunderhack.util;

import com.mrzak34.thunderhack.util.Util;
import net.minecraft.class_1263;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_636;

public class csUtil
implements Util {
    public static volatile csUtil a = new csUtil();

    public class_1263 a() {
        return csUtil.mc.field_1724.method_31548();
    }

    public class_636 b() {
        return csUtil.mc.field_1761;
    }

    public void a(class_1297 entity) {
        a.b().method_2918((class_1657)csUtil.mc.field_1724, entity);
    }

    public double d() {
        return csUtil.mc.field_1724.method_23317();
    }

    public double e() {
        return csUtil.mc.field_1724.method_23318();
    }

    public double f() {
        return csUtil.mc.field_1724.method_23321();
    }
}

