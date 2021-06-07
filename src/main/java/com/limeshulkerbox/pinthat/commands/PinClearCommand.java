package com.limeshulkerbox.pinthat.commands;

import com.limeshulkerbox.pinthat.ModInitializer;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;

public final class PinClearCommand implements Command<FabricClientCommandSource> {
    @Override
    public int run(CommandContext context) {
        ModInitializer.config.pinnedMessage = "";
        return 0;
    }
}
