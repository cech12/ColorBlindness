package de.cech12.colorblindness;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import javax.annotation.Nonnull;

public class ColorEffect extends Effect {

    protected ColorEffect(int red, int green, int blue) {
        super(EffectType.NEUTRAL, getColorFromRGB(red, green, blue));
    }

    static private int getColorFromRGB(int red, int green, int blue) {
        int rgb = Math.max(Math.min(0xFF, red), 0);
        rgb = (rgb << 8) + Math.max(Math.min(0xFF, green), 0);
        rgb = (rgb << 8) + Math.max(Math.min(0xFF, blue), 0);
        return rgb;
    }

    @Override
    public void performEffect(@Nonnull LivingEntity entityLivingBaseIn, int amplifier) {
        //do nothing than rendering
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return false;
    }


}
