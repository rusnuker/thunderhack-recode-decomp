//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package meteordevelopment.orbit;

import meteordevelopment.orbit.listeners.*;

public interface IEventBus
{
    void registerLambdaFactory(final String p0, final LambdaListener.Factory p1);
    
     <T> T post(final T p0);
    
     <T extends ICancellable> T post(final T p0);
    
    void subscribe(final Object p0);
    
    void subscribe(final Class<?> p0);
    
    void subscribe(final IListener p0);
    
    void unsubscribe(final Object p0);
    
    void unsubscribe(final Class<?> p0);
    
    void unsubscribe(final IListener p0);
}
