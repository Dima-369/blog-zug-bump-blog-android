package gira.blogzug_pwned;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.IntentService;
import android.content.Intent;
import android.util.Base64;

public class POSTService extends IntentService {

	public static int zahlA = 0;
	public static int zahlB = 0;
	public static Random rnd;

	public POSTService() {

		super("POSTService");
	}

	@Override
	protected void onHandleIntent(Intent arg0) {

		rnd = new Random();

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://blog-zug.com/zug.php");

		zahlA = rnd.nextInt(10);
		zahlB = rnd.nextInt(10);

		List<NameValuePair> nameValuePairs = new ArrayList<>(6);
		nameValuePairs.add(new BasicNameValuePair("summe", String.valueOf(zahlA + zahlB)));
		nameValuePairs.add(new BasicNameValuePair("button", "Aufspringen"));
		nameValuePairs.add(new BasicNameValuePair("zahla", String.valueOf(zahlA)));
		nameValuePairs.add(new BasicNameValuePair("zahlb", String.valueOf(zahlB)));

		try {

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		httppost.addHeader("Referer", "http://blog-zug.com/");

		try {

			//"phpbb3_bzf2_u=9724; phpbb3_bzf2_k=bd903633477e84a2; phpbb3_bzf2_sid=8645d7cf77c629c1e3d186bd7d99f9e8; blogan12=2; blog=www.le-meme-contraire.com; PHPSESSID=7m65357nbsb0npj00nlo68ap36");
			httppost.addHeader("Cookie", new String(Base64.decode(new String("cGhwYmIzX2J6ZjJfdT05NzI0OyBwaHBiYjNfYnpmMl9rPWJkOTAzNjMzNDc3ZTg0YTI7IHBocGJiM19iemYyX3NpZD04NjQ1ZDdjZjc3YzYyOWMxZTNkMTg2YmQ3ZDk5ZjllODsgYmxvZ2FuMTI9MjsgYmxvZz13d3cubGUtbWVtZS1jb250cmFpcmUuY29tOyBQSFBTRVNTSUQ9N202NTM1N25ic2IwbnBqMDBubG82OGFwMzY=").getBytes("UTF-8"), Base64.DEFAULT), "UTF-8"));

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		try {

			httpclient.execute(httppost).getStatusLine().getStatusCode();

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
