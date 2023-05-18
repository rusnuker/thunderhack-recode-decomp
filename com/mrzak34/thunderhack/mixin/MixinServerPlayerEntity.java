//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import org.spongepowered.asm.mixin.*;
import net.minecraft.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.mrzak34.thunderhack.event.events.*;
import com.mrzak34.thunderhack.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ class_3222.class })
public class MixinServerPlayerEntity
{
    @Inject(method = { "updateInput" }, cancellable = true, at = { @At("HEAD") })
    private void updateInputPre(final float sidewaysSpeed, final float forwardSpeed, final boolean jumping, final boolean sneaking, final CallbackInfo callback) {
        final UpdateInputEvent.Pre event = new UpdateInputEvent.Pre(sidewaysSpeed, forwardSpeed, jumping, sneaking);
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
    
    @Inject(method = { "updateInput" }, cancellable = true, at = { @At("RETURN") })
    private void updateInputPost(final float sidewaysSpeed, final float forwardSpeed, final boolean jumping, final boolean sneaking, final CallbackInfo callback) {
        final UpdateInputEvent.Post event = new UpdateInputEvent.Post(sidewaysSpeed, forwardSpeed, jumping, sneaking);
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
}
