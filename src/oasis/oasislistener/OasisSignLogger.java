package oasis.oasislistener;

import oasis.OasisEssentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class OasisSignLogger implements Listener {

	private OasisEssentials plugin;
	public OasisSignLogger(OasisEssentials plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority=EventPriority.MONITOR)
	public void onPlayerQuit(PlayerQuitEvent event){

		if(plugin.doNOTlog.contains(event.getPlayer().getUniqueId())){
			plugin.doNOTlog.remove(event.getPlayer().getUniqueId());
			return;
		}

		if(plugin.doNOTnotify.contains(event.getPlayer().getUniqueId())){
			plugin.doNOTnotify.remove(event.getPlayer().getUniqueId());
			return;
		}
	}

	@EventHandler(priority= EventPriority.MONITOR)
	public void onPlayerKick(PlayerKickEvent event){
		if(plugin.doNOTlog.contains(event.getPlayer().getUniqueId())){
			plugin.doNOTlog.remove(event.getPlayer().getUniqueId());
			return;
		}

		if(plugin.doNOTnotify.contains(event.getPlayer().getUniqueId())){
			plugin.doNOTnotify.remove(event.getPlayer().getUniqueId());
			return;
		}
	}

	@EventHandler(priority= EventPriority.MONITOR)
	public void onSignChange(SignChangeEvent event){
		Block block = event.getBlock();
		int x = block.getX();
		int y = block.getY();
		int z = block.getZ();
		String world = block.getWorld().getName();
		String playa = event.getPlayer().getName();
		Player player = event.getPlayer();

		if(player.hasPermission("oasissignlogger.bypass") || plugin.doNOTlog.contains(player.getUniqueId())){
			return;
		}

		String line = ChatColor("&a[&bSIGN&a] &7" + playa + " World:" + world + " X:" + x + " Y:" + y + " Z:" + z);

		int count=0;
		for(String string:event.getLines()){
			if (string.contains("[private]")
					|| string.contains("[disposal]")
					|| string.contains("[more users]")
					|| string.contains("[More Users]")
					|| string.contains("[Private]")
					|| string.contains("[Disposal]")) {
				return;
			}
			if(string.length()==0){
				count++;
				if(count==4){return;}
				continue;
			}
			line = line.concat(" | " + string);
		}


		System.out.println(ChatColor.stripColor(line));

		for(Player p:Bukkit.getOnlinePlayers()){
			if(!player.equals(p)){
				if(p.hasPermission("oasissignlogger.notify")){
					if (!plugin.doNOTnotify.contains(p.getUniqueId())) {
						p.sendMessage(line);
					}
				}
			} else if(player.hasPermission("debug")){
				p.sendMessage(line);
			}
		}
	}

	public String ChatColor(String msg){
		return ChatColor.translateAlternateColorCodes('&', msg);
	}

}
