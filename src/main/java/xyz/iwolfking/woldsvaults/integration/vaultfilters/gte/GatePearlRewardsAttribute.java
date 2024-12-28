package xyz.iwolfking.woldsvaults.integration.vaultfilters.gte;

import iskallia.vault.item.OfferingItem;
import net.joseph.vaultfilters.attributes.abstracts.StringListAttribute;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import shadows.gateways.gate.Gateway;
import shadows.gateways.gate.Reward;
import shadows.gateways.item.GatePearlItem;

import java.util.ArrayList;
import java.util.List;

public class GatePearlRewardsAttribute extends StringListAttribute {
    public GatePearlRewardsAttribute(String value) {
        super(value);
    }

    public String getTranslationKey() {
        return "gte_gate_pearl_rewards";
    }

    public String getValue(ItemStack itemStack) {
        return "";
    }

    public static List<String> getRewardItems(ItemStack stack) {
        List<String> itemNamesList = new ArrayList<>();
        if (stack.getItem() instanceof GatePearlItem) {
            Gateway gate = GatePearlItem.getGate(stack);

            if (gate == null) {
                return List.of();
            }

            List<Reward> gateRewards = gate.getRewards();


            for(Reward reward : gateRewards) {
                itemNamesList.add(reward.getName());
            }

        }

        return itemNamesList;
    }

    public List<String> getValues(ItemStack itemStack) {
        return getRewardItems(itemStack);
    }
}
