package net.gshp.gepp.model;

import net.gshp.gepp.adapter.AdapterSchedule;
import net.gshp.gepp.dao.DaoAgenda;
import net.gshp.gepp.dto.DtoSchedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by leo on 10/03/18.
 */

public class ModelSchedule {

    private DaoAgenda daoAgenda;
    private List<DtoSchedule>lstSchedule;
    private AdapterSchedule adapter;

    public ModelSchedule() {
        daoAgenda = new DaoAgenda();
    }

    public AdapterSchedule getAdapterSchedule(long idUser) {

        int lstWithBigZise = 0;

        List<DtoSchedule> lstSchedule, lstMonday, lstTuesday, lstWednesday, lstThursday, lstFriday, lstSaturday, lstSunday;
        lstSchedule = new ArrayList<>();
        lstMonday = new ArrayList<>();
        lstTuesday = new ArrayList<>();
        lstWednesday = new ArrayList<>();
        lstThursday = new ArrayList<>();
        lstFriday = new ArrayList<>();
        lstSaturday = new ArrayList<>();
        lstSunday = new ArrayList<>();

        daoAgenda.select(lstMonday, lstTuesday, lstWednesday, lstThursday, lstFriday, lstSaturday, lstSunday,idUser);

        lstWithBigZise = lstMonday.size();
        lstWithBigZise = lstTuesday.size() > lstWithBigZise ? lstTuesday.size() : lstWithBigZise;
        lstWithBigZise = lstWednesday.size() > lstWithBigZise ? lstWednesday.size() : lstWithBigZise;
        lstWithBigZise = lstThursday.size() > lstWithBigZise ? lstThursday.size() : lstWithBigZise;
        lstWithBigZise = lstFriday.size() > lstWithBigZise ? lstFriday.size() : lstWithBigZise;
        lstWithBigZise = lstSaturday.size() > lstWithBigZise ? lstSaturday.size() : lstWithBigZise;
        lstWithBigZise = lstSunday.size() > lstWithBigZise ? lstSunday.size() : lstWithBigZise;
        for (int i = 0; i < lstWithBigZise; i++) {
            if (lstMonday.size() > i) lstSchedule.add(lstMonday.get(i));
            else lstSchedule.add(new DtoSchedule());
            if (lstTuesday.size() > i) lstSchedule.add(lstTuesday.get(i));
            else lstSchedule.add(new DtoSchedule());
            if (lstWednesday.size() > i) lstSchedule.add(lstWednesday.get(i));
            else lstSchedule.add(new DtoSchedule());
            if (lstThursday.size() > i) lstSchedule.add(lstThursday.get(i));
            else lstSchedule.add(new DtoSchedule());
            if (lstFriday.size() > i) lstSchedule.add(lstFriday.get(i));
            else lstSchedule.add(new DtoSchedule());
            if (lstSaturday.size() > i) lstSchedule.add(lstSaturday.get(i));
            else lstSchedule.add(new DtoSchedule());
            if (lstSunday.size() > i) lstSchedule.add(lstSunday.get(i));
            else lstSchedule.add(new DtoSchedule());
        }

        return adapter=new AdapterSchedule(lstSchedule);


    }

    public int isSomeReportIncomplete(){
        return daoAgenda.isSomeReportIncomplete();
    }

    public boolean isMakeTodayThisSchedule(long schedule){
        return daoAgenda.isMakeTodayThisSchedule(schedule);
    }

    private String getDateWithFormat(Date date) {
        String dateFormat;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat = sdf.format(date);
        return dateFormat;
    }

    public static Calendar getCalendar() {
        Calendar cal = Calendar.getInstance(Locale.GERMANY);
        return cal;
    }

}
