package stat1kDev.downloadmaps;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


import static stat1kDev.downloadmaps.DeviceMemory.getAvailableInternalMemorySize;
import static stat1kDev.downloadmaps.DeviceMemory.getTotalInternalMemorySize;

public class MainActivity extends AppCompatActivity {

    private TextView textViewFreeMemory;
    private ProgressBar progressBarMemory;
    private RecyclerView countriesRecyclerView;

    private CountriesAdapter countriesAdapter;

    private double freeMemory;
    private double allMemory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTextViewFreeMemory();
        initProgressBarMemory();

        initRecyclerView();
        loadCountries();

    }


    private void initTextViewFreeMemory() {
        textViewFreeMemory = findViewById(R.id.tv_free_memory);
        freeMemory = getAvailableInternalMemorySize();
        textViewFreeMemory.setText(getResources().getString(R.string.free_memory, freeMemory));
    }

    private void initProgressBarMemory() {
        progressBarMemory = findViewById(R.id.progress_bar_memory);
        freeMemory = getAvailableInternalMemorySize();
        allMemory = getTotalInternalMemorySize();

        progressBarMemory.setMax((int) allMemory);
        progressBarMemory.setProgress((int) allMemory - (int) freeMemory);

        progressBarMemory.setScaleY(4f);
    }

    private void initRecyclerView() {
        countriesRecyclerView = findViewById(R.id.rv_countries);
        countriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        countriesAdapter = new CountriesAdapter();
        countriesRecyclerView.setAdapter(countriesAdapter);

        countriesAdapter.setOnItemClickListener(new CountriesAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.d("LOG_TAG", "itemClicked" + position);
            }
        });


    }

    private void loadCountries() {

        ParserXml parserXml = new ParserXml(this);

        List<String> list = parserXml.parserXmlForCountries();

        countriesAdapter.setItems(list);
        countriesAdapter.notifyDataSetChanged();

    }

   /* public ArrayList<String> parserXmlForCountries() {
        ArrayList<String> listNamesCountries = new ArrayList<>();

        try {

            XmlPullParser xmlPullParser = getResources().getXml(R.xml.regions);

            while (xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT) {

                switch (xmlPullParser.getEventType()) {
                    case XmlPullParser.START_DOCUMENT: {
                        if (BuildConfig.DEBUG) {
                            Log.d("LOG_TAG", "START_DOCUMENT");
                        }
                        break;
                    }

                    case XmlPullParser.START_TAG: {
                        if (BuildConfig.DEBUG) {
                            Log.d("LOG_TAG", "START_TAG: имя тега = "
                                    + xmlPullParser.getName()
                                    + ", уровень = "
                                    + xmlPullParser.getDepth()
                                    + ", число атрибутов = "
                                    + xmlPullParser.getAttributeCount());
                        }

                        if (xmlPullParser.getName().equals("region")) {
                            if (xmlPullParser.getAttributeValue(null, "lang") != null) {
                                listNamesCountries.add(xmlPullParser.getAttributeValue(null, "name"));

                            }
                            if (xmlPullParser.getDepth() == 3 && xmlPullParser.getAttributeValue(null, "join_map_files") != null) {

                            }

                        }
                        break;
                    }

                    case XmlPullParser.END_TAG: {
                        if (BuildConfig.DEBUG) {
                            Log.d("LOG_TAG", "END_TAG: имя тега = " + xmlPullParser.getName());
                        }
                        break;
                    }
                    default:
                        break;

                }
                xmlPullParser.next();

            }

        } catch (Throwable e) {
            e.printStackTrace();
        }

        return listNamesCountries;
    }*/


}
