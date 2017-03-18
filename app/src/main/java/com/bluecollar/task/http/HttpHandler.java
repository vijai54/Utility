package com.bluecollar.task.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;


public class HttpHandler {


	public static final String TAG = "HttpHandler";


	/**
	 * Given a string representation of a URL, sets up a connection and gets
	 * an input stream.
	 * @param urlString A string representation of a URL.
	 * @return An InputStream retrieved from a successful HttpURLConnection.
	 * @throws java.io.IOException
	 */
	private InputStream downloadUrl(String urlString) throws IOException {
		// BEGIN_INCLUDE(get_inputstream)
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setReadTimeout(10000 /* milliseconds */);
		conn.setConnectTimeout(15000 /* milliseconds */);
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		// Start the query
		conn.connect();
		InputStream stream = conn.getInputStream();
		return stream;
		// END_INCLUDE(get_inputstream)
	}

	/** Initiates the fetch operation. */
	private String loadFromNetwork(String urlString) throws IOException {
		InputStream stream = null;
		String str ="";

		try {
			stream = downloadUrl(urlString);
			str = readIt(stream, 500);
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
		return str;
	}



	/** Reads an InputStream and converts it to a String.
	 * @param stream InputStream containing HTML from targeted site.
	 * @param len Length of string that this method returns.
	 * @return String concatenated according to len parameter.
	 * @throws java.io.IOException
	 * @throws java.io.UnsupportedEncodingException
	 */
	private String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
		Reader reader = null;
		reader = new InputStreamReader(stream, "UTF-8");
		char[] buffer = new char[len];
		reader.read(buffer);
		return new String(buffer);
	}


	protected String doInBackground(String... params) {
		String JsonResponse = null;
		String JsonDATA = params[0];
		HttpURLConnection urlConnection = null;
		BufferedReader reader = null;
		try {
			URL url = new URL("http://appliedinformatics.com/trialx");
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			// is output buffer writter
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-Type", "application/json");
			urlConnection.setRequestProperty("Accept", "application/json");
		//set headers and method
			Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
			writer.write(JsonDATA);
// json data
			writer.close();
			InputStream inputStream = urlConnection.getInputStream();
//input stream
			StringBuffer buffer = new StringBuffer();
			if (inputStream == null) {
				// Nothing to do.
				return null;
			}
			reader = new BufferedReader(new InputStreamReader(inputStream));

			String inputLine;
			while ((inputLine = reader.readLine()) != null)
				buffer.append(inputLine + "\n");
			if (buffer.length() == 0) {
				// Stream was empty. No point in parsing.
				return null;
			}
			JsonResponse = buffer.toString();
			//response data
			Log.i(TAG,JsonResponse);
			/*try {
				//send to post execute
				return JsonResponse;
			} catch (JSONException e) {
				e.printStackTrace();
			}*/  /// need to check   vijay
			return null;

		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (final IOException e) {
					Log.e(TAG, "Error closing stream", e);
				}
			}
		}
		return null;
	}
}