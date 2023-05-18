//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.Account;

import java.util.*;
import org.jetbrains.annotations.*;
import java.util.function.*;
import java.util.concurrent.*;

public class OfflineAccount implements Account
{
    private final String name;
    private final UUID uuid;
    
    public OfflineAccount(final String name, final UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }
    
    @NotNull
    public UUID uuid() {
        return this.uuid;
    }
    
    @NotNull
    public String name() {
        return this.name;
    }
    
    @NotNull
    public CompletableFuture<Account.AuthData> login(@NotNull final BiConsumer<String, Object[]> progressHandler) {
        return CompletableFuture.completedFuture(new Account.AuthData(this.name(), this.uuid(), "0", "legacy"));
    }
}
