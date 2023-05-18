//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import org.spongepowered.asm.mixin.*;
import com.mojang.authlib.*;
import com.mrzak34.thunderhack.module.modules.movement.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.mrzak34.thunderhack.*;
import org.spongepowered.asm.mixin.injection.*;
import com.mrzak34.thunderhack.module.modules.render.*;
import net.minecraft.*;
import com.mrzak34.thunderhack.event.events.*;

@Mixin({ class_746.class })
public class MixinClientPlayerEntity extends class_742
{
    private MixinClientPlayerEntity(final class_638 world, final GameProfile profile) {
        super(world, profile);
    }
    
    @Redirect(method = { "tickMovement" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"), require = 0)
    private boolean tickMovement_isUsingItem(final class_746 player) {
        return (!NoSlow.getInstance().isToggled() || !NoSlow.getInstance().item.isEnabled()) && player.method_6115();
    }
    
    @Inject(method = { "move" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V") }, cancellable = true)
    private void move(final class_1313 type, final class_243 movement, final CallbackInfo callback) {
        final MoveEvent event = new MoveEvent(type, movement.method_10216(), movement.method_10214(), movement.method_10215());
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            super.method_5784(type, new class_243(event.getX(), event.getY(), event.getZ()));
            callback.cancel();
        }
    }
    
    @Redirect(method = { "updateNausea" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;closeHandledScreen()V", ordinal = 0), require = 0)
    private void updateNausea_closeHandledScreen(final class_746 player) {
        if (!NoRender.portals.isEnabled()) {
            this.method_7346();
        }
    }
    
    @Redirect(method = { "updateNausea" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V", ordinal = 0), require = 0)
    private void updateNausea_setScreen(final class_310 client, final class_437 screen) {
        if (!NoRender.portals.isEnabled()) {
            client.method_1507(screen);
        }
    }
    
    @Inject(method = { "sendMovementPackets" }, at = { @At("HEAD") }, cancellable = true)
    private void sendMovementPacketsPre(final CallbackInfo callback) {
        final MovementEvent.Pre event = new MovementEvent.Pre();
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
    
    @Inject(method = { "sendMovementPackets" }, at = { @At("RETURN") }, cancellable = true)
    private void sendMovementPacketsPost(final CallbackInfo callback) {
        final MovementEvent.Post event = new MovementEvent.Post();
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
}
