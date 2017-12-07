<%-- 
    Document   : index
    Created on : Nov 24, 2017, 12:15:36 PM
    Author     : WY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recommendations</title>
         <% String UserId = request.getParameter("UserId"); %> 
        <script type="text/javascript">
            var xmlhttp;
            //var row0 = document.getElementsByClassName("column")[0];
            //var row1 = document.getElementsByClassName("column")[1];
            //var row2 = document.getElementsByClassName("column")[2];
            //var row3 = document.getElementsByClassName("column")[3];
            function createXMLHttpObject(){
                if(window.XMLHttpRequest){
                    xmlhttp = new XMLHttpRequest();
                }else if(window.ActiveXObject){
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                return xmlhttp;
            }
            function loadRecInfo(){
                xmlhttp = createXMLHttpObject();
                var url = "TestServlet?UserId=<%=UserId%>";
                xmlhttp.onreadystatechange = callback;
                xmlhttp.open("GET",url,true);
                xmlhttp.send();
            }
            function callback(){
                if(xmlhttp.readyState==4 && xmlhttp.status==200){
                    var list = xmlhttp.responseXML.getElementsByTagName("posterv");                   
                    for(var i=0; i<list.length;i++){
                        var src = list[i].childNodes[0].nodeValue;
                        if(src=="null") {src ="noposter.png" }
                        var m = document.createElement("img");
                        m.setAttribute("src",src);
                        m.setAttribute("style","width:100%");
                        document.getElementsByClassName("column")[i%4].appendChild(m);
                    }                               
                }              
            }
        </script>
        <style>
            * {
                box-sizing: border-box;
              }

            body {
                margin: 0;
                font-family: Arial;
            }

            .header {
                text-align: center;
                padding: 32px;
            }

/* Create four equal columns that floats next to each other */
            .column {
                float: left;
                width: 25%;
                padding: 10px;
            }

            .column img {
                margin-top: 12px;
            }

/* Clear floats after the columns */
            .row:after {
                content: "";
                display: table;
                clear: both;
            }

/* Responsive layout - makes a two column-layout instead of four columns */
@media (max-width: 800px) {
    .column {
        width: 50%;
    }
}

/* Responsive layout - makes the two columns stack on top of each other instead of next to each other */
@media (max-width: 600px) {
    .column {
        width: 100%;
    }
}
</style>
    </head>
    
    <body onload="loadRecInfo()">
        <div class="header">
            <h1>Recommendations</h1>
        </div>
        <div class="row">
            <div class="column"></div>
            <div class="column"></div>
            <div class="column"></div>
            <div class="column"></div>
        </div>
        
        
    </body>
</html>
