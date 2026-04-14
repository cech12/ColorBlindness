package de.cech12.colorblindness.client.renderer;

import de.cech12.colorblindness.client.ColorblindnessReloadListener;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForgeReloader {

    @SubscribeEvent
    public static void addReloadListener(RegisterClientReloadListenersEvent event) {
        if (event == null) {
            return;
        }
        event.registerReloadListener(new ColorblindnessReloadListener());
    }

}
