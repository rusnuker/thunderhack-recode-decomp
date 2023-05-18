//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package meteordevelopment.orbit.listeners;

import java.util.function.*;

public class ConsumerListener<T> implements IListener
{
    private final Class<?> target;
    private final int priority;
    private final Consumer<T> executor;
    
    public ConsumerListener(final Class<?> target, final int priority, final Consumer<T> executor) {
        this.target = target;
        this.priority = priority;
        this.executor = executor;
    }
    
    public ConsumerListener(final Class<?> target, final Consumer<T> executor) {
        this(target, 0, executor);
    }
    
    @Override
    public void call(final Object event) {
        this.executor.accept((T)event);
    }
    
    @Override
    public Class<?> getTarget() {
        return this.target;
    }
    
    @Override
    public int getPriority() {
        return this.priority;
    }
    
    @Override
    public boolean isStatic() {
        return false;
    }
}
