package xyz.iwolfking.woldsvaults.mixins;


import iskallia.vault.client.gui.screen.bounty.BountyScreen;
import net.minecraft.network.chat.TextComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(BountyScreen.class)
public class MixinBountyScreen {

    @Shadow @Final public static Map<String, TextComponent> OBJECTIVE_NAME = Map.of("obelisk", new TextComponent("Obelisks"), "boss", new TextComponent("Hunt The Guardians"), "cake", new TextComponent("Find The Cakes"), "scavenger", new TextComponent("Scavenger Hunt"), "vault", new TextComponent("Any Vault"), "monolith", new TextComponent("Light the Braziers"), "elixir", new TextComponent("Gather Elixir"), "unhinged_scavenger", new TextComponent("Unhinged Scavenger Hunt"), "haunted_braziers", new TextComponent("Haunted Braziers"), "enchanted_elixir", new TextComponent("Enchanted Elixir"));


}
