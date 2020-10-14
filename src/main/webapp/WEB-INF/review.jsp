<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Games and Genres</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
    <script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">    
        <div class="row">
            <div class="col-sm-4">
                <div class="card">
                    <div class="card-header bg-dark text-light"><h2>${someGame.title}</h2></div>
                    <div class="card-body">
                        <p>Studio: ${someGame.studio}</p>
                        <p>Year: ${someGame.year}</p>
                        <p>Genres: ${someGame.genreDescription()}</p>
                        <p>Average Rating: ...</p>
                    </div>
                </div>
            </div>
            <div class="col-sm-8">
                <div class="card">
                    <div class="card-header bg-dark text-light"><h2>Reviews</h2></div>
                    <div class="card-body">
                        <form:form action="/games/${someGame.id}/review" method="post" modelAttribute="newReview">
                            <div class="row">
                                <div class="col-sm-4 form-group">
                                    <label>Rating</label>
                                    <select name="rating" class="form-control">
                                        <option>5</option>
                                        <option>4</option>
                                        <option selected>3</option>
                                        <option>2</option>
                                        <option>1</option>
                                    </select>
                                </div>
                                <div class="col-sm-8 form-group">
                                    <label>Review</label>
                                    <form:textarea path="content" class="form-control"></form:textarea>
                                    <form:errors path="content" class="text-danger" />
                                </div>
                            </div>
                            <input type="submit" value="Review ${someGame.title}" class="btn btn-primary btn-block" />
                        </form:form>
                    </div>
                </div>
           </div>
        </div>
    </div>
</body>
</html>