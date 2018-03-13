package net.gshp.gepp.model;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import net.gshp.gepp.R;
import net.gshp.gepp.activity.Home;
import net.gshp.gepp.activity.ScanAlert;
import net.gshp.gepp.activity.Visit;
import net.gshp.gepp.activity.VisitByDay;
import net.gshp.gepp.dialog.DialogMessageDocuments;

/**
 * Created by leo on 8/03/18.
 */

public class ModelAHBottomNavigation implements AHBottomNavigation.OnTabSelectedListener {

    private Activity activity;
    private AHBottomNavigation ahBottomNavigation;
    private AHBottomNavigationItem ahHome, ahSchedule, ahVisit, ahScan, ahFile;

    public ModelAHBottomNavigation(Activity activity) {
        this.activity = activity;
        ahBottomNavigation = (AHBottomNavigation) activity.findViewById(R.id.bottom_navigation);

        ahHome = new AHBottomNavigationItem("", R.drawable.icn_home, R.color.color_black);
        ahSchedule = new AHBottomNavigationItem("", R.drawable.icn_vdtos, R.color.color_black);
        ahVisit = new AHBottomNavigationItem("", R.drawable.icn_alertas, R.color.color_black);
        ahScan = new AHBottomNavigationItem("", R.drawable.icn_mensajes, R.color.color_black);
        ahFile = new AHBottomNavigationItem("", R.drawable.icn_documentos, R.color.color_black);

        ahBottomNavigation.setAccentColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.colorSelectedNavigationBar));
        ahBottomNavigation.setInactiveColor(ContextCompat.getColor(activity.getApplicationContext(), R.color.colorNoSelectedNavigationBar));

        ahBottomNavigation.addItem(ahHome);
        ahBottomNavigation.addItem(ahSchedule);
        ahBottomNavigation.addItem(ahVisit);
        ahBottomNavigation.addItem(ahScan);
        ahBottomNavigation.addItem(ahFile);

        ahBottomNavigation.setOnTabSelectedListener(this);

    }


    public void onResume() {
        int countNew = new ModelNewMessageFile().countNewFile() + new ModelNewMessageFile().countNewMessage();
        if (activity instanceof Home) {
            ahBottomNavigation.setCurrentItem(0);
        } else if (activity instanceof Visit) {
            ahBottomNavigation.setCurrentItem(4);
        } else if (activity instanceof VisitByDay) {
            ahBottomNavigation.setCurrentItem(1);
        }

        if (countNew > 0) {
            ahBottomNavigation.setNotification(countNew + "", 3);
        } else {
            ahBottomNavigation.setNotification("", 3);
        }
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {

        if (activity instanceof Home) {
            Intent intent;
            switch (position) {
                case 0:
                    break;
                case 1:
                    activity.startActivity(new Intent(activity.getApplicationContext(), VisitByDay.class));
                    break;
                case 2:
                    activity.startActivity(new Intent(activity.getApplicationContext(), ScanAlert.class));
                    break;

                case 3:
                    DialogMessageDocuments dialogMessageDocuments = new DialogMessageDocuments();
                    FragmentManager fragmentManager = ((Home) activity).getSupportFragmentManager();
                    dialogMessageDocuments.show(fragmentManager, "message");
                    break;
                case 4:
                    activity.startActivity(new Intent(activity.getApplicationContext(), Visit.class));
                    break;
            }
        } else if (activity instanceof VisitByDay) {
            switch (position) {
                case 0:
                    activity.finish();
                    break;
                case 1:

                    break;
                case 2:
                    activity.startActivity(new Intent(activity.getApplicationContext(), ScanAlert.class));
                    activity.finish();
                    break;
                case 3:
                    DialogMessageDocuments dialogMessageDocuments = new DialogMessageDocuments();
                    FragmentManager fragmentManager = ((VisitByDay) activity).getSupportFragmentManager();
                    dialogMessageDocuments.show(fragmentManager, "message");
                    break;
                case 4:
                    activity.startActivity(new Intent(activity.getApplicationContext(), Visit.class));
                    activity.finish();
                    break;
            }
        } else if (activity instanceof Visit) {
            switch (position) {
                case 0:
                    activity.startActivity(new Intent(activity.getApplicationContext(), Home.class));
                    activity.finish();
                    break;
                case 1:
                    activity.startActivity(new Intent(activity.getApplicationContext(), VisitByDay.class));
                    activity.finish();
                    break;
                case 3:
                    DialogMessageDocuments dialogMessageDocuments = new DialogMessageDocuments();
                    FragmentManager fragmentManager = ((Visit) activity).getSupportFragmentManager();
                    dialogMessageDocuments.show(fragmentManager, "message");
                    break;
                case 4:

                    break;
            }
        }


        return false;
    }
    public AHBottomNavigation getView() {
        return ahBottomNavigation;
    }

}
