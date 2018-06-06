package Api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ApiGameN {
    // Create URL
    URL loginEndpoint = new URL("gamenewsuca.herokuapp.com/login");

    // Create connection
    HttpsURLConnection myConnection =
            (HttpsURLConnection)loginEndpoint.openConnection();

    public ApiGameN() throws IOException {
    }
}
