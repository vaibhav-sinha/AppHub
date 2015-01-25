package com.adobe.apphub.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.adobe.apphub.Adapters.AppListAdapter;
import com.adobe.apphub.Base.BaseFragment;
import com.adobe.apphub.Datastore.Server;
import com.adobe.apphub.Events.LoadAppListEvent;
import com.adobe.apphub.Events.ProductSelectedEvent;
import com.adobe.apphub.Helpers.ApiService;
import com.adobe.apphub.Helpers.AppDataSortHelper;
import com.adobe.apphub.Models.AppData;
import com.adobe.apphub.R;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;


public class HomeFragment extends BaseFragment {

    @Inject
    EventBus eventBus;
    @Inject
    ApiService apiService;

    private ListView appList;
    private TextView count;
    private ImageView close;
    private Button inApp;
    private Button ratings;

    private List<AppData> appDataList;
    private AppDataSortHelper appDataSortHelper;
    private ProgressDialog progressDialog;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appDataSortHelper = new AppDataSortHelper();
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        appList = (ListView) rootView.findViewById(R.id.hAppList);
        count = (TextView) rootView.findViewById(R.id.hCount);
        close = (ImageView) rootView.findViewById(R.id.hClose);
        inApp = (Button) rootView.findViewById(R.id.hIAP);
        ratings = (Button) rootView.findViewById(R.id.hRatings);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitApp();
            }
        });

        inApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appDataSortHelper.sortByIAP(appDataList);
                setData(appDataList);
            }
        });

        ratings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appDataSortHelper.sortByRatings(appDataList);
                setData(appDataList);
            }
        });

        appList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductSelectedEvent event = new ProductSelectedEvent();
                event.setSuccess(true);
                event.setAppData((AppData) appList.getAdapter().getItem(position));
                eventBus.post(event);
            }
        });

        apiService.loadAppList(getActivity());

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Adobe Products");
        progressDialog.setMessage("Downloading data...");
        progressDialog.show();

        return rootView;
    }

    private void exitApp() {
        getActivity().finish();
    }

    private void setData(List<AppData> appDataList) {
        AppListAdapter appListAdapter = new AppListAdapter(getActivity(), R.layout.item_app_list, appDataList);
        appList.setAdapter(appListAdapter);
    }

    public void onEventMainThread(LoadAppListEvent event) {
        if(event.getSuccess()) {
            appDataList = event.getAppDataList();
            cleanList(appDataList);
            count.setText(Integer.toString(appDataList.size()));
            setData(appDataList);
        }
        else {
            Toast.makeText(getActivity(), "Could not fetch list of apps. Error = " + event.getError(), Toast.LENGTH_LONG).show();
        }
        progressDialog.dismiss();
    }

    private void cleanList(List<AppData> appDataList) {
        for(AppData appData : appDataList) {
            if(appData.getName() == null || appData.getName().equals("")) {
                appDataList.remove(appData);
            }
        }
    }
}
