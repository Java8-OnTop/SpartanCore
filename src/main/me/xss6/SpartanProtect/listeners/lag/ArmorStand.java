package me.xss6.SpartanProtect.listeners.lag;

import me.xss6.SpartanProtect.Spartan;
import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import java.util.ArrayList;

public class ArmorStand implements Listener { // I don't think this works
    private final Spartan plugin;

    public ArmorStand(Spartan plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent evt) {
        if (plugin.getConfig().getBoolean("FixArmorStandLag")) {
            Chunk c = evt.getChunk();
            if (!evt.isNewChunk()) {
                ArrayList<Entity> entities = new ArrayList<>();
                for (Entity entity : c.getEntities()) {
                    if (entity.getType().equals(EntityType.ARMOR_STAND) && (!(entity.getType() == null))) {
                        entities.add(entity);
                        for (Entity e : entities) {
                            entity.remove();
                        }
                    }
                }
            }
        }
    }
}
