package xyz.iwolfking.woldsvaults.data.vhapi.lib;

import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.data.vhapi.VHAPIModules;

public interface IVHAPIModule {
    void init();

    static void registerVHAPIFile(String path, String directory) {
        VHAPIModules.registerManualConfigFile("/vhapi_configs/" + path, WoldsVaults.id(directory));
    }
}
