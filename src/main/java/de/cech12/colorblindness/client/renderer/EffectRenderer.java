package de.cech12.colorblindness.client.renderer;

import com.google.gson.JsonSyntaxException;
import de.cech12.colorblindness.ColorBlindness;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

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

    private static ShaderGroup achromatomalyShader;
    private static ShaderGroup achromatopsiaShader;
    private static ShaderGroup deuteranomalyShader;
    private static ShaderGroup deuteranopiaShader;
    private static ShaderGroup protanomalyShader;
    private static ShaderGroup protanopiaShader;
    private static ShaderGroup tritanomalyShader;
    private static ShaderGroup tritanopiaShader;

    private static int lastWidth = 0;
    private static int lastHeight = 0;
    private static ShaderGroup lastShader = null;


    private static ShaderGroup createShaderGroup(ResourceLocation location) {
        try {
            Minecraft mc = Minecraft.getInstance();
            return new ShaderGroup(mc.getTextureManager(), mc.getResourceManager(), mc.getMainRenderTarget(), location);
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

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onRender(TickEvent.RenderTickEvent event) {
        if (event == null || event.phase != TickEvent.Phase.END) {
            return;
        }

        ClientPlayerEntity player = Minecraft.getInstance().player;
        if (player != null) {
            makeColorShaders();

            ShaderGroup activeShader = null;
            if (player.hasEffect(ColorBlindness.ACHROMATOMALY.get())) {
                activeShader = achromatomalyShader;
            } else if (player.hasEffect(ColorBlindness.ACHROMATOPSIA.get())) {
                activeShader = achromatopsiaShader;
            } else if (player.hasEffect(ColorBlindness.DEUTERANOMALY.get())) {
                activeShader = deuteranomalyShader;
            } else if (player.hasEffect(ColorBlindness.DEUTERANOPIA.get())) {
                activeShader = deuteranopiaShader;
            } else if (player.hasEffect(ColorBlindness.PROTANOMALY.get())) {
                activeShader = protanomalyShader;
            } else if (player.hasEffect(ColorBlindness.PROTANOPIA.get())) {
                activeShader = protanopiaShader;
            } else if (player.hasEffect(ColorBlindness.TRITANOMALY.get())) {
                activeShader = tritanomalyShader;
            } else if (player.hasEffect(ColorBlindness.TRITANOPIA.get())) {
                activeShader = tritanopiaShader;
            }

            if (activeShader != null) {
                if (lastShader != activeShader) {
                    lastShader = activeShader;
                    lastWidth = 0;
                    lastHeight = 0;
                }
                updateShaderGroupSize(activeShader);
                activeShader.process(event.renderTickTime);
                Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
            }
        }
    }

    public static void updateShaderGroupSize(ShaderGroup shaderGroup) {
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
