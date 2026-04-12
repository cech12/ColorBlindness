package de.cech12.colorblindness.client.renderer;

import de.cech12.colorblindness.client.ColorblindnessReloadListener;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;

@SuppressWarnings("unused")
@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class NeoForgeReloader {

    @SubscribeEvent
    public static void addReloadListener(RegisterClientReloadListenersEvent event) {
        if (event == null) {
            return;
        }
        event.registerReloadListener(new ColorblindnessReloadListener());
    }

}
