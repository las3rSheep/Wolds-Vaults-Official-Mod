package xyz.iwolfking.woldsvaults.data;

import net.minecraft.world.item.Item;

import javax.annotation.Nullable;

public class CoinDefinitions {
    public static record CoinDefinition(Item coinItem, @Nullable Item previousHigherDenomination, @Nullable Item nextLowerDenomination, int coinValue) {
        public CoinDefinition(Item coinItem, @Nullable Item previousHigherDenomination, @Nullable Item nextLowerDenomination, int coinValue) {
            this.coinItem = coinItem;
            this.previousHigherDenomination = previousHigherDenomination;
            this.nextLowerDenomination = nextLowerDenomination;
            this.coinValue = coinValue;
        }

        public Item coinItem() {
            return this.coinItem;
        }

        @Nullable
        public Item previousHigherDenomination() {
            return this.previousHigherDenomination;
        }

        @Nullable
        public Item nextLowerDenomination() {
            return this.nextLowerDenomination;
        }

        public int coinValue() {
            return this.coinValue;
        }
    }
}
