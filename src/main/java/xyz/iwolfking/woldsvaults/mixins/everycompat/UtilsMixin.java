package xyz.iwolfking.woldsvaults.mixins.everycompat;

import net.mehvahdjukaar.every_compat.misc.Utils;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = Utils.class, remap = false)
public class UtilsMixin {
    @Redirect(method = {"lambda$addStandardResources$1", "lambda$addStandardResources$3"}, at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V"))
    private static void dontLogBuildscapeModels(Logger logger, String message, Object b, Object exception){
        if (b.toString().contains("buildscape")) {
            return;
        }
        logger.error(message, b, exception);
    }
}