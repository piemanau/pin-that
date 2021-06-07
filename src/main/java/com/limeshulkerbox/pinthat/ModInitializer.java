package com.limeshulkerbox.pinthat;

import com.limeshulkerbox.pinthat.commands.PinClearCommand;
import com.limeshulkerbox.pinthat.commands.PinCommand;
import com.limeshulkerbox.pinthat.config.ConfigStructure;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.minecraft.command.argument.MessageArgumentType;

@Environment(EnvType.CLIENT)
public class ModInitializer implements ClientModInitializer {

    public static ConfigStructure config;

    @Override
    public void onInitializeClient() {
        AutoConfig.register(ConfigStructure.class, JanksonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ConfigStructure.class).getConfig();

         ClientCommandManager.DISPATCHER.register(ClientCommandManager.literal("pin")
                 .then(ClientCommandManager.literal("set")
                         .then(ClientCommandManager.argument("message", MessageArgumentType.message())
                        .executes(new PinCommand())))
                 .then(ClientCommandManager.literal("clear")
                        .executes(new PinClearCommand())));
    }
}
