package oasis.oasislistener;

import oasis.OasisEssentials;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class OasisCoFH implements Listener {

	public OasisCoFH(OasisEssentials oasisEssentials) {
		// TODO Auto-generated constructor stub
	}

	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event){
		String message = event.getMessage().toLowerCase();
		if(message.contains("/cofh")){
			Player player = event.getPlayer();
			if(message.contains("/cofh version")){
				if(!player.hasPermission("oasiscofh.version")){
					event.setCancelled(true);
					return;
				}
			}
			
			if(message.contains("/cofh help")){
				if(!player.hasPermission("oasiscofh.help")){
					event.setCancelled(true);
					return;
				}
			}
			
			if(message.contains("/cofh syntax")){
				if(!player.hasPermission("oasiscofh.syntax")){
					event.setCancelled(true);
					return;
				}
			}
			
			if(message.contains("/cofh friend")){
				if(!player.hasPermission("oasiscofh.friend")){
					event.setCancelled(true);
					return;
				}
			}
			
			if(message.contains("/cofh tps")){
				if(!player.hasPermission("oasiscofh.tps")){
					event.setCancelled(true);
					return;
				}
			}
			
			if(message.contains("/cofh enchant")){
				if(!player.hasPermission("oasiscofh.enchant")){
					event.setCancelled(true);
					return;
				}
			}
			
			if(message.contains("/cofh killall")){
				if(!player.hasPermission("oasiscofh.killall")){
					event.setCancelled(true);
					return;
				}
			}
			
			if(message.contains("/cofh tpx")){
				if(!player.hasPermission("oasiscofh.tpx")){
					event.setCancelled(true);
					return;
				}
			}
			
			if(message.contains("/cofh clearblocks")){
				if(!player.hasPermission("oasiscofh.clearblocks")){
					event.setCancelled(true);
					return;
				}
			}
			
			if(message.contains("/cofh countblocks")){
				if(!player.hasPermission("oasiscofh.countblocks")){
					event.setCancelled(true);
					return;
				}
			}
			
			if(message.contains("/cofh replaceblocks")){
				if(!player.hasPermission("oasiscofh.replaceblocks")){
					event.setCancelled(true);
					return;
				}
			}
			
			if(message.contains("/cofh reloadworldgen")){
				if(!player.hasPermission("oasiscofh.reloadworldgen")){
					event.setCancelled(true);
					return;
				}
			}
			
			if(message.contains("/cofh unloadchunks")){
				if(!player.hasPermission("oasiscofh.unloadchunks")){
					event.setCancelled(true);
					return;
				}
			}
		}
	}
	
}
