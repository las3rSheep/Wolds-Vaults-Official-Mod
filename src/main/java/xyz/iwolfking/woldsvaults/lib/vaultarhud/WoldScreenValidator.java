package xyz.iwolfking.woldsvaults.lib.vaultarhud;

import appeng.client.gui.me.common.MEStorageScreen;
import appeng.client.gui.me.items.CraftingTermScreen;
import cofh.core.client.gui.ContainerScreenCoFH;
import com.refinedmods.refinedstorage.screen.grid.GridScreen;
import io.iridium.vaultarhud.util.Point;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.*;
import net.p3pp3rf1y.sophisticatedcore.client.gui.StorageScreenBase;
import org.cyclops.cyclopscore.client.gui.container.ContainerScreenExtended;

import java.util.Arrays;
import java.util.List;

public class WoldScreenValidator {
    public static final List<Class<? extends Screen>> VANILLA_SCREENS = Arrays.asList(InventoryScreen.class, ContainerScreen.class, CraftingScreen.class, FurnaceScreen.class);
    public static final List<Class<? extends Screen>> RS_SCREENS = Arrays.asList(GridScreen.class);
    public static final List<Class<? extends Screen>> SOPHISTICATED_BACKPACK_SCREENS = Arrays.asList(StorageScreenBase.class);
    public static final List<Class<? extends Screen>> AE2_SCREENS = Arrays.asList(MEStorageScreen.class, CraftingTermScreen.class);
    public static final List<Class<? extends Screen>> COLOSSALCHEST_SCREENS = Arrays.asList(ContainerScreenExtended.class);
    public static final List<Class<? extends Screen>> THERMAL_SCREENS = Arrays.asList(ContainerScreenCoFH.class);

    public WoldScreenValidator() {
    }

    public static boolean isValidScreen(Screen screen) {
        return isScreenInList(screen, VANILLA_SCREENS) || isScreenInList(screen, RS_SCREENS) || isScreenInList(screen, SOPHISTICATED_BACKPACK_SCREENS) || isScreenInList(screen, AE2_SCREENS) || isScreenInList(screen, COLOSSALCHEST_SCREENS) || isScreenInList(screen, THERMAL_SCREENS);
    }

    public static boolean isScreenInList(Screen screen, List<Class<? extends Screen>> screenList) {
        return screenList.stream().anyMatch((cls) -> {
            return cls.isInstance(screen);
        });
    }

    public static Point getScreenHUDCoordinates(Screen screen, Point offset) {
        int screenWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int screenHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        double x = 0.0;
        double y = 0.0;
        if (isScreenInList(screen, VANILLA_SCREENS)) {
            x = (double)((screenWidth - 176) / 2) - offset.getX();
            y = (double)((screenHeight + 166) / 2) - offset.getY();
        }

        AbstractContainerScreen gs;
        if (isScreenInList(screen, SOPHISTICATED_BACKPACK_SCREENS)) {
            gs = (AbstractContainerScreen)screen;
            x = (double)((screenWidth - 176) / 2) - offset.getX();
            y = (double)((screenHeight + gs.getYSize()) / 2) - offset.getY();
        }

        if (isScreenInList(screen, RS_SCREENS)) {
            gs = (AbstractContainerScreen)screen;
            x = (double)((screenWidth - gs.getXSize()) / 2) - offset.getX();
            y = (double)((screenHeight + gs.getYSize()) / 2) - offset.getY() - 1.0;
        }

        if (isScreenInList(screen, AE2_SCREENS) || isScreenInList(screen, COLOSSALCHEST_SCREENS)  || isScreenInList(screen, THERMAL_SCREENS)) {
            gs = (AbstractContainerScreen)screen;
            x = (double)((screenWidth - gs.getXSize()) / 2) - offset.getX();
            y = (double)((screenHeight + gs.getYSize()) / 2) - offset.getY();
        }

        return new Point(x, y);
    }
}
