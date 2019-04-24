    $(function (){

    /*地图部分 */
    let map = new AMap.Map('container', {
        resizeEnable: true,
        zoom:12,
    })

    gdMap((res,map)=>{
        $(".load").css("display",'none')
        map.setCenter([res.position.lng,res.position.lat])
        var marker = new AMap.Marker({
            position: map.getCenter(),
            icon: 'http://a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png',
        });
    
        marker.setMap(map);
    
        // 设置鼠标划过点标记显示的文字提示
        marker.setTitle('我的位置');
    
        // 设置label标签
        // label默认蓝框白底左上角显示，样式className为：amap-marker-label
        marker.setLabel({
            offset: new AMap.Pixel(0, 10),  //设置文本标注偏移量
            content: "<div class='info'>我的位置", //设置文本标注内容
            direction: 'right' //设置文本标注方位
        });

        // 设置所有植物的位置标记
        //加载SimpleMarker
        AMapUI.loadUI(['overlay/SimpleMarker'], function(SimpleMarker) {
            //获取初始数据
            $.ajax({
                url:"curing/getAll?pageSize=9999&pageNum=1",
                type:"get",
                dataType:"json",
                contentType:"application/json;charset=UTF-8",
                success(res){
                    $(res.data).each((index,item)=>{
                        
                        var iconTheme = 'default';

                        //内置的样式
                        var iconStyles = SimpleMarker.getBuiltInIconStyles(iconTheme);

                        let marker =new SimpleMarker({
                            iconTheme: iconTheme,
                            //使用内置的iconStyle
                            iconStyle: parseInt(item.status)==0?iconStyles[13]:(parseInt(item.status)==1?"green":"red"),

                            //图标文字
                            iconLabel: {
                                //A,B,C.....
                                innerHTML: item.name,
                                style: {
                                    color: 'white'
                                }
                            },

                            //显示定位点
                            // showPositionPoint:true,

                            map: map,
                            position: [parseFloat(item.longitude||(Math.random()*112).toFixed(6)),parseFloat(item.latitude||(Math.random()*27).toFixed(6))],

                            //Marker的label(见https://lbs.amap.com/api/javascript-api/reference/overlay/#Marker)
                            // label: {
                            //     content: iconStyles[index],
                            //     offset: new AMap.Pixel(27, 25)
                            // }
                        });

                        //绑定事件
                        marker.on("click",temp)
                        //桥梁
                        function temp(e){
                            clickHandle(item)
                        }
                    })
                }
            })
        });
    })
    //高德地图获取位置函数
    function gdMap(success,error){
        map.plugin(["AMap.ControlBar"],function(){
            var controlBar = new AMap.ControlBar({
                position:{
                    right:'10px',
                    bottom:'0px'
                }
            })
            map.addControl(controlBar)
        });
        map.plugin(["AMap.Scale"],function(){
            var scale = new AMap.Scale();
            map.addControl(scale);   
        });

        map.plugin('AMap.Geolocation', function() {
        let geolocation = new AMap.Geolocation({
            // 是否使用高精度定位，默认：true
            enableHighAccuracy: true,
            // 设置定位超时时间，默认：无穷大
            timeout: 10000,
            //设置浏览器优先定位
            GeoLocationFirst:true,
            showMarker: true,
        })

        geolocation.getCurrentPosition()
        AMap.event.addListener(geolocation, 'complete',onComplete)
        AMap.event.addListener(geolocation, 'error', onError)

        function onComplete (data) {
            // data是具体的定位信息
            success(data,map)
        }

        function onError (data) {
            // 定位出错
            error(data)
        }
        })
}
let timeout;
//处理标注点击事件
function clickHandle(e){
    clearTimeout(timeout)
    $(".toast").css("display","block")
    $(".toast p").get(0).innerHTML="id:"+e.id;
    $(".toast p").get(1).innerHTML="name:"+e.name;
    timeout=setTimeout(function(){
        $(".toast").css("display","none")
    },3000)
}

})