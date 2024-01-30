package de.cech12.colorblindness.platform;

import de.cech12.colorblindness.NeoForgeColorBlindnessMod;
import de.cech12.colorblindness.platform.services.IRegistryHelper;
import net.minecraft.world.effect.MobEffect;

/**
 * The registry service implementation for NeoForge.
 */
public class NeoForgeRegistryHelper implements IRegistryHelper {

    @Override
    public MobEffect getAchromatomalyEffect() {
        return NeoForgeColorBlindnessMod.ACHROMATOMALY.get();
    }

    @Override
    public MobEffect getAchromatopsiaEffect() {
        return NeoForgeColorBlindnessMod.ACHROMATOPSIA.get();
    }

    @Override
    public MobEffect getDeuteranomalyEffect() {
        return NeoForgeColorBlindnessMod.DEUTERANOMALY.get();
    }

    @Override
    public MobEffect getDeuteranopiaEffect() {
        return NeoForgeColorBlindnessMod.DEUTERANOPIA.get();
    }

    @Override
    public MobEffect getProtanomalyEffect() {
        return NeoForgeColorBlindnessMod.PROTANOMALY.get();
    }

    @Override
    public MobEffect getProtanopiaEffect() {
        return NeoForgeColorBlindnessMod.PROTANOPIA.get();
    }

    @Override
    public MobEffect getTritanomalyEffect() {
        return NeoForgeColorBlindnessMod.TRITANOMALY.get();
    }

    @Override
    public MobEffect getTritanopiaEffect() {
        return NeoForgeColorBlindnessMod.TRITANOPIA.get();
    }

}
