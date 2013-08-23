package com.staffplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;



public class mainClass extends JavaPlugin implements Listener{

	String staff = getConfig().getString("Pos1") + getConfig().getString("Pos2") + getConfig().getString("Pos3") + getConfig().getString("Pos4") + getConfig().getString("Pos5") + getConfig().getString("Pos6") + getConfig().getString("Pos7") + getConfig().getString("Pos8") + getConfig().getString("Pos9") + getConfig().getString("Pos10");
	String positions = getConfig().getString("Pos1").substring(0, getConfig().getString("Pos1").indexOf(" ")) + " " + getConfig().getString("Pos2").substring(0, getConfig().getString("Pos2").indexOf(" ")) + " " + getConfig().getString("Pos3").substring(0, getConfig().getString("Pos3").indexOf(" ")) + " " + getConfig().getString("Pos4").substring(0, getConfig().getString("Pos4").indexOf(" ")) + " " + getConfig().getString("Pos5").substring(0, getConfig().getString("Pos5").indexOf(" ")) + " " + getConfig().getString("Pos6").substring(0, getConfig().getString("Pos6").indexOf(" ")) + " " + getConfig().getString("Pos7").substring(0, getConfig().getString("Pos7").indexOf(" ")) + " " + getConfig().getString("Pos8").substring(0, getConfig().getString("Pos8").indexOf(" ")) + " " + getConfig().getString("Pos9").substring(0, getConfig().getString("Pos9").indexOf(" ")) + " " + getConfig().getString("Pos10").substring(0, getConfig().getString("Pos10").indexOf(" "));
	String onlineStaff = "";
	String joinName = "";
	String leaveName = "";
	@Override
	public void onEnable(){
		//Enable logic
		getServer().getPluginManager().registerEvents(new mainClass(), this);
		getConfig().options().copyDefaults(true);
		reloadConfig();
		saveConfig();
	}
	@EventHandler
	public void onPlayerLogin(PlayerJoinEvent join){
		joinName = join.getPlayer().getName();
		if (getConfig().contains(joinName)){
			onlineStaff = onlineStaff + joinName + "  ";
		}
	}
	
	@EventHandler
	public void onPlayerLogout(PlayerQuitEvent leave){
		leaveName = leave.getPlayer().getName();
		if (onlineStaff.contains(leaveName)){
			onlineStaff = onlineStaff.replace(leaveName, "");
		}
	}
	
	
	
	
	// /staff
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("staff")){
			if (args.length > 1){
				sender.sendMessage("Too many arguments!");
				return false;
			}else{
				if (args.length < 1){
					args[0] = "list";
				}else{
				if (args[0].equalsIgnoreCase("list")){
					//list staff
					sender.sendMessage("Staff: " + staff);
					return true;
				}else{
					if (args[0].equalsIgnoreCase("online")){
						//list online staff
						sender.sendMessage("Online Staff: " + onlineStaff);
						return true;
					}else{
						if (args[0].equalsIgnoreCase("positions")){
							//list staff positions
							sender.sendMessage("Staff Positions: " + positions);
							return true;
						}else{
							if (args[0].equalsIgnoreCase("app")){
								//tell sender to email staff apps to email
								sender.sendMessage(getConfig().getString("contact"));
								return true;
							}else{
								return false;
							}}}}}}}
								return false;
					}
	
	@Override
	public void onDisable(){
		//Disable logic
		this.saveConfig();
	}
}
