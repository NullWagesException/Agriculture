$(function(){
    let nowIndex = 0;//当前显示图片索引
    let AllWidth = $('.movingpicture').css("width")//获取轮播图宽度
    let interVal = setInterval(autoSwiper,3000)
    let currentSubId=''
    let subData={}//储存当前点击项的数据

    //初始化轮播图
    $.ajax({
        url:"images/getAllImages",
        type:"get",
        dataType:"json",
        contentType:"application/json;charset=UTF-8",
        success(res){
            $(res).each((index,item)=>{
                let html=
                `
                <img src=${item.imagepath} alt="">
                `
                let div=document.createElement("div");
                div.classList.add("l")
                div.innerHTML=html
                $('.dot').before(div)
            })
            //轮播图左右箭头显示
            $('.movingpicture').hover(function(){
                $(".lblr").css("display",'flex')
                clearInterval(interVal)
            },function(){
                $(".lblr").css("display",'none')
                interVal = setInterval(autoSwiper,3000)
            })
            $(".lblr").hover(function(){
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
        }
    })


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
    $.ajax({
        url:"article/getAllPC",
        type:"get",
        dataType:"json",
        contentType:"application/json;charset=UTF-8",
        success(res){
            $(res).each((index,item)=>{
                let html = `
                <span class="edit"></span>
                <span class='delete'></span>
                <input type="hidden" value="${item.id}">
                <img style="float: left;width: 200px;" src=${item.imagepath} alt="">
                <h1>${item.title}</h1>
                <p class="date">${item.time}</p>
                <span class='text'>${item.text}</span>
                `
                let li = document.createElement("li")
                li.innerHTML=html;
                $(li).click(function(){
                    
                })
                $(".des").append(li);
            })

            //删除
            $('.delete').click(function(e){
                $.ajax({
                    url:"article/delete",
                    type:"get",
                    data:{id:$(this).next().get(0).value},
                    dataType:"json",
                    contentType:"application/json;charset=UTF-8",
                    success(res){
                        location.reload()
                    }
                })
                e.stopPropagation();
            })
            //编辑
            $('.edit').click(function(e){
                $("body").css("overflow","hidden")
                $('.menceng').css({display:"block"})
                $('.menceng button').get(0).innerHTML="修改"
                currentSubId=$(this).siblings().get(1).value
                $(".toast>ul>li").eq(0).children().get(0).value=$(this).siblings().get(2).src;
                $(".toast>ul>li").eq(1).children().get(0).value=$(this).siblings().get(3).innerHTML;
                $(".toast>ul>li").eq(2).children().get(0).value=$(this).siblings().get(5).innerHTML;
            })
        }
    })

    //修改按钮绑定提交
    $(".menceng .toast button").eq(0).click(function(){
        $("body").css("overflow","auto")
        $('.menceng').css({display:"none"})
        $(this).parent().siblings().each((index,item)=>{
            subData[$(item).children().get(0).name]= $(item).children().eq(0).val()
            $(item).children().get(0).value=''
        })
        if(currentSubId){
            subData['id']=parseInt(currentSubId);
            $.ajax({
                url:"article/update",
                type:"get",
                data:subData,
                dataType:"json",
                contentType:"application/json;charset=UTF-8",
                success(res){
                    location.reload()
                }
            })
        }else{
            delete subData['time']
            $.ajax({
                url:"article/insert",
                type:"get",
                data:subData,
                dataType:"json",
                contentType:"application/json;charset=UTF-8",
                success(res){
                    location.reload()
                }
            })
        }
    })
    //取消
    $(".menceng .toast button").eq(1).click(function(){
        $("body").css("overflow","auto")
        $('.menceng').css({display:"none"})
        $(this).parent().siblings().each((index,item)=>{
            $(item).children().get(0).value=''
        })
    })

    //新增
    $(".addNew").click(function(e){
        $("body").css("overflow","hidden")
        $('.menceng').css({display:"block"})
        $('.menceng button').get(0).innerHTML="新增"
        currentSubId=null
    })
})