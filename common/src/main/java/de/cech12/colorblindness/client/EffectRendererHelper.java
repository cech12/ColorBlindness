package de.cech12.colorblindness.client;

import com.google.gson.JsonSyntaxException;
import de.cech12.colorblindness.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private static Holder<MobEffect> achromatomalyHolder;
    private static Holder<MobEffect> achromatopsiaHolder;
    private static Holder<MobEffect> deuteranomalyHolder;
    private static Holder<MobEffect> deuteranopiaHolder;
    private static Holder<MobEffect> protanomalyHolder;
    private static Holder<MobEffect> protanopiaHolder;
    private static Holder<MobEffect> tritanomalyHolder;
    private static Holder<MobEffect> tritanopiaHolder;

    private static int lastWidth = 0;
    private static int lastHeight = 0;

    private static final List<PostChain> activeShaders = new ArrayList<>();

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

        makeColorShaders(mc);
        makeHolders();

        fillActiveShaders(player);

        if (activeShaders.isEmpty()) {
            return;
        }

        for (PostChain shader : activeShaders) {
            if (shader != null) {
                shader.process(renderTickTime);
            }
        }
        mc.getMainRenderTarget().bindWrite(false);
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

    private static void makeHolders() {
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
