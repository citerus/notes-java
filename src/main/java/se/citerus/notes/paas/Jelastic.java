package se.citerus.notes.paas;

public class Jelastic implements Connector {

   @Override
   public boolean available() {
      return "jelastic".equals(System.getProperty("environment"));
   }

   @Override
   public String url() {
      return System.getProperty("mongo_url");
   }

   @Override
   public String db() {
      return "notes";
   }
}
