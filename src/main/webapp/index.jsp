<%@ page import="se.citerus.notes.Note" %>
<%@ page import="se.citerus.notes.NoteRepository" %>
<%@ page import="se.citerus.notes.paas.ConnectionFactory" %>
<!DOCTYPE html>
<html>
<head><title>Notes</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/notes.css" rel="stylesheet" type="text/css">
    <link href="css/jquery-ui-1.10.2.custom.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container"><a class="brand" href="#">Notes!</a></div>
    </div>
</div>
<header>
    <div class="container"><img id="note" src="img/note.png">

        <div id="header-txt"><h1>NotesJAVA!</h1>

            <p class="lead">
                <small> Create and read awesome notes, right here!</small>
            </p>
        </div>
    </div>
</header>
<div class="container">
    <div class="row-fluid">
        <div class="span4">
            <div class="affix-top" data-offset-top="215" data-spy="affix" id="add-note">
                <form action="./controller" method="POST">
                    <fieldset><label>Heading</label><input id="heading" name="heading"
                                                           type="text"><label>Note</label>
                        <textarea id="body" name="body" rows="5"></textarea>
                        <button class="btn" type="submit">Create Note!</button>
                    </fieldset>
                </form>
            </div>
        </div>
        <div class="span8">
            <%
                for (final Note note : NoteRepository.getInstance().findAll()) {
            %>
            <div class="note">
                <div class="row-fluid">
                    <div class="span11">
                        <legend>
                            <%=note.heading()%>
                        </legend>
                    </div>
                    <div class="span1">
                        <div class="del">
                            <form action="./controller" method="POST">
                                <input id="_method" name="_method" type="hidden" value="DELETE">
                                <input id="id" name="id" type="hidden" value="<%=note.id()%>">
                                <button class="btn btn-mini btn-link" type="submit"><i class="icon-remove"></i></button>
                            </form>
                        </div>
                    </div>
                </div>
                <div>
                    <%=note.body()%>
                </div>
                <p class="text-info">
                    <small>
                        <%=String.format("%tFT%<tRZ", note.timestamp())%>
                    </small>
                </p>
            </div>
            <%
                }
            %>
        </div>
    </div>
</div>
<footer>
    <div class="container"><p class="muted credit">By <a href="http://www.citerus.se/">Citerus</a> <i
            class="icon-star-empty"></i> Styling support by <a href="http://twitter.github.com/bootstrap/index.html">Bootstrap. </a><i
            class="icon-star-empty"></i> Icons by <a href="http://glyphicons.com/">Glyphicons</a></p></div>
</footer>
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.2.custom.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/notes.js" type="text/javascript"></script>
</body>
</html>