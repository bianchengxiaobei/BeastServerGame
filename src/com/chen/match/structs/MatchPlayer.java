package com.chen.match.structs;

import com.chen.player.structs.Player;

public class MatchPlayer 
{
	private Player player = null;
	private boolean isMonster = false;
	private int matchTeamId;
	private int punishLeftTime;
	public MatchPlayer(Player player)
	{
		this.player = player;
		this.matchTeamId = 0;
		this.isMonster = false;
		this.setPunishLeftTime(0);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isMonster() {
		return isMonster;
	}

	public void setMonster(boolean isMonster) {
		this.isMonster = isMonster;
	}

	public int getMatchTeamId() {
		return matchTeamId;
	}

	public void setMatchTeamId(int matchTeamId) {
		this.matchTeamId = matchTeamId;
	}

	public int getPunishLeftTime() {
		return punishLeftTime;
	}

	public void setPunishLeftTime(int punishLeftTime) {
		this.punishLeftTime = punishLeftTime;
	}
}
