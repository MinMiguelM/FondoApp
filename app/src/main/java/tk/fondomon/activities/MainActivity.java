package tk.fondomon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import tk.fondomon.adapters.BinderData;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static final String KEY_ID = "id";
    static final String KEY_TITLE = "title";
    static final String KEY_DESC = "desc";
    static final String KEY_IMG = "img";

    List<HashMap<String,String>> actionDataCollection;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            listActions();
            BinderData bindingData = new BinderData(this,actionDataCollection);
            list = (ListView) findViewById(R.id.list);
            list.setAdapter(bindingData);
            // Click event for single list row
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MainActivity.this,RequestActivity.class);
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(MainActivity.this,CreateRequestActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_sign_out) {
            // Handle the camera action
        } /*else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * List the app's actions
     */
    public void listActions() throws Exception{
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(getAssets().open("actionsData.xml"));

        actionDataCollection = new ArrayList<>();

        // normalize text representation
        doc.getDocumentElement ().normalize ();
        NodeList actionList = doc.getElementsByTagName("actionData");
        HashMap<String,String> map = null;

        for (int i = 0; i < actionList.getLength(); i++) {
            map = new HashMap<>();
            Node firstWeatherNode = actionList.item(i);
            if(firstWeatherNode.getNodeType() == Node.ELEMENT_NODE){
                Element firstActionElement = (Element)firstWeatherNode;
                // ID
                NodeList idList = firstActionElement.getElementsByTagName(KEY_ID);
                Element firstIdElement = (Element)idList.item(0);
                NodeList textIdList = firstIdElement.getChildNodes();
                map.put(KEY_ID, textIdList.item(0).getNodeValue().trim());

                // TITLE
                NodeList titleList = firstActionElement.getElementsByTagName(KEY_TITLE);
                Element firstTitleElement = (Element)titleList.item(0);
                NodeList textTitleList = firstTitleElement.getChildNodes();
                map.put(KEY_TITLE, textTitleList.item(0).getNodeValue().trim());

                // DESCRIPTION
                NodeList descList = firstActionElement.getElementsByTagName(KEY_DESC);
                Element firstDescElement = (Element)descList.item(0);
                NodeList textDescList = firstDescElement.getChildNodes();
                map.put(KEY_DESC,textDescList.item(0).getNodeValue().trim());

                // IMAGE
                NodeList imgList = firstActionElement.getElementsByTagName(KEY_IMG);
                Element firstImgElement = (Element)imgList.item(0);
                NodeList textImgList = firstImgElement.getChildNodes();
                map.put(KEY_IMG,textImgList.item(0).getNodeValue().trim());

                actionDataCollection.add(map);
            }
        }
    }
}
