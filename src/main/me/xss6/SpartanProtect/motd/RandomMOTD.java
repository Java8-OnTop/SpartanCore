package me.xss6.SpartanProtect.motd;


import org.bukkit.event.Listener;
import me.xss6.SpartanProtect.listeners.ServerPingListener.java;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class RandomMOTD extends JavaPlugin
{
    public static RandomMOTD Instance;
    
    public void onEnable() {
        RandomMOTD.Instance = this;
        this.getServer().getPluginManager().registerEvents((Listener)new ServerPingListener(), (Plugin)this);
        this.getLogger().info("RandomMOTD v" + this.getDescription().getVersion() + " Enabled!");
    }
    
    public void onDisable() {
        this.getLogger().info("RandomMOTD v" + this.getDescription().getVersion() + " Disabled!");
    }
}
