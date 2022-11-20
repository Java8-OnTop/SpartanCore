package me.xss6.SpartanProtect;

import org.bukkit.BanList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Spartan extends JavaPlugin implements Listener, CommandExecutor {
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Spartan Protection is enabled");
        checkConfig();
        getServer().getPluginManager().registerEvents(this, this);
        this.getCommand("SpartanProtect").setExecutor(this); //TODO: handle NullPointerException thing (what should I do?)
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Spartan Protection is disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                getLogger().info("Spartan Protection config reloaded");
                return true;
            }
        }
        return true;
    }
  
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (args.length >= 1) {
          if (args[0].equalIgnoreCase("discord")) {
              sender.sendMessage("https://dsc.gg/spartananarchy");
              return true;
          }
          
          if (args[0].equalIgnoreCase("donate")) {
              sender.sendMessage("To donate, visit: http://spartananarchy.tk/");
              return true;
          }
      }
      return true;
  }
}
