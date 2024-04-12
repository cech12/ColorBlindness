package de.cech12.colorblindness.mixin;

import de.cech12.colorblindness.client.EffectRendererHelper;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {

    @Inject(method = "render", at = @At("RETURN"))
    private void renderProxy(float partialTick, long nanos, boolean error, CallbackInfo info) {
        EffectRendererHelper.renderColorBlindnessEffect(partialTick);
    }
}