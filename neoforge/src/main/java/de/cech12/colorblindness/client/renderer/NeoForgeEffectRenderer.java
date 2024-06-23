package de.cech12.colorblindness.client.renderer;

import de.cech12.colorblindness.client.EffectRendererHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderFrameEvent;

@SuppressWarnings("unused")
@EventBusSubscriber(Dist.CLIENT)
public class NeoForgeEffectRenderer {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onRender(RenderFrameEvent.Post event) {
        if (event == null) {
            return;
        }
        EffectRendererHelper.renderColorBlindnessEffect(event.getPartialTick().getGameTimeDeltaTicks());
    }

}
