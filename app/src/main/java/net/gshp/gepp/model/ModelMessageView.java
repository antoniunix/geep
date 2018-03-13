package net.gshp.gepp.model;

import net.gshp.gepp.dao.DaoMessage;
import net.gshp.gepp.dao.DaoMessageView;
import net.gshp.gepp.dto.DtoMessage;
import net.gshp.gepp.dto.DtoMessageView;

/**
 * Created by leo on 13/03/18.
 */

public class ModelMessageView {
    private DaoMessage daoMessage;
    private DaoMessageView daoMessageView;


    public ModelMessageView() {
        daoMessage = new DaoMessage();
        daoMessageView = new DaoMessageView();
    }

    public DtoMessage getItem(int id) {
        return daoMessage.SelectById(id);
    }

    public void messageView(DtoMessageView dtoMessageView) {
        if (daoMessageView.isViewMessage(dtoMessageView.getIdMessage()) == 0) {
            daoMessageView.Insert(dtoMessageView);
        }
    }
}
