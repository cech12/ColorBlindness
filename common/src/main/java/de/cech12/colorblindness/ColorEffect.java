package de.cech12.colorblindness;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

import javax.annotation.Nonnull;

public class ColorEffect extends MobEffect {

    public static final ColorEffect ACHROMATOMALY = new ColorEffect(100, 100, 100);
    public static final ColorEffect ACHROMATOPSIA = new ColorEffect(56, 56, 56);
    public static final ColorEffect DEUTERANOMALY = new ColorEffect(0, 159, 0);
    public static final ColorEffect DEUTERANOPIA = new ColorEffect(0, 216, 0);
    public static final ColorEffect PROTANOMALY = new ColorEffect(159, 0, 0);
    public static final ColorEffect PROTANOPIA = new ColorEffect(216, 0, 0);
    public static final ColorEffect TRITANOMALY = new ColorEffect(0, 0, 159);
    public static final ColorEffect TRITANOPIA = new ColorEffect(0, 0, 216);
    
    protected ColorEffect(int red, int green, int blue) {
        super(MobEffectCategory.NEUTRAL, getColorFromRGB(red, green, blue));
    }

    static private int getColorFromRGB(int red, int green, int blue) {
        int rgb = Math.max(Math.min(0xFF, red), 0);
        rgb = (rgb << 8) + Math.max(Math.min(0xFF, green), 0);
        rgb = (rgb << 8) + Math.max(Math.min(0xFF, blue), 0);
        return rgb;
    }

    @Override
    public void applyEffectTick(@Nonnull LivingEntity entityLivingBaseIn, int amplifier) {
        //do nothing than rendering
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return false;
    }

}
