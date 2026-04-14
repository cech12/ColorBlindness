package de.cech12.colorblindness.client;

import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import org.jetbrains.annotations.NotNull;

public class ColorblindnessReloadListener implements ResourceManagerReloadListener {

    @Override
    public void onResourceManagerReload(@NotNull ResourceManager resourceManager) {
        EffectRendererHelper.resetShaders();
    }

}
