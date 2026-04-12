package de.cech12.colorblindness;

import de.cech12.colorblindness.client.ColorblindnessReloadListener;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;

public class FabricColorBlindnessClientMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(new ReloadListener());
    }

    private static class ReloadListener extends ColorblindnessReloadListener implements IdentifiableResourceReloadListener {
        @Override
        public ResourceLocation getFabricId() {
            return Constants.id("reload");
        }
    }

}
