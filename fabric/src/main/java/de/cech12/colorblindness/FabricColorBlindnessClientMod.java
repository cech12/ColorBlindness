package de.cech12.colorblindness;

import de.cech12.colorblindness.client.ColorblindnessReloadListener;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.minecraft.server.packs.PackType;

public class FabricColorBlindnessClientMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ResourceLoader.get(PackType.CLIENT_RESOURCES).registerReloadListener(Constants.id("reload"), new ColorblindnessReloadListener());
    }

}
