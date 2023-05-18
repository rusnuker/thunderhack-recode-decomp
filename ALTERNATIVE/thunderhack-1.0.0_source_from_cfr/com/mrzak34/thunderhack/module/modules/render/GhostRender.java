/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1657
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_2561
 *  net.minecraft.class_2765
 *  net.minecraft.class_2767
 *  net.minecraft.class_2824
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_3417
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_4597$class_4598
 *  net.minecraft.class_640
 *  net.minecraft.class_7439
 *  net.minecraft.class_7833
 */
package com.mrzak34.thunderhack.module.modules.render;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.event.events.RenderWorldEvent;
import com.mrzak34.thunderhack.mixin.IPlayerInteractEntityC2SPacket;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.module.modules.thread.ThreadDeathPlayer;
import com.mrzak34.thunderhack.module.modules.thread.ThreadDeathUnknown;
import com.mrzak34.thunderhack.module.modules.thread.ThreadDeleteGhostEntry;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.util.FakePlayerEntity;
import com.mrzak34.thunderhack.util.FakePlayerUtil;
import com.mrzak34.thunderhack.util.PlayerUtil;
import com.mrzak34.thunderhack.util.RenderUtil;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import net.minecraft.class_1657;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2765;
import net.minecraft.class_2767;
import net.minecraft.class_2824;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_3417;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_640;
import net.minecraft.class_7439;
import net.minecraft.class_7833;

public class GhostRender
extends Module {
    NumberSetting life = new NumberSetting("life", 1.0f, 0.0f, 10.0f, true);
    NumberSetting lineWidth = new NumberSetting("line width", 1.0f, 0.0f, 10.0f, true);
    NumberSetting playerRadius = new NumberSetting("player rad", 1.0f, 0.1f, 3.0f, true);
    NumberSetting secondsWithPvp = new NumberSetting("pvp time", 7.0f, 1.0f, 30.0f, true);
    BooleanSetting testId = new BooleanSetting("id test", false);
    public static NumberSetting enemyid = new NumberSetting("enemyid", -1.0f, 0.0f, 10.0f, false);
    public HashMap<class_2767, FakePlayerEntity> soundPlayer = new HashMap();
    public List<class_2767> sounds = new ArrayList<class_2767>();
    public class_640 ghost;
    private static GhostRender INSTANCE = new GhostRender();

    public GhostRender() {
        super("GhostRender", 0, false, Category.RENDER);
        this.addSettings(this.life, this.playerRadius, this.secondsWithPvp, this.testId, enemyid, this.lineWidth);
        this.setInstance();
    }

    @Subscribe
    public void onRenderWorld(RenderWorldEvent event) {
        try {
            for (class_2767 unknown : this.sounds) {
                try {
                    class_238 unknownHitBox = new class_238(unknown.method_11890() - 0.3, unknown.method_11889(), unknown.method_11893() - 0.3, unknown.method_11890() + 0.3, unknown.method_11889() + 1.8, unknown.method_11893() + 0.3);
                    RenderUtil.drawOutline(event.getMatrices(), unknownHitBox, Color.WHITE, 100.0);
                    this.renderNameTag(new class_243(unknown.method_11890(), unknown.method_11889() + 1.8, unknown.method_11893()));
                }
                catch (Exception exception) {}
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Subscribe
    public void onPacketSend(PacketEvent.Send event) {
        if (event.getPacket() instanceof class_2824) {
            class_2824 packet = (class_2824)event.getPacket();
            ((IPlayerInteractEntityC2SPacket)packet).setEntityId((int)enemyid.getValue());
        }
    }

    @Subscribe
    public void onPacketReceive(PacketEvent.Receive event) {
        String a;
        class_243 soundPos;
        class_2767 packet;
        if (event.getPacket() instanceof class_2767 && ((packet = (class_2767)event.getPacket()).method_11894() == class_3417.field_14914 || packet.method_11894() == class_3417.field_15016 || packet.method_11894() == class_3417.field_14999 || packet.method_11894() == class_3417.field_14840 || packet.method_11894() == class_3417.field_14706 || packet.method_11894() == class_3417.field_14810) && PlayerUtil.thereIsSomeoneHere(soundPos = new class_243(packet.method_11890(), packet.method_11889(), packet.method_11893()), (float)this.playerRadius.getValue())) {
            if (this.ghost != null) {
                this.soundPlayer.put(packet, new FakePlayerEntity((class_1657)GhostRender.mc.field_1724, soundPos, this.ghost));
                FakePlayerUtil.addWithPlayer(this.soundPlayer.get((Object)packet));
                new ThreadDeathPlayer(packet, (long)(this.life.getValue() * 1000.0), this.soundPlayer.get((Object)packet)).start();
            } else {
                this.sounds.add(packet);
                new ThreadDeathUnknown(packet, (long)(this.life.getValue() * 1000.0)).start();
            }
        }
        if (event.getPacket() instanceof class_2765) {
            packet = (class_2765)event.getPacket();
            GhostRender.mc.field_1724.method_43496(class_2561.method_30163((String)("sound from entity: " + packet.method_11882())));
        }
        if (event.getPacket() instanceof class_7439 && (a = (packet = (class_7439)event.getPacket()).comp_763().getString()).contains("\u043d\u0430\u043f\u0430\u043b \u043d\u0430 \u0432\u0430\u0441")) {
            this.ghost = GhostRender.getGhost(a);
            new ThreadDeleteGhostEntry((long)(this.life.getValue() * 1000.0)).start();
            GhostRender.mc.field_1724.method_43496(class_2561.method_30163((String)("found god: " + this.ghost.method_2966().getName())));
        }
    }

    private void renderNameTag(class_243 pos) {
        class_327 tr = GhostRender.mc.field_1772;
        String displayTag = "ghost";
        int width = tr.method_1727(displayTag) / 2;
        class_4184 camera = GhostRender.mc.field_1773.method_19418();
        double scale = 1.0;
        class_4587 matrices = RenderUtil.matrixFrom(pos);
        matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        matrices.method_22904(0.0, 0.0, 0.0);
        matrices.method_22905(-0.025f * (float)scale, -0.025f * (float)scale, 1.0f);
        class_4597.class_4598 immediate = class_4597.method_22991((class_287)class_289.method_1348().method_1349());
        class_332.method_25303((class_4587)matrices, (class_327)GhostRender.mc.field_1772, (String)displayTag, (int)(-(width / 2)), (int)0, (int)-1);
    }

    public static String solvename(String notsolved) {
        AtomicReference<String> mb = new AtomicReference<String>("err");
        Objects.requireNonNull(mc.method_1562()).method_2880().forEach(player -> {
            if (notsolved.contains(player.method_2966().getName())) {
                mb.set(player.method_2966().getName());
            }
        });
        return mb.get();
    }

    public static UUID getGhosUUID(String notsolved) {
        AtomicReference<Object> ghostId = new AtomicReference<Object>(null);
        Objects.requireNonNull(mc.method_1562()).method_2880().forEach(player -> {
            if (notsolved.contains(player.method_2966().getName())) {
                ghostId.set(player.method_2966().getId());
            }
        });
        return ghostId.get();
    }

    public static class_640 getGhost(String notsolved) {
        AtomicReference<Object> ghost = new AtomicReference<Object>(null);
        Objects.requireNonNull(mc.method_1562()).method_2880().forEach(player -> {
            if (notsolved.contains(player.method_2966().getName())) {
                ghost.set(player);
            }
        });
        return ghost.get();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        FakePlayerUtil.clear();
        this.sounds.clear();
    }

    public static GhostRender getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GhostRender();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

