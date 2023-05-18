//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import org.spongepowered.asm.mixin.*;
import net.minecraft.*;
import com.mrzak34.thunderhack.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.mrzak34.thunderhack.event.events.*;

@Mixin({ class_1657.class })
public abstract class MixinPlayerEntity extends class_1309
{
    public MixinPlayerEntity(final class_1937 worldIn) {
        super(class_1299.field_6097, worldIn);
    }
    
    @Inject(method = { "travel" }, at = { @At("HEAD") }, cancellable = true)
    private void travel(final class_243 movement, final CallbackInfo callback) {
        final TravelEvent event = new TravelEvent(movement.method_10216(), movement.method_10214(), movement.method_10215());
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
    
    @Inject(method = { "clipAtLedge" }, at = { @At("HEAD") }, cancellable = true)
    protected void clipAtLedge(final CallbackInfoReturnable<Boolean> info) {
        final ClipAtLedgeEvent event = new ClipAtLedgeEvent();
        Main.EVENT_BUS.post((Object)event);
        if (event.isSet()) {
            info.setReturnValue((Object)event.isClip());
        }
    }
}
