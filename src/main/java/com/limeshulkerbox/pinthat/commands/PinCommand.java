package com.limeshulkerbox.pinthat.commands;

import com.limeshulkerbox.pinthat.ModInitializer;
import com.limeshulkerbox.pinthat.config.ConfigStructure;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.network.chat.Component;

public final class PinCommand implements Command<FabricClientCommandSource> {
    @Override
    public int run(CommandContext<FabricClientCommandSource> context) throws CommandSyntaxException {
        ModInitializer.config.pinnedMessage = context.getArgument("message", MessageArgument.Message.class).getText();
        context.getSource().sendFeedback(
            Component.literal("Pinned message set to " + ModInitializer.config.pinnedMessage)
        );
        AutoConfig.getConfigHolder(ConfigStructure.class).save();
        return 0;
    }
}
