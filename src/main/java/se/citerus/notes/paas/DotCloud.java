package se.citerus.notes.paas;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.util.HashMap;

public class DotCloud implements Connector {

   private HashMap<String, Object> properties;

   public DotCloud() {
      try {
         final ObjectMapper mapper = new ObjectMapper(new JsonFactory());
         this.properties =  mapper.readValue(new File("/home/dotcloud/environment.json"),
                 new TypeReference<HashMap<String, Object>>() {
                 });
      } catch (final Exception exception) {
         // ignore
      }
   }

   @Override
   public boolean available() {
      return properties != null;
   }

   @Override
   public String url() {
      final String url = properties.get("DOTCLOUD_MONGO_MONGODB_URL").toString();
      return "mongodb://mongo:secret" + url.substring(url.indexOf("@")) + "/" + db();
   }

   @Override
   public String db() {
      return "notes";
   }
}
