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
        <link rel="stylesheet" type="text/css" href="RecPageSS.css">
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,500,600" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Recommendations</title>
        <% String UserId = request.getParameter("UserId");%> 
        <script type="text/javascript">
            var xmlhttp;
            function createXMLHttpObject() {
                if (window.XMLHttpRequest) {
                    xmlhttp = new XMLHttpRequest();
                } else if (window.ActiveXObject) {
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                return xmlhttp;
            }
            function loadRecInfo() {
                xmlhttp = createXMLHttpObject();
                var url = "TestServlet?UserId=<%=UserId%>";
                xmlhttp.onreadystatechange = callback;
                xmlhttp.open("GET", url, true);
                xmlhttp.send();
            }
            function callback() {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    console.log("hi");
                    var mvlist = xmlhttp.responseXML.getElementsByTagName("movie");
                    for (var i = 0; i < mvlist.length; i++) {
                        var amv = mvlist[i];
                        var mid = mvlist[i].childNodes[0].innerHTML;
                        var mtitle = amv.childNodes[1].firstChild.nodeValue;//u
                        var myear = amv.childNodes[2].innerHTML;//u
                        var msrch = amv.childNodes[3].innerHTML;//u
                        var msrcv = amv.childNodes[4].innerHTML;//u
                        var psrc;
                        if(i%2==0){
                            psrc = msrch;
                        } else {
                            psrc = msrcv;
                        }
                        if(psrc=="null"){
                            psrc = "noposter.png";
                        }                                              
                        var mduration = amv.childNodes[5].innerHTML;
                        var moverview = amv.childNodes[6].innerHTML;
                        //poster+title+year+duration
                        var bx = document.createElement("div");
                        bx.setAttribute("class", "box");
                        var show = document.createElement("div");
                        show.setAttribute("class","original");
                        
                        var img = document.createElement("img");//poster
                        img.setAttribute("src", psrc);

                        var mvtt = document.createElement("h4");//title
                        var icon = document.createElement("i");
                        icon.setAttribute("class","fa fa-film fa-lg");
                        mvtt.appendChild(icon);
                        mvtt.appendChild(document.createTextNode(" "+mtitle));
                        var mvinfo = document.createElement("p");//yaer+duration
                        mvinfo.appendChild(document.createTextNode("Released: " + myear));
                        var md = document.createElement("p");
                        md.appendChild(document.createTextNode("Duration: " + mduration+ " min"));
                        //overview+link
                        var hid = document.createElement("div");
                        hid.setAttribute("class", "overlay");
                        var hidbut = document.createElement("a");
                        hidbut.setAttribute("class","btn");
                        hidbut.setAttribute("href","/movie.jsp/?MovieId="+mid);
                        hidbut.appendChild(document.createTextNode("iMovie"));
                        hidbut.appendChild(document.createElement("span"));
                        var hidtx = document.createElement("span");
                        hidtx.setAttribute("class","text");
                        hidtx.appendChild(document.createTextNode(moverview));
                        hid.appendChild(hidtx);
                        hid.appendChild(hidbut);
                        //attaching
                        show.appendChild(img);
                        show.appendChild(mvtt);
                        show.appendChild(mvinfo);
                        show.appendChild(md);
                        bx.appendChild(show);
                        bx.appendChild(hid);
                        document.getElementById("columns").appendChild(bx);
                    }
                } else
                    console.log("oh no");
            }
        </script>

    </head>

    <body onload="loadRecInfo()">
        <div class="header">
            <h1><span id="its">It's</span><span id="mvtime">Movie Time</span></h1>
        </div>
        <div id="wrapper">
            <div id="columns"></div>
        </div>
    </body>
</html>
