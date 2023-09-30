package dev.bernouy.cms.conf;

public class TDBMother {

    protected ReqTDB reqTDB = null;
    protected boolean isBuild = false;

    public TDBMother(){
        reqTDB = TDBUtil.getReqTDB();
    }

}
