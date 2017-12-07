<%-- 
    Document   : newjsp
    Created on : Dec 4, 2017, 4:09:49 PM
    Author     : WY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
<head>
<style>
.container {
    position: relative;
    font-family: Arial;
}

.text-block {
    transition: 1s ease;
    opacity: 0;
    font-size: 24px;
    position: absolute;
    bottom: 20px;
    right: 20px;
    background-color: black;
    color: white;
    width: 80%;
    height: 25%;
    padding-left: 20px;
    padding-right: 20px;
}
.container:hover .image {
  opacity: 0.3;
}

.container:hover .text-block {
  opacity: 0.7;
}
</style>
</head>
<body>

<h2>Image Text Blocks</h2>
<p>How to place text blocks over an image:</p>

<div class="container">
  <img clss ="image" src="https://image.tmdb.org/t/p/w640/3YBcjvU88Q990Ow1fbya29Ph5kb.jpg" alt="Nature" style="width:100%;">
  <div class="text-block">
    <h4>The Title of the Movie</h4>
    <p>Actor Adam, Actress Eva</p>
  </div>
</div>

</body>
</html> 
