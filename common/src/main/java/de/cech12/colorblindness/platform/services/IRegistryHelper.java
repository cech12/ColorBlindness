package de.cech12.colorblindness.platform.services;

import net.minecraft.world.effect.MobEffect;

/**
 * Common registry helper service interface.
 */
public interface IRegistryHelper {

    /**
     * @return Achromatomaly effect instance
     */
    MobEffect getAchromatomalyEffect();

    /**
     * @return Achromatopsia effect instance
     */
    MobEffect getAchromatopsiaEffect();

    /**
     * @return Deuteranomaly effect instance
     */
    MobEffect getDeuteranomalyEffect();

    /**
     * @return Deuteranopia effect instance
     */
    MobEffect getDeuteranopiaEffect();

    /**
     * @return Protanomaly effect instance
     */
    MobEffect getProtanomalyEffect();

    /**
     * @return Protanopia effect instance
     */
    MobEffect getProtanopiaEffect();

    /**
     * @return Tritanomaly effect instance
     */
    MobEffect getTritanomalyEffect();

    /**
     * @return Tritanopia effect instance
     */
    MobEffect getTritanopiaEffect();

}
