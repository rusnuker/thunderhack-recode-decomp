/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.event.events;

public final class KeyAction
extends Enum<KeyAction> {
    public static final /* enum */ KeyAction Press = new KeyAction();
    public static final /* enum */ KeyAction Repeat = new KeyAction();
    public static final /* enum */ KeyAction Release = new KeyAction();
    private static final /* synthetic */ KeyAction[] $VALUES;

    public static KeyAction[] values() {
        return (KeyAction[])$VALUES.clone();
    }

    public static KeyAction valueOf(String name) {
        return Enum.valueOf(KeyAction.class, name);
    }

    public static KeyAction get(int action) {
        if (action == 1) {
            return Press;
        }
        return action == 0 ? Release : Repeat;
    }

    private static /* synthetic */ KeyAction[] $values() {
        return new KeyAction[]{Press, Repeat, Release};
    }

    static {
        $VALUES = KeyAction.$values();
    }
}

