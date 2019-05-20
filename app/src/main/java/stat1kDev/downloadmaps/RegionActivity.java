package stat1kDev.downloadmaps;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class RegionActivity extends AppCompatActivity {

    private RecyclerView regionsRecyclerView;
    private CountriesAdapter regionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);

        Intent intent = getIntent();
        setActionBarTitle();


        ParserXml parserXml = new ParserXml(this);

        String titleText = intent.getStringExtra("toolbarName");

        List<String> list = parserXml.parserXmlForRegionsCounrty(titleText);

        regionsRecyclerView = findViewById(R.id.rv_regions);
        regionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        regionsAdapter = new CountriesAdapter();
        regionsRecyclerView.setAdapter(regionsAdapter);

        regionsAdapter.setItems(list);
        regionsAdapter.notifyDataSetChanged();



    }

    public void setActionBarTitle() {
        Intent intent = getIntent();
        String titleText = intent.getStringExtra("toolbarName");

        getSupportActionBar().setTitle(titleText);
    }

}
