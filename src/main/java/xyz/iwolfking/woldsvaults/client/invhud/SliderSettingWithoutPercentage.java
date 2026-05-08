package xyz.iwolfking.woldsvaults.client.invhud;

import iskallia.vault.client.gui.framework.element.SliderElement;
import iskallia.vault.client.gui.framework.element.spi.AbstractSpatialElement;
import iskallia.vault.client.render.hud.module.settings.SliderSetting;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class SliderSettingWithoutPercentage extends SliderSetting {
    public SliderSettingWithoutPercentage(String key, Component displayName, Component tooltip,
                                          Supplier<Component> labelSupplier,
                                          Supplier<Float> valueGetter, Consumer<Float> valueSetter) {
        super(key, displayName, tooltip, labelSupplier, valueGetter, valueSetter);
    }


    @Override
    public AbstractSpatialElement<?> createElement(int x, int y, int width, final Runnable onChange) {
        AbstractSpatialElement<?> el = super.createElement(x,y,width,onChange);
        if (el instanceof SliderElement slider) slider.hidePercentage(true);
        return el;
    }
}
