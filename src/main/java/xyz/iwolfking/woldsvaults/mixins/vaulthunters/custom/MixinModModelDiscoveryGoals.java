package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import iskallia.vault.VaultMod;
import iskallia.vault.core.vault.VaultRegistry;
import iskallia.vault.core.vault.influence.VaultGod;
import iskallia.vault.discoverylogic.goal.base.DiscoveryGoal;
import iskallia.vault.init.ModModelDiscoveryGoals;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.api.data.discovery.DiscoveredRecipesData;
import xyz.iwolfking.woldsvaults.api.data.discovery.DiscoveredThemesData;
import xyz.iwolfking.woldsvaults.discovery.recipes.LootersPouchDiscoveryGoal;
import xyz.iwolfking.woldsvaults.discovery.recipes.TreasuredJewelDiscoveryGoal;
import xyz.iwolfking.woldsvaults.discovery.recipes.WizardPouchDiscoveryGoal;
import xyz.iwolfking.woldsvaults.discovery.themes.GodThemesDiscoveryGoal;

@Mixin(value = ModModelDiscoveryGoals.class, remap = false)
public abstract class MixinModModelDiscoveryGoals {

    @Unique
    private static WizardPouchDiscoveryGoal WIZARD_POUCH_UNLOCK;
    private static LootersPouchDiscoveryGoal LOOTERS_POUCH_UNLOCK;
    private static TreasuredJewelDiscoveryGoal TREASURED_JEWEL;

    static {
        WIZARD_POUCH_UNLOCK = registerGoal(WoldsVaults.id("cast_250_abilities"), (new WizardPouchDiscoveryGoal(250.0F)).setReward((player, goal) -> {
            DiscoveredRecipesData data = DiscoveredRecipesData.get(player.getLevel());
            ResourceLocation unlock = WoldsVaults.id("wizard_trinket_pouch");
            if(!data.hasDiscovered(player, unlock)) {
                MutableComponent info = (new TranslatableComponent("unlock_goal.woldsvaults.wizard_trinket_pouch")).withStyle(ChatFormatting.BLUE);
                player.displayClientMessage(info, false);
                data.discoverRecipeAndBroadcast(unlock, player);
            }
        }));

        LOOTERS_POUCH_UNLOCK = registerGoal(WoldsVaults.id("open_15_treasure_rooms"), (new LootersPouchDiscoveryGoal(15.0F)).setReward((player, goal) -> {
            DiscoveredRecipesData data = DiscoveredRecipesData.get(player.getLevel());
            ResourceLocation unlock = WoldsVaults.id("looters_trinket_pouch");
            if(!data.hasDiscovered(player, unlock)) {
                MutableComponent info = (new TranslatableComponent("unlock_goal.woldsvaults.looters_trinket_pouch")).withStyle(ChatFormatting.GOLD);
                player.displayClientMessage(info, false);
                data.discoverRecipeAndBroadcast(unlock, player);
            }
        }));

        TREASURED_JEWEL = registerGoal(WoldsVaults.id("open_10_treasure_rooms"), (new TreasuredJewelDiscoveryGoal(10.0F)).setReward((player, goal) -> {
            DiscoveredRecipesData data = DiscoveredRecipesData.get(player.getLevel());
            ResourceLocation unlock = WoldsVaults.id("treasure_jewel");
            if(!data.hasDiscovered(player, unlock)) {
                MutableComponent info = (new TranslatableComponent("unlock_goal.woldsvaults.treasure_jewel")).withStyle(ChatFormatting.GOLD);
                player.displayClientMessage(info, false);
                data.discoverRecipeAndBroadcast(unlock, player);
            }
        }));



    }

    @Shadow
    private static <G extends DiscoveryGoal<G>> G registerGoal(ResourceLocation id, G goal) {
        return null;
    }
}
