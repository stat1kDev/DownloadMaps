package stat1kDev.downloadmaps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

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

    @Override
    protected void onResume() {
        super.onResume();
        initProgressBarMemory();
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

    }

    private void loadCountries() {
        ParserXml parserXml = new ParserXml(this);
        List<String> list = parserXml.parserXmlForCountries();
        countriesAdapter.setItems(list);
        countriesAdapter.notifyDataSetChanged();

    }



}
