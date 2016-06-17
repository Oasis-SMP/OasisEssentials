package oasis.oasislistener;

import oasis.OasisEssentials;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class OasisReports implements Listener {

	private OasisEssentials plugin;
	public OasisReports(OasisEssentials plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onStaffJoin(final PlayerJoinEvent event){
		if(event.getPlayer().hasPermission("oasis.reports.staff")){
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
				@Override
				public void run(){
					event.getPlayer().sendMessage(ChatColor.GRAY + "There are " + ChatColor.RED + "[" + ChatColor.BLUE + plugin.reports.size() + ChatColor.RED + "]" + ChatColor.GRAY + " reports to check out!  " + ChatColor.YELLOW + "Have a nice day! :)");
				}
			},200L);
		}
		if(!event.getPlayer().hasPlayedBefore()){
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
				@Override
				public void run(){
					event.getPlayer().sendMessage(ChatColor.BLUE + "Welcome to Oasis!  If you find a grief, you can report it by doing /report Some one griefed me, while standing near the grief of looking at it!");
				}
			},200L);
		}
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		if((event.getMessage().contains("grief") || event.getMessage().contains("griefed") || event.getMessage().contains("grif")) && !event.getPlayer().hasPermission("oasisreports.staff")){
			event.getPlayer().sendMessage(ChatColor.BLUE + "You can report a grief by doing /report Some one griefed me, while standing near the grief or looking at it!");
		}
	}

}
