package se.citerus.notes.paas;

public class OpenShift implements Connector {

   @Override
   public boolean available() {
      return System.getenv("OPENSHIFT_APP_UUID") != null;
   }

   @Override
   public String url() {
      return System.getenv("OPENSHIFT_MONGODB_DB_URL") + db();
   }

   @Override
   public String db() {
      return "notes";
   }
}
