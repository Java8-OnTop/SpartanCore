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
import org.bukkit.World;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.entity.*;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Objects;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import me.xss6.SpartanProtect.listeners.ServerPingListener;
import me.xss6.SpartanProtect.listeners.JoinCheck;
import me.xss6.SpartanProtect.listeners.InventoryOpenCheck;
import me.xss6.SpartanProtect.listeners.lag.*;
import me.xss6.SpartanProtect.listeners.AntiNetherRoof;
import me.xss6.SpartanProtect.listeners.RandomRespawn;
import me.xss6.SpartanProtect.listeners.LimitArmorStands;
import org.bukkit.plugin.PluginManager;
import com.comphenix.protocol.ProtocolManager;

public final class Spartan extends JavaPlugin implements Listener, CommandExecutor {
    @Override
    private static Spartan Instance;
    private ProtocolManager protocolManager;
    
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
        
        //stacked items
        pm.registerEvents(this, this);
        pm.registerEvents(new JoinCheck(this), this);
        pm.registerEvents(new InventoryOpenCheck(this), this);
        
        //exploits
        pm.registerEvents(new ArmorStand(this), this);
        
        //features
        pm.registerEvents(new RandomRespawn(), this);
        pm.registerEvents(new AntiNetherRoof(), this);
        
        LimitArmorStands.limit();
        
        
        if (instance == null) instance = this;
        
        if (getConfig().getBoolean("DisableAllProtocolLib")) {
            getLogger().info("You specified to disable all ProtocolLib patches.");
        } else {
            if (getServer().getPluginManager().getPlugin("ProtocolLib") != null) {
                getLogger().info("Detected ProtocolLib!");
                ProtocolLib.protocolLibWrapper(this);
            } else {
                getLogger().warning("Did not detect ProtocolLib, disabling packet patches");
            }
        }
    }
    public ProtocolManager getProtocolManager() {
        return protocolManager;
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
    
    public Integer count(Chunk c, Material m ) { // Super cool count feature to count the amount of blocks per chunk.
        int num = 0;
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 256; y++) {
                    if (c.getBlock(x, y, z).getType() == m) {
                        num++;
                    }
                }
            }
        }
        return num;
    }
    
    public static Spartan getInstance() {
        return instance;
    }

}
