package se.citerus.notes.paas;

public interface Connector {

   /**
    * @return determines whether connector is applicable within the current runtime.
    */
   boolean available();

   /**
    * @return database url
    */
   String url();

   /**
    * @return database name
    */
   String db();
}
