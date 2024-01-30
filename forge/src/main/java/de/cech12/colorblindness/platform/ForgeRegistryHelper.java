package de.cech12.colorblindness.platform;

import de.cech12.colorblindness.ForgeColorBlindnessMod;
import de.cech12.colorblindness.platform.services.IRegistryHelper;
import net.minecraft.world.effect.MobEffect;

/**
 * The registry service implementation for Forge.
 */
public class ForgeRegistryHelper implements IRegistryHelper {

    @Override
    public MobEffect getAchromatomalyEffect() {
        return ForgeColorBlindnessMod.ACHROMATOMALY.get();
    }

    @Override
    public MobEffect getAchromatopsiaEffect() {
        return ForgeColorBlindnessMod.ACHROMATOPSIA.get();
    }

    @Override
    public MobEffect getDeuteranomalyEffect() {
        return ForgeColorBlindnessMod.DEUTERANOMALY.get();
    }

    @Override
    public MobEffect getDeuteranopiaEffect() {
        return ForgeColorBlindnessMod.DEUTERANOPIA.get();
    }

    @Override
    public MobEffect getProtanomalyEffect() {
        return ForgeColorBlindnessMod.PROTANOMALY.get();
    }

    @Override
    public MobEffect getProtanopiaEffect() {
        return ForgeColorBlindnessMod.PROTANOPIA.get();
    }

    @Override
    public MobEffect getTritanomalyEffect() {
        return ForgeColorBlindnessMod.TRITANOMALY.get();
    }

    @Override
    public MobEffect getTritanopiaEffect() {
        return ForgeColorBlindnessMod.TRITANOPIA.get();
    }

}
