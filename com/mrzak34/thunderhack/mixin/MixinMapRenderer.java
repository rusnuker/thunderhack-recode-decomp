//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import org.spongepowered.asm.mixin.*;
import net.minecraft.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.mrzak34.thunderhack.event.events.*;
import com.mrzak34.thunderhack.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ class_330.class })
public class MixinMapRenderer
{
    @Inject(method = { "draw" }, at = { @At("HEAD") }, cancellable = true)
    private void renderMapPost(final class_4587 matrices, final class_4597 vertexConsumers, final int id, final class_22 state, final boolean hidePlayerIcons, final int light, final CallbackInfo callback) {
        final RenderMapEvent event = new RenderMapEvent(matrices, vertexConsumers, id, state, hidePlayerIcons, light);
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
}
