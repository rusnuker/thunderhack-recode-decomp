/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_1299
 *  net.minecraft.class_2561
 *  net.minecraft.class_2604
 *  net.minecraft.class_2960
 *  net.minecraft.class_5244
 *  net.minecraft.class_689
 *  net.minecraft.class_700
 */
package com.mrzak34.thunderhack.module.modules.render;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.event.events.ParticleEvent;
import com.mrzak34.thunderhack.event.events.RenderMapEvent;
import com.mrzak34.thunderhack.event.events.RenderSignEvent;
import com.mrzak34.thunderhack.mixin.ISignBlockEntity;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.ParentSetting;
import com.mrzak34.thunderhack.util.RenderUtil;
import net.minecraft.class_1299;
import net.minecraft.class_2561;
import net.minecraft.class_2604;
import net.minecraft.class_2960;
import net.minecraft.class_5244;
import net.minecraft.class_689;
import net.minecraft.class_700;

public class NoRender
extends Module {
    public BooleanSetting fire = new BooleanSetting("fire", true);
    public BooleanSetting water = new BooleanSetting("water", true);
    public BooleanSetting hurtcam = new BooleanSetting("hurtcam", true);
    public BooleanSetting explosParticle = new BooleanSetting("particle", true);
    public BooleanSetting tnt = new BooleanSetting("tnt", false);
    public ParentSetting explosion = new ParentSetting("explosion", false, false, this.explosParticle, this.tnt);
    public BooleanSetting totem = new BooleanSetting("totem", false);
    public BooleanSetting nausea = new BooleanSetting("nausea", false);
    public static BooleanSetting fog = new BooleanSetting("fog", false);
    public static BooleanSetting blindness = new BooleanSetting("blindness", false);
    public static BooleanSetting projectile = new BooleanSetting("projectile", false);
    public static BooleanSetting map = new BooleanSetting("map", false);
    public static BooleanSetting item = new BooleanSetting("item", false);
    public static BooleanSetting sign = new BooleanSetting("sign", false);
    public static BooleanSetting blockOverlay = new BooleanSetting("block overlay", false);
    public static BooleanSetting firework = new BooleanSetting("firework", false);
    public static BooleanSetting fishinghook = new BooleanSetting("fishinghook", false);
    public static BooleanSetting portals = new BooleanSetting("portals", true);
    public static BooleanSetting enchantingTable = new BooleanSetting("enchanting table", true);
    public static BooleanSetting fallingBlocks = new BooleanSetting("falling blocks", true);
    public static BooleanSetting elderGuardian = new BooleanSetting("elder guardian", false);
    public static final class_2960 lightning = new class_2960("textures/lightning.png");
    private static NoRender INSTANCE = new NoRender();

    public NoRender() {
        super("NoRender", 0, false, Category.RENDER);
        this.addSettings(this.fire, this.water, this.hurtcam, this.explosion, this.totem, this.nausea, fog, blindness, projectile, map, item, sign, blockOverlay, firework, fishinghook, portals, enchantingTable, fallingBlocks, elderGuardian);
        this.setInstance();
    }

    @Subscribe
    public void onRenderSign(RenderSignEvent event) {
        if (sign.isEnabled()) {
            ((ISignBlockEntity)event.getSignBlockEntity()).setText(new class_2561[]{class_5244.field_39003, class_5244.field_39003, class_5244.field_39003, class_5244.field_39003});
        }
    }

    @Subscribe
    public void onRenderMap(RenderMapEvent event) {
        if (map.isEnabled()) {
            event.setCancelled(true);
            event.getMatrices().method_22904(0.0, 0.0, -10.0);
            RenderUtil.drawTexture(lightning, 0, 0, 64, 64, event.getMatrices());
        }
    }

    @Subscribe
    public void onPacketReceive(PacketEvent.Receive event) {
        if (event.getPacket() instanceof class_2604) {
            class_2604 packet = (class_2604)event.getPacket();
            if (item.isEnabled() && packet.method_11169() == class_1299.field_6052) {
                event.setCancelled(true);
            }
            if (projectile.isEnabled() && (packet.method_11169() == class_1299.field_6122 || packet.method_11169() == class_1299.field_6068 || packet.method_11169() == class_1299.field_6064 || packet.method_11169() == class_1299.field_6144 || packet.method_11169() == class_1299.field_6082 || packet.method_11169() == class_1299.field_6135 || packet.method_11169() == class_1299.field_6127)) {
                event.setCancelled(true);
            }
            if (firework.isEnabled() && packet.method_11169() == class_1299.field_6133) {
                event.setCancelled(true);
            }
            if (fishinghook.isEnabled() && packet.method_11169() == class_1299.field_6103) {
                event.setCancelled(true);
            }
            if (this.tnt.isEnabled() && packet.method_11169() == class_1299.field_6063) {
                event.setCancelled(true);
            }
            if (fallingBlocks.isEnabled() && packet.method_11169() == class_1299.field_6089) {
                event.setCancelled(true);
            }
        }
    }

    @Subscribe
    public void onParticle(ParticleEvent event) {
        if (this.explosParticle.isEnabled() && event.getParticle() instanceof class_689) {
            event.setCancelled(true);
        }
        if (elderGuardian.isEnabled() && event.getParticle() instanceof class_700) {
            event.setCancelled(true);
        }
    }

    public static NoRender getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NoRender();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

