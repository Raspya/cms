package dev.bernouy.cms.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TDBUtil {

    private static ReqTDB reqTDB;

    @Autowired
    public TDBUtil(ReqTDB a){
        reqTDB = a;
    }

    public static ReqTDB getReqTDB(){
        return reqTDB;
    }

}
