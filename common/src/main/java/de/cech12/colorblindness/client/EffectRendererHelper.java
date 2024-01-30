package de.cech12.colorblindness.client;

import com.google.gson.JsonSyntaxException;
import de.cech12.colorblindness.Constants;
import de.cech12.colorblindness.platform.Services;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class EffectRendererHelper {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final ResourceLocation ACHROMATOMALY = new ResourceLocation(Constants.MOD_ID, "shaders/post/achromatomaly.json");
    private static final ResourceLocation ACHROMATOPSIA = new ResourceLocation(Constants.MOD_ID, "shaders/post/achromatopsia.json");
    private static final ResourceLocation DEUTERANOMALY = new ResourceLocation(Constants.MOD_ID, "shaders/post/deuteranomaly.json");
    private static final ResourceLocation DEUTERANOPIA = new ResourceLocation(Constants.MOD_ID, "shaders/post/deuteranopia.json");
    private static final ResourceLocation PROTANOMALY = new ResourceLocation(Constants.MOD_ID, "shaders/post/protanomaly.json");
    private static final ResourceLocation PROTANOPIA = new ResourceLocation(Constants.MOD_ID, "shaders/post/protanopia.json");
    private static final ResourceLocation TRITANOMALY = new ResourceLocation(Constants.MOD_ID, "shaders/post/tritanomaly.json");
    private static final ResourceLocation TRITANOPIA = new ResourceLocation(Constants.MOD_ID, "shaders/post/tritanopia.json");

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
    private static PostChain lastShader = null;

    /**
     * Should be called by a render event and renders the effect if it is active.
     * @param renderTickTime render tick time
     */
    public static void renderColorBlindnessEffect(float renderTickTime) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            makeColorShaders();

            PostChain activeShader = null;
            if (player.hasEffect(Services.REGISTRY.getAchromatomalyEffect())) {
                activeShader = achromatomalyShader;
            } else if (player.hasEffect(Services.REGISTRY.getAchromatopsiaEffect())) {
                activeShader = achromatopsiaShader;
            } else if (player.hasEffect(Services.REGISTRY.getDeuteranomalyEffect())) {
                activeShader = deuteranomalyShader;
            } else if (player.hasEffect(Services.REGISTRY.getDeuteranopiaEffect())) {
                activeShader = deuteranopiaShader;
            } else if (player.hasEffect(Services.REGISTRY.getProtanomalyEffect())) {
                activeShader = protanomalyShader;
            } else if (player.hasEffect(Services.REGISTRY.getProtanopiaEffect())) {
                activeShader = protanopiaShader;
            } else if (player.hasEffect(Services.REGISTRY.getTritanomalyEffect())) {
                activeShader = tritanomalyShader;
            } else if (player.hasEffect(Services.REGISTRY.getTritanopiaEffect())) {
                activeShader = tritanopiaShader;
            }

            if (activeShader != null) {
                if (lastShader != activeShader) {
                    lastShader = activeShader;
                    lastWidth = 0;
                    lastHeight = 0;
                }
                updateShaderGroupSize(activeShader);
                activeShader.process(renderTickTime);
                Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
            }
        }
    }

    private static PostChain createShaderGroup(ResourceLocation location) {
        try {
            Minecraft mc = Minecraft.getInstance();
            return new PostChain(mc.getTextureManager(), mc.getResourceManager(), mc.getMainRenderTarget(), location);
        } catch (IOException ioexception) {
            LOGGER.warn("Failed to load shader: {}", location, ioexception);
        } catch (JsonSyntaxException jsonsyntaxexception) {
            LOGGER.warn("Failed to parse shader: {}", location, jsonsyntaxexception);
        }
        return null;
    }

    private static void makeColorShaders() {
        if (achromatomalyShader == null) {
            achromatomalyShader = createShaderGroup(ACHROMATOMALY);
        }
        if (achromatopsiaShader == null) {
            achromatopsiaShader = createShaderGroup(ACHROMATOPSIA);
        }
        if (deuteranomalyShader == null) {
            deuteranomalyShader = createShaderGroup(DEUTERANOMALY);
        }
        if (deuteranopiaShader == null) {
            deuteranopiaShader = createShaderGroup(DEUTERANOPIA);
        }
        if (protanomalyShader == null) {
            protanomalyShader = createShaderGroup(PROTANOMALY);
        }
        if (protanopiaShader == null) {
            protanopiaShader = createShaderGroup(PROTANOPIA);
        }
        if (tritanomalyShader == null) {
            tritanomalyShader = createShaderGroup(TRITANOMALY);
        }
        if (tritanopiaShader == null) {
            tritanopiaShader = createShaderGroup(TRITANOPIA);
        }
    }

    private static void updateShaderGroupSize(PostChain shaderGroup) {
        if (shaderGroup != null) {
            Minecraft mc = Minecraft.getInstance();
            int width = mc.getWindow().getWidth();
            int height = mc.getWindow().getHeight();
            if (width != lastWidth || height != lastHeight) {
                lastWidth = width;
                lastHeight = height;
                shaderGroup.resize(width, height);
            }
        }
    }

}
