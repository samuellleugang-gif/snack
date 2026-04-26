package com.samuell.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import com.samuell.client.manager.ModuleManager;
import com.samuell.client.gui.ModuleScreen;
import net.minecraft.client.MinecraftClient;

public class SamuellClient implements ClientModInitializer {
    public static final String MOD_ID = "samuellclient";
    public static ModuleManager moduleManager;
    private static KeyBinding openMenuKey;

    @Override
    public void onInitializeClient() {
        moduleManager = new ModuleManager();
        
        // Enregistre la touche J pour ouvrir le menu
        openMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.samuellclient.menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_J,
                "category.samuellclient.main"
        ));

        // Event au clic sur J
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openMenuKey.wasPressed()) {
                MinecraftClient.getInstance().setScreen(new ModuleScreen());
            }
        });
    }
}
