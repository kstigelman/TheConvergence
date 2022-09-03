package com.stiggles.smp5;

import org.bukkit.entity.Player;

public class CoinBank {
    private int balance;
    private Player owner;

    /**
     * Default constructor for CoinBank. Should only be used for testing, or perhaps a "World Bank"
     */
    public CoinBank () {
        balance = 0;
        owner = null;
    }

    /**
     * Standard constructor for CoinBank.
     *
     * @param owner The player that the account should be linked to.
     */
    public CoinBank (Player owner) {
        balance = 0;
        this.owner = owner;
    }

    /**
     * @return The player that is linked to the account
     */
    public Player getOwnerName () {
        return owner;
    }

    /**
     * @return The total coins the player has
     */
    public int getTotalCoins() {
        return balance;
    }

    /**
     * Withdraw a certain amount of coins from an account
     *
     * @param amount The amount of coins to remove
     * @return Whether or not the transaction was successful
     */
    public boolean withdraw (int amount) {
        if (!hasSufficientFunds(amount))
            return false;
        balance -= amount;
        return true;
    }

    /**
     * Deposit a certain amount of coins from an account
     *
     * @param amount The amount of coins to add
     */
    public void deposit (int amount) {
        balance += amount;
    }

    /**
     * Checks if an account has enough money to complete a transaction
     *
     * @param amount The amount trying to be removed
     * @return Whether the new balance would be above zero
     */
    private boolean hasSufficientFunds(int amount) {
        return balance - amount > 0;
    }

    @Override
    public String toString() {
        return owner.getName() + ": " + balance;
    }

}
