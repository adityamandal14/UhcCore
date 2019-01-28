package com.gmail.val59000mc.playuhc.mc1_8.players;

import com.gmail.val59000mc.playuhc.mc1_8.game.GameManager;
import com.gmail.val59000mc.playuhc.mc1_8.players.PlayersManager;
import com.gmail.val59000mc.playuhc.mc1_8.players.UhcPlayer;
import com.gmail.val59000mc.playuhc.mc1_8.players.UhcTeam;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class TeamManager {

    private GameManager gm;
    private PlayersManager pm;
    private List<String> prefixes;

    public TeamManager(){
        gm = GameManager.getGameManager();
        pm = gm.getPlayersManager();
        loadPrefixes();
    }

    public List<UhcTeam> getUhcTeams(){
        List<UhcTeam> teams = new ArrayList<>();
        for(UhcPlayer player : pm.getPlayersList()){

            UhcTeam team = player.getTeam();
            if(!teams.contains(team)) {
                teams.add(team);
            }
        }
        return teams;
    }

    private int lastTeamNumber = 0;

    public int getNewTeamNumber(){
        lastTeamNumber++;
        return lastTeamNumber;
    }

    private void loadPrefixes(){

        prefixes = new ArrayList<>();

        // team prefix's
        List<ChatColor> colors = new ArrayList<>();

        colors.add(ChatColor.RED);
        colors.add(ChatColor.BLUE);
        colors.add(ChatColor.DARK_GREEN);
        colors.add(ChatColor.DARK_AQUA);
        colors.add(ChatColor.DARK_PURPLE);
        colors.add(ChatColor.YELLOW);
        colors.add(ChatColor.GOLD);
        colors.add(ChatColor.GREEN);
        colors.add(ChatColor.AQUA);
        colors.add(ChatColor.LIGHT_PURPLE);

        List<String> colorEdits = new ArrayList<>();
        colorEdits.add("");
        colorEdits.add("" + ChatColor.BOLD);
        colorEdits.add("" + ChatColor.ITALIC);
        colorEdits.add("" + ChatColor.UNDERLINE);
        colorEdits.add("" + ChatColor.BOLD + "" + ChatColor.ITALIC);
        colorEdits.add("" + ChatColor.BOLD + "" + ChatColor.UNDERLINE);
        colorEdits.add("" + ChatColor.ITALIC + "" + ChatColor.UNDERLINE);
        colorEdits.add("" + ChatColor.ITALIC + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD);

        for (String colorEdit : colorEdits){
            for (ChatColor color : colors){
                prefixes.add(color + colorEdit);
            }
        }

    }

    private List<String> getUsedPrefixes(){
        List<String> used = new ArrayList<>();
        for (UhcTeam team : getUhcTeams()){
            used.add(team.getPrefix().replace("■ ", ""));
        }
        return used;
    }

    public String getTeamPrefix(){

        for (String s : prefixes){

            if (!getUsedPrefixes().contains(s)){
                return s;
            }
        }

        return ChatColor.DARK_GRAY + "";
    }

}