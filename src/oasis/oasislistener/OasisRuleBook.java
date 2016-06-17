package oasis.oasislistener;

import oasis.OasisEssentials;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;

public class OasisRuleBook implements Listener {

	private OasisEssentials plugin;
	public OasisRuleBook(OasisEssentials plugin) {
		this.plugin = plugin;
	}

	@EventHandler
    public void OnPlayerKick(PlayerKickEvent event) {
        if (event.getPlayer().isBanned() == false) {
            if (plugin.enablekick) {
                plugin.kickedplayer.add(event.getPlayer().getName());
            }
        }
    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer(); // Who set off the PlayerJoinEvent

        if (!player.hasPlayedBefore()) {
            plugin.givebook(player, 1);
        }

        if (plugin.kickedplayer.contains(player.getName())) {
        	player.sendMessage(ChatColor.DARK_RED + "Apparently you have been a bad bad Minecrafter...");
        	player.sendMessage(ChatColor.DARK_RED + "So here is another rule book for you to read!");
            plugin.givebook(player, 1);
            plugin.kickedplayer.remove(player.getName());
        }
    }
}
