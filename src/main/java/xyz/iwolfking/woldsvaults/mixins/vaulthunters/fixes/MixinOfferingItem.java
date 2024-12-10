package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import iskallia.vault.item.OfferingItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.function.Consumer;

@Mixin(value = OfferingItem.class, remap = false)
public class MixinOfferingItem {
    /**
     * @author
     * @reason
     */
    @Overwrite
    public void loadModels(Consumer<ModelResourceLocation> consumer) {
        consumer.accept(new ModelResourceLocation("the_vault:offering/light_melee#inventory"));
        consumer.accept(new ModelResourceLocation("the_vault:offering/heavy_melee#inventory"));
        consumer.accept(new ModelResourceLocation("the_vault:offering/light_ranged#inventory"));
        consumer.accept(new ModelResourceLocation("the_vault:offering/heavy_ranged#inventory"));
        consumer.accept(new ModelResourceLocation("the_vault:offering/aoe_attack#inventory"));
        consumer.accept(new ModelResourceLocation("the_vault:offering/attribute_damage#inventory"));
        consumer.accept(new ModelResourceLocation("the_vault:offering/attribute_health#inventory"));
        consumer.accept(new ModelResourceLocation("the_vault:offering/on-hit_effect#inventory"));
        consumer.accept(new ModelResourceLocation("the_vault:offering/buff_effect#inventory"));
    }
}
