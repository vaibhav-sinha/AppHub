package com.adobe.apphub.Helpers;

import com.adobe.apphub.Models.AppData;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by vaisinha on 25/01/15.
 */
public class AppDataSortHelper {

    public void sortByIAP(List<AppData> appDataList) {
        Collections.sort(appDataList, new IAPComparator());
    }

    public void sortByRatings(List<AppData> appDataList) {
        Collections.sort(appDataList);
    }

    public class IAPComparator implements Comparator<AppData> {
        public int compare(AppData a, AppData b) {
            if(a.getInapp_purchase().equals("YES") && b.getInapp_purchase().equals("YES")) {
                return 0;
            }
            if(a.getInapp_purchase().equals("NO") && b.getInapp_purchase().equals("NO")) {
                return 0;
            }
            if(a.getInapp_purchase().equals("YES") && b.getInapp_purchase().equals("NO")) {
                return -1;
            }
            if(a.getInapp_purchase().equals("NO") && b.getInapp_purchase().equals("YES")) {
                return 1;
            }
            return 0;
        }
    }
}
