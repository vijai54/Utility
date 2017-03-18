package com.bluecollar.task.json;



public class JSONPayload {

	public JSONPayload() {

	}

/*	Log.d("mName: ", name);
	Log.d("mMobile: ", mobile);
	Log.d("mGovtId: ", govidId);
	Log.d("mAddress: ", address);
	Log.d("mSummary: ", summary);
	Log.d("mServiceSelected: ", servicesSelected);*/


	public String getRegistrationPayload(String mName, String summary,
			String govtID,String mobile, String address,String serviceSelected) {

		String login_json = "{" + "\"msg_name\":\"Registration\","
				 + "\"request_data\":" + "{"
				+ "\"name\":\"" + mName + "\"," +""
				+ "\"services\":\"" + serviceSelected + "\","
				+ "\"summary\":\"" + summary + "\"," +""
				+ "\"id\":\"" + govtID + "\"," + "\"mobile\":\""
				+ mobile+ "\","  +"\"address\":\""
				+ address+ "\"" + "},";
				//+ "\"request_list\":" + "null" + "}";
		return login_json;
	}


	public String getServiceRequestPayload(String mName, String summary,
										 String mobile, String address,String serviceSelected) {

		String login_json = "{" + "\"msg_name\":\"Registration\","
				+ "\"request_data\":" + "{"
				+ "\"name\":\"" + mName + "\"," +""
				+ "\"services\":\"" + serviceSelected + "\","
				+ "\"summary\":\"" + summary + "\"," +""
				+ "\"mobile\":\""+ mobile+ "\","
				+"\"address\":\""
				+ address+ "\"" + "},";
		//+ "\"request_list\":" + "null" + "}";
		return login_json;
	}


	/*String payload = payloadPage.getLoginPayload(time, userNameEditText.getText().toString(), passwordEditText.getText().toString(), pushIpAddress + ":"
			+ AppConstants.PushPortAddress);

	try
	{

		Log.i("Loginpayload", payload);
		response = HttpHandler.sendPostRequest(AppConstants.URL + loginUrl, payload);
		loginResponse = EntityUtils.toString(response.getEntity());
		Log.i("AppConstants.URL+loginUrl", AppConstants.URL + loginUrl);
		Log.i("response", loginResponse);*/



	public String getAlertListPayload(String mtime, int Patient_id) {

		String getAlertList_json = "{" + "\"current_ts\":\""
				+ mtime + "\"," + "\"msg_name\":\"getEventList\","
				+ "\"request_data\":" + "{" + "\"patient_id\":\"" + Patient_id
				+ "\"," + "\"from\":" + "\"20001122112233\"," + "\"to\":" + "\"21001122112233\"" + "},"
				+ "\"request_list\":" + "null" + "}";
		return getAlertList_json;

	}







}
