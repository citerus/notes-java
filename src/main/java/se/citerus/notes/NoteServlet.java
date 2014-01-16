package se.citerus.notes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoteServlet extends HttpServlet {

   @Override
   public void doPost(final HttpServletRequest request, final HttpServletResponse response)
           throws IOException {
      final boolean isDelete = "DELETE".equals(request.getParameter("_method"));

      if (isDelete) {
         delete(request);
      } else {
         put(request);
      }
      response.sendRedirect("./");
   }

   private void delete(final HttpServletRequest request) {
      NoteRepository.getInstance().delete(request.getParameter("id"));
   }

   private void put(final HttpServletRequest request) {
      NoteRepository.getInstance().put(new Note(request.getParameter("heading"), request.getParameter("body")));
   }
}
