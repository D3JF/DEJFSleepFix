package cf.dejf.DEJFSleepFix;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class DEJFSleepFix extends JavaPlugin {

    private DEJFSleepFix plugin;
    private Logger log;
    private String pluginName;
    private PluginDescriptionFile pdf;

    public static DEJFSleepFix instance;
    public static DEJFSleepFix getInstance() { return instance; }

    public static List<UUID> sleepingPlayers = new ArrayList<>();

    @Override
    public void onEnable() {

        plugin = this;
        instance = this;
        log = this.getServer().getLogger();
        pdf = this.getDescription();
        pluginName = pdf.getName();
        log.info("[" + pluginName + "] Loading plugin version " + pdf.getVersion());

        this.getServer().getPluginManager().registerEvent(Event.Type.PLAYER_BED_ENTER, new PlayerBedListener(plugin), Event.Priority.Normal, this);
        this.getServer().getPluginManager().registerEvent(Event.Type.PLAYER_BED_LEAVE, new PlayerBedListener(plugin), Event.Priority.Normal, this);

        log.info("[" + pluginName + "] loaded successfully!");

    }

    @Override
    public void onDisable() {

    }


}
