package net.gshp.gepp.model;

import android.widget.ListAdapter;

import net.gshp.gepp.adapter.AdapterMessage;
import net.gshp.gepp.dao.DaoMessage;
import net.gshp.gepp.dto.DtoMessage;

import java.util.List;

/**
 * Created by leo on 13/03/18.
 */

public class ModelMessage {
    private DaoMessage daoMessage;
    private List<DtoMessage> list;

    public ModelMessage() {
        daoMessage = new DaoMessage();
    }

    public ListAdapter getAdapter() {

        list = daoMessage.Select();

        return new AdapterMessage(list);

    }
}
