/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.util;

import com.mrzak34.thunderhack.util.FakeCrystalEntity;
import com.mrzak34.thunderhack.util.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class FakeCrystalUtil
implements Util {
    private static final List<FakeCrystalEntity> ENTITIES = new ArrayList<FakeCrystalEntity>();

    public static FakeCrystalEntity get(String name) {
        for (FakeCrystalEntity fp : ENTITIES) {
            if (!fp.method_5820().equals(name)) continue;
            return fp;
        }
        return null;
    }

    public static void add(double x, double y, double z) {
        FakeCrystalEntity fakePlayer = new FakeCrystalEntity(x, y, z);
        fakePlayer.spawn();
        ENTITIES.add(fakePlayer);
    }

    public static void remove(FakeCrystalEntity fp) {
        ENTITIES.removeIf(fp1 -> {
            if (fp1.method_5820().equals(fp.method_5820())) {
                fp1.despawn();
                return true;
            }
            return false;
        });
    }

    public static void clear() {
        if (ENTITIES.isEmpty()) {
            return;
        }
        ENTITIES.forEach(FakeCrystalEntity::despawn);
        ENTITIES.clear();
    }

    public static void forEach(Consumer<FakeCrystalEntity> action) {
        for (FakeCrystalEntity fakePlayer : ENTITIES) {
            action.accept(fakePlayer);
        }
    }

    public static int count() {
        return ENTITIES.size();
    }

    public static Stream<FakeCrystalEntity> stream() {
        return ENTITIES.stream();
    }

    public static boolean contains(FakeCrystalEntity fp) {
        return ENTITIES.contains((Object)fp);
    }
}

