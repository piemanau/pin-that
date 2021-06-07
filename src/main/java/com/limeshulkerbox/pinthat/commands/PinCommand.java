package com.limeshulkerbox.pinthat.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.minecraft.client.network.ClientCommandSource;
import net.minecraft.command.argument.MessageArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.lwjgl.system.CallbackI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

public final class PinCommand implements Command<FabricClientCommandSource> {
    public static String PinnedMessage = "";

    @Override
    public int run(CommandContext<FabricClientCommandSource> context) throws CommandSyntaxException {
        PinnedMessage = context.getArgument("message", MessageArgumentType.MessageFormat.class).format(null, false).asString();
        return 0;
    }
}
