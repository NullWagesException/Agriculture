
// Change Chart type function
function ChangeChartType(chart, series, newType) {
    newType = newType.toLowerCase();
    for (var i = 0; i < series.length; i++) {
        var srs = series[0];
        try {
            srs.chart.addSeries({
                type: newType,
                stack: srs.stack,
                yaxis: srs.yaxis,
                name: srs.name,
                color: srs.color,
                data: srs.options.data
            },
            false);
            series[0].remove();
        } catch (e) {
        }
    }
}

// Two charts definition
var chart1, chart2;

// Once DOM (document) is finished loading
$(document).ready(function() {

    $.get('http://192.168.1.54:8080/agri/curing/getTime',function(res){
        $(".load").css("display",'none')
        let timeArr=[]
        let numArr=[]
        let temp=0
        for(let i in res){
            timeArr.push(i);
            temp+=res[i]
            numArr.push(temp)
        }
        // First chart initialization
        chart1 = new Highcharts.Chart({
            chart: {
            renderTo: 'chart_1',
            type: 'column',
            height: 350,
            },
            title: {
            text: '每日任务增长曲线图'
            },
            xAxis: {
            categories: timeArr
            },
            yAxis: {
            title: {
                text: '增长数目'
            }
            },
            series: [{
            name: '养护任务增长曲线',
            data: numArr
            }]
        });
        ChangeChartType(chart1, chart1.series, 'line');
    })

    // Second chart initialization (pie chart)
    $.get('http://192.168.1.54:8080/agri/curing/getScale',function(res){
        console.log(res)
        chart2 = new Highcharts.Chart({
            chart: {
                renderTo: 'chart_2',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                height: 350,
            },
            title: {
                text: '所有植物养护信息统计饼图'
            },
            tooltip: {
                pointFormat: '<b>{point.percentage}%</b>',
                percentageDecimals: 1
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },
             series: [{
             type: 'pie',
                name: 'Dev #1',
                data: [
                    ['良好', res[1]],
                    ['不良', res[2]],
                    ['养护中', res[0]],
                ]
             }]
        });
    })
    
});