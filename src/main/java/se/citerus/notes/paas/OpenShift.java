package se.citerus.notes.paas;

public class OpenShift implements Connector {

   @Override
   public boolean available() {
      return false;
   }

   @Override
   public String url() {
      return "TODO";
   }

   @Override
   public String db() {
      return "TODO";
   }
}