package de.cech12.colorblindness.client;

import com.google.gson.JsonSyntaxException;
import com.mojang.blaze3d.resource.GraphicsResourceAllocator;
import de.cech12.colorblindness.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LevelTargetBundle;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

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
    private static final GraphicsResourceAllocator ALLOCATOR = GraphicsResourceAllocator.UNPOOLED;

    private static final List<PostChain> activeShaders = new ArrayList<>();

    private static PostChain achromatomalyShader;
    private static PostChain achromatopsiaShader;
    private static PostChain deuteranomalyShader;
    private static PostChain deuteranopiaShader;
    private static PostChain protanomalyShader;
    private static PostChain protanopiaShader;
    private static PostChain tritanomalyShader;
    private static PostChain tritanopiaShader;
    private static Holder<MobEffect> achromatomalyHolder;
    private static Holder<MobEffect> achromatopsiaHolder;
    private static Holder<MobEffect> deuteranomalyHolder;
    private static Holder<MobEffect> deuteranopiaHolder;
    private static Holder<MobEffect> protanomalyHolder;
    private static Holder<MobEffect> protanopiaHolder;
    private static Holder<MobEffect> tritanomalyHolder;
    private static Holder<MobEffect> tritanopiaHolder;

    /**
     * Should be called by a render event and renders the effect if it is active.
     * @param renderTickTime render tick time
     */
    public static void renderColorBlindnessEffect(float renderTickTime) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }

        makeColorShaders();
        fillActiveShaders(player);

        if (activeShaders.isEmpty()) {
            return;
        }

        for (PostChain shader : activeShaders) {
            if (shader != null) {
                shader.process(mc.getMainRenderTarget(), ALLOCATOR);
            }
        }
    }

    private static void fillActiveShaders(LocalPlayer player) {
        activeShaders.clear();
        if (hasEffect(player, achromatopsiaHolder)) {
            activeShaders.add(achromatopsiaShader);
            return;
        }

        if (hasEffect(player, achromatomalyHolder)) {
            activeShaders.add(achromatomalyShader);
        }
        if (hasEffect(player, tritanopiaHolder)) {
            activeShaders.add(tritanopiaShader);
        } else if (hasEffect(player, tritanomalyHolder)) {
            activeShaders.add(tritanomalyShader);
        }
        if (hasEffect(player, deuteranopiaHolder)) {
            activeShaders.add(deuteranopiaShader);
        } else if (hasEffect(player, deuteranomalyHolder)) {
            activeShaders.add(deuteranomalyShader);
        }
        if (hasEffect(player, protanopiaHolder)) {
            activeShaders.add(protanopiaShader);
        } else if (hasEffect(player, protanomalyHolder)) {
            activeShaders.add(protanomalyShader);
        }
    }

    private static boolean hasEffect(LocalPlayer player, Holder<MobEffect> effectHolder) {
        return effectHolder != null && player.hasEffect(effectHolder);
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
        if (achromatomalyHolder == null) {
            achromatomalyHolder = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(Constants.ACHROMATOMALY.get());
        }
        if (achromatopsiaHolder == null) {
            achromatopsiaHolder = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(Constants.ACHROMATOPSIA.get());
        }
        if (deuteranomalyHolder == null) {
            deuteranomalyHolder = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(Constants.DEUTERANOMALY.get());
        }
        if (deuteranopiaHolder == null) {
            deuteranopiaHolder = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(Constants.DEUTERANOPIA.get());
        }
        if (protanomalyHolder == null) {
            protanomalyHolder = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(Constants.PROTANOMALY.get());
        }
        if (protanopiaHolder == null) {
            protanopiaHolder = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(Constants.PROTANOPIA.get());
        }
        if (tritanomalyHolder == null) {
            tritanomalyHolder = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(Constants.TRITANOMALY.get());
        }
        if (tritanopiaHolder == null) {
            tritanopiaHolder = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(Constants.TRITANOPIA.get());
        }
    }

}
