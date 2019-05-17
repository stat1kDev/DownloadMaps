package stat1kDev.downloadmaps;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder> {

    private List<String> countriesList = new ArrayList<>();
    private static ClickListener clickListener;
    private Context context;


    @NonNull
    @Override
    public CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_view, viewGroup, false);
        return new CountriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CountriesViewHolder countriesViewHolder, int i) {
        countriesViewHolder.bind(countriesList.get(i));

        context = countriesViewHolder.nameCountry.getContext();
        ParserXml xml = new ParserXml(context);

        List<String> listCountriesWithRegions = xml.parserXmlForCountriesWithRegions();

        for (int j = 0; j < listCountriesWithRegions.size(); j++) {
            if (countriesViewHolder.nameCountry.getText().toString().equals(listCountriesWithRegions.get(j))) {
                countriesViewHolder.importImageButton.setVisibility(View.GONE);
                countriesViewHolder.removeImageButton.setVisibility(View.GONE);
                break;
            } else {
                countriesViewHolder.importImageButton.setVisibility(View.VISIBLE);
                countriesViewHolder.removeImageButton.setVisibility(View.GONE);
            }
        }

        countriesViewHolder.importImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countriesViewHolder.downloadProgressBar.setVisibility(View.VISIBLE);
                countriesViewHolder.importImageButton.setVisibility(View.GONE);
                countriesViewHolder.removeImageButton.setVisibility(View.VISIBLE);
                countriesViewHolder.nameCountry.setPadding(0, 0, 0, 30);
            }
        });

        countriesViewHolder.removeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countriesViewHolder.downloadProgressBar.setVisibility(View.GONE);
                countriesViewHolder.importImageButton.setVisibility(View.VISIBLE);
                countriesViewHolder.removeImageButton.setVisibility(View.GONE);
                countriesViewHolder.nameCountry.setPadding(0, 0, 0, 0);
            }
        });
    }


    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    public void setItems(List<String> countries) {
        countriesList.addAll(countries);
        notifyDataSetChanged();
    }

    class CountriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mapIcon;
        private TextView nameCountry;
        private ProgressBar downloadProgressBar;
        private ImageButton importImageButton;
        private ImageButton removeImageButton;

        public CountriesViewHolder(View itemView) {
            super(itemView);

            mapIcon = itemView.findViewById(R.id.iv_item_map);
            nameCountry = itemView.findViewById(R.id.tv_item_country);
            downloadProgressBar = itemView.findViewById(R.id.progress_bar_download);
            importImageButton = itemView.findViewById(R.id.ib_item_import);
            removeImageButton = itemView.findViewById(R.id.ib_item_remove);

            itemView.setOnClickListener(this);

        }

        public void bind(String name) {
            nameCountry.setText(name);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        CountriesAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }


}
