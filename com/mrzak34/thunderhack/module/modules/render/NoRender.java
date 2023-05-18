//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.render;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.mixin.*;
import com.google.common.eventbus.*;
import com.mrzak34.thunderhack.util.*;
import com.mrzak34.thunderhack.event.events.*;
import net.minecraft.*;

public class NoRender extends Module
{
    public BooleanSetting fire;
    public BooleanSetting water;
    public BooleanSetting hurtcam;
    public BooleanSetting explosParticle;
    public BooleanSetting tnt;
    public ParentSetting explosion;
    public BooleanSetting totem;
    public BooleanSetting nausea;
    public static BooleanSetting fog;
    public static BooleanSetting blindness;
    public static BooleanSetting projectile;
    public static BooleanSetting map;
    public static BooleanSetting item;
    public static BooleanSetting sign;
    public static BooleanSetting blockOverlay;
    public static BooleanSetting firework;
    public static BooleanSetting fishinghook;
    public static BooleanSetting portals;
    public static BooleanSetting enchantingTable;
    public static BooleanSetting fallingBlocks;
    public static BooleanSetting elderGuardian;
    public static final class_2960 lightning;
    private static NoRender INSTANCE;
    
    public NoRender() {
        super("NoRender", 0, false, Category.RENDER);
        this.fire = new BooleanSetting("fire", true);
        this.water = new BooleanSetting("water", true);
        this.hurtcam = new BooleanSetting("hurtcam", true);
        this.explosParticle = new BooleanSetting("particle", true);
        this.tnt = new BooleanSetting("tnt", false);
        this.explosion = new ParentSetting("explosion", false, false, new Setting[] { this.explosParticle, this.tnt });
        this.totem = new BooleanSetting("totem", false);
        this.nausea = new BooleanSetting("nausea", false);
        this.addSettings(new Setting[] { this.fire, this.water, this.hurtcam, this.explosion, this.totem, this.nausea, NoRender.fog, NoRender.blindness, NoRender.projectile, NoRender.map, NoRender.item, NoRender.sign, NoRender.blockOverlay, NoRender.firework, NoRender.fishinghook, NoRender.portals, NoRender.enchantingTable, NoRender.fallingBlocks, NoRender.elderGuardian });
        this.setInstance();
    }
    
    @Subscribe
    public void onRenderSign(final RenderSignEvent event) {
        if (NoRender.sign.isEnabled()) {
            ((ISignBlockEntity)event.getSignBlockEntity()).setText(new class_2561[] { class_5244.field_39003, class_5244.field_39003, class_5244.field_39003, class_5244.field_39003 });
        }
    }
    
    @Subscribe
    public void onRenderMap(final RenderMapEvent event) {
        if (NoRender.map.isEnabled()) {
            event.setCancelled(true);
            event.getMatrices().method_22904(0.0, 0.0, -10.0);
            RenderUtil.drawTexture(NoRender.lightning, 0, 0, 64, 64, event.getMatrices());
        }
    }
    
    @Subscribe
    public void onPacketReceive(final PacketEvent.Receive event) {
        if (event.getPacket() instanceof class_2604) {
            final class_2604 packet = (class_2604)event.getPacket();
            if (NoRender.item.isEnabled() && packet.method_11169() == class_1299.field_6052) {
                event.setCancelled(true);
            }
            if (NoRender.projectile.isEnabled() && (packet.method_11169() == class_1299.field_6122 || packet.method_11169() == class_1299.field_6068 || packet.method_11169() == class_1299.field_6064 || packet.method_11169() == class_1299.field_6144 || packet.method_11169() == class_1299.field_6082 || packet.method_11169() == class_1299.field_6135 || packet.method_11169() == class_1299.field_6127)) {
                event.setCancelled(true);
            }
            if (NoRender.firework.isEnabled() && packet.method_11169() == class_1299.field_6133) {
                event.setCancelled(true);
            }
            if (NoRender.fishinghook.isEnabled() && packet.method_11169() == class_1299.field_6103) {
                event.setCancelled(true);
            }
            if (this.tnt.isEnabled() && packet.method_11169() == class_1299.field_6063) {
                event.setCancelled(true);
            }
            if (NoRender.fallingBlocks.isEnabled() && packet.method_11169() == class_1299.field_6089) {
                event.setCancelled(true);
            }
        }
    }
    
    @Subscribe
    public void onParticle(final ParticleEvent event) {
        if (this.explosParticle.isEnabled() && event.getParticle() instanceof class_689) {
            event.setCancelled(true);
        }
        if (NoRender.elderGuardian.isEnabled() && event.getParticle() instanceof class_700) {
            event.setCancelled(true);
        }
    }
    
    public static NoRender getInstance() {
        if (NoRender.INSTANCE == null) {
            NoRender.INSTANCE = new NoRender();
        }
        return NoRender.INSTANCE;
    }
    
    private void setInstance() {
        NoRender.INSTANCE = this;
    }
    
    static {
        NoRender.fog = new BooleanSetting("fog", false);
        NoRender.blindness = new BooleanSetting("blindness", false);
        NoRender.projectile = new BooleanSetting("projectile", false);
        NoRender.map = new BooleanSetting("map", false);
        NoRender.item = new BooleanSetting("item", false);
        NoRender.sign = new BooleanSetting("sign", false);
        NoRender.blockOverlay = new BooleanSetting("block overlay", false);
        NoRender.firework = new BooleanSetting("firework", false);
        NoRender.fishinghook = new BooleanSetting("fishinghook", false);
        NoRender.portals = new BooleanSetting("portals", true);
        NoRender.enchantingTable = new BooleanSetting("enchanting table", true);
        NoRender.fallingBlocks = new BooleanSetting("falling blocks", true);
        NoRender.elderGuardian = new BooleanSetting("elder guardian", false);
        lightning = new class_2960("textures/lightning.png");
        NoRender.INSTANCE = new NoRender();
    }
}
