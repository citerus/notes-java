package se.citerus.notes.paas;

import com.jayway.jsonpath.JsonPath;

public class CloudFoundry implements Connector {

    @Override
    public boolean available() {
        return System.getenv("VCAP_SERVICES") != null;
    }

    @Override
    public String url() {

        String json = System.getenv("VCAP_SERVICES");

        return JsonPath.read(json, "$.['mongodb-2.2'][0].credentials.url");
    }

    @Override
    public String db() {
        return "db";
    }

}