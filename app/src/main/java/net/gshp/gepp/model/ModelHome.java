package net.gshp.gepp.model;

import net.gshp.gepp.dao.DaoUserInfo;
import net.gshp.gepp.dto.DtoUserInfo;

/**
 * Created by leo on 8/03/18.
 */

public class ModelHome {

    private DaoUserInfo daoUserInfo;
    private DtoUserInfo dtoUserInfo;

    public ModelHome() {
        daoUserInfo = new DaoUserInfo();
        dtoUserInfo = new DtoUserInfo();

    }

    public DtoUserInfo getUserInfo() {
       return dtoUserInfo = daoUserInfo.select();
    }


}
