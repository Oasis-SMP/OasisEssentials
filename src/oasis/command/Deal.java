package oasis.command;

import oasis.OasisEssentials;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public class Deal implements CommandExecutor {

	private OasisEssentials plugin;
	public Deal(OasisEssentials plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String name,
			String[] args) {
		new BukkitRunnable() {
			int count = 1;
			String msg = ChatColor.translateAlternateColorCodes('&', "&a[&bOasis&cSMP&a] &c(â€¢_â€¢)");
			@Override
			public void run() {
				if(count==2){msg = ChatColor.translateAlternateColorCodes('&', "&a[&bOasis&cSMP&a] &c( â€¢_â€¢)>âŒ�â– -â– ");}
				if(count==3){msg = ChatColor.translateAlternateColorCodes('&', "&a[&bOasis&cSMP&a] &c(âŒ�â– _â– )");}
				if(count==4){
					msg = ChatColor.translateAlternateColorCodes('&', "&a[&bOasis&cSMP&a] &c(âŒ�â– _â– ) &bDeal with it!");
				}
				plugin.getServer().broadcastMessage(msg);
				if(count==4){this.cancel();}
				count++;
			}

		}.runTaskTimer(plugin, 30L, 30L);
		return true;
	}

}
