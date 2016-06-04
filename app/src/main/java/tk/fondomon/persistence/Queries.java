package tk.fondomon.persistence;

/**
 * Created by ASUS on 6/4/2016.
 */
public class Queries {

    // Host where is the server
    final static String HOST = "https://fondomon-minmiguelm.c9users.io";

    // Port of the server, here the server receive the requests
    final static String PORT = "8181";

    // Queries with the URL mapping
    final static String GET_USER_BY_USERNAME = HOST + ":" + PORT + "/user/getByUsername/";
}
