package de.cech12.colorblindness.client;

import com.google.gson.JsonSyntaxException;
import com.mojang.blaze3d.resource.GraphicsResourceAllocator;
import de.cech12.colorblindness.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LevelTargetBundle;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EffectRendererHelper {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final Identifier ACHROMATOMALY = Constants.id("achromatomaly");
    private static final Identifier ACHROMATOPSIA = Constants.id("achromatopsia");
    private static final Identifier DEUTERANOMALY = Constants.id("deuteranomaly");
    private static final Identifier DEUTERANOPIA = Constants.id("deuteranopia");
    private static final Identifier PROTANOMALY = Constants.id("protanomaly");
    private static final Identifier PROTANOPIA = Constants.id("protanopia");
    private static final Identifier TRITANOMALY = Constants.id("tritanomaly");
    private static final Identifier TRITANOPIA = Constants.id("tritanopia");
    private static final GraphicsResourceAllocator ALLOCATOR = GraphicsResourceAllocator.UNPOOLED;

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
                activeShader.process(Minecraft.getInstance().getMainRenderTarget(), ALLOCATOR);
            }
        }
    }

    private static PostChain createShaderGroup(Identifier location) {
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
