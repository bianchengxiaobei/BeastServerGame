package com.chen.battle.structs.map;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.chen.structs.Vector3;
import com.chen.utils.GameTools;

public class SSMapConfigLoader
{
	public void Load(SSMapData data,String filePath)
	{
		try 
		{
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputStream stream = new FileInputStream(filePath);
			Document doc = builder.parse(stream);
			NodeList list = doc.getElementsByTagName("table");
			if (list.getLength() > 0)
			{
				Node node = list.item(0);
				NodeList childrens = node.getChildNodes();
				for (int i=0;i<childrens.getLength();i++)
				{
					if ("map".equals(childrens.item(i).getNodeName()))
					{
						NodeList childs = childrens.item(i).getChildNodes();
						for (int j=0;j<childs.getLength();j++)
						{							
							if (childs.item(j) instanceof Element)
							{
								SSMapNode mapNode = new SSMapNode();
								mapNode.setX(Integer.parseInt(((Element)childs.item(j)).getAttribute("X")));
								mapNode.setY(Integer.parseInt(((Element)childs.item(j)).getAttribute("Y")));
								mapNode.setU(Integer.parseInt(((Element)childs.item(j)).getAttribute("U")));
								mapNode.setStrType(((Element)childs.item(j)).getAttribute("type"));
								if (!SSMapCfg.MapNodeTransfer(mapNode))
								{
									System.err.println("初始化地图格子失败:Transfer");
									return ;
								}
								if (data.getMapData().containsKey(mapNode.getX()))
								{
									if (data.getMapData().get(mapNode.getX()).containsKey(mapNode.getY()))
									{
										System.err.println("重复加载格子");
										return;
									}
									data.getMapData().get(mapNode.getX()).put(mapNode.getY(), mapNode);
								}
								else
								{
									HashMap<Integer, SSMapNode> mapNodes = new HashMap<Integer, SSMapNode>();
									mapNodes.put(mapNode.getY(), mapNode);
									data.getMapData().put(mapNode.getX(), mapNodes);
								}
							}						
						}
					}
					else if ("mapinfo".equals(childrens.item(i).getNodeName()))
					{
						if (childrens.item(i) instanceof Element)
						{
							Element root = (Element)childrens.item(i);
							String pos = root.getAttribute("pos");
							Vector3 realPos = GameTools.String2Vector(pos);
							String rotation = root.getAttribute("rotation");
							Vector3 realRotation = GameTools.String2Vector(rotation);
							data.InitPos = realPos;
							data.InitRotation = realRotation;
							NodeList childs = childrens.item(i).getChildNodes();
							for (int j=0;j<childs.getLength();j++)
							{
								try 
								{
									if (childs.item(j) instanceof Element)
									{
										Element child = (Element)childs.item(j);
										String mapNodeType = child.getAttribute("type");
										EMapNodeType eMapNodeType = SSMapCfg.String2MapNodeType(mapNodeType);
										MapNodeTypeInfo mapInfo = new MapNodeTypeInfo(eMapNodeType);
										String localPosition = child.getAttribute("localPos");
										String localRotation = child.getAttribute("localRotation");
										if (localPosition != null)
										{
											mapInfo.LocalPos = GameTools.String2Vector(localPosition);
										}
										if (localRotation != null)
										{
											mapInfo.LocalRotation = GameTools.String2Vector(localRotation);
										}	
										data.getMapNodeTypeInfo().put(eMapNodeType, mapInfo);
									}
								} 
								catch (Exception e) 
								{
									e.printStackTrace();
								}
							}
						}										
					}
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
