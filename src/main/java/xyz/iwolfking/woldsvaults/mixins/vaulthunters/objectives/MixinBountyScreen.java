package xyz.iwolfking.woldsvaults.mixins.vaulthunters.objectives;


import iskallia.vault.client.gui.screen.bounty.BountyScreen;
import net.minecraft.network.chat.TextComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import xyz.iwolfking.woldsvaults.api.registry.CustomVaultObjectiveRegistry;
import xyz.iwolfking.woldsvaults.api.registry.record.CustomVaultObjectiveEntry;

import java.util.Map;

@Mixin(value = BountyScreen.class, remap = false)
public class MixinBountyScreen {

    @Shadow @Final @Mutable
    public static Map<String, TextComponent> OBJECTIVE_NAME;

    static {
        for(CustomVaultObjectiveEntry entry : CustomVaultObjectiveRegistry.getCustomVaultObjectiveEntries()) {
            OBJECTIVE_NAME.put(entry.id(), new TextComponent(entry.name()));
        }
    }

}
