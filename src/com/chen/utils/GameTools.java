package com.chen.utils;

import com.chen.structs.Vector3;

public class GameTools
{
	public static Vector3 String2Vector(String vec)
	{
		Vector3 vector3 = new Vector3(0, 0, 0);
		try 
		{
			String[] array = vec.split(",");
			if (array.length == 3)
			{
				vector3.setX(Float.parseFloat(array[0]));
				vector3.setY(Float.parseFloat(array[1]));
				vector3.setZ(Float.parseFloat(array[2]));
			}
			return vector3;
		}
		catch (Exception e)
		{
			System.err.println("字符串转换成Vector3出错");
			e.printStackTrace();
		}
		return null;
	}
}
