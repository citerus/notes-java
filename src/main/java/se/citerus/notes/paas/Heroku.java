package se.citerus.notes.paas;

public class Heroku implements Connector {

   @Override
   public boolean available() {
      return System.getenv("MONGOHQ_URL") != null;
   }

   @Override
   public String url() {
      return System.getenv("MONGOHQ_URL");
   }

   @Override
   public String db() {
      return url().substring(url().lastIndexOf("/") + 1);
   }
}
