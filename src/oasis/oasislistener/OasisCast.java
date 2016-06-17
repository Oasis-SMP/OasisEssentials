package oasis.oasislistener;

import oasis.OasisEssentials;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
// TODO Need to setup config to section off each plugin im adding to this
// TODO will be like oasiscast.SOMESECTION and oasischat.SOMESECTION
public class OasisCast implements Listener {

	private OasisEssentials plugin;
	public OasisCast(OasisEssentials plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void OnPlayerCommand(PlayerCommandPreprocessEvent event){
		if (event.getMessage().startsWith("/")) {
			String name;
			for (String key : plugin.getConfig().getKeys(false)) {
				if (event.getMessage().startsWith("/" + key.toLowerCase() + " ")) {
					String msg = event.getMessage().substring(key.length() + 2);
					name = plugin.getConfig().getString(key + ".Name");
					if (plugin.getConfig().getString(key + ".Name")
							.contains("%random%")) {
						name = name.substring(8);
						StringBuffer thismsg = new StringBuffer();
						for (char string : name.toCharArray()) {
							thismsg.append(rColor() + "" + string);
						}
						name = thismsg.toString();
					}
					msg = plugin.getConfig().getString(key + ".Text")
							.concat(msg);
					if (plugin.getConfig().getString(key + ".Text")
							.contains("%random%")) {
						msg = msg.substring(8);
						StringBuffer thismsg2 = new StringBuffer();
						for (char string : msg.toCharArray()) {
							thismsg2.append(rColor() + "" + string);
						}
						msg = thismsg2.toString();
					}

					if (event.getPlayer().hasPermission(
							"oasis.cast.cmd." + key)) {
						plugin.getServer().broadcastMessage(
								ChatColor.translateAlternateColorCodes('&',
										name + " " + msg));
						event.setCancelled(true);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void OnRedstoneBlockEvent(BlockRedstoneEvent event){
		String name;
		Block block = event.getBlock();
		if (block instanceof CommandBlock) {
			String command = ((CommandBlock) block.getState()).getCommand()
					.toLowerCase().trim().split(" ")[0];
			for (String key : plugin.getConfig().getKeys(false)) {
				if (command.equalsIgnoreCase(key)) {
					String msg = ((CommandBlock) block.getState()).getCommand()
							.substring(key.length());
					name = plugin.getConfig().getString(key + ".Name");
					if (plugin.getConfig().getString(key + ".Name")
							.contains("%random%")) {
						name = name.substring(8);
						StringBuffer thismsg = new StringBuffer();
						for (char string : name.toCharArray()) {
							thismsg.append(rColor() + "" + string);
						}
						name = thismsg.toString();
					}
					msg = plugin.getConfig().getString(key + ".Text")
							.concat(msg);
					if (plugin.getConfig().getString(key + ".Text")
							.contains("%random%")) {
						msg = msg.substring(8);
						StringBuffer thismsg2 = new StringBuffer();
						for (char string : msg.toCharArray()) {
							thismsg2.append(rColor() + "" + string);
						}
						msg = thismsg2.toString();
					}
					plugin.getServer().broadcastMessage(
							ChatColor.translateAlternateColorCodes('&', name
									+ " " + msg));
				}
			}
		}
	}
	
	@EventHandler
	public void OnPlayerChat(AsyncPlayerChatEvent event){
		synchronized (plugin.lock){
			Player player = event.getPlayer();
			if (player.hasPermission("oasis.enderdragon")) {
				if (event.getMessage().contains("@rawr")) {
					for (Player playa : player.getWorld().getPlayers()) {
						playa.playSound(playa.getLocation(),
								Sound.ENDERDRAGON_GROWL, 1, 1);
					}
				}
			}
			
			if (player.hasPermission("oasis.enderdragon")) {
				if (event.getMessage().contains("@roar")) {
					for (Player playa : player.getWorld().getPlayers()) {
						playa.playSound(playa.getLocation(),
								Sound.ENDERDRAGON_GROWL, 1, 1);
					}
				}
			}
			
			if (player.hasPermission("oasis.cow")) {
				if (event.getMessage().contains("@moo")) {
					for (Player playa : player.getWorld().getPlayers()) {
						playa.playSound(playa.getLocation(),
								Sound.COW_IDLE, 1, 1);
					}
				}
			}
			
			if (player.hasPermission("oasis.pig")) {
				if (event.getMessage().contains("@oink")) {
					for (Player playa : player.getWorld().getPlayers()) {
						playa.playSound(playa.getLocation(),
								Sound.PIG_IDLE, 1, 1);
					}
				}
			}
			
			if (player.hasPermission("oasis.wolf")) {
				if (event.getMessage().contains("@bark")) {
					for (Player playa : player.getWorld().getPlayers()) {
						playa.playSound(playa.getLocation(),
								Sound.WOLF_BARK, 1, 1);
					}
				}
			}
			
			if (player.hasPermission("oasis.wolf")) {
				if (event.getMessage().contains("@growl")) {
					for (Player playa : player.getWorld().getPlayers()) {
						playa.playSound(playa.getLocation(),
								Sound.WOLF_GROWL, 1, 1);
					}
				}
			}
			
			if (player.hasPermission("oasis.wolf")) {
				if (event.getMessage().contains("@howl")) {
					for (Player playa : player.getWorld().getPlayers()) {
						playa.playSound(playa.getLocation(),
								Sound.WOLF_HOWL, 1, 1);
					}
				}
			}
			
			if (player.hasPermission("oasis.ocelot")) {
				if (event.getMessage().contains("@meow")) {
					for (Player playa : player.getWorld().getPlayers()) {
						playa.playSound(playa.getLocation(),
								Sound.CAT_MEOW, 1, 1);
					}
				}
			}
			
			if (player.hasPermission("oasis.ocelot")) {
				if (event.getMessage().contains("@hiss")) {
					for (Player playa : player.getWorld().getPlayers()) {
						playa.playSound(playa.getLocation(),
								Sound.CAT_HISS, 1, 1);
					}
				}
			}
			
			if (player.hasPermission("oasis.ocelot")) {
				if (event.getMessage().contains("@purr")) {
					for (Player playa : player.getWorld().getPlayers()) {
						playa.playSound(playa.getLocation(),
								Sound.CAT_PURR, 1, 1);
					}
				}
			}
			
			if (player.hasPermission("oasis.burp")) {
				if (event.getMessage().contains("@burp")) {
					for (Player playa : player.getWorld().getPlayers()) {
						playa.playSound(playa.getLocation(),
								Sound.BURP, 1, 1);
					}
				}
			}
			
			if (player.hasPermission("oasis.donkey")) {
				if (event.getMessage().contains("@jackass")) {
					for (Player playa : player.getWorld().getPlayers()) {
						playa.playSound(playa.getLocation(),
								Sound.DONKEY_IDLE, 1, 1);
					}
				}
			}
			
			if (player.hasPermission("oasis.zombie")) {
				if (event.getMessage().contains("@moan")) {
					for (Player playa : player.getWorld().getPlayers()) {
						playa.playSound(playa.getLocation(),
								Sound.ZOMBIE_IDLE, 1, 1);
					}
				}
			}
			
			if (player.hasPermission("oasis.sheep")) {
				if (event.getMessage().contains("@bah")) {
					for (Player playa : player.getWorld().getPlayers()) {
						playa.playSound(playa.getLocation(),
								Sound.SHEEP_IDLE, 1, 1);
					}
				}
			}
			
			if (player.hasPermission("oasis.levelup")) {
				if (event.getMessage().contains("@level up")) {
					for (Player playa : player.getWorld().getPlayers()) {
						playa.playSound(playa.getLocation(),
								Sound.LEVEL_UP, 1, 1);
					}
				}
			}
			
			if (player.hasPermission("oasis.levelup")) {
				if (event.getMessage().contains("@level up")) {
					for (Player playa : player.getWorld().getPlayers()) {
						playa.playSound(playa.getLocation(),
								Sound.LEVEL_UP, 1, 1);
					}
				}
			}
		}
	}

	@EventHandler
	public void OnConsoleCommand(ServerCommandEvent event){
		String name;
		for(String key:plugin.getConfig().getKeys(false)){
			if(event.getCommand().contains(key.toLowerCase()+" ")){
				String msg = event.getCommand().substring(key.length()+1);
				name=plugin.getConfig().getString(key+".Name");
				if(plugin.getConfig().getString(key+".Name").contains("%random%")){
					name=name.substring(8);
					StringBuffer thismsg = new StringBuffer();
					for(char string: name.toCharArray()){
						thismsg.append(rColor() + "" + string);
					}
					name=thismsg.toString();
				}
				msg = plugin.getConfig().getString(key+".Text").concat(msg);
				if(plugin.getConfig().getString(key+".Text").contains("%random%")){
					msg=msg.substring(8);
					StringBuffer thismsg2 = new StringBuffer();
					for(char string: msg.toCharArray()){
						thismsg2.append(rColor() + "" + string);
					}
					msg=thismsg2.toString();
				}
				plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', name + " " + msg));
			}
		}
	}

	private ChatColor rColor(){
		//RED ORANGE YELLOW GREEN BLUE INDIGO VIOLET
		switch (randomNum(1,7)){
		case 1: return ChatColor.RED;
		case 2: return ChatColor.GOLD;
		case 3: return ChatColor.YELLOW;
		case 4: return ChatColor.GREEN;
		case 5: return ChatColor.BLUE;
		case 6: return ChatColor.DARK_PURPLE;
		case 7: return ChatColor.LIGHT_PURPLE;
		default: return ChatColor.DARK_AQUA;
		}
	}

	public int randomNum(Integer lownum, double d) {
		return lownum + (int)(Math.random() * ((d - lownum) + 1));
	}
}
