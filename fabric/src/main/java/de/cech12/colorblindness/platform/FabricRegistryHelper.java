package de.cech12.colorblindness.platform;

import de.cech12.colorblindness.FabricColorBlindnessMod;
import de.cech12.colorblindness.platform.services.IRegistryHelper;
import net.minecraft.world.effect.MobEffect;

/**
 * The registry service implementation for Fabric.
 */
public class FabricRegistryHelper implements IRegistryHelper {

    @Override
    public MobEffect getAchromatomalyEffect() {
        return FabricColorBlindnessMod.ACHROMATOMALY;
    }

    @Override
    public MobEffect getAchromatopsiaEffect() {
        return FabricColorBlindnessMod.ACHROMATOPSIA;
    }

    @Override
    public MobEffect getDeuteranomalyEffect() {
        return FabricColorBlindnessMod.DEUTERANOMALY;
    }

    @Override
    public MobEffect getDeuteranopiaEffect() {
        return FabricColorBlindnessMod.DEUTERANOPIA;
    }

    @Override
    public MobEffect getProtanomalyEffect() {
        return FabricColorBlindnessMod.PROTANOMALY;
    }

    @Override
    public MobEffect getProtanopiaEffect() {
        return FabricColorBlindnessMod.PROTANOPIA;
    }

    @Override
    public MobEffect getTritanomalyEffect() {
        return FabricColorBlindnessMod.TRITANOMALY;
    }

    @Override
    public MobEffect getTritanopiaEffect() {
        return FabricColorBlindnessMod.TRITANOPIA;
    }

}
