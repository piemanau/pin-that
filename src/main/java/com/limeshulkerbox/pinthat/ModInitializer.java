package com.limeshulkerbox.pinthat;

import com.limeshulkerbox.pinthat.commands.PinClearCommand;
import com.limeshulkerbox.pinthat.commands.PinColorCommand;
import com.limeshulkerbox.pinthat.commands.PinCommand;
import com.limeshulkerbox.pinthat.config.ConfigStructure;
import com.mojang.brigadier.arguments.StringArgumentType;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.commands.arguments.ColorArgument;
import net.minecraft.commands.arguments.MessageArgument;

@Environment(EnvType.CLIENT)
public class ModInitializer implements ClientModInitializer {

    public static ConfigStructure config;

    @Override
    public void onInitializeClient() {
        AutoConfig.register(ConfigStructure.class, JanksonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ConfigStructure.class).getConfig();
        AutoConfig.getConfigHolder(ConfigStructure.class).save();

        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("pin")
                    .then(ClientCommandManager.literal("set")
                    .then(ClientCommandManager.argument("message", MessageArgument.message())
                        .executes(new PinCommand())))

                    .then(ClientCommandManager.literal("color")
                    .then(ClientCommandManager.argument("color", ColorArgument.color())
                        .executes(new PinColorCommand())))

                    .then(ClientCommandManager.literal("clear")
                        .executes(new PinClearCommand())));
        }));
    }
}
