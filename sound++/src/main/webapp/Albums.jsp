<%-- 
    Document   : Albums
    Created on : Feb 9, 2019, 2:14:52 AM
    Author     : maian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html
    xmlns="http://www.w3.org/1999/xhtml"
    xmlns:h="http://xmlns.jcp.org/jsf/html"
    xmlns:f="http://xmlns.jcp.org/jsf/core"
    xmlns:ui="http://xmlns.jcp.org/jsf/facelets">
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootswatch/4.2.1/lux/bootstrap.min.css" />
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
        <script src="resources/js/jquery-1.12.4.js" type="text/javascript"></script>
        <script src="resources/js/itemInfo.js" type="text/javascript"></script>
        <link href="resources/css/style.css" rel="stylesheet" type="text/css">
            <title>Sound++</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">

            <a class="navbar-brand" href="faces/index.xhtml"><img id="logo" src="assets/logo.png" alt="Logo of online music store Sound++"></img></a>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor03" aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarColor03">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="faces/index.xhtml">${internationalization.home}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/getAlbums">${internationalization.albums}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="">${internationalization.tracks}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="">${internationalization.about}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="">${internationalization.login}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="">${internationalization.cart}</a>
                    </li>
                </ul>

                <li class="nav-item dropdown">
                    <div class="form-group">
                        <form>
                            <select name="item" value="${internationalization.language}">
                                <option value="en">${internationalization.english}</option>
                                <option value="fr">${internationalization.french}</option>
                            </select>
                            <button class="btn btn-outline-primary btn-sm" value ="Change" action = "albums">Change</button>
                        </form>

                    </div>
                </li>

                <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" name="search" placeholder="${internationalization.search}"/>
                    <button class="btn btn-outline-primary" value ="${internationalization.search}" action = "search">${internationalization.search}</button>
                </form>
            </div>
        </nav>
        <div id="albumTable">
            <table class="table table-hover">
                <tr>
                    <th scope="col">${internationalization.albumimage}</th>
                    <th scope="col">${internationalization.albumtitle}</th>
                    <th scope="col">${internationalization.artist}</th>
                    <th scope="col">${internationalization.albumdate}</th>
                    <th scope="col">${internationalization.numberSongs}</th>
                    <th scope="col">${internationalization.cost}</th>
                </tr>

                <c:forEach items="${albumList}" var="album">
                    <tr class="table-secondary" id ="${album[0]}">

                        <th><img src="${album[11]}" alt="Album's cover" class="albumPic"/></th>
                        <td>${album[1]}</td>
                        <td>
                            <fmt:formatDate type="date" value ="${album[2]}" />
                        </td>
                        <td>${album[5]}</td>
                        <td>${album[6]}</td>
                        <td></td>

                    </tr>
                </c:forEach>


            </table> 
            <div>
                <ul class="pagination" >
                    <c:choose>
                        <c:when test="${currentPage != 1}">
                            <li class="page-item">
                                <a class="page-link" href="getAlbums?currentPage=${currentPage - 1}">&laquo;</a>
                            </li>
                        </c:when>    
                        <c:otherwise>
                            <li class="page-item disabled">
                                <a class="page-link" href="#">&laquo;</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach begin="1" end="${totalPage}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <li class="page-item active">
                                    <a class="page-link" href="#">${currentPage}</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link" href="getAlbums?currentPage=${i}">${i}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>


                    <c:choose>
                        <c:when test="${currentPage lt totalPage}">
                            <li class="page-item">
                                <a class="page-link" href="getAlbums?currentPage=${currentPage + 1}">&raquo;</a>
                            </li>
                        </c:when>    
                        <c:otherwise>
                            <li class="page-item disabled">
                                <a class="page-link" href="#">&raquo;</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div></div>
        <div class="card border-light mb-3" id ="info">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="#">Albums</a></li>
                <li class="breadcrumb-item active">Data</li>
            </ol>
            <div class="card-body">
                <p class ="imageContainer"><img src="" alt="Album's cover" id="albumCover"></p>
                <div class="contentContainer">
                    <h4 class="card-title" id ="title"></h4>
                    <p class="card-text" id = "artist">Updating</p>
                    <p class="card-text" id = "released_date"> </p>
                    <p class="card-text" id = "review">Updating</p>
                    <p class="card-text" id = "cost"></p>
                    <form class="form-inline my-2 my-lg-0">
                        <div class="form-group" class="col-sm-2 col-form-label">
                            <label for="quantity">Quantity</label>
                            <select name = "quantity" class="custom-select">
                                <option selected="1" value="1">1</option>
                                <c:forEach var = "i" begin = "2" end = "100">
                                    <option value="${i}">${i}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button class="btn btn-secondary my-2 my-sm-0" value ="${internationalization.inventoryadd}" action = "add">${internationalization.inventoryadd}</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
