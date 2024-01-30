package de.cech12.colorblindness.client.renderer;

import de.cech12.colorblindness.client.EffectRendererHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class NeoForgeEffectRenderer {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onRender(TickEvent.RenderTickEvent event) {
        if (event == null || event.phase != TickEvent.Phase.END) {
            return;
        }
        EffectRendererHelper.renderColorBlindnessEffect(event.renderTickTime);
    }

}
