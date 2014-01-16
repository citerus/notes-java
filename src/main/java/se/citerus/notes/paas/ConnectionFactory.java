package se.citerus.notes.paas;

import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ConnectionFactory {

   public Connector get() {
      for (final Connector connector : scan()) {
         System.out.println("validating connector: " + connector.getClass().getSimpleName());
         if (connector.available()) {
            System.out.println("successfully validated; using connector: " + connector + " " +
                    "(url: " + connector.url() + "; database: " + connector.db() + ")");
            return connector;
         }
      }
      System.out.println("no external connector available; using localhost");
      return new Localhost();
   }

   private List<Connector> scan() {
      final Reflections reflections = new Reflections("se.citerus.notes.paas");
      final Set<Class<? extends Connector>> classes = reflections.getSubTypesOf(Connector.class);
      final List<Connector> connectors = new ArrayList<Connector>();

      for (final Class<? extends Connector> aClass : classes) {
         try {
            if (aClass != Localhost.class) {
               connectors.add(aClass.newInstance());
            }
         } catch (final Exception exception) {
            throw new RuntimeException(exception);
         }
      }
      return connectors;
   }
}
