package de.cech12.colorblindness.client.renderer;

import de.cech12.colorblindness.client.EffectRendererHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.listener.Priority;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(Dist.CLIENT)
public class ForgeEffectRenderer {

    @SubscribeEvent(priority = Priority.LOWEST)
    public static void onRender(TickEvent.RenderTickEvent event) {
        if (event == null || event.phase != TickEvent.Phase.END) {
            return;
        }
        EffectRendererHelper.renderColorBlindnessEffect(event.getTimer().getGameTimeDeltaTicks());
    }

}
