package com.chen.match.structs;

import com.chen.data.bean.MapBean;
import com.chen.data.manager.DataManager;

public abstract class MatchList
{
	protected MapBean mapBean;
	public MatchList(int mapId)
	{
		setMapBean(DataManager.getInstance().mapContainer.getList().get(mapId));
	}
	public MapBean getMapBean() {
		return mapBean;
	}
	public void setMapBean(MapBean mapBean) {
		this.mapBean = mapBean;
	}
	public abstract boolean addOneTeam(MatchTeam team);
	public abstract boolean removeOneTeam(MatchTeam team);
	public abstract void update();
	public abstract boolean addInvitePlayer(MatchPlayer player,int matchRoomId,boolean isAccept);
}
