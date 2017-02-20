package com.chen.match.structs;

import java.util.Vector;

public class MatchList_AI extends MatchList
{
	private int userCount;
	private Vector<MatchTeam> teams;
	public MatchList_AI(int mapId)
	{
		super(mapId);
		this.userCount = 0;
	}
	@Override
	public boolean addOneTeam(MatchTeam team) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean removeOneTeam(MatchTeam team) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean addInvitePlayer(MatchPlayer player, int matchRoomId,
			boolean isAccept) {
		// TODO Auto-generated method stub
		return false;
	}
}
