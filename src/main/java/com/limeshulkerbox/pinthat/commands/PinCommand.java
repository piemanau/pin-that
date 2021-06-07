package com.limeshulkerbox.pinthat.commands;

import com.limeshulkerbox.pinthat.ModInitializer;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.minecraft.command.argument.MessageArgumentType;

public final class PinCommand implements Command<FabricClientCommandSource> {
    @Override
    public int run(CommandContext<FabricClientCommandSource> context) throws CommandSyntaxException {
        ModInitializer.config.pinnedMessage = context.getArgument("message", MessageArgumentType.MessageFormat.class).format(null, false).asString();
        return 0;
    }
}
