package xyz.iwolfking.woldsvaults.mixins.vaulthunters.objectives;


import iskallia.vault.client.gui.framework.render.spi.IElementRenderer;
import iskallia.vault.client.gui.framework.render.spi.ITooltipRendererFactory;
import iskallia.vault.client.gui.framework.screen.AbstractElementContainerScreen;
import iskallia.vault.client.gui.screen.bounty.BountyScreen;
import iskallia.vault.container.BountyContainer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Inventory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(value = BountyScreen.class, remap = false)
public abstract class MixinBountyScreen extends AbstractElementContainerScreen<BountyContainer> {



    @Final
    @Shadow @Mutable
    public static Map<String, TextComponent> OBJECTIVE_NAME = Map.ofEntries(
            Map.entry("unhinged_scavenger", new TextComponent("Unhinged Scavenger Hunt")),
            Map.entry("enchanted_elixir", new TextComponent("Enchanted Elixir")),
            Map.entry("brutal_bosses", new TextComponent("Brutal Bosses")),
            Map.entry("obelisk", new TextComponent("Obelisks")),
            Map.entry("boss", new TextComponent("Hunt The Guardians")),
            Map.entry("cake", new TextComponent("Find The Cakes")),
            Map.entry("scavenger", new TextComponent("Scavenger Hunt")),
            Map.entry("vault", new TextComponent("Any Vault")),
            Map.entry("monolith", new TextComponent("Light the Braziers")),
            Map.entry("elixir", new TextComponent("Gather Elixir")),
            Map.entry("haunted_braziers", new TextComponent("Haunted Braziers")),
            Map.entry("bingo", new TextComponent("Bingo")),
            Map.entry("ballistic_bingo", new TextComponent("Ballistic Bingo"))
    );

//    static {
//        for(CustomVaultObjectiveEntry entry : CustomVaultObjectiveRegistry.getCustomVaultObjectiveEntries()) {
//            OBJECTIVE_NAME.put(entry.id(), new TextComponent(entry.name()));
//        }
//    }

    public MixinBountyScreen(BountyContainer container, Inventory inventory, Component title, IElementRenderer elementRenderer, ITooltipRendererFactory<AbstractElementContainerScreen<BountyContainer>> tooltipRendererFactory) {
        super(container, inventory, title, elementRenderer, tooltipRendererFactory);
    }
}
