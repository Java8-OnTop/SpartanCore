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
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import me.xss6.SpartanProtect.listeners.ServerPingListener;
import me.xss6.SpartanProtect.listeners.JoinCheck;
import me.xss6.SpartanProtect.listeners.InventoryOpenCheck;
import org.bukkit.plugin.PluginManager;

public final class Spartan extends JavaPlugin implements Listener, CommandExecutor {
    @Override
    final Spartan Instance;
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Spartan Protection is enabled");
        checkConfig();
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        this.getCommand("SpartanProtect").setExecutor(this); //TODO: handle NullPointerException thing (what should I do?)
        Spartan.Instance = this;
        this.getServer().getPluginManager().registerEvents((Listener)new ServerPingListener(), (Plugin)this);
        this.getLogger().info("SpartanProtect v" + "0.0.2" + " Enabled!");
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(this, this);
        pm.registerEvents(new JoinCheck(this), this);
        pm.registerEvents(new InventoryOpenCheck(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Spartan Protection is disabled");
        this.getLogger().info("RandomMOTD v" + this.getDescription().getVersion() + " Disabled!");
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
