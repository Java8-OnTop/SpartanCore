package me.xss6.SpartanProtect.commands;

import me.xss6.SpartanProtect.Spartan;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Reload implements CommandExecutor {
    private final Spartan plugin;

    public Reload(Spartan plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args[0].equalsIgnoreCase("reload")) {
            if (sender.isOp() || (!(sender instanceof Player))) {
                plugin.reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "SpartanProtect Config Reloaded!");
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
            }
        }

        if (args[0].equalsIgnoreCase("ver")) {
            sender.sendMessage(ChatColor.GREEN + "This server is currently running SpartanProtect " + ChatColor.GRAY + "v" + ChatColor.GREEN + plugin.getDescription().getVersion());
        }

        if (label.equalsIgnoreCase("sef")) { // Checking label instead of argument to fix an error
            sender.sendMessage(ChatColor.GREEN + "This server is currently running SpartanProtect " + ChatColor.GRAY + "v" + ChatColor.GREEN + plugin.getDescription().getVersion());
        }

        if (args[0].equalsIgnoreCase("test")) {
            sender.sendMessage("WOrks");
        }
        return false;
    }
}