package dev.bernouy.cms.conf;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TDBUtil {

    private static ReqTDB reqTDB;
    public static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public TDBUtil(ReqTDB a){
        reqTDB = a;
    }

    public static ReqTDB getReqTDB(){
        return reqTDB;
    }

    public static JsonNode toJsonNode(String json){
        try {
            return objectMapper.readTree(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
