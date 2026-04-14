package de.cech12.colorblindness.client;

import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.NotNull;

public class ColorblindnessReloadListener extends SimplePreparableReloadListener<Boolean> {

    @Override
    @NotNull
    protected Boolean prepare(@NotNull ResourceManager resourceManager, @NotNull ProfilerFiller profilerFiller) {
        EffectRendererHelper.unloadShaders();
        return Boolean.TRUE;
    }

    @Override
    protected void apply(@NotNull Boolean unused, @NotNull ResourceManager resourceManager, @NotNull ProfilerFiller profilerFiller) {
        EffectRendererHelper.resetShaders();
    }

}
