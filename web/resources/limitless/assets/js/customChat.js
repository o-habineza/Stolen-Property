/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var dataLenders = [];
var dataTaxes = [];

$.getJSON("http://localhost:8080/gisa/chatDataServlet", function (result) {
    for (var i = 0; i < result.length; i++) {
        dataLenders.push({"label": result[i].x, "y": result[i].y});
    }

    var chart = new CanvasJS.Chart("chartContainerInvestment", {
        theme: "theme2",
        zoomEnabled: true,
        animationEnabled: true,
        title: {
            text: "Lenders per Week"
        },
        subtitles: [
            {
                text: "Weekly data"
            }
        ],
        axisY: {
            title: "Number of Lenders"
        },
        axisX: {
            title: "Weeks",
            interval: 1,
            intervalType: "week"
        },
        data: [{
                type: "line",
                dataPoints: dataLenders
            }]
    });
    chart.render();
});

$.getJSON("http://localhost:8080/gisa/chatDataServlet", function (result) {
    for (var i = 0; i < result.length; i++) {
        dataTaxes.push({"label": result[i].x, "y": result[i].y});
    }

    var chart2 = new CanvasJS.Chart("chartContainertaxes", {
        theme: "theme2",
        zoomEnabled: true,
        animationEnabled: true,
        title: {
            text: "Lenders per Week"
        },
        subtitles: [
            {
                text: "Weekly data"
            }
        ],
        axisY: {
            title: "Number of Lenders"
        },
        axisX: {
            title: "Weeks",
            interval: 1,
            intervalType: "week"
        },
        data: [{
                type: "line",
                dataPoints: dataTaxes
            }]
    });
    chart2.render();
});
//
//$(function () {
//    $.getJSON("http://localhost:8080/gisa/chatDataServlet", function (result2) {
//        var chart2 = new CanvasJS.Chart("chartContainertaxes", {
//            theme: "theme2",
//            zoomEnabled: true,
//            animationEnabled: true,
//            title: {
//                text: "Lenders per Week"
//            },
//            subtitles: [
//                {
//                    text: "Weekly data"
//                }
//            ],
//            axisY: {
//                title: "Number of Lenders",
//            },
//            axisX: {
//                title: "Weeks",
//                interval: 1
//            },
//            data: [{
//                    type: "line",
//                    dataPoints: result2
//                }]
//        });
//        chart2.render();
//    });
//});
//--------------------------------------------------------------------------------------------
