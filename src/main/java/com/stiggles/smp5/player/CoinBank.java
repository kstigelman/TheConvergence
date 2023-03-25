package com.stiggles.smp5.player;


import org.bukkit.Bukkit;
import java.util.UUID;

public class CoinBank {
    private int balance;
    private UUID owner;
    /**
     * Default constructor for CoinBank. Should only be used for testing, or perhaps a "World Bank"
     */
    public CoinBank () {
        this (null, 0);
    }

    /**
     * Standard constructor for CoinBank.
     *
     * @param owner The player that the account should be linked to.
     */
    public CoinBank (UUID owner) {
        this (owner, 0);
    }

    public CoinBank (UUID owner, int balance) {
        this.balance = balance;
        this.owner = owner;
    }
    /**
     * @return The player that is linked to the account
     */
    public UUID getOwner () {
        return owner;
    }

    /**
     * @return The total config.yml the player has
     */
    public int getBalance () {
        return balance;
    }

    /**
     * Withdraw a certain amount of coins from an account
     *
     * @param amount The amount of coins.yml to remove
     * @return Whether or not the transaction was successful
     */
    public boolean withdraw (int amount) {
        if (!hasSufficientFunds(amount))
            return false;
        balance -= amount;
        return true;
    }

    /**
     * Deposit a certain amount of coins to an account
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
        return (balance - amount) > 0;
    }

    @Override
    public String toString() {
        if (Bukkit.getPlayer(owner) == null)
            return "Error: Player not found with UUID: " + owner.toString ();
        return Bukkit.getPlayer (owner).getName () + ": " + balance;
    }

}
