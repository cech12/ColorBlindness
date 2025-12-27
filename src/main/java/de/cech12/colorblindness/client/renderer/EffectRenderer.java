package de.cech12.colorblindness.client.renderer;

import com.google.gson.JsonSyntaxException;
import de.cech12.colorblindness.ColorBlindness;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static de.cech12.colorblindness.ColorBlindness.MOD_ID;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class EffectRenderer {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final ResourceLocation ACHROMATOMALY = new ResourceLocation(MOD_ID, "shaders/post/achromatomaly.json");
    private static final ResourceLocation ACHROMATOPSIA = new ResourceLocation(MOD_ID, "shaders/post/achromatopsia.json");
    private static final ResourceLocation DEUTERANOMALY = new ResourceLocation(MOD_ID, "shaders/post/deuteranomaly.json");
    private static final ResourceLocation DEUTERANOPIA = new ResourceLocation(MOD_ID, "shaders/post/deuteranopia.json");
    private static final ResourceLocation PROTANOMALY = new ResourceLocation(MOD_ID, "shaders/post/protanomaly.json");
    private static final ResourceLocation PROTANOPIA = new ResourceLocation(MOD_ID, "shaders/post/protanopia.json");
    private static final ResourceLocation TRITANOMALY = new ResourceLocation(MOD_ID, "shaders/post/tritanomaly.json");
    private static final ResourceLocation TRITANOPIA = new ResourceLocation(MOD_ID, "shaders/post/tritanopia.json");

    private static PostChain achromatomalyShader;
    private static PostChain achromatopsiaShader;
    private static PostChain deuteranomalyShader;
    private static PostChain deuteranopiaShader;
    private static PostChain protanomalyShader;
    private static PostChain protanopiaShader;
    private static PostChain tritanomalyShader;
    private static PostChain tritanopiaShader;

    private static int lastWidth = 0;
    private static int lastHeight = 0;

    private static final List<PostChain> activeShaders = new ArrayList<>();

    private static PostChain createShaderGroup(ResourceLocation location) {
        try {
            Minecraft mc = Minecraft.getInstance();
            PostChain postChain = new PostChain(mc.getTextureManager(), mc.getResourceManager(), mc.getMainRenderTarget(), location);
            postChain.resize(mc.getWindow().getWidth(), mc.getWindow().getHeight());
            return postChain;
        } catch (IOException ioexception) {
            LOGGER.warn("Failed to load shader: {}", location, ioexception);
        } catch (JsonSyntaxException jsonsyntaxexception) {
            LOGGER.warn("Failed to parse shader: {}", location, jsonsyntaxexception);
        }
        return null;
    }

    private static void makeColorShaders(Minecraft mc) {
        int width = mc.getWindow().getWidth();
        int height = mc.getWindow().getHeight();
        boolean resizeNeeded = false;
        if (width != lastWidth || height != lastHeight) {
            lastWidth = width;
            lastHeight = height;
            resizeNeeded = true;
        }
        if (achromatomalyShader == null) {
            achromatomalyShader = createShaderGroup(ACHROMATOMALY);
        } else if (resizeNeeded) {
            achromatomalyShader.resize(width, height);
        }
        if (achromatopsiaShader == null) {
            achromatopsiaShader = createShaderGroup(ACHROMATOPSIA);
        } else if (resizeNeeded) {
            achromatopsiaShader.resize(width, height);
        }
        if (deuteranomalyShader == null) {
            deuteranomalyShader = createShaderGroup(DEUTERANOMALY);
        } else if (resizeNeeded) {
            deuteranomalyShader.resize(width, height);
        }
        if (deuteranopiaShader == null) {
            deuteranopiaShader = createShaderGroup(DEUTERANOPIA);
        } else if (resizeNeeded) {
            deuteranopiaShader.resize(width, height);
        }
        if (protanomalyShader == null) {
            protanomalyShader = createShaderGroup(PROTANOMALY);
        } else if (resizeNeeded) {
            protanomalyShader.resize(width, height);
        }
        if (protanopiaShader == null) {
            protanopiaShader = createShaderGroup(PROTANOPIA);
        } else if (resizeNeeded) {
            protanopiaShader.resize(width, height);
        }
        if (tritanomalyShader == null) {
            tritanomalyShader = createShaderGroup(TRITANOMALY);
        } else if (resizeNeeded) {
            tritanomalyShader.resize(width, height);
        }
        if (tritanopiaShader == null) {
            tritanopiaShader = createShaderGroup(TRITANOPIA);
        } else if (resizeNeeded) {
            tritanopiaShader.resize(width, height);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onRender(TickEvent.RenderTickEvent event) {
        if (event == null || event.phase != TickEvent.Phase.END) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }

        makeColorShaders(mc);

        fillActiveShaders(player);

        if (activeShaders.isEmpty()) {
            return;
        }

        for (PostChain shader : activeShaders) {
            if (shader != null) {
                shader.process(event.renderTickTime);
            }
        }
        mc.getMainRenderTarget().bindWrite(false);
    }


    private static void fillActiveShaders(LocalPlayer player) {
        activeShaders.clear();
        if (player.hasEffect(ColorBlindness.ACHROMATOPSIA.get())) {
            activeShaders.add(achromatopsiaShader);
            return;
        }

        if (player.hasEffect(ColorBlindness.ACHROMATOMALY.get())) {
            activeShaders.add(achromatomalyShader);
        }
        if (player.hasEffect(ColorBlindness.TRITANOPIA.get())) {
            activeShaders.add(tritanopiaShader);
        } else if (player.hasEffect(ColorBlindness.TRITANOMALY.get())) {
            activeShaders.add(tritanomalyShader);
        }
        if (player.hasEffect(ColorBlindness.DEUTERANOPIA.get())) {
            activeShaders.add(deuteranopiaShader);
        } else if (player.hasEffect(ColorBlindness.DEUTERANOMALY.get())) {
            activeShaders.add(deuteranomalyShader);
        }
        if (player.hasEffect(ColorBlindness.PROTANOPIA.get())) {
            activeShaders.add(protanopiaShader);
        } else if (player.hasEffect(ColorBlindness.PROTANOMALY.get())) {
            activeShaders.add(protanomalyShader);
        }
    }

}
