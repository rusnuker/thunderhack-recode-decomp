/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  net.minecraft.class_1297
 *  org.jetbrains.annotations.Nullable
 */
package com.mrzak34.thunderhack.util;

import com.google.common.base.Predicate;
import net.minecraft.class_1297;
import org.jetbrains.annotations.Nullable;

public class HUtil
implements Predicate {
    public boolean apply(@Nullable Object input) {
        class_1297 entity = (class_1297)input;
        return entity != null && entity.method_30948();
    }
}

