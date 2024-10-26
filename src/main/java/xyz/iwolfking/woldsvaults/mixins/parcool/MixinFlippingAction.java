package xyz.iwolfking.woldsvaults.mixins.parcool;


import com.alrex.parcool.client.input.KeyBindings;
import com.alrex.parcool.client.input.KeyRecorder;
import com.alrex.parcool.common.action.impl.Flipping;
import com.alrex.parcool.common.capability.IStamina;
import com.alrex.parcool.common.capability.Parkourability;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.nio.ByteBuffer;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "parcool")
        }
)
@Mixin(value = Flipping.class, remap = false)
public class MixinFlippingAction {
    /**
     * @author iwolfking
     * @reason Disable Parcool flipping when pressing left and right movement keys
     */
    @Overwrite
    public boolean canStart(Player player, Parkourability parkourability, IStamina stamina, ByteBuffer startInfo) {
        Flipping.FlippingDirection fDirection;
        if (KeyBindings.getKeyBack().isDown()) {
            fDirection = Flipping.FlippingDirection.Back;
        } else {
            fDirection = Flipping.FlippingDirection.Front;
        }

        startInfo.putInt(fDirection.getCode());
        return parkourability.getActionInfo().can(Flipping.class) && !stamina.isExhausted() && parkourability.getAdditionalProperties().getNotLandingTick() <= 1 && (KeyRecorder.keyFlipping.isPressed());
    }
}
