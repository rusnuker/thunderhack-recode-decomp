/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1657
 *  net.minecraft.class_1713
 *  net.minecraft.class_1747
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2248
 *  net.minecraft.class_2596
 *  net.minecraft.class_2868
 *  net.minecraft.class_310
 */
package com.mrzak34.thunderhack.util;

import com.mrzak34.thunderhack.util.Util;
import java.util.Comparator;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2248;
import net.minecraft.class_2596;
import net.minecraft.class_2868;
import net.minecraft.class_310;

public class InventoryUtil
implements Util {
    private static final class_310 mc = class_310.method_1551();

    public static int getSlot(boolean offhand, boolean reverse, Comparator<Integer> comparator) {
        return IntStream.of(InventoryUtil.getInventorySlots(offhand)).boxed().min(reverse ? comparator.reversed() : comparator).get();
    }

    public static class_1268 selectSlot(boolean offhand, IntPredicate filter) {
        return InventoryUtil.selectSlot(InventoryUtil.getSlot(offhand, filter));
    }

    public static int getSlot(boolean offhand, IntPredicate filter) {
        return IntStream.of(InventoryUtil.getInventorySlots(offhand)).filter(filter).findFirst().orElse(-1);
    }

    public static int findHotbarBlock(class_2248 ... blockIn) {
        for (int i = 0; i < 9; ++i) {
            class_1799 stack = InventoryUtil.mc.field_1724.method_31548().method_5438(i);
            if (stack == class_1799.field_8037 || !(stack.method_7909() instanceof class_1747)) continue;
            return i;
        }
        return -1;
    }

    public static class_1268 selectSlot(int slot) {
        if (slot >= 0 && slot <= 36) {
            if (slot < 9) {
                if (slot != InventoryUtil.mc.field_1724.method_31548().field_7545) {
                    InventoryUtil.mc.field_1724.method_31548().field_7545 = slot;
                    InventoryUtil.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(slot));
                }
                return class_1268.field_5808;
            }
            if (InventoryUtil.mc.field_1724.field_7498 == InventoryUtil.mc.field_1724.field_7512) {
                for (int i = 0; i <= 8; ++i) {
                    if (!InventoryUtil.mc.field_1724.method_31548().method_5438(i).method_7960()) continue;
                    InventoryUtil.mc.field_1761.method_2906(InventoryUtil.mc.field_1724.field_7512.field_7763, slot, 0, class_1713.field_7794, (class_1657)InventoryUtil.mc.field_1724);
                    if (i != InventoryUtil.mc.field_1724.method_31548().field_7545) {
                        InventoryUtil.mc.field_1724.method_31548().field_7545 = i;
                        InventoryUtil.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(i));
                    }
                    return class_1268.field_5808;
                }
                InventoryUtil.mc.field_1761.method_2906(InventoryUtil.mc.field_1724.field_7512.field_7763, slot, 0, class_1713.field_7790, (class_1657)InventoryUtil.mc.field_1724);
                InventoryUtil.mc.field_1761.method_2906(InventoryUtil.mc.field_1724.field_7512.field_7763, 36 + InventoryUtil.mc.field_1724.method_31548().field_7545, 0, class_1713.field_7790, (class_1657)InventoryUtil.mc.field_1724);
                InventoryUtil.mc.field_1761.method_2906(InventoryUtil.mc.field_1724.field_7512.field_7763, slot, 0, class_1713.field_7790, (class_1657)InventoryUtil.mc.field_1724);
                return class_1268.field_5808;
            }
        } else if (slot == 40) {
            return class_1268.field_5810;
        }
        return null;
    }

    public static void switchToHotbarSlot(class_1792 item, boolean silent) {
        int slot = InventoryUtil.findHotbarItem(item);
        if (slot > -1) {
            InventoryUtil.switchToHotbarSlot(slot, silent);
        }
    }

    public static void switchToHotbarSlot(int slot, boolean silent) {
        if (InventoryUtil.mc.field_1724.method_31548().field_7545 != slot && slot >= 0) {
            if (silent) {
                InventoryUtil.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(slot));
            } else {
                InventoryUtil.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(slot));
                InventoryUtil.mc.field_1724.method_31548().field_7545 = slot;
            }
        }
    }

    public static int findHotbarItem(class_1792 item) {
        for (int i = 0; i <= 8; ++i) {
            class_1799 stack = InventoryUtil.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7909() != item) continue;
            return i;
        }
        return -1;
    }

    public static int[] getInventorySlots(boolean offhand) {
        int[] i = new int[offhand ? 38 : 37];
        i[0] = InventoryUtil.mc.field_1724.method_31548().field_7545;
        i[1] = 40;
        for (int j = 0; j < 36; ++j) {
            if (j == InventoryUtil.mc.field_1724.method_31548().field_7545) continue;
            i[offhand ? j + 2 : j + 1] = j;
        }
        return i;
    }

    public static int getCappuchinoAtHotbar() {
        for (int i = 0; i < 9; ++i) {
            class_1799 itemStack = Util.mc.field_1724.method_31548().method_5438(i);
            if (itemStack.method_7909() != class_1802.field_8574) continue;
            class_1792 maybepot = itemStack.method_7909();
            if (!itemStack.method_7964().toString().contains("\u041a\u0430\u043f\u043f\u0443\u0447\u0438\u043d\u043e")) continue;
            return i;
        }
        return -1;
    }

    public static int getAmericanoAtHotbar() {
        for (int i = 0; i < 9; ++i) {
            class_1799 itemStack = Util.mc.field_1724.method_31548().method_5438(i);
            if (itemStack.method_7909() != class_1802.field_8574) continue;
            class_1792 maybepot = itemStack.method_7909();
            if (!itemStack.method_7964().toString().contains("\u0410\u043c\u0435\u0440\u0438\u043a\u0430\u043d\u043e")) continue;
            return i;
        }
        return -1;
    }

    public static class_1799 getPotionItemStack() {
        for (int i = 0; i < 9; ++i) {
            class_1799 itemStack = Util.mc.field_1724.method_31548().method_5438(i);
            if (itemStack.method_7909() != class_1802.field_8574) continue;
            class_1792 maybepot = itemStack.method_7909();
            if (!itemStack.method_7964().toString().contains("\u041a\u0430\u043f\u043f\u0443\u0447\u0438\u043d\u043e")) continue;
            return itemStack;
        }
        return null;
    }

    public static class Task {
        private final int slot;
        private final boolean update;
        private final boolean quickClick;

        public Task() {
            this.update = true;
            this.slot = -1;
            this.quickClick = false;
        }

        public Task(int slot) {
            this.slot = slot;
            this.quickClick = false;
            this.update = false;
        }

        public Task(int slot, boolean quickClick) {
            this.slot = slot;
            this.quickClick = quickClick;
            this.update = false;
        }

        public void run() {
            if (this.update) {
                InventoryUtil.mc.field_1761.method_2927();
            }
            if (this.slot != -1) {
                InventoryUtil.mc.field_1761.method_2906(InventoryUtil.mc.field_1724.field_7512.field_7763, this.slot, 0, this.quickClick ? class_1713.field_7794 : class_1713.field_7790, (class_1657)InventoryUtil.mc.field_1724);
            }
        }

        public boolean isSwitching() {
            return !this.update;
        }
    }
}

