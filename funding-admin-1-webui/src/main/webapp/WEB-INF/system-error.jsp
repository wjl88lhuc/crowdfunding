<%--
  Created by IntelliJ IDEA.
  User: wenjunlong8
  Date: 2020/1/16 0016
  Time: 2:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>❤汇聚点滴的力量，成就非凡的伟业❤</title>
    <!--所有域路径（包括control路径，如xxx.html）相关的，前面都会默认加入base标签的href的路径，-->
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/carousel.css">
    <style>
        h3 {
            font-weight:bold;
        }
        #footer {
            padding: 15px 0;
            background: #fff;
            border-top: 1px solid #ddd;
            text-align: center;
        }
        #topcontrol {
            color: #fff;
            z-index: 99;
            width: 30px;
            height: 30px;
            font-size: 20px;
            background: #222;
            position: relative;
            right: 14px !important;
            bottom: 11px !important;
            border-radius: 3px !important;
        }
        #topcontrol:after {
            /*top: -2px;*/
            left: 8.5px;
            content: "\f106";
            position: absolute;
            text-align: center;
            font-family: FontAwesome;
        }
        #topcontrol:hover {
            color: #fff;
            background: #18ba9b;
            -webkit-transition: all 0.3s ease-in-out;
            -moz-transition: all 0.3s ease-in-out;
            -o-transition: all 0.3s ease-in-out;
            transition: all 0.3s ease-in-out;
        }
        /* 侧栏导航 */
        .sideBox{padding:10px;height:220px;background:#fff;margin-bottom:10px;overflow:hidden;}
        .sideBox .hd{height:30px; line-height:30px; background:#f60; padding:0 10px;text-align:center;overflow:hidden;}
        .sideBox .hd .more{color:#fff;}
        .sideBox .hd h3 span{font-weight:bold; font-size:14px;color:#fff;}
        .sideBox .bd{padding:5px 0 0;}
        #sideMenu .bd li{margin-bottom:2px; height:30px; line-height:30px;text-align:center; overflow:hidden;}
        #sideMenu .bd li a{display:block;background:#EAE6DD;}
        #sideMenu .bd li a:hover{background:#D5CFBF;}
        /* 列表页 */
        #mainBox{margin-bottom:10px;padding:10px;background:#fff;overflow:hidden;}
        #mainBox .mHd{border-bottom:2px solid #09c;height:30px;line-height:30px;}
        #mainBox .mHd h3{display:initial;*display:inline;zoom:1;padding:0 15px;background:#09c;color:#fff;}
        #mainBox .mHd h3 span{color:#fff;font-size:14px;font-weight:bold;}
        #mainBox .path{float:right;}
        /* 位置导航 */
        .path{ height:30px; line-height:30px; padding-left:10px;}
        .path a,.path span{ margin:0 5px;}
        /* 文章列表 */
        .newsList{padding:10px;text-align:left;}
        .newsList li{background:url("../images/share/point.png") no-repeat 2px 14px; padding-left:10px;height:30px; line-height:30px;}
        .newsList li a{display:inline-block;*display:inline;zoom:1;font-size:14px;}
        .newsList li .date{float:right; color:#999;}
        .newsList li.split{margin-bottom:10px;padding-bottom:10px;border-bottom:1px dotted #ddd;height:0px;line-height:0px;overflow:hidden;}
        /* 通用带图片的信息列表_普通式 */
        .picList{padding:10px;text-align:left;}
        .picList li{margin:0 5px;height:190px;}
        h3.break {
            font-size:16px;
            display: block;
            white-space: nowrap;
            word-wrap: normal;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        h3.break>a {
            text-decoration:none;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a>
        </div>
    </div>
</nav>
<br/>
<br/>
<br/>
<br/>
<br/>
<div class="container">
    <h1>${requestScope.exception.message}</h1>
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#backBtn").click(function () {
                window.history.back();
            })
        })
    </script>
    <button id="backBtn" class="btn btn-lg btn-success btn-block">后退</button>
</div>
</body>
</html>
