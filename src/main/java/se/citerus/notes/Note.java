package se.citerus.notes;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable {

   private transient String id;

   private final String heading;
   private final String body;
   private final Date ts;

   public Note(final String heading, final String body) {
      this(null, heading, body, new Date());
   }

   public Note(final String id, final String heading, final String body, final Date ts) {
      this.id = id;
      this.heading = heading;
      this.body = body;
      this.ts = ts;
   }

   public String id() {
      return id;
   }

   public String heading() {
      return heading;
   }

   public String body() {
      return body;
   }

   public Date timestamp() {
      return ts;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Note)) return false;

      Note note = (Note) o;

      if (id != null ? !id.equals(note.id) : note.id != null) return false;
      if (ts != null ? !ts.equals(note.ts) : note.ts != null) return false;

      return true;
   }

   @Override
   public int hashCode() {
      int result = id != null ? id.hashCode() : 0;
      result = 31 * result + (ts != null ? ts.hashCode() : 0);
      return result;
   }

   @Override
   public String toString() {
      return "Note{" +
              "id='" + id + '\'' +
              ", heading='" + heading + '\'' +
              ", body='" + body + '\'' +
              ", ts=" + ts +
              '}';
   }
}
