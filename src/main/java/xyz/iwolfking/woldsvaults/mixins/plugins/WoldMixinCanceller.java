package xyz.iwolfking.woldsvaults.mixins.plugins;

import com.bawnorton.mixinsquared.api.MixinCanceller;
import net.minecraftforge.fml.loading.LoadingModList;

import java.util.List;

public class WoldMixinCanceller implements MixinCanceller {
    @Override
    public boolean shouldCancel(List<String> list, String s) {
        if(s.equals("net.joseph.vaultfilters.mixin.compat.MixinCreateFilteringBehaviour")) {
            return LoadingModList.get().getModFileById("vaultfilters") != null;
        }
        return false;
    }
}
