package xyz.iwolfking.woldsvaults.mixins.ae2.terminal;

import appeng.api.config.IncludeExclude;
import appeng.api.config.TerminalStyle;
import appeng.api.stacks.AEItemKey;
import appeng.api.storage.AEKeyFilter;
import appeng.client.gui.AEBaseScreen;
import appeng.client.gui.WidgetContainer;
import appeng.client.gui.me.common.MEStorageScreen;
import appeng.client.gui.me.common.Repo;
import appeng.client.gui.style.ScreenStyle;
import appeng.client.gui.widgets.ISortSource;
import appeng.client.gui.widgets.TabButton;
import appeng.core.definitions.AEBlockEntities;
import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import appeng.items.storage.ViewCellItem;
import appeng.menu.SlotSemantics;
import appeng.menu.me.common.GridInventoryEntry;
import appeng.menu.me.common.MEStorageMenu;
import appeng.util.IConfigManagerListener;
import appeng.util.prioritylist.IPartitionList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = MEStorageScreen.class, remap = false)
public abstract class MixinMEStorageScreen<C extends MEStorageMenu> extends AEBaseScreen<C> implements ISortSource, IConfigManagerListener {

    @Unique
    private int ae2tabs$selectedViewCell = -1;

    @Unique
    private final List<TabButton> ae2tabs$tabs = new ArrayList<>();

    @Shadow
    @Final
    private List<ItemStack> currentViewCells;

    @Shadow
    @Final
    protected Repo repo;


    public MixinMEStorageScreen(C menu, Inventory playerInventory, Component title, ScreenStyle style) {
        super(menu, playerInventory, title, style);
    }

    @Inject(method = "createPartitionList", at = @At("HEAD"), cancellable = true)
    private void ae2tabs$overridePartition(List<ItemStack> viewCells,
                                           CallbackInfoReturnable<IPartitionList> cir) {

        if (ae2tabs$selectedViewCell >= 0 && ae2tabs$selectedViewCell < viewCells.size()) {

            ItemStack stack = viewCells.get(ae2tabs$selectedViewCell);

            cir.setReturnValue(
                    ViewCellItem.createFilter(AEKeyFilter.none(), List.of(stack))
            );
        }
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void ae2tabs$init(CallbackInfo ci) {
        ae2tabs$tabs.forEach(this::removeWidget);
        ae2tabs$tabs.clear();

        ItemRenderer ir = Minecraft.getInstance().getItemRenderer();

        // All Items button
        TabButton allItemsBtn = new TabButton(
                new ItemStack(AEBlocks.CONTROLLER),
                new TextComponent("All Items"),
                ir,
                btn -> ae2tabs$selectTab(-1)
        );
        ae2tabs$addTab(allItemsBtn, 0);

        // View cell tabs
        for (int i = 0; i < currentViewCells.size(); i++) {
            ItemStack stack = currentViewCells.get(i);
            if(stack.isEmpty()) continue;

            GridInventoryEntry entry = repo.getAllEntries().stream()
                    .filter(e -> e.getWhat() instanceof AEItemKey &&
                            ViewCellItem.createFilter(AEKeyFilter.none(), List.of(stack))
                                    .matchesFilter(e.getWhat(), IncludeExclude.WHITELIST))
                    .findFirst().orElse(null);

            ItemStack icon = entry != null && entry.getWhat() instanceof AEItemKey itemKey
                    ? itemKey.toStack() : stack;

            int index = i;
            TabButton tab = new TabButton(icon, stack.getHoverName(), ir, btn -> ae2tabs$selectTab(index));
            ae2tabs$addTab(tab, i + 1);
        }
    }

    @Redirect(
            method = "containerTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lappeng/client/gui/me/common/Repo;setPartitionList(Lappeng/util/prioritylist/IPartitionList;)V"
            )
    )
    private void ae2tabs$redirectPartition(Repo repo, IPartitionList original) {

        if (ae2tabs$selectedViewCell >= 0 && ae2tabs$selectedViewCell < currentViewCells.size()) {

            ItemStack stack = currentViewCells.get(ae2tabs$selectedViewCell);

            IPartitionList filter =
                    ViewCellItem.createFilter(AEKeyFilter.none(), List.of(stack));

            repo.setPartitionList(filter);

        } else {

            repo.setPartitionList(original);
        }
    }

    @Inject(method = "containerTick", at = @At("TAIL"))
    private void ae2tabs$updateTabs(CallbackInfo ci) {

        if (ae2tabs$tabs.size() != currentViewCells.size()) {

            MEStorageScreen<?> screen = (MEStorageScreen<?>)(Object)this;

            ae2tabs$tabs.forEach(this::removeWidget);
            ae2tabs$tabs.clear();

            ae2tabs$init(ci);
        }
    }

    @Unique
    private void ae2tabs$addTab(TabButton tab, int index) {
        int x, y;

        if(config.getTerminalStyle().equals(TerminalStyle.TALL) || config.getTerminalStyle().equals(TerminalStyle.FULL)) {
            List<Slot> viewCellSlots = menu.getSlots(SlotSemantics.VIEW_CELL);
            Slot last = viewCellSlots.get(viewCellSlots.size() - 1);
            int bottomY = last.y + 32;
            x = getGuiLeft() + this.imageWidth + 8;
            y = bottomY + index * 22;
        } else {
            x = getGuiLeft() + 8 + index * 22;
            y = getGuiTop() - 22;
        }

        tab.x = x;
        tab.y = y;
        tab.setStyle(TabButton.Style.HORIZONTAL);
        tab.setSelected(index - 1 == ae2tabs$selectedViewCell);
        ae2tabs$tabs.add(tab);
        addRenderableWidget(tab);
    }

    @Unique
    private void ae2tabs$selectTab(int index) {
        ae2tabs$selectedViewCell = index;

        IPartitionList filter = null;
        if(index >= 0 && index < currentViewCells.size()) {
            filter = ViewCellItem.createFilter(AEKeyFilter.none(), List.of(currentViewCells.get(index)));
        }
        repo.setPartitionList(filter);

        for(int i = 0; i < ae2tabs$tabs.size(); i++) {
            ae2tabs$tabs.get(i).setSelected(i - 1 == index);
        }

        repo.updateView();
    }
}
