package com.bluecollar.task.json;

import org.json.JSONObject;
import org.json.JSONTokener;

public class CommonJSONParsers 
{
	
	 public String CheckAck(String response)
	 {
			String acknowledgement = "";
			try {
				
				JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
				acknowledgement = object.getString("ack");
				

			} catch (Exception je)
			{
				je.printStackTrace();
			}

			return acknowledgement;
		}

}
