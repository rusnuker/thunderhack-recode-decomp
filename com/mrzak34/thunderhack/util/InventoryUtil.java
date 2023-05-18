//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.util;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import net.minecraft.*;

public class InventoryUtil implements Util
{
    private static final class_310 mc;
    
    public static int getSlot(final boolean offhand, final boolean reverse, final Comparator<Integer> comparator) {
        return IntStream.of(getInventorySlots(offhand)).boxed().min(reverse ? comparator.reversed() : comparator).get();
    }
    
    public static class_1268 selectSlot(final boolean offhand, final IntPredicate filter) {
        return selectSlot(getSlot(offhand, filter));
    }
    
    public static int getSlot(final boolean offhand, final IntPredicate filter) {
        return IntStream.of(getInventorySlots(offhand)).filter(filter).findFirst().orElse(-1);
    }
    
    public static int findHotbarBlock(final class_2248... blockIn) {
        for (int i = 0; i < 9; ++i) {
            final class_1799 stack = InventoryUtil.mc.field_1724.method_31548().method_5438(i);
            if (stack != class_1799.field_8037 && stack.method_7909() instanceof class_1747) {
                return i;
            }
        }
        return -1;
    }
    
    public static class_1268 selectSlot(final int slot) {
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
                    if (InventoryUtil.mc.field_1724.method_31548().method_5438(i).method_7960()) {
                        InventoryUtil.mc.field_1761.method_2906(InventoryUtil.mc.field_1724.field_7512.field_7763, slot, 0, class_1713.field_7794, (class_1657)InventoryUtil.mc.field_1724);
                        if (i != InventoryUtil.mc.field_1724.method_31548().field_7545) {
                            InventoryUtil.mc.field_1724.method_31548().field_7545 = i;
                            InventoryUtil.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(i));
                        }
                        return class_1268.field_5808;
                    }
                }
                InventoryUtil.mc.field_1761.method_2906(InventoryUtil.mc.field_1724.field_7512.field_7763, slot, 0, class_1713.field_7790, (class_1657)InventoryUtil.mc.field_1724);
                InventoryUtil.mc.field_1761.method_2906(InventoryUtil.mc.field_1724.field_7512.field_7763, 36 + InventoryUtil.mc.field_1724.method_31548().field_7545, 0, class_1713.field_7790, (class_1657)InventoryUtil.mc.field_1724);
                InventoryUtil.mc.field_1761.method_2906(InventoryUtil.mc.field_1724.field_7512.field_7763, slot, 0, class_1713.field_7790, (class_1657)InventoryUtil.mc.field_1724);
                return class_1268.field_5808;
            }
        }
        else if (slot == 40) {
            return class_1268.field_5810;
        }
        return null;
    }
    
    public static void switchToHotbarSlot(final class_1792 item, final boolean silent) {
        final int slot = findHotbarItem(item);
        if (slot > -1) {
            switchToHotbarSlot(slot, silent);
        }
    }
    
    public static void switchToHotbarSlot(final int slot, final boolean silent) {
        if (InventoryUtil.mc.field_1724.method_31548().field_7545 != slot && slot >= 0) {
            if (silent) {
                InventoryUtil.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(slot));
            }
            else {
                InventoryUtil.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(slot));
                InventoryUtil.mc.field_1724.method_31548().field_7545 = slot;
            }
        }
    }
    
    public static int findHotbarItem(final class_1792 item) {
        for (int i = 0; i <= 8; ++i) {
            final class_1799 stack = InventoryUtil.mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7909() == item) {
                return i;
            }
        }
        return -1;
    }
    
    public static int[] getInventorySlots(final boolean offhand) {
        final int[] i = new int[offhand ? 38 : 37];
        i[0] = InventoryUtil.mc.field_1724.method_31548().field_7545;
        i[1] = 40;
        for (int j = 0; j < 36; ++j) {
            if (j != InventoryUtil.mc.field_1724.method_31548().field_7545) {
                i[offhand ? (j + 2) : (j + 1)] = j;
            }
        }
        return i;
    }
    
    public static int getCappuchinoAtHotbar() {
        for (int i = 0; i < 9; ++i) {
            final class_1799 itemStack = Util.mc.field_1724.method_31548().method_5438(i);
            if (itemStack.method_7909() == class_1802.field_8574) {
                final class_1792 maybepot = itemStack.method_7909();
                if (itemStack.method_7964().toString().contains("\u041a\u0430\u043f\u043f\u0443\u0447\u0438\u043d\u043e")) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public static int getAmericanoAtHotbar() {
        for (int i = 0; i < 9; ++i) {
            final class_1799 itemStack = Util.mc.field_1724.method_31548().method_5438(i);
            if (itemStack.method_7909() == class_1802.field_8574) {
                final class_1792 maybepot = itemStack.method_7909();
                if (itemStack.method_7964().toString().contains("\u0410\u043c\u0435\u0440\u0438\u043a\u0430\u043d\u043e")) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public static class_1799 getPotionItemStack() {
        for (int i = 0; i < 9; ++i) {
            final class_1799 itemStack = Util.mc.field_1724.method_31548().method_5438(i);
            if (itemStack.method_7909() == class_1802.field_8574) {
                final class_1792 maybepot = itemStack.method_7909();
                if (itemStack.method_7964().toString().contains("\u041a\u0430\u043f\u043f\u0443\u0447\u0438\u043d\u043e")) {
                    return itemStack;
                }
            }
        }
        return null;
    }
    
    static {
        mc = class_310.method_1551();
    }
    
    public static class Task
    {
        private final int slot;
        private final boolean update;
        private final boolean quickClick;
        
        public Task() {
            this.update = true;
            this.slot = -1;
            this.quickClick = false;
        }
        
        public Task(final int slot) {
            this.slot = slot;
            this.quickClick = false;
            this.update = false;
        }
        
        public Task(final int slot, final boolean quickClick) {
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
