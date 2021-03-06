<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Zaplanuj Jedzonko</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
          rel="stylesheet">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
</head>

<body>
<%@ include file="fragments/dashHeader.jspf"%>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@include file="fragments/dashSideMenu.jspf"%>

        <div class="m-4 p-4 width-medium">
            <div class="dashboard-header m-4">
                <div class="dashboard-menu">
                    <div class="menu-item border-dashed">
                        <a href="">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <a href="/app/recipe/add"><span class="title">dodaj przepis</span></a>
                        </a>
                    </div>
                    <div class="menu-item border-dashed">
                        <a href="">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <a href="/app/plan/add"><span class="title">dodaj plan</span></a>
                        </a>
                    </div>
                    <div class="menu-item border-dashed">
                        <a href="">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <a href="/app/recipe/plan/add"><span class="title">dodaj przepis do planu</span></a>
                        </a>
                    </div>
                </div>

                <div class="dashboard-alerts">
                    <div class="alert-item alert-info">
                        <i class="fas icon-circle fa-info-circle"></i>
                        <span class="font-weight-bold">Liczba przepisów: ${countRecipe} </span>
                    </div>
                    <div class="alert-item alert-light">
                        <i class="far icon-calendar fa-calendar-alt"></i>
                        <span class="font-weight-bold">Liczba planów: ${countPlan}</span>
                    </div>
                </div>
            </div>
            <div class="m-4 p-4 border-dashed">
                <h2 class="dashboard-content-title">
                    <span>Ostatnio dodany plan:</span> ${plan.name}
                </h2>
                <table class="table">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2">Poniedziałek</th>
                        <th class="col-8"></th>
                        <th class="col-2"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pon}" var="pon">
                    <tr class="d-flex">
                        <td class="col-2">${pon.meal_name}</td>
                        <td class="col-8">${pon.recipe_name}</td>
                        <td class="col-2">
                            <a href="/app/recipe/details?id=${pon.recipe_id}" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                        </td>
                    </tr>

                </c:forEach>
                </table>
                <table class="table">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2">Wtorek</th>
                        <th class="col-8"></th>
                        <th class="col-2"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${wt}" var="wt">
                    <tr class="d-flex">
                        <td class="col-2">${wt.meal_name}</td>
                        <td class="col-8">${wt.recipe_name}</td>
                        <td class="col-2">
                            <a href="/app/recipe/details?id=${wt.recipe_id}" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
                <table class="table">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2">Środa</th>
                        <th class="col-8"></th>
                        <th class="col-2"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${sr}" var="sr">
                    <tr class="d-flex">
                        <td class="col-2">${sr.meal_name}</td>
                        <td class="col-8">${sr.recipe_name}</td>
                        <td class="col-2">
                            <a href="/app/recipe/details?id=${sr.recipe_id}" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
                <table class="table">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2">Czwartek</th>
                        <th class="col-8"></th>
                        <th class="col-2"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${czw}" var="czw">
                    <tr class="d-flex">
                        <td class="col-2">${czw.meal_name}</td>
                        <td class="col-8">${czw.recipe_name}</td>
                        <td class="col-2">
                            <a href="/app/recipe/details?id=${czw.recipe_id}" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
                <table class="table">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2">Piątek</th>
                        <th class="col-8"></th>
                        <th class="col-2"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pt}" var="pt">
                    <tr class="d-flex">
                        <td class="col-2">${pt.meal_name}</td>
                        <td class="col-8">${pt.recipe_name}</td>
                        <td class="col-2">
                            <a href="/app/recipe/details?id=${pt.recipe_id}" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
                <table class="table">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2">Sobota</th>
                        <th class="col-8"></th>
                        <th class="col-2"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${sb}" var="sb">
                    <tr class="d-flex">
                        <td class="col-2">${sb.meal_name}</td>
                        <td class="col-8">${sb.recipe_name}</td>
                        <td class="col-2">
                            <a href="/app/recipe/details?id=${sb.recipe_id}" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
                <table class="table">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2">Niedziela</th>
                        <th class="col-8"></th>
                        <th class="col-2"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${nd}" var="nd">
                    <tr class="d-flex">
                        <td class="col-2">${nd.meal_name}</td>
                        <td class="col-8">${nd.recipe_name}</td>
                        <td class="col-2">
                            <a href="/app/recipe/details?id=${nd.recipe_id}" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</section>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>