/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_2561
 *  net.minecraft.class_2596
 *  net.minecraft.class_2708
 *  net.minecraft.class_2793
 *  net.minecraft.class_2828
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_4597$class_4598
 *  net.minecraft.class_490
 *  net.minecraft.class_7833
 */
package com.mrzak34.thunderhack.module.modules.movement;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mrzak34.thunderhack.event.events.KeyEvent;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.event.events.RenderWorldEvent;
import com.mrzak34.thunderhack.mixin.IPlayerPositionLookS2CPacket;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.KeybindSetting;
import com.mrzak34.thunderhack.settings.ModeSetting;
import com.mrzak34.thunderhack.util.RenderUtil;
import com.mrzak34.thunderhack.util.TimerUtil;
import java.awt.Color;
import java.util.Objects;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_2708;
import net.minecraft.class_2793;
import net.minecraft.class_2828;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_490;
import net.minecraft.class_7833;

public class ChorusControl
extends Module {
    ModeSetting mode = new ModeSetting("mode", "predict", "predict", "teleport");
    KeybindSetting teleport = new KeybindSetting("teleport", 0, false);
    int teleportId;
    boolean chorusing = false;
    class_243 chorusPos;
    class_243 preTeleportPos;
    TimerUtil teleportTimer = new TimerUtil();

    public ChorusControl() {
        super("ChorusControl", 0, false, Category.MOVEMENT);
        this.addSettings(this.teleport, this.mode);
    }

    @Subscribe
    public void onKey(KeyEvent event) {
        if (this.isToggled() && this.chorusing && this.mode.getMode().equals("predict") && event.key == this.teleport.code && !(ChorusControl.mc.field_1755 instanceof class_490)) {
            this.preTeleportPos = null;
            this.chorusing = false;
            if (this.chorusPos != null) {
                ChorusControl.mc.field_1724.method_33574(this.chorusPos);
                try {
                    ChorusControl.mc.field_1724.field_3944.method_2883((class_2596)new class_2793(this.teleportId));
                }
                catch (Exception var3) {
                    System.out.println("failed teleport");
                }
            }
            this.chorusPos = class_243.field_1353;
        }
    }

    @Subscribe
    public void onRenderWorld(RenderWorldEvent event) {
        if (this.isToggled() && this.chorusing && this.chorusPos != class_243.field_1353 && this.chorusPos != null) {
            class_238 crosusBox = new class_238(this.chorusPos.method_10216() - 0.3, this.chorusPos.method_10214(), this.chorusPos.method_10215() - 0.3, this.chorusPos.method_10216() + 0.3, this.chorusPos.method_10214() + 1.8, this.chorusPos.method_10215() + 0.3);
            RenderUtil.drawOutline(event.getMatrices(), crosusBox, new Color(135, 0, 255), 100.0);
            this.renderNameTag(new class_243(this.chorusPos.method_10216(), this.chorusPos.method_10214() + 2.0, this.chorusPos.method_10215()));
            this.renderChorus(new class_243(this.chorusPos.method_10216(), this.chorusPos.method_10214() + 0.9, this.chorusPos.method_10215()));
        }
    }

    @Subscribe
    public void onPacketSend(PacketEvent.Send event) {
        if (this.chorusing && event.getPacket() instanceof class_2828) {
            event.setCancelled(true);
        }
        if (event.getPacket() instanceof class_2793) {
            if (!this.chorusing) {
                return;
            }
            class_2793 packet = (class_2793)event.getPacket();
            this.teleportId = packet.method_12086();
            ChorusControl.mc.field_1724.method_33574(this.preTeleportPos);
            event.setCancelled(true);
        }
    }

    @Subscribe
    public void onPacketReceive(PacketEvent.Receive event) {
        class_2708 packet;
        if (this.isToggled() && !ChorusControl.fullNullCheck() && event.getPacket() instanceof class_2708) {
            packet = (class_2708)event.getPacket();
            if (ChorusControl.mc.field_1724.method_31548().method_7391().method_7909() == class_1802.field_8233) {
                this.chorusPos = new class_243(packet.method_11734(), packet.method_11735(), packet.method_11738());
                this.preTeleportPos = new class_243(ChorusControl.mc.field_1724.method_23317(), ChorusControl.mc.field_1724.method_23318(), ChorusControl.mc.field_1724.method_23321());
                this.chorusing = true;
            }
        }
        if (this.isToggled() && this.mode.getMode().equals("teleport") && event.getPacket() instanceof class_2708) {
            packet = (class_2708)event.getPacket();
            if (this.chorusing && ChorusControl.mc.field_1724.method_31548().method_7391().method_7909() != class_1802.field_8233) {
                ChorusControl.mc.field_1724.method_43496(class_2561.method_30163((String)"teleport"));
                this.chorusing = false;
                if (this.chorusPos != null) {
                    ((IPlayerPositionLookS2CPacket)packet).setX(this.chorusPos.field_1352);
                    ((IPlayerPositionLookS2CPacket)packet).setY(this.chorusPos.field_1351);
                    ((IPlayerPositionLookS2CPacket)packet).setZ(this.chorusPos.field_1350);
                    try {
                        ChorusControl.mc.field_1724.field_3944.method_2883((class_2596)new class_2793(this.teleportId));
                    }
                    catch (Exception var2) {
                        System.out.println("failed teleport");
                    }
                }
                this.chorusPos = class_243.field_1353;
            }
        }
    }

    private void renderNameTag(class_243 pos) {
        class_327 tr = ChorusControl.mc.field_1772;
        String displayTag = "teleport";
        int width = tr.method_1727(displayTag) / 2;
        class_4184 camera = ChorusControl.mc.field_1773.method_19418();
        double scale = 1.0;
        class_4587 matrices = RenderUtil.matrixFrom(pos);
        matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        matrices.method_22904(0.0, 0.0, 0.0);
        matrices.method_22905(-0.025f * (float)scale, -0.025f * (float)scale, 1.0f);
        class_4597.class_4598 immediate = class_4597.method_22991((class_287)class_289.method_1348().method_1349());
        int n = -width;
        Objects.requireNonNull(tr);
        class_332.method_25303((class_4587)matrices, (class_327)ChorusControl.mc.field_1772, (String)displayTag, (int)n, (int)(-(9 / 2)), (int)-1);
    }

    private void renderChorus(class_243 pos) {
        class_4587 matrices = RenderUtil.matrixFrom(pos);
        class_4184 camera = ChorusControl.mc.field_1773.method_19418();
        matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        matrices.method_22904(0.0, 0.0, 0.0);
        matrices.method_22905(-0.025f, -0.025f, 1.0f);
        RenderUtil.drawItem(matrices, 0, 0, 20.0, new class_1799((class_1935)class_1802.field_8233));
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.chorusing = false;
        this.chorusPos = class_243.field_1353;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.chorusing = false;
        if (this.chorusPos != null) {
            ChorusControl.mc.field_1724.method_33574(this.chorusPos);
            try {
                ChorusControl.mc.field_1724.field_3944.method_2883((class_2596)new class_2793(this.teleportId));
            }
            catch (Exception var2) {
                System.out.println("failed teleport");
            }
        }
        this.chorusPos = class_243.field_1353;
    }
}

