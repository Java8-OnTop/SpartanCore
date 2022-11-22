package me.xss6.SpartanProtect.listeners;

import org.bukkit.event.EventHandler;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.Listener;

public class ServerPingListener implements Listener
{
    @EventHandler
    public void a(final ServerListPingEvent event) {
        if (Bukkit.getServer().hasWhitelist()) {
            final List<String> whitelistList = (List<String>)RandomMOTD.Instance.getConfig().getStringList("motds");
            event.setMotd(ChatColor.translateAlternateColorCodes('&', (String)whitelistList.get((int)Math.floor(Math.random() * whitelistList.size()))));
        }
        else {
            final List<String> list = (List<String>)RandomMOTD.Instance.getConfig().getStringList("motds");
            event.setMotd(ChatColor.translateAlternateColorCodes('&', (String)list.get((int)Math.floor(Math.random() * list.size()))));
        }
    }
}
