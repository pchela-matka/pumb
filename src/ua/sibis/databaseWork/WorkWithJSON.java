package ua.sibis.databaseWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;

public class WorkWithJSON {

	private String link = "http://192.168.91.30/local/queue/.api?live-sum-people.json";

	public CameraData getJSON() {
		CameraData result = new CameraData();
		String resultstr = "";
		try {

			URL url = new URL(link);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {

				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			// String output;
			System.out.println("Output from Server .... \n");
			String output;
			while ((output = br.readLine()) != null) {
				resultstr += output;
			}

			conn.disconnect();

			JSONObject obj = new JSONObject(resultstr);
			result.setSerial(obj.getString("serial"));
			result.setName(obj.getString("name"));
			result.setTimestamp(obj.getString("timestamp"));
			result.setRegion1name(obj.getString("region1name"));
			result.setRegion1people(obj.getInt("region1people"));
			result.setRegion2name(obj.getString("region2name"));
			result.setRegion2people(obj.getInt("region2people"));
			result.setRegion3name(obj.getString("region3name"));
			result.setRegion3people(obj.getInt("region3people"));
		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}