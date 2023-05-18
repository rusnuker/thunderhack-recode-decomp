//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.movement;

import io.netty.util.internal.*;
import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import java.util.*;
import java.util.concurrent.*;
import com.mrzak34.thunderhack.*;
import net.minecraft.*;
import com.google.common.eventbus.*;
import java.util.function.*;
import com.mrzak34.thunderhack.event.events.*;
import com.mrzak34.thunderhack.util.*;

public class PacketFly extends Module
{
    BooleanSetting autoClip;
    BooleanSetting limit;
    BooleanSetting antiKick;
    NumberSetting speed;
    NumberSetting timer;
    NumberSetting increaseTicks;
    NumberSetting factor;
    ModeSetting mode;
    ModeSetting phase;
    ModeSetting type;
    private int Field3526;
    private final ConcurrentSet Field3527;
    private final Random Field3528;
    private final ConcurrentHashMap Field3529;
    private int Field3530;
    private int Field3531;
    private int Field3532;
    private boolean Field3533;
    private boolean Field3534;
    
    public PacketFly() {
        super("PacketFly", 0, false, Category.MOVEMENT);
        this.autoClip = new BooleanSetting("auto-clip", false);
        this.limit = new BooleanSetting("limit", true);
        this.antiKick = new BooleanSetting("anti kick", true);
        this.speed = new NumberSetting("speed", 1.0f, 0.0f, 3.0f, true);
        this.timer = new NumberSetting("timer", 1.0f, 0.0f, 3.0f, true);
        this.increaseTicks = new NumberSetting("increase-ticks", 20.0f, 1.0f, 20.0f, false);
        this.factor = new NumberSetting("factor", 1.0f, 1.0f, 10.0f, false);
        this.mode = new ModeSetting("mode", "fast", new String[] { "fast", "factor", "rubber", "limit" });
        this.phase = new ModeSetting("phase", "full", new String[] { "full", "off", "semi" });
        this.type = new ModeSetting("type", "preserve", new String[] { "preserve", "up", "down", "bounds" });
        this.Field3526 = -1;
        this.Field3527 = new ConcurrentSet();
        this.Field3528 = new Random();
        this.Field3529 = new ConcurrentHashMap();
        this.Field3530 = 0;
        this.Field3531 = 0;
        this.Field3532 = 0;
        this.Field3533 = false;
        this.Field3534 = false;
        this.addSettings(new Setting[] { this.autoClip, this.limit, this.antiKick, this.speed, this.timer, this.increaseTicks, this.factor, this.mode, this.phase, this.type });
    }
    
    private static boolean Method4295(final Object o) {
        return System.currentTimeMillis() - ((Map.Entry)o).getValue().Method1915() > TimeUnit.SECONDS.toMillis(30L);
    }
    
    public static double[] Method3180(final double d) {
        double d2 = PacketFly.mc.field_1724.field_3913.field_3905;
        double d3 = PacketFly.mc.field_1724.field_3913.field_3907;
        float f = PacketFly.mc.field_1724.method_36454();
        final double[] dArray = new double[2];
        if (d2 == 0.0 && d3 == 0.0) {
            dArray[1] = (dArray[0] = 0.0);
        }
        else {
            if (d2 != 0.0) {
                if (d3 > 0.0) {
                    f += ((d2 > 0.0) ? -45 : 45);
                }
                else if (d3 < 0.0) {
                    f += ((d2 > 0.0) ? 45 : -45);
                }
                d3 = 0.0;
                if (d2 > 0.0) {
                    d2 = 1.0;
                }
                else if (d2 < 0.0) {
                    d2 = -1.0;
                }
            }
            dArray[0] = d2 * d * Math.cos(Math.toRadians(f + 90.0f)) + d3 * d * Math.sin(Math.toRadians(f + 90.0f));
            dArray[1] = d2 * d * Math.sin(Math.toRadians(f + 90.0f)) - d3 * d * Math.cos(Math.toRadians(f + 90.0f));
        }
        return dArray;
    }
    
    public void onEnable() {
        super.onEnable();
        this.Field3526 = -1;
        this.Field3531 = 0;
        this.Field3532 = 0;
        if (fullNullCheck() && PacketFly.mc.field_1724 != null) {
            this.Method4286();
        }
        if (this.autoClip.isEnabled() && this.Field3534) {
            PacketFly.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)PacketFly.mc.field_1724, class_2848.class_2849.field_12984));
        }
    }
    
    public void onDisable() {
        super.onDisable();
        Main.TICK_TIMER = 1.0f;
    }
    
    public void Method4286() {
        this.Field3530 = 0;
        this.Field3526 = 0;
        this.Field3527.clear();
        this.Field3529.clear();
    }
    
    public boolean Method4289(final int n) {
        ++this.Field3530;
        if (this.Field3530 >= n) {
            this.Field3530 = 0;
            return true;
        }
        return false;
    }
    
    public void Method4290(final class_2828 cPacketPlayer) {
        this.Field3527.add((Object)cPacketPlayer);
        PacketFly.mc.field_1724.field_3944.method_2883((class_2596)cPacketPlayer);
    }
    
    private int Method4291() {
        if (PacketFly.mc.method_1542()) {
            return 2000;
        }
        final int n = this.Field3528.nextInt(29000000);
        if (this.Field3528.nextBoolean()) {
            return n;
        }
        return -n;
    }
    
    public class_243 Method4292(final class_243 vec3d, final class_243 vec3d2) {
        class_243 vec3d3 = vec3d.method_1019(vec3d2);
        final String mode = this.type.getMode();
        switch (mode) {
            case "preserve": {
                vec3d3 = vec3d3.method_1031((double)this.Method4291(), 0.0, (double)this.Method4291());
                break;
            }
            case "up": {
                vec3d3 = vec3d3.method_1031(0.0, 1337.0, 0.0);
                break;
            }
            case "down": {
                vec3d3 = vec3d3.method_1031(0.0, -1337.0, 0.0);
                break;
            }
            case "bounds": {
                vec3d3 = new class_243(vec3d3.field_1352, (PacketFly.mc.field_1724.method_23318() <= 10.0) ? 255.0 : 1.0, vec3d3.field_1350);
                break;
            }
        }
        return vec3d3;
    }
    
    public void Method4293(final Double d, final Double d2, final Double d3, final Boolean bl) {
        final class_243 vec3d = new class_243((double)d, (double)d2, (double)d3);
        final class_243 vec3d2 = PacketFly.mc.field_1724.method_19538().method_1019(vec3d);
        final class_243 vec3d3 = this.Method4292(vec3d, vec3d2);
        this.Method4290((class_2828)new class_2828.class_2829(vec3d2.field_1352, vec3d2.field_1351, vec3d2.field_1350, PacketFly.mc.field_1724.method_24828()));
        this.Method4290((class_2828)new class_2828.class_2829(vec3d3.field_1352, vec3d3.field_1351, vec3d3.field_1350, PacketFly.mc.field_1724.method_24828()));
        if (bl) {
            PacketFly.mc.field_1724.field_3944.method_2883((class_2596)new class_2793(++this.Field3526));
            this.Field3529.put(this.Field3526, new Class443(vec3d2.field_1352, vec3d2.field_1351, vec3d2.field_1350, System.currentTimeMillis()));
        }
    }
    
    private boolean Method4294() {
        return PacketFly.mc.field_1687.method_20812((class_1297)PacketFly.mc.field_1724, PacketFly.mc.field_1724.method_5829().method_1009(-0.0625, -0.0625, -0.0625)).iterator().hasNext();
    }
    
    @Subscribe
    public void onPacketReceive(final PacketEvent.Receive eventNetworkPrePacketEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (PacketFly.mc.field_1724 != null && eventNetworkPrePacketEvent.getPacket() instanceof class_2708) {
            final class_2708 sPacketPlayerPosLook = (class_2708)eventNetworkPrePacketEvent.getPacket();
            final Class443 class443 = this.Field3529.remove(sPacketPlayerPosLook.method_11737());
            if (PacketFly.mc.field_1724.method_5805() && PacketFly.mc.field_1687.method_8393((int)PacketFly.mc.field_1724.method_23317(), (int)PacketFly.mc.field_1724.method_23321()) && !(PacketFly.mc.field_1755 instanceof class_434) && !this.mode.getMode().equals("rubber") && class443 != null && Class443.Method1920(class443) == sPacketPlayerPosLook.method_11734() && Class443.Method1921(class443) == sPacketPlayerPosLook.method_11735() && Class443.Method1922(class443) == sPacketPlayerPosLook.method_11738()) {
                eventNetworkPrePacketEvent.setCancelled(true);
                return;
            }
            this.Field3526 = sPacketPlayerPosLook.method_11737();
        }
    }
    
    @Subscribe
    public void onPacketSend(final PacketEvent.Send eventNetworkPostPacketEvent) {
        if (eventNetworkPostPacketEvent.getPacket() instanceof class_2828) {
            if (this.Field3527.contains((Object)eventNetworkPostPacketEvent.getPacket())) {
                this.Field3527.remove((Object)eventNetworkPostPacketEvent.getPacket());
                return;
            }
            eventNetworkPostPacketEvent.setCancelled(true);
        }
    }
    
    public void onTick() {
        super.onTick();
        this.Field3529.entrySet().removeIf(PacketFly::Method4295);
    }
    
    @Subscribe
    public void onMove(final MoveEvent eventPlayerMove) {
        if (!eventPlayerMove.isCancelled()) {
            if (!this.mode.getMode().equals("rubber") && this.Field3526 == 0) {
                return;
            }
            eventPlayerMove.setCancelled(true);
            eventPlayerMove.setX(PacketFly.mc.field_1724.method_18798().field_1352);
            eventPlayerMove.setY(PacketFly.mc.field_1724.method_18798().field_1351);
            eventPlayerMove.setZ(PacketFly.mc.field_1724.method_18798().field_1350);
            if (!this.phase.getMode().equals("off") && (this.phase.getMode().equals("semi") || this.Method4294())) {
                PacketFly.mc.field_1724.field_5960 = true;
            }
        }
    }
    
    @Subscribe
    public void onMovmentPre(final MovementEvent.Pre event) {
        if (this.timer.getValue() != 1.0) {
            Main.TICK_TIMER = (float)this.timer.getValue();
        }
        PacketFly.mc.field_1724.method_18800(0.0, 0.0, 0.0);
        if (!this.mode.getMode().equals("rubber") && this.Field3526 == 0) {
            if (this.Method4289(4)) {
                this.Method4293(0.0, 0.0, 0.0, false);
            }
            return;
        }
        final boolean bl = this.Method4294();
        double d = 0.0;
        d = ((PacketFly.mc.field_1724.field_3913.field_3904 && (bl || !PlayerUtil.isMoving())) ? ((this.antiKick.isEnabled() && !bl) ? (this.Method4289(this.mode.getMode().equals("rubber") ? 10 : 20) ? -0.032 : 0.062) : 0.062) : (PacketFly.mc.field_1724.field_3913.field_3903 ? -0.062 : (bl ? 0.0 : (this.Method4289(4) ? (this.antiKick.isEnabled() ? -0.04 : 0.0) : 0.0))));
        if (this.phase.getMode().equals("full") && bl && PlayerUtil.isMoving() && d != 0.0) {
            d = (PacketFly.mc.field_1724.field_3913.field_3904 ? (d /= 2.5) : (d /= 1.5));
        }
        if (PacketFly.mc.field_1724.field_3913.field_3904 && this.autoClip.isEnabled()) {
            PacketFly.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)PacketFly.mc.field_1724, this.Field3534 ? class_2848.class_2849.field_12979 : class_2848.class_2849.field_12984));
            this.Field3534 = !this.Field3534;
        }
        final double[] dArray = Method3180((this.phase.getMode().equals("full") && bl) ? 0.034444444444444444 : (this.speed.getValue() * 0.26));
        int n = 1;
        if (this.mode.getMode().equals("factor") && PacketFly.mc.field_1724.field_6012 % this.increaseTicks.getValue() == 0.0) {
            n = (int)this.factor.getValue();
        }
        for (int i = 1; i <= n; ++i) {
            if (this.mode.getMode().equals("limit")) {
                if (PacketFly.mc.field_1724.field_6012 % 2 == 0) {
                    if (this.Field3533 && d >= 0.0) {
                        this.Field3533 = false;
                        d = -0.032;
                    }
                    PacketFly.mc.field_1724.method_18800(dArray[0] * i, d * i, dArray[1] * i);
                    this.Method4293(PacketFly.mc.field_1724.method_18798().field_1352, PacketFly.mc.field_1724.method_18798().field_1351, PacketFly.mc.field_1724.method_18798().field_1350, !this.limit.isEnabled());
                }
                else if (d < 0.0) {
                    this.Field3533 = true;
                }
            }
            else {
                PacketFly.mc.field_1724.method_18800(dArray[0] * i, d * i, dArray[1] * i);
                this.Method4293(PacketFly.mc.field_1724.method_18798().field_1352, PacketFly.mc.field_1724.method_18798().field_1351, PacketFly.mc.field_1724.method_18798().field_1350, !this.mode.getMode().equals("rubber"));
            }
        }
    }
    
    public static class Class443
    {
        private final double Field1501;
        private final double Field1502;
        private final double Field1503;
        private final long Field1504;
        
        public Class443(final double d, final double d2, final double d3, final long l) {
            this.Field1501 = d;
            this.Field1502 = d2;
            this.Field1503 = d3;
            this.Field1504 = l;
        }
        
        static double Method1920(final Class443 class443) {
            return class443.Field1501;
        }
        
        static double Method1921(final Class443 class443) {
            return class443.Field1502;
        }
        
        static double Method1922(final Class443 class443) {
            return class443.Field1503;
        }
        
        public long Method1915() {
            return this.Field1504;
        }
    }
}
