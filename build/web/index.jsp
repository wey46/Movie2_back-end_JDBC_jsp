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
                    console.log("hi");
                    var mvlist = xmlhttp.responseXML.getElementsByTagName("movie");                  
                    for(var i=0; i<mvlist.length;i++){
                        var amv = mvlist[i];   
                        var mtitle = amv.childNodes[1].firstChild.nodeValue;//u
                        var myear = amv.childNodes[2].innerHTML;//u
                        var msrch = amv.childNodes[3].innerHTML;//u
                        var msrcv = amv.childNodes[4].innerHTML;//u
                        //var psrc = msrch == "null"? (msrcv=="null"? "noposter.png":msrcv):msrch;
                        var psrc = msrch;
                        var mduration = amv.childNodes[5].innerHTML;
                        var moverview = amv.childNodes[6].innerHTML;
                        //poster+title+year+duration
                        var bx = document.createElement("div");
                        bx.setAttribute("class","box");
                        
                        var img = document.createElement("img");//poster
                        img.setAttribute("src",psrc);
                        
                        var mvtt = document.createElement("h4");//title
                        mvtt.appendChild(document.createTextNode(mtitle));
                        var mvinfo = document.createElement("p");//yaer+duration
                        mvinfo.appendChild(document.createTextNode("Released: " + myear));
                        var md = document.createElement("p");
                        md.appendChild(document.createTextNode("Duration: "+mduration)); 
                        //overview+link
                        var hid = document.createElement("div");
                        hid.setAttribute("class","overlay");
                        var overvue = document.createElement("p");
                        overvue.appendChild(document.createTextNode(moverview));
                        hid.appendChild(overvue);
                        //attaching
                        bx.appendChild(img);
                        bx.appendChild(mvtt);
                        bx.appendChild(mvinfo);
                        bx.appendChild(md);
                        bx.appendChild(hid);
                        document.getElementById("columns").appendChild(bx);
                    }                               
                } else console.log("oh no");             
            }
        </script>
   
    </head>
    
    <body onload="loadRecInfo()">
        <div class="header">
            <h1>Recommendations</h1>
        </div>
        <div id="wrapper">
            <div id="columns"></div>
        </div>
        
        
    </body>
</html>
