package de.cech12.colorblindness;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

/**
 * Class that contains all common constants.
 */
public class Constants {

    /** mod id */
    public static final String MOD_ID = "colorblindness";
    /** mod name*/
    public static final String MOD_NAME = "Color Blindness";
    /** Logger instance */
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    /** Supplier for registered ACHROMATOMALY MobEffect */
    public static Supplier<MobEffect> ACHROMATOMALY;
    /** Supplier for registered ACHROMATOPSIA MobEffect */
    public static Supplier<MobEffect> ACHROMATOPSIA;
    /** Supplier for registered DEUTERANOMALY MobEffect */
    public static Supplier<MobEffect> DEUTERANOMALY;
    /** Supplier for registered DEUTERANOPIA MobEffect */
    public static Supplier<MobEffect> DEUTERANOPIA;
    /** Supplier for registered PROTANOMALY MobEffect */
    public static Supplier<MobEffect> PROTANOMALY;
    /** Supplier for registered PROTANOPIA MobEffect */
    public static Supplier<MobEffect> PROTANOPIA;
    /** Supplier for registered TRITANOMALY MobEffect */
    public static Supplier<MobEffect> TRITANOMALY;
    /** Supplier for registered TRITANOPIA MobEffect */
    public static Supplier<MobEffect> TRITANOPIA;

    private Constants() {}

    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

}