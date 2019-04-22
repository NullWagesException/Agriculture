    $(function (){

    
    /*地图部分 */
    let map = new AMap.Map('container', {
        resizeEnable: true,
        zoom:12,
    })

    gdMap((res,map)=>{
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
        marker.on("click",clickHandle,map)
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

    //处理标注点击事件
function clickHandle(e){
    // 创建纯文本标记
//     var text = new AMap.Text({
//        text:'纯文本标记',
//        cursor:'pointer',
//        style:{
//            'padding': '.75rem 1.25rem',
//            'margin-bottom': '1rem',
//            'border-radius': '.25rem',
//            'background-color': 'white',
//            'width': '15rem',
//            'border-width': 0,
//            'box-shadow': '0 2px 6px 0 rgba(114, 124, 245, .5)',
//            'text-align': 'center',
//            'font-size': '20px',
//            'color': 'blue'
//        },
//        position: [e.lnglat.lng,e.lnglat.lat]
//    });
//    text.setMap(map);
}

})