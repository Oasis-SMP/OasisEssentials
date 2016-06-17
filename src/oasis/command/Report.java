package oasis.command;

import oasis.OasisEssentials;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Report implements CommandExecutor {

	private OasisEssentials plugin;
	public Report(OasisEssentials plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String name,
			String[] args) {
		if( sender instanceof ConsoleCommandSender){
			if(plugin.reports.isEmpty()){
				sender.sendMessage(ChatColor.translateAlternateColorCodes('~',"~cNo plugin.reports to check!"));
				return true;
			} else {
				for (String count : plugin.reports) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('~',"*  ~c[~9" + count + "~c] - ~7" + plugin.getConfig().getString(count + ".report")));
				}
				return true;
			}
		}
		if (((Player) sender).hasPermission("oasisplugin.reports.staff")) {
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("list")) {
					if (sender.hasPermission("oasisplugin.reports.list")) {
						if(plugin.reports.isEmpty()){
							SendMsg(((Player) sender), "~cNo plugin.reports to check!");
							return true;
						} else {
							for (String count : plugin.reports) {
								SendMsg(((Player) sender), "*  ~c[~9" + count + "~c] - ~7"
										+ plugin.getConfig().getString(count + ".report"));
							}
							return true;
						}
					}
				}

				if (args[0].equalsIgnoreCase("clearall")) {
					if (sender.hasPermission("oasisplugin.reports.clearall")) {
						for(String count: plugin.reports){
							plugin.getConfig().set(count, null);
						}
						plugin.reports.clear();
						sender.sendMessage(ChatColor.GREEN + "plugin.reports cleared!");
						return true;
					}
				}

			}
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("clear")) {
					if (sender.hasPermission("oasisplugin.reports.clear")) {
						if (plugin.getConfig().getKeys(false) != null) {
							try {
								Integer.parseInt(args[1]);
							} catch (NumberFormatException e) {
								SendMsg(((Player) sender), "~4" + args[1]
										+ " is not a number!");
								return true;
							}
							plugin.getConfig().set(args[1], null);
							plugin.saveConfig();
							plugin.reports.clear();
							plugin.reports.addAll(plugin.getConfig().getKeys(false));
							sender.sendMessage(ChatColor.BLUE + "Report #" + args[1] + " cleared!  " + ChatColor.YELLOW + "Good Job! ;)");
							return true;
						} else {
							SendMsg(((Player) sender), "~cNo plugin.reports to check!");
							return true;
						}
					}
				}
				
				if(args[0].equalsIgnoreCase("check")){
					if(plugin.reports.contains(args[1])){
						World world = plugin.getServer().getWorld(plugin.getConfig().getString(args[1] + ".world"));
						int x = plugin.getConfig().getInt(args[1] + ".x");
						int y = plugin.getConfig().getInt(args[1] + ".y");
						int z = plugin.getConfig().getInt(args[1] + ".z");
						float pitch = plugin.getConfig().getInt(args[1] + ".pitch");
						float yaw = plugin.getConfig().getInt(args[1] + ".yaw");
						Location loc = new Location(world, x, y, z, yaw, pitch);
						((Player) sender).teleport(loc);
						sender.sendMessage(ChatColor.BLUE + "Teleported to the report!");
						return true;
					} else {
						sender.sendMessage(ChatColor.RED + args[1] + " is not in the plugin.reports list!");
						return true;
					}
				}
			}
		} else {
			if (plugin.reports.isEmpty()) {
				sendReport(1,((Player) sender),args);
						return true;
			} else {
				int i=1;
				for(String s: plugin.reports){
					if(i!=Integer.parseInt(s)){
						sendReport(i,((Player) sender),args);
						return true;
					}
					i++;
				}
				sendReport(i,((Player) sender),args);
				return true;
			}
		}
		return false;
	}
	
	public void sendReport(int i, Player player, String[] args){
		StringBuilder sbuilder = new StringBuilder();
		sbuilder.append(player.getName() + ":");
		for(int a=0;a<args.length;a++){
			sbuilder.append(" ");
			sbuilder.append(args[a]);
		}
		plugin.getConfig().set(String.valueOf(i)+".report",sbuilder.toString());
		plugin.getConfig().set(String.valueOf(i)+".world", player.getLocation().getWorld().getName());
		plugin.getConfig().set(String.valueOf(i)+".x", player.getLocation().getBlockX());
		plugin.getConfig().set(String.valueOf(i)+".y", player.getLocation().getBlockY());
		plugin.getConfig().set(String.valueOf(i)+".z", player.getLocation().getBlockZ());
		plugin.getConfig().set(String.valueOf(i)+".pitch", player.getLocation().getPitch());
		plugin.getConfig().set(String.valueOf(i)+".yaw", player.getLocation().getYaw());
		plugin.saveConfig();
		plugin.reports.clear();
		plugin.reports.addAll(plugin.getConfig().getKeys(false));
		player.sendMessage(ChatColor.BLUE + "Report sent!  Staff will be notified when they join!");
		plugin.getServer().broadcast(ChatColor.BLUE + "A new report was just submited!", "oasisplugin.reports.staff");
	}
	
	public static void SendMsg(Player player, String msg){
		player.sendMessage(ChatColor.translateAlternateColorCodes('~', msg));
	}

}
