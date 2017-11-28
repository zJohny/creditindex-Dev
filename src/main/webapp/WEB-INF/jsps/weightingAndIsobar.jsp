<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cxt" value="${pageContent.request.contentPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
    <title>行业信贷风险指数</title>
    <meta charset="UTF-8"/>
    <link href="${ctx}/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet">
    <link href="${ctx}/bootstrap/css/bootstrap-table.min.css" rel="stylesheet">
    <script src="${cxt}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${cxt}/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${cxt}/bootstrap/js/bootstrap-table-all.min.js"></script>
    <script src="${cxt}/bootstrap/js/bootstrap-table-zh-CN.js"></script>
    <script src="${cxt}/js/Echarts/echarts.js" type="text/javascript"></script>
    <script src="${cxt}/js/Echarts/indexUtil.js" type="text/javascript"></script>
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        td {
            white-space: nowrap;
            overflow: hidden;
            word-break: keep-all;
            max-width: 106px;
        }
        textarea { display:inline-block; margin-right:3px; vertical-align:middle;}
    </style>

</head>
<body style="background-color: #1E2131">
<div style="width:100%;">
    <div align="left" style="margin-left:10px;;margin-top:10px;">
        <span style="font-family:微软雅黑;font-size: 20px;color:#fff" id="index"><strong>信贷指数:</strong></span>
        <select id="selectTest" name="select" onchange="ch2()"
                style="width:150px;height: 32px;font-size: 16px;background-color:#1E2131;color:#fff">
            <option value="01" selected>等权</option>
            <option value="02">加权</option>
        </select>
    </div>
    <div style="width: 45%;margin-left:10px;margin-top:10px;">
        <div>
            <div>
                <div style="width:90%;height:20px;float:left;margin-left:650px;">
                    <div style="height: 392px;width: 760px;border-bottom:solid 1px #34394A; border-left:solid 1px #34394A; border-right:solid 1px #34394A; border-top:solid 1px #34394A">
                        <div align="left">
                            <label id="lable"
                                   style="font-family:微软雅黑;font-size: 18px;color:#fff"><strong>指数值(单位:bp)</strong></label>
                        </div>
                        <div id="tables">
                            <table class="table" id="table" style="width:740px;height:300px; border-top:none">
                                <thead>
                                <tr class="changeTr">
                                    <th></th>
                                    <th></th>
                                    <c:forEach items="${list}" var="t">
                                        <th>${t}</th>
                                    </c:forEach>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list2}" var="idri" varStatus="t">
                                    <tr class="changeTr">
                                        <td><a style="text-decoration: none"
                                               title="${idri.username}">${idri.username}</a>
                                        </td>
                                        <td>
                                            <a href='#'
                                               onclick='openLineWindon("${idri.id}","${idri.startTime}","${idri.endTime}","${idri.username}")'>
                                                <img src="../../img/app/chart.png">
                                            </a>
                                        </td>
                                        <c:forEach items="${bodyList}" var="beans">
                                            <c:if test="${beans.inducode eq idri.id}">
                                                <td class="text-center">
                                                        ${beans.idri}
                                                </td>
                                            </c:if>
                                        </c:forEach>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div id="weights" style="display:none;">
                            <table class="table" id="weight" style="width:740px;height:300px;">
                                <thead>
                                <tr class="changeTr">
                                    <th></th>
                                    <th></th>
                                    <c:forEach items="${list}" var="t">
                                        <th>${t}</th>
                                    </c:forEach>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list2}" var="idri" varStatus="t">
                                    <tr class="changeTr">
                                        <td>
                                            <a style="text-decoration: none"
                                               title="${idri.username}">${idri.username}</a>
                                        </td>
                                        <td>
                                            <a href='#'
                                               onclick='openLineWindon("${idri.id}","${idri.startTime}","${idri.endTime}")'>
                                                <img src="../../img/app/chart.png">
                                            </a>
                                        </td>
                                        <c:forEach items="${weighting}" var="w">
                                            <c:if test="${w.inducode eq idri.id}">
                                                <td class="text-center">
                                                        ${w.idri}
                                                </td>
                                            </c:if>
                                        </c:forEach>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div style="width: 620px;height: 392px; border-bottom:solid 1px #34394A; border-left:solid 1px #34394A; border-right:solid 1px #34394A; border-top:solid 1px #34394A">
            <div style="width:100%;" class="chart swiper-slide">
                <span style="margin-left:10px;font-weight:bold;font-family:微软雅黑;font-size: 18px;color:#fff"
                      id="exponent">行业信贷风险指数-等权</span>

                <div id="canvasEight" style="width: 620px;height:392px;"></div>
            </div>
        </div>
    </div>
    <div style="width: 45%;">
        <div style="margin-left:10px;margin-top:40px;">
            <div>
                <div style="width:20%;height:30px;float:left;margin-left:650px;">
                    <div style="width:760px;height:300px;border-bottom:solid 1px #34394A; border-left:solid 1px #34394A; border-right:solid 1px #34394A; border-top:solid 1px #34394A;">
                        <div id="toolbar" style="height:32px;float: right;position: relative;">
                            <select id="mySelect" name="s1" onchange="ch3()"
                                    style="width:150px;height: 32px;font-size: 16px;background-color:#1E2131;color:#fff">
                                <option value="yer">年同比</option>
                                <option value="months">月同比</option>
                                <option value="week" style="display:none;">周同比</option>
                                <option value="day" selected>日环比</option>
                            </select>
                        </div>
                        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
                        <div align="left" style="margin-left:10px;;margin-top:10px;">
                            <span style="font-family:微软雅黑;font-size: 20px;color:#fff" id="orderindex"><strong>八个行业信贷风险指数排名</strong></span>
                            <span style="font-family:微软雅黑;font-size: 14px;color:#fff" id="order">(排名越高风险越高)</span>
                        </div>

                        <div id="main" style="width: 700px;height:280px;">
                        </div>
                    </div>
                </div>
                <script type="text/javascript">
                    // 基于准备好的dom，初始化echarts实例
                    function loadData(code) {
                        var myChart = echarts.init(document.getElementById('main'));
                        var s1 = document.getElementsByName("s1")[0];
                        var timetype = s1.value;
                        var s2 = document.getElementsByName("select")[0];
                        var weighttype = s2.value;
                        var linkUrl = "/idri/queryIndexdateNew?Yoyg=" + timetype + "&weighttype=" + weighttype;
                        // 指定图表的配置项和数据
                        var option;
                        if (code == 'black') {
                            option = {
                                tooltip: {
                                    trigger: 'axis'//显示鼠标的到达区域的值
                                },
                                //图例
                                legend: {
                                    data: ['行业排名']
                                },
                                //X轴
                                xAxis: [{
                                    type: 'category',
                                    data: (function () {
                                        var arr = [];
                                        $.ajax({
                                            type: "post",
                                            async: false, //表示同步执行
                                            url: linkUrl,
                                            data: {},
                                            dataType: "json", //返回数据形式为json
                                            success: function (json) {
                                                if (json) {
                                                    for (var i = 0; i < json.length; i++) {
                                                        arr.push(json[i].inducode);
                                                    }
                                                }
                                            },
                                            error: function (errorMsg) {
                                                alert("不好意思,图表请求数据失败啦!");
                                                myChart.hideLoading();
                                            }
                                        })
                                        return arr;
                                    })(),
                                    axisTick: {
                                        alignWithLabel: true
                                    },
                                    // 控制网格线是否显示
                                    splitLine: {
                                        show: false,
                                        //  改变轴线颜色
                                        lineStyle: {
                                            // 使用深浅的间隔色
                                            color: '#34394A'
                                        }
                                    },
                                    //  改变x轴颜色
                                    axisLine: {
                                        lineStyle: {
                                            color: '#34394A'
//                            width:8,//这里是为了突出显示加上的，可以去掉
                                        }
                                    },
                                    //  改变x轴字体颜色和大小
                                    axisLabel: {
                                        showMinLabel: true,
                                        showMaxLabel: true,
                                        textStyle: {
                                            color: '#999'
                                        }
                                    }
                                }
                                ],
                                //Y轴
                                yAxis: {
                                    type: 'value',
                                    axisTick: {
                                        show: false
                                    },
                                    axisLine: {
                                        lineStyle: {
                                            color: '#34394A'
                                        }
                                    },
                                    splitLine: {
                                        show: true,
                                        //  改变轴线颜色
                                        lineStyle: {
                                            // 使用深浅的间隔色
                                            color: ['#34394A']
                                        }
                                    },
                                    axisLabel: {
                                        textStyle: {
                                            color: '#999'
                                        }
                                    }
                                },
                                //具体数据
                                series: [{
                                    name: '行业信贷风险指数',
                                    type: 'bar',//'bar'表示直方图; line 折线图
                                    barMaxWidth: '25',//柱形图的宽度设置
                                    itemStyle: {//设置柱形图的颜色
                                        normal: {
                                            color: '#0084fe'
                                        }
                                    },
                                    data: (function () {
                                        var arr = [];
                                        $.ajax({
                                            type: "post",
                                            async: false, //同步执行
                                            url: linkUrl,
                                            data: {},
                                            dataType: "json", //返回数据形式为json
                                            success: function (json) {
                                                if (json) {
                                                    for (var i = 0; i < json.length; i++) {
                                                        arr.push(json[i].idri);
                                                    }
                                                }
                                            },
                                            error: function (errorMsg) {
                                                alert("不好意思,图表请求数据失败啦!");
                                                myChart.hideLoading();
                                            }
                                        })
                                        return arr;
                                    })(),
                                    markPoint: {
                                        data: [
                                            {type: 'max', name: '最大值'},
                                            {type: 'min', name: '最小值'}
                                        ]
                                    },
                                    markLine: {
                                        data: [
                                            {type: 'average', name: '平均值'}
                                        ]
                                    }
                                }]
                            };
                        } else {
                            option = {
                                tooltip: {
                                    trigger: 'axis'//显示鼠标的到达区域的值
                                },
                                //图例
                                legend: {
                                    data: ['行业排名']
                                },
                                //X轴
                                xAxis: [{
                                    type: 'category',
                                    data: (function () {
                                        var arr = [];
                                        $.ajax({
                                            type: "post",
                                            async: false, //表示同步执行
                                            url: linkUrl,
                                            data: {},
                                            dataType: "json", //返回数据形式为json
                                            success: function (json) {
                                                if (json) {
                                                    for (var i = 0; i < json.length; i++) {
                                                        arr.push(json[i].inducode);
                                                    }
                                                }
                                            },
                                            error: function (errorMsg) {
                                                alert("不好意思,图表请求数据失败啦!");
                                                myChart.hideLoading();
                                            }
                                        })
                                        return arr;
                                    })(),
                                    splitLine: {
                                        show: false,
                                        //  改变轴线颜色
                                        lineStyle: {
                                            // 使用深浅的间隔色
                                            color: '#CACACA'
                                        }
                                    },
                                    //  改变x轴颜色
                                    axisLine: {
                                        lineStyle: {
                                            color: '#CACACA'
//                            width:8,//这里是为了突出显示加上的，可以去掉
                                        }
                                    },
                                    //  改变x轴字体颜色和大小
                                    axisLabel: {
                                        showMinLabel: true,
                                        showMaxLabel: true,
                                        textStyle: {
                                            color: '#555'
                                        }
                                    }
                                }
                                ],
                                //Y轴
                                yAxis: {
                                    type: 'value',
                                    axisTick: {
                                        show: false
                                    },
                                    axisLine: {
                                        lineStyle: {
                                            color: '#CACACA'
                                        }
                                    },
                                    splitLine: {
                                        show: true,
                                        //  改变轴线颜色
                                        lineStyle: {
                                            // 使用深浅的间隔色
                                            color: ['#CACACA']
                                        }
                                    },
                                    axisLabel: {
                                        textStyle: {
                                            color: '#555'
                                        }
                                    }
                                },
                                //具体数据
                                series: [{
                                    name: '行业信贷风险指数',
                                    type: 'bar',//'bar'表示直方图; line 折线图
                                    barMaxWidth: '25',//柱形图的宽度设置
                                    itemStyle: {//设置柱形图的颜色
                                        normal: {
                                            color: '#0084fe'
                                        }
                                    },
                                    data: (function () {
                                        var arr = [];
                                        $.ajax({
                                            type: "post",
                                            async: false, //同步执行
                                            url: linkUrl,
                                            data: {},
                                            dataType: "json", //返回数据形式为json
                                            success: function (json) {
                                                if (json) {
                                                    for (var i = 0; i < json.length; i++) {
                                                        console.log(json[i].context);
                                                        arr.push(json[i].idri);
                                                    }
                                                }
                                            },
                                            error: function (errorMsg) {
                                                alert("不好意思,图表请求数据失败啦!");
                                                myChart.hideLoading();
                                            }
                                        })
                                        return arr;
                                    })(),
                                    markPoint: {
                                        data: [
                                            {type: 'max', name: '最大值'},
                                            {type: 'min', name: '最小值'}
                                        ]
                                    },
                                    markLine: {
                                        data: [
                                            {type: 'average', name: '平均值'}
                                        ]
                                    }
                                }]

                            };
                        }
                        // 使用刚指定的配置项和数据显示图表。
                        myChart.setOption(option);
                    }
                </script>
            </div>
            <br>

        </div>
        <div style="width: 625px;height: 300px;margin-top:-20px;margin-left:10px;border-bottom:solid 1px #34394A; border-left:solid 1px #34394A; border-right:solid 1px #34394A; border-top:solid 1px #34394A">
            <span id="spa" style="font-family:微软雅黑;font-size: 18px;color: #fff"><strong>指数介绍</strong></span>
            <textarea class="textarea"  id="TextArea1" cols="60" rows="10" name="creditRisk" readonly="readonly"
                      style="color:#999;background:transparent;border-style:none;width: 600px;resize: none;">
       行业信贷风险指数(Industry Credit Risk Index)是衡量并描述国内8个大类行业综合债务风险的一套信用风险指数体系。 指数以A股全体上市企业为样本，依据证监会行业分类标准，基于算数平均法和债务加权法，自下而上进行指数计算。使用者可以通过指数的运算结果，从不同角度和侧重，分析各行业的信用风险水平与趋势，为国内信贷和债券市场提供了“风向标”和“指示器”。
       行业信贷风险指数的数据采集自公开市场交易、上市企业季报、年报等公开数据。指数成分样本的筛选和权重的计算循序《行业信贷风险指数的编制方法》，后经指数评审委员会最后确认形成。个体预期违约概率经流动性指标模型、Merton违约概率模型、“壳”价值修正模型等计算得出，并按相应计算公式得出最终指数。
            </textarea>
            <div id="down">
                <span style="font-size: 20px">下载:</span>
                <a href="/img/IDRICM.pdf" style="font-size: 16px" download>
                    <img src="../../img/app/pdf.png">编制方法.pdf
                </a><%--“http://localhost:8282”此域名可省，工程自行提供--%>
            </div>
        </div>
    </div>
    <div id="showLine" data-backdrop="static" class="modal col-md-6 col-md-offset-3"
         style="height:375px;background: #fff; margin-top: 70px; margin-bottom: 50px;overflow:hidden;" tabindex="-1"
         role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true;">
        <div class="modal-header" style="padding: 0px;border-bottom: none;">
            <img id="cls" src="../../img/app/closeblack.png" class="close" data-dismiss="modal"/>
        </div>
        <div class="modal-body">
            <div><label id="lables" style="margin-left:318px;font-family:微软雅黑;font-size: 15px;color: #fff">等权</label>
            </div>
            <div id="lineChartParent" style="width:100%;">
                <div id="canvas" style="width: 700px;height:280px;"></div>
            </div>
        </div>
    </div>
</div>
<script>
    var theme;$(function(){var color=theme;theme=window.localStorage.getItem("theme");skin(theme,true);loadData(theme)});function skin(code,clientFlag){w=$("#selectTest").val();if(code=="black"){showLine();$("tr.changeTr").css({backgroundColor:"#1E2131"});theme=code;loadData(code);document.body.style.backgroundColor="#1E2131";$("#table th").css({color:"#8dbff3"});$("#table tr td a").css({color:"#999"});$("#table tr td ").css({color:"#999"});$("#weight th").css({color:"#8dbff3"});$("#weight tr td a").css({color:"#999"});$("#weight tr td ").css({color:"#999"});$("#showLine").css({backgroundColor:"#34394A"});$("#lable").css({color:"#fff"});$("#user").css({color:"#fff"});$("#index").css({color:"#fff"});$("#spa").css({color:"#fff"});$("#down").css({color:"#fff"});$("#TextArea1").css({backgroundColor:"#1E2131"});$("#selectTest").css({backgroundColor:"#1E2131"});$("#mySelect").css({backgroundColor:"#1E2131"});$("#mySelect").css({color:"#fff"});$("#TextArea1").css({color:"#999"});$("#selectTest").css({color:"#fff"});$("#orderindex").css({color:"#fff"});$("#order").css({color:"#fff"});$("#lables").css({color:"#fff"});$("#exponent").css({color:"#fff"});$("#cls").attr("src","../../img/app/closeblack.png");$("tr").mouseover(function(){$(this).css("background-color","#2C364D")});$("tr").mouseout(function(){$(this).css("background-color","#1E2131")})}else{showLine();$("tr.changeTr").css({backgroundColor:"white"});theme=code;loadData(code);document.body.style.backgroundColor="white";$("#table tr td a").css({color:"#333"});$("#table tr td ").css({color:"#333"});$("#table th").css({color:"#222"});$("#weight tr td a").css({color:"#333"});$("#weight tr td ").css({color:"#333"});$("#weight th").css({color:"#222"});$("#lables").css({color:"#333"});$("tr.changeTr").css({backgroundColor:"white"});$("#showLine").css({backgroundColor:"#FFFFFF"});$("#lable").css({color:"#222"});$("#exponent").css({color:"#333"});$("#index").css({color:"#222"});$("#spa").css({color:"#222"});$("#down").css({color:"#222"});$("#TextArea1").css({color:"#333"});$("#selectTest").css({color:"#333"});$("#TextArea1").css({backgroundColor:"white"});$("#selectTest").css({backgroundColor:"white"});$("#mySelect").css({color:"#333"});$("#orderindex").css({color:"#333"});$("#order").css({color:"#333"});$("#mySelect").css({backgroundColor:"white"});$("#cls").attr("src","../../img/app/close.png");$("tr").mouseover(function(){$(this).css("background-color","#E5E5E5")});$("tr").mouseout(function(){$(this).css("background-color","white")})}}$("#selectTest").change(function(){showLine();w=$("#selectTest").val();if(w=="02"){$("#weights").show();$("#tables").hide()}else{$("#weights").hide();$("#tables").show()}});$("#weight").bootstrapTable();$("#table").bootstrapTable();function openLineWindon(id,startTime,endTime,username){var myChart=echarts.init(document.getElementById("canvas"));w=$("#selectTest").val();if(w=="02"){$("#lables").html("加权")}else{if(w=="01"){$("#lables").html("等权")}}$.ajax({url:"${ctx}/um/tradeLineChart",type:"post",cache:false,contentType:"application/json; charset=utf-8",data:JSON.stringify({id:id,creditorType:$("#selectTest").val(),startTime:startTime,endTime:endTime}),dataType:"json",success:function(trade){var minLine=(trade.minIdri*0.98).toFixed(1);console.log(minLine);console.log(trade);option={tooltip:{trigger:"axis"},legend:{left:"center",data:[trade.datasets[0].label],textStyle:{color:"#999"}},grid:{left:"5%",right:"14%",bottom:"7%"},xAxis:{type:"category",boundaryGap:false,data:trade.labels,axisTick:{alignWithLabel:true},splitLine:{show:false,lineStyle:{color:"#999"}},axisLine:{lineStyle:{color:"#999"}},axisLabel:{showMinLabel:true,showMaxLabel:true,textStyle:{color:"#999"}}},yAxis:{type:"value",axisTick:{show:false},axisLine:{lineStyle:{color:"#999"}},"min":minLine,splitLine:{show:true,lineStyle:{color:["#999"]}},axisLabel:{textStyle:{color:"#999"}}},series:[{itemStyle:{normal:{color:"#90CEFF"}},name:trade.datasets[0].label,type:"line",data:trade.datasets[0].data}]};myChart.setOption(option)}});$("#showLine").modal("show")}$("#showLine").on("hidden.bs.modal",function(){var myChart=echarts.init(document.getElementById("canvasEight"));myChart.clean()});function showLine(){var myChart=echarts.init(document.getElementById("canvasEight"));w=$("#selectTest").val();if(w=="02"){$("#exponent").text("行业信贷风险指数-加权")}else{if(w=="01"){$("#exponent").text("行业信贷风险指数-等权")}}$.ajax({url:"${ctx}/um/eightEchars?type="+w,type:"post",cache:false,contentType:"application/json; charset=utf-8",data:{},dataType:"json",success:function(data){if(theme=="white"){option={tooltip:{trigger:"axis"},legend:[{x:"center",data:[data.labels[0],data.labels[1],data.labels[2]],textStyle:{color:"#555"}},{x:"center",top:"4%",data:[data.labels[3],data.labels[4],data.labels[5]],textStyle:{color:"#555"}},{x:"center",top:"8%",data:[data.labels[6],data.labels[7]],textStyle:{color:"#555"}}],toolbox:{left:"right",feature:{dataZoom:{yAxisIndex:"none"},restore:{}}},grid:{left:"3%",right:"7%",bottom:"13%",containLabel:true},xAxis:{type:"category",boundaryGap:false,data:data.datasets[0].dateTime,axisTick:{alignWithLabel:true},splitLine:{show:false,lineStyle:{color:"#CACACA"}},axisLine:{lineStyle:{color:"#CACACA"}},axisLabel:{showMinLabel:true,showMaxLabel:true,textStyle:{color:"#555"}}},yAxis:{type:"value",axisTick:{show:false},axisLine:{lineStyle:{color:"#CACACA"}},splitLine:{show:true,lineStyle:{color:["#CACACA"]}},axisLabel:{textStyle:{color:"#555"}}},series:[{itemStyle:{normal:{color:"#9BD976"}},name:[data.labels[0]],type:"line",data:data.datasets[0].data},{itemStyle:{normal:{color:"#90CEFF"}},name:[data.labels[1]],type:"line",data:data.datasets[1].data},{itemStyle:{normal:{color:"#F2DB95"}},name:[data.labels[2]],type:"line",data:data.datasets[2].data},{itemStyle:{normal:{color:"#759CFF"}},name:[data.labels[3]],type:"line",data:data.datasets[3].data},{itemStyle:{normal:{color:"#62AE96"}},name:[data.labels[4]],type:"line",data:data.datasets[4].data},{itemStyle:{normal:{color:"#F1B080"}},name:[data.labels[5]],type:"line",data:data.datasets[5].data},{itemStyle:{normal:{color:"#BF7EF1"}},name:[data.labels[6]],type:"line",data:data.datasets[6].data},{itemStyle:{normal:{color:"#2B99FF"}},name:[data.labels[7]],type:"line",data:data.datasets[7].data}]}
    }else{option={tooltip:{trigger:"axis"},legend:[{x:"center",data:[data.labels[0],data.labels[1],data.labels[2]],textStyle:{color:"#fff"}},{x:"center",top:"4%",data:[data.labels[3],data.labels[4],data.labels[5]],textStyle:{color:"#fff"}},{x:"center",top:"8%",data:[data.labels[6],data.labels[7]],textStyle:{color:"#fff"}}],toolbox:{left:"right",feature:{dataZoom:{yAxisIndex:"none"},restore:{}}},grid:{left:"3%",right:"7%",bottom:"13%",containLabel:true},xAxis:{type:"category",boundaryGap:false,data:data.datasets[0].dateTime,axisTick:{alignWithLabel:true},splitLine:{show:false,lineStyle:{color:"#34394A"}},axisLine:{lineStyle:{color:"#34394A"}},axisLabel:{showMinLabel:true,showMaxLabel:true,textStyle:{color:"#999"}}},yAxis:{type:"value",axisTick:{show:false},axisLine:{lineStyle:{color:"#34394A"}},splitLine:{show:true,lineStyle:{color:["#34394A"]}},axisLabel:{textStyle:{color:"#999"}}},series:[{itemStyle:{normal:{color:"#9BD976"}},name:[data.labels[0]],type:"line",data:data.datasets[0].data},{itemStyle:{normal:{color:"#90CEFF"}},name:[data.labels[1]],type:"line",data:data.datasets[1].data},{itemStyle:{normal:{color:"#F2DB95"}},name:[data.labels[2]],type:"line",data:data.datasets[2].data},{itemStyle:{normal:{color:"#759CFF"}},name:[data.labels[3]],type:"line",data:data.datasets[3].data},{itemStyle:{normal:{color:"#62AE96"}},name:[data.labels[4]],type:"line",data:data.datasets[4].data},{itemStyle:{normal:{color:"#F1B080"}},name:[data.labels[5]],type:"line",data:data.datasets[5].data},{itemStyle:{normal:{color:"#BF7EF1"}},name:[data.labels[6]],type:"line",data:data.datasets[6].data},{itemStyle:{normal:{color:"#2B99FF"}},name:[data.labels[7]],type:"line",data:data.datasets[7].data}]}}myChart.setOption(option);$("#canvasEight").css("width",$("#canvasEight").width())}})};
</script>
</body>
</html>