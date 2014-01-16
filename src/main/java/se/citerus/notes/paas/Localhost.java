package se.citerus.notes.paas;

public class Localhost implements Connector {

   @Override
   public boolean available() {
      throw new IllegalStateException("cannot call available on localhost connector");
   }

   @Override
   public String url() {
      return "mongodb://mongo:secret@localhost:27017/" + db();
   }

   @Override
   public String db() {
      return "notes";
   }
}

