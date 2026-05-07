package xyz.iwolfking.woldsvaults.mixins.openpac;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import iskallia.vault.block.entity.SchematicTerminalBlockEntity;
import iskallia.vault.network.message.SchematicTerminalPlaceMessage;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import xaero.pac.common.claims.player.IPlayerChunkClaim;
import xaero.pac.common.claims.player.IPlayerClaimPosList;
import xaero.pac.common.claims.player.IPlayerDimensionClaims;
import xaero.pac.common.parties.party.IPartyPlayerInfo;
import xaero.pac.common.parties.party.ally.IPartyAlly;
import xaero.pac.common.parties.party.member.IPartyMember;
import xaero.pac.common.server.IServerData;
import xaero.pac.common.server.ServerData;
import xaero.pac.common.server.claims.IServerClaimsManager;
import xaero.pac.common.server.claims.IServerDimensionClaimsManager;
import xaero.pac.common.server.claims.IServerRegionClaims;
import xaero.pac.common.server.claims.player.IServerPlayerClaimInfo;
import xaero.pac.common.server.parties.party.IServerParty;
import xyz.iwolfking.vhapi.api.util.MessageUtils;

import java.util.List;
import java.util.Set;


@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "openpartiesandclaims")
        }
)
@Mixin(value = SchematicTerminalPlaceMessage.class, remap = false)
public class MixinSchematicTerminalPlaceMessage {
    @WrapOperation(method = "lambda$handle$2", at = @At(value = "INVOKE", target = "Liskallia/vault/core/world/storage/IZonedWorld;runWithBypass(Ljava/lang/Object;ZLjava/lang/Runnable;)V"))
    private static void preventPlacingIfNoPermission(Object object, boolean bypassed, Runnable runnable, Operation<Void> original, @Local(name = "requester") ServerPlayer requester, @Local(name = "nonAirPositions") Set<BlockPos> nonAirPositions, @Local(name = "inscription") ItemStack inscription, @Local(name = "terminal") SchematicTerminalBlockEntity terminal) {
        for (BlockPos nonAirPosition : nonAirPositions) {
            IServerData<IServerClaimsManager<IPlayerChunkClaim, IServerPlayerClaimInfo<IPlayerDimensionClaims<IPlayerClaimPosList>>, IServerDimensionClaimsManager<IServerRegionClaims>>, IServerParty<IPartyMember, IPartyPlayerInfo, IPartyAlly>>
                    serverData = ServerData.from(requester.getServer());
            if(serverData.getChunkProtection().onEntityPlaceBlock(requester, requester.getLevel(), nonAirPosition)) {
                MessageUtils.sendMessage(requester, new TranslatableComponent("fix.woldsvaults.schematic_terminal_no_permission").withStyle(ChatFormatting.RED));
                terminal.getInventory().setStackInSlot(0, inscription);
                return;
            }
        }

        original.call(object, bypassed, runnable);
    }
}
