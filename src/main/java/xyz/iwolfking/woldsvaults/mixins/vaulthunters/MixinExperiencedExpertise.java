package xyz.iwolfking.woldsvaults.mixins.vaulthunters;

import iskallia.vault.skill.expertise.type.ExperiencedExpertise;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = ExperiencedExpertise.class, remap = false)
public abstract class MixinExperiencedExpertise {
    @Shadow public abstract float getIncreasedExpPercentage();

    /**
     * @author iwolfking
     * @reason Disable Experienced Expertise increasing normal XP
     */
    @Overwrite
    @SubscribeEvent
    public static void onOrbPickup(PlayerXpEvent.PickupXp event) {
        return;
    }
}
