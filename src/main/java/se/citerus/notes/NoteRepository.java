package se.citerus.notes;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.bson.types.ObjectId;
import se.citerus.notes.paas.ConnectionFactory;
import se.citerus.notes.paas.Connector;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoteRepository {

   private static NoteRepository INSTANCE;

   public static NoteRepository getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new NoteRepository(new ConnectionFactory().get());
      }
      return INSTANCE;
   }

   private final DB database;

   private NoteRepository(final Connector connector) {
      try {
         final MongoClient client = new MongoClient(new MongoClientURI(connector.url()));
         database = client.getDB(connector.db());
      } catch (final Exception exception) {
         throw new RuntimeException("failed to connect to " + connector.url() + "; " + exception.getMessage());
      }
   }

   public List<Note> findAll() {
      final List<Note> notes = new ArrayList<Note>();
      final DBCursor cursor = database.getCollection("notes").find().sort(new BasicDBObject("ts", -1));
      try {
         while (cursor.hasNext()) {
            final DBObject object = cursor.next();
            notes.add(new Note(object.get("_id").toString(), object.get("heading").toString(),
                    object.get("body").toString(), (Date) object.get("ts")));
         }
      } finally {
         cursor.close();
      }
      return notes;
   }

   public void put(final Note note) {
      database.getCollection("notes").insert(new BasicDBObject("heading", note.heading()).append("body", note.body())
              .append("ts", note.timestamp()));
   }

   public void delete(final String id) {
      database.getCollection("notes").remove(new BasicDBObject("_id", new ObjectId(id)));
   }
}
