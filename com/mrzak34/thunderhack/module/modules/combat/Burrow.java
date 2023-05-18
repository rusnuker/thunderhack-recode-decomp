//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.combat;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.util.*;
import net.minecraft.*;
import java.util.*;

public class Burrow extends Module
{
    ModeSetting mode;
    NumberSetting height;
    private final List<Double> offsets;
    private class_2338 startPos;
    int tick;
    boolean fill;
    
    public Burrow() {
        super("Burrow", 0, false, Category.COMBAT);
        this.mode = new ModeSetting("mode", "obi", new String[] { "obi", "anvil" });
        this.height = new NumberSetting("height", 3.0f, -10.0f, 10.0f, false);
        this.offsets = Arrays.asList(0.4199999, 0.7531999, 1.0013359, 1.1661092);
        this.addSettings(new Setting[] { this.mode, this.height });
    }
    
    public void onEnable() {
        super.onEnable();
        this.fill = true;
    }
    
    public void onTick() {
        super.onTick();
        if (this.mode.getMode().equals("obi")) {
            final int startSlot = Burrow.mc.field_1724.method_31548().field_7545;
            this.startPos = new class_2338((class_2382)Burrow.mc.field_1724.method_24515());
            if (InventoryUtil.findHotbarBlock(class_2246.field_10443, class_2246.field_10540, class_2246.field_10034) == -1) {
                this.setToggled(false);
                this.tick = 0;
                return;
            }
            if (!this.check()) {
                return;
            }
            ++this.tick;
            if (this.fill) {
                InventoryUtil.switchToHotbarSlot(InventoryUtil.findHotbarBlock(class_2246.field_10443, class_2246.field_10540, class_2246.field_10034), false);
                this.offsets.forEach(offset -> Burrow.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(Burrow.mc.field_1724.method_23317(), Burrow.mc.field_1724.method_23318() + offset, Burrow.mc.field_1724.method_23321(), true)));
                Burrow.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)Burrow.mc.field_1724, class_2848.class_2849.field_12979));
                BlockUtil.placeBlock(this.startPos);
                Burrow.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)Burrow.mc.field_1724, class_2848.class_2849.field_12984));
                Burrow.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(Burrow.mc.field_1724.method_23317(), Burrow.mc.field_1724.method_23318() + this.height.getValue(), Burrow.mc.field_1724.method_23321(), false));
                InventoryUtil.switchToHotbarSlot(startSlot, false);
                this.fill = false;
            }
            if (this.tick >= 8) {
                this.tick = 0;
                this.setToggled(false);
            }
        }
        if (this.mode.getMode().equals("anvil")) {
            final int startSlot = Burrow.mc.field_1724.method_31548().field_7545;
            this.startPos = new class_2338((class_2382)Burrow.mc.field_1724.method_24515());
            if (Burrow.mc.field_1687.method_8320(this.startPos.method_10086(2)).method_26215() && this.isNoEntitiesToPos(this.startPos.method_10086(2))) {
                final int anvilSlot = InventoryUtil.findHotbarItem(class_1802.field_8782);
                if (anvilSlot != -1) {
                    InventoryUtil.switchToHotbarSlot(anvilSlot, false);
                    BlockUtil.placeBlock(this.startPos.method_10086(2));
                    InventoryUtil.switchToHotbarSlot(startSlot, false);
                }
            }
            else if (!Burrow.mc.field_1687.method_8320(this.startPos.method_10086(2)).method_26215()) {
                this.setToggled(false);
            }
        }
    }
    
    private boolean isNoEntitiesToPos(final class_2338 pos) {
        for (final class_1297 entity : Burrow.mc.field_1687.method_8335((class_1297)null, new class_238((double)pos.method_10263(), (double)(pos.method_10264() + 1), (double)pos.method_10260(), (double)(pos.method_10263() + 1), (double)(pos.method_10264() + 2), (double)(pos.method_10260() + 1)))) {
            if (entity instanceof class_1511 || entity instanceof class_1657) {
                return false;
            }
        }
        return true;
    }
    
    private boolean check() {
        return Burrow.mc.field_1724.method_24828() && Burrow.mc.field_1687.method_8320(this.startPos).method_26204() == class_2246.field_10124 && Burrow.mc.field_1687.method_8320(this.startPos.method_10069(0, 3, 0)).method_26204() == class_2246.field_10124;
    }
}
