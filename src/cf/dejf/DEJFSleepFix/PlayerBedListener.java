package cf.dejf.DEJFSleepFix;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerListener;

import static cf.dejf.DEJFSleepFix.DEJFSleepFix.sleepingPlayers;

public class PlayerBedListener extends PlayerListener {

    private DEJFSleepFix plugin;

    public PlayerBedListener(DEJFSleepFix plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        sleepingPlayers.add(player.getUniqueId());
        int amount = sleepingPlayers.size();
        int needed = (int) Math.ceil(((double) plugin.getServer().getOnlinePlayers().length) / 2);
        if(amount < needed) {
            plugin.getServer().broadcastMessage("" + ChatColor.AQUA + amount + ChatColor.DARK_AQUA +
                    " player(s) sleeping. " + ChatColor.AQUA + needed + ChatColor.DARK_AQUA + " needed to skip time to day.");
        } else {
            plugin.getServer().broadcastMessage(ChatColor.DARK_AQUA + "Skipping time to " + ChatColor.AQUA + "day" +
                    ChatColor.DARK_AQUA + "!");
            player.getWorld().setTime(0L);
        }
    }

    @Override
    public void onPlayerBedLeave(PlayerBedLeaveEvent event) {
        Player player = event.getPlayer();
        sleepingPlayers.remove(player.getUniqueId());
        int amount = sleepingPlayers.size();
        int needed = (int) Math.ceil(((double) plugin.getServer().getOnlinePlayers().length) / 2);
        if(amount >= needed) {
            plugin.getServer().broadcastMessage(ChatColor.DARK_AQUA + "Skipping time to " + ChatColor.AQUA + "day" +
                    ChatColor.DARK_AQUA + "!");
            player.getWorld().setTime(0L);
        }
    }

}
