/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.class_1313
 *  net.minecraft.class_243
 *  net.minecraft.class_310
 *  net.minecraft.class_437
 *  net.minecraft.class_638
 *  net.minecraft.class_742
 *  net.minecraft.class_746
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.mrzak34.thunderhack.mixin;

import com.mojang.authlib.GameProfile;
import com.mrzak34.thunderhack.Main;
import com.mrzak34.thunderhack.event.events.MoveEvent;
import com.mrzak34.thunderhack.event.events.MovementEvent;
import com.mrzak34.thunderhack.module.modules.movement.NoSlow;
import com.mrzak34.thunderhack.module.modules.render.NoRender;
import net.minecraft.class_1313;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_437;
import net.minecraft.class_638;
import net.minecraft.class_742;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_746.class})
public class MixinClientPlayerEntity
extends class_742 {
    private MixinClientPlayerEntity(class_638 world, GameProfile profile) {
        super(world, profile);
    }

    @Redirect(method={"tickMovement"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"), require=0)
    private boolean tickMovement_isUsingItem(class_746 player) {
        return NoSlow.getInstance().isToggled() && NoSlow.getInstance().item.isEnabled() ? false : player.method_6115();
    }

    @Inject(method={"move"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/AbstractClientPlayerEntity;move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V")}, cancellable=true)
    private void move(class_1313 type, class_243 movement, CallbackInfo callback) {
        MoveEvent event = new MoveEvent(type, movement.method_10216(), movement.method_10214(), movement.method_10215());
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            super.method_5784(type, new class_243(event.getX(), event.getY(), event.getZ()));
            callback.cancel();
        }
    }

    @Redirect(method={"updateNausea"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;closeHandledScreen()V", ordinal=0), require=0)
    private void updateNausea_closeHandledScreen(class_746 player) {
        if (!NoRender.portals.isEnabled()) {
            this.method_7346();
        }
    }

    @Redirect(method={"updateNausea"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/MinecraftClient;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V", ordinal=0), require=0)
    private void updateNausea_setScreen(class_310 client, class_437 screen) {
        if (!NoRender.portals.isEnabled()) {
            client.method_1507(screen);
        }
    }

    @Inject(method={"sendMovementPackets"}, at={@At(value="HEAD")}, cancellable=true)
    private void sendMovementPacketsPre(CallbackInfo callback) {
        MovementEvent.Pre event = new MovementEvent.Pre();
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }

    @Inject(method={"sendMovementPackets"}, at={@At(value="RETURN")}, cancellable=true)
    private void sendMovementPacketsPost(CallbackInfo callback) {
        MovementEvent.Post event = new MovementEvent.Post();
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
}

