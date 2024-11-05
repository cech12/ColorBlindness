package de.cech12.colorblindness.client;

import com.google.gson.JsonSyntaxException;
import com.mojang.blaze3d.resource.CrossFrameResourcePool;
import de.cech12.colorblindness.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LevelTargetBundle;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EffectRendererHelper {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final ResourceLocation ACHROMATOMALY = Constants.id("achromatomaly");
    private static final ResourceLocation ACHROMATOPSIA = Constants.id("achromatopsia");
    private static final ResourceLocation DEUTERANOMALY = Constants.id("deuteranomaly");
    private static final ResourceLocation DEUTERANOPIA = Constants.id("deuteranopia");
    private static final ResourceLocation PROTANOMALY = Constants.id("protanomaly");
    private static final ResourceLocation PROTANOPIA = Constants.id("protanopia");
    private static final ResourceLocation TRITANOMALY = Constants.id("tritanomaly");
    private static final ResourceLocation TRITANOPIA = Constants.id("tritanopia");
    private static final CrossFrameResourcePool RESOURCE_POOL = new CrossFrameResourcePool(3);

    private static PostChain achromatomalyShader;
    private static PostChain achromatopsiaShader;
    private static PostChain deuteranomalyShader;
    private static PostChain deuteranopiaShader;
    private static PostChain protanomalyShader;
    private static PostChain protanopiaShader;
    private static PostChain tritanomalyShader;
    private static PostChain tritanopiaShader;

    /**
     * Should be called by a render event and renders the effect if it is active.
     * @param renderTickTime render tick time
     */
    public static void renderColorBlindnessEffect(float renderTickTime) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            makeColorShaders();

            PostChain activeShader = null;
            if (player.hasEffect(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(Constants.ACHROMATOMALY.get()))) {
                activeShader = achromatomalyShader;
            } else if (player.hasEffect(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(Constants.ACHROMATOPSIA.get()))) {
                activeShader = achromatopsiaShader;
            } else if (player.hasEffect(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(Constants.DEUTERANOMALY.get()))) {
                activeShader = deuteranomalyShader;
            } else if (player.hasEffect(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(Constants.DEUTERANOPIA.get()))) {
                activeShader = deuteranopiaShader;
            } else if (player.hasEffect(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(Constants.PROTANOMALY.get()))) {
                activeShader = protanomalyShader;
            } else if (player.hasEffect(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(Constants.PROTANOPIA.get()))) {
                activeShader = protanopiaShader;
            } else if (player.hasEffect(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(Constants.TRITANOMALY.get()))) {
                activeShader = tritanomalyShader;
            } else if (player.hasEffect(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(Constants.TRITANOPIA.get()))) {
                activeShader = tritanopiaShader;
            }

            if (activeShader != null) {
                activeShader.process(Minecraft.getInstance().getMainRenderTarget(), RESOURCE_POOL);
            }
        }
    }

    private static PostChain createShaderGroup(ResourceLocation location) {
        try {
            return Minecraft.getInstance().getShaderManager().getPostChain(location, LevelTargetBundle.MAIN_TARGETS);
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

}
