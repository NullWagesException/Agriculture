$(function(){
    let nowIndex = 0;//当前显示图片索引
    let AllWidth = $('.movingpicture').css("width")//获取轮播图宽度
    let interVal = setInterval(autoSwiper,3000)
    let currentSubId=''
    let subData={}//储存当前点击项的数据

    //轮播图左右箭头显示
    $('.movingpicture').hover(function(){
        $(".lblr").css("display",'flex')
        clearInterval(interVal)
    },function(){
        $(".lblr").css("display",'none')
        interVal = setInterval(autoSwiper,3000)
    })
    $(".lblr .left").hover(function(){
        $(".lblr").css("display",'flex')
        clearInterval(interVal)
    })

    //左右箭头控制轮播图
    $(".lblr .left").click(function(){
        autoSwiper()
        let that=this;
        $(this).css("pointer-events","none")
        setTimeout(function(){
            $(that).css("pointer-events","auto")
        },1000)

    })
    $(".lblr .right").click(function(){
        autoSwiper('-'+AllWidth)
    })

    //初始化图片位置
    $(".l").each((index,item)=>{
        if(index!=nowIndex){
            $('.l').eq(index).css("left",AllWidth)
        }
    })
    //初始化小点
    $(".l").each(index=>{
        let li =document.createElement("li")
        li.classList.add("dot_m")
        $(".dot").append(li)
    })
    $(".dot_m").get(nowIndex).id="current"

    //自动轮播

    function autoSwiper(allWidth=AllWidth){
        if(allWidth!=AllWidth){
            $(".l").each((index,item)=>{
                if(index!=nowIndex){
                    $('.l').eq(index).css("left",allWidth)
                }
            })
        }else{
            $(".l").each((index,item)=>{
                if(index!=nowIndex){
                    $('.l').eq(index).css("left",AllWidth)
                }
            })
        }
        $('.l').eq(nowIndex).animate({left:parseInt($($('.l')[nowIndex]).css("left"))-parseInt(allWidth)+"px"},1000,"linear",function(){
            $('.l').eq(nowIndex-1).css("left",allWidth)
        })
        $(".dot_m").get(nowIndex).id=""
        allWidth==AllWidth?nowIndex++:nowIndex--;
        if(nowIndex>=$('.l').length){
            nowIndex=0;
        }
        if(nowIndex<0){
            nowIndex=$('.l').length-1
        }
        $(".dot_m").get(nowIndex).id="current"
        $('.l').eq(nowIndex).animate({left:parseInt($($('.l')[nowIndex]).css("left"))-parseInt(allWidth)+"px"},1000,"linear")
    }

    //初始化新闻
    for(let i=0;i<5;i++){
        let html = `
        <input type="hidden" value="${i}">
        <img style="float: left;width: 200px;" src="https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2804043051,2117814611&fm=26&gp=0.jpg" alt="">
        <h1>听闻爱情</h1>
        <p class="date">1999/1/2</p>
        生活星期天早上和朋友一起聊天，朋友说了一个他们听过的故事：“一尊佛像前有一条铺着石板的路，人们每天都踏着这一阶一阶的石板去膜拜佛像。石阶看着人们踏着自己去膜拜佛像，心里很不舒服。石阶心里想，自己和佛本来就来自同一块石头，为什么自己要成为踏脚石，让人们踩着自去去膜拜它呢！它对佛抱怨说这样太不公平！佛像说：这没有什么不公平，你们成为台阶只需挨了四刀，而我是挨了千刀万剐才成了人们膜拜的佛像。”
　　他说这个故事给留下非常深刻的印象——人确实需要磨练才能成才，不一样的付出，当然收获不一样的结果。

　　“自古雄才多磨难，从来纨绔少伟男，”“宝剑锋从磨砺出，梅花香自苦寒来，“一个人要想成为人上之人，必须吃得苦中之苦。只要有抱负，就去努力。不努力不付出，石阶永远不可能变成佛像的！
        `
        let li = document.createElement("li")
        li.innerHTML=html;
        $(li).click(function(){
            $("body").css("overflow","hidden")
            $('.menceng').css({display:"block"})
            currentSubId=$(this).children().get(0).value
        })
        $(".des").append(li);
    }

    //修改按钮绑定提交
    $(".menceng .toast button").eq(0).click(function(){
        $("body").css("overflow","auto")
        $('.menceng').css({display:"none"})
        $(this).parent().siblings().each((index,item)=>{
            subData[$(item).children().get(0).name]= $(item).children().eq(0).val()
            $(item).children().get(0).value=''
        })
        subData['id']=currentSubId;
    })
    $(".menceng .toast button").eq(1).click(function(){
        $("body").css("overflow","auto")
        $('.menceng').css({display:"none"})
        $(this).parent().siblings().each((index,item)=>{
            $(item).children().get(0).value=''
        })
    })
})