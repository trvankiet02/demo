<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url value="/resources/fe/" var="URL"></c:url>
<c:url value="/resources/" var="URL1"></c:url>

<!DOCTYPE html>
<html lang="fr">
<head>
<!-- Site meta -->
<title>Sản Phẩm</title>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="Colo Shop Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="${URL}styles/bootstrap4/bootstrap.min.css">
    <link href="${URL}plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="${URL}plugins/OwlCarousel2-2.2.1/owl.carousel.css">
    <link rel="stylesheet" type="text/css" href="${URL}plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
    <link rel="stylesheet" type="text/css" href="${URL}plugins/OwlCarousel2-2.2.1/animate.css">
    <link rel="stylesheet" type="text/css" href="${URL}plugins/jquery-ui-1.12.1.custom/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="${URL}styles/categories_styles.css">
    <link rel="stylesheet" type="text/css" href="${URL}styles/categories_responsive.css">

   

</head>
<body>

 	<%@ include file="/common/category/header.jsp"%>

	


	<!-- body -->
	<sitemesh:write property='body'></sitemesh:write>
	<!-- body -->

	<!--=== Footer v4 ===-->
	
 	<jsp:include page="/common/category/footer.jsp"></jsp:include> 

	<!--=== End Footer v4 ===-->
   
    <script src="${URL}js/jquery-3.2.1.min.js"></script>
    <script src="${URL}styles/bootstrap4/popper.js"></script>
    <script src="${URL}styles/bootstrap4/bootstrap.min.js"></script>
    <script src="${URL}plugins/Isotope/isotope.pkgd.min.js"></script>
    <script src="${URL}plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
    <script src="${URL}plugins/easing/easing.js"></script>
    <script src="${URL}plugins/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
    <script src="${URL}js/categories_custom.js"></script>
    <script src="${URL}js/single_custom.js "></script>
</body>
</html>