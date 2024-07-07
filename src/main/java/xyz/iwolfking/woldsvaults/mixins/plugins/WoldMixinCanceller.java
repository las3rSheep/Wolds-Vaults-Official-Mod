package xyz.iwolfking.woldsvaults.mixins.plugins;

import com.bawnorton.mixinsquared.api.MixinCanceller;

import java.util.List;

public class WoldMixinCanceller implements MixinCanceller {
    @Override
    public boolean shouldCancel(List<String> list, String s) {
//        if(s.equals("net.joseph.vaultfilters.mixin.compat.MixinCreateFilteringBehaviour")) {
//            return LoadingModList.get().getModFileById("vaultfilters") != null;
//        }
//        if(s.equals("dev.attackeight.the_vault_jei.mixin.IntegrationJEIMixin")) {
//            return LoadingModList.get().getModFileById("the_vault_jei") != null;
//        }
        return false;
    }
}
