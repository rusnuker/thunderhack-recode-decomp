//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import org.spongepowered.asm.mixin.*;
import org.joml.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.*;
import com.mojang.blaze3d.systems.*;
import com.mrzak34.thunderhack.event.events.*;
import com.mrzak34.thunderhack.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ class_761.class })
public class MixinWorldRenderer
{
    @Inject(method = { "render" }, at = { @At("RETURN") })
    private void render_return(final class_4587 matrices, final float tickDelta, final long limitTime, final boolean renderBlockOutline, final class_4184 camera, final class_757 gameRenderer, final class_765 lightmapTextureManager, final Matrix4f positionMatrix, final CallbackInfo callback) {
        RenderSystem.clear(256, class_310.field_1703);
        final RenderWorldEvent event = (RenderWorldEvent)new RenderWorldEvent.Post(tickDelta, matrices);
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
}
