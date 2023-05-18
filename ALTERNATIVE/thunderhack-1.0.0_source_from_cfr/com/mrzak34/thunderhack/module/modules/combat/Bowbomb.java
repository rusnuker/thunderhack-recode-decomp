/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_2561
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828$class_2829
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 *  net.minecraft.class_2848
 *  net.minecraft.class_2848$class_2849
 *  net.minecraft.class_2886
 */
package com.mrzak34.thunderhack.module.modules.combat;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_2846;
import net.minecraft.class_2848;
import net.minecraft.class_2886;

public class Bowbomb
extends Module {
    String lol = "\u26a1";
    protected static final String LAG_MESSAGE = "\u0101\u0201\u0301\u0401\u0601\u0701\u0801\u0901\u0a01\u0b01\u0e01\u0f01\u1001\u1101\u1201\u1301\u1401\u1501\u1601\u1701\u1801\u1901\u1a01\u1b01\u1c01\u1d01\u1e01\u1f01 \u2101\u2201\u2301\u2401\u2501\u2701\u2801\u2901\u2a01\u2b01\u2c01\u2d01\u2e01\u2f01\u3001\u3101\u3201\u3301\u3401\u3501\u3601\u3701\u3801\u3901\u3a01\u3b01\u3c01\u3d01\u3e01\u3f01\u4001\u4101\u4201\u4301\u4401\u4501\u4601\u4701\u4801\u4901\u4a01\u4b01\u4c01\u4d01\u4e01\u4f01\u5001\u5101\u5201\u5301\u5401\u5501\u5601\u5701\u5801\u5901\u5a01\u5b01\u5c01\u5d01\u5e01\u5f01\u6001\u6101\u6201\u6301\u6401\u6501\u6601\u6701\u6801\u6901\u6a01\u6b01\u6c01\u6d01\u6e01\u6f01\u7001\u7101\u7201\u7301\u7401\u7501\u7601\u7701\u7801\u7901\u7a01\u7b01\u7c01\u7d01\u7e01\u7f01\u8001\u8101\u8201\u8301\u8401\u8501\u8601\u8701\u8801\u8901\u8a01\u8b01\u8c01\u8d01\u8e01\u8f01\u9001\u9101\u9201\u9301\u9401\u9501\u9601\u9701\u9801\u9901\u9a01\u9b01\u9c01\u9d01\u9e01\u9f01\ua001\ua101\ua201\ua301\ua401\ua501\ua601\ua701\ua801\ua901\uaa01\uab01\uac01\uad01\uae01\uaf01\ub001\ub101\ub201\ub301\ub401\ub501\ub601\ub701\ub801\ub901\uba01\ubb01\ubc01\ubd01";
    public static BooleanSetting bows = new BooleanSetting("bows", true);
    public static BooleanSetting pearls = new BooleanSetting("pearls", true);
    public static BooleanSetting eggs = new BooleanSetting("eggs", true);
    public static BooleanSetting snowballs = new BooleanSetting("snowballs", true);
    public static BooleanSetting trident = new BooleanSetting("trident", true);
    public static BooleanSetting crossbow = new BooleanSetting("crossbow", true);
    public static NumberSetting timeout = new NumberSetting("timeout", 5000.0f, 100.0f, 20000.0f, false);
    public static NumberSetting spoofs = new NumberSetting("spoofs", 10.0f, 1.0f, 300.0f, false);
    public static BooleanSetting bypass = new BooleanSetting("bypass", false);
    private static long lastShootTime;

    public Bowbomb() {
        super("Bowbomb", 0, false, Category.COMBAT);
        this.addSettings(bows, crossbow, trident, pearls, eggs, snowballs, timeout, spoofs, bypass);
    }

    @Subscribe
    public void onPacketSend(PacketEvent.Send event) {
        class_1799 handStack;
        class_2886 packet2;
        if (event.getPacket() instanceof class_2846) {
            class_2846 packet = (class_2846)event.getPacket();
            if (packet.method_12363() == class_2846.class_2847.field_12974) {
                class_1799 handStack2 = Bowbomb.mc.field_1724.method_6047();
                if (!handStack2.method_7960() && handStack2.method_7909() != null && handStack2.method_7909() == class_1802.field_8102 && bows.isEnabled()) {
                    this.doSpoofs();
                } else if (handStack2.method_7909() == class_1802.field_8547 && trident.isEnabled()) {
                    this.doSpoofs();
                }
            }
        } else if (event.getPacket() instanceof class_2886 && (packet2 = (class_2886)event.getPacket()).method_12551() == class_1268.field_5808 && !(handStack = Bowbomb.mc.field_1724.method_6047()).method_7960() && handStack.method_7909() != null) {
            if (handStack.method_7909() == class_1802.field_8803 && eggs.isEnabled()) {
                this.doSpoofs();
            } else if (handStack.method_7909() == class_1802.field_8634 && pearls.isEnabled()) {
                this.doSpoofs();
            } else if (handStack.method_7909() == class_1802.field_8543 && snowballs.isEnabled()) {
                this.doSpoofs();
            } else if (handStack.method_7909() == class_1802.field_8399 && crossbow.isEnabled()) {
                this.doSpoofs();
            }
        }
    }

    private void doSpoofs() {
        if ((double)(System.currentTimeMillis() - lastShootTime) >= timeout.getValue()) {
            lastShootTime = System.currentTimeMillis();
            Bowbomb.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)Bowbomb.mc.field_1724, class_2848.class_2849.field_12981));
            int index = 0;
            while ((double)index < spoofs.getValue()) {
                if (bypass.isEnabled()) {
                    Bowbomb.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(Bowbomb.mc.field_1724.method_23317(), Bowbomb.mc.field_1724.method_23318() + 1.0E-10, Bowbomb.mc.field_1724.method_23321(), false));
                    Bowbomb.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(Bowbomb.mc.field_1724.method_23317(), Bowbomb.mc.field_1724.method_23318() - 1.0E-10, Bowbomb.mc.field_1724.method_23321(), true));
                } else {
                    Bowbomb.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(Bowbomb.mc.field_1724.method_23317(), Bowbomb.mc.field_1724.method_23318() - 1.0E-10, Bowbomb.mc.field_1724.method_23321(), true));
                    Bowbomb.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(Bowbomb.mc.field_1724.method_23317(), Bowbomb.mc.field_1724.method_23318() + 1.0E-10, Bowbomb.mc.field_1724.method_23321(), false));
                }
                ++index;
            }
            Bowbomb.mc.field_1724.method_43496(class_2561.method_30163((String)"spoof"));
        }
    }
}

