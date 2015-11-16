<%@page session="false" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>SStestProject</title>

    <c:url var="home" value="/" scope="request"/>

    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>

    <spring:url value="/resources/core/js/jquery.1.10.2.min.js" var="jqueryJs"/>
    <script src="${jqueryJs}"></script>

    <spring:url value="/resources/core/js/script.js" var="scriptJs"/>
</head>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">SStestProject</a>
        </div>
    </div>
</nav>

<div class="container" style="min-height: 500px">

    <div class="starter-template">
        <h1>Available Data</h1>
        <br>

        <div id="feedback"></div>
        <ul id="dataList" class="list-group">
        </ul>

        <br>

        <form class="form-horizontal" id="data-form">
            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label">String Value</label>

                <div class="col-sm-10">
                    <input type=text class="form-control" id="stringValue">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" id="btn-add"
                            class="btn btn-success btn-lg">Add
                    </button>
                    <button type="submit" id="btn-update"
                            class="btn btn-warning btn-lg hidden"> Update
                    </button>
                </div>
            </div>
        </form>

        <div id="dataControls" class="hidden">
            <button type="button" id="btn-move-up"
                    class="btn btn-primary btn-lg">Move Up
            </button>
            <button type="button" id="btn-move-down"
                    class="btn btn-primary btn-lg">Move Down
            </button>
            <button type="button" id="btn-delete"
                    class="btn btn-danger btn-lg">Delete
            </button>
        </div>
    </div>
</div>

<br>

<div class="container">
    <footer>
        <p>
            3emluk
        </p>
    </footer>
</div>

<script src="${scriptJs}"></script>

</body>
</html>