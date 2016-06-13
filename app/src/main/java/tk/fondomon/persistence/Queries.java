package tk.fondomon.persistence;

/**
 * Created by ASUS on 6/4/2016.
 */
public class Queries {

    // Host where is the server
    final static String HOST = "http://192.168.0.15";

    // Port of the server, here the server receive the requests
    final static String PORT = "8181";

    // Queries with the URL mapping
    public static final String GET_USER_BY_USERNAME = HOST + ":" + PORT + "/members/getByMemberName/";
    public static final String GET_REQUESTS_BY_IDMEMBER = HOST + ":" + PORT + "/requests/getByIdMember/";
    public static final String INSERT_REQUEST = HOST + ":" + PORT + "/requests/add";
    public static final String GET_ALL_REQUESTS = HOST + ":" + PORT + "/requests/getAll";
    public static final String UPDATE_REQUEST = HOST + ":" + PORT + "/requests/update";
    public static final String INSERT_TOPIC = HOST + ":" + PORT + "/topics/add";
}
