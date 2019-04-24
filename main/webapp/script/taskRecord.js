let allPage=1;
let nowPage=1;
let searchPage=1,allSearchPage=1
let nowSearchType=0;
let allow=0;
window.onload=function(){
    document.getElementsByClassName("head")[0].style.width=$("tr").eq(0).width()+"px"
}
$(function(){
    //初始化
    page(nowPage);

    $(".page .left").click(()=>{
        if(nowSearchType==0){
            nowPage--;
            if(nowPage<1){
                nowPage++
                alert("页码到底了哦")
                return
            }
            window.page(nowPage)
        }else if(nowSearchType==1){
            alert("没有更多数据了哦")
            return;
        }
        else{
            searchPage--;
            if(searchPage<1){
                searchPage++
                alert("页码到底了哦")
                return
            }
            window.page(searchPage)
        }
    })
    $(".page .right").click(()=>{
        if(nowSearchType==0){
            nowPage++;
            if(nowPage>allPage){
                nowPage--
                alert("页码到底了哦")
                return
            }
            window.page(nowPage)
        }else if(nowSearchType==1){
            alert("没有更多数据了哦")
            return;
        }
        else{
            searchPage++;
            if(searchPage>allPage){
                searchPage--
                alert("页码到底了哦")
                return
            }
            window.page(searchPage)
        }
    })

    //id查询
    $(".search button").click(function(){
        nowSearchType=1;
        let param=$(".head input").get(0).value;
        if(!(parseFloat(param).toString() == "NaN")){
            window.page(param,false,true)
        }
    })
    //状态查询
    $(".select_search button").click(function(){
        let status=parseInt($(".select_search select").get(0).value)
        console.log(status)
        if(status==-1){
            location.reload();
            return;
        }
        nowSearchType=1;
        page(status,true)
    })
})

//分页获取
function page(num,search=false,id=false){
    //清空数据
    let initHtml =`
    <tr>
        <th>请求人员</th>
        <th>植物id</th>
        <th>编号</th>
        <th>名称</th>
        <th>生长状况</th>
        <th>期望措施</th>
        <th>实际措施</th>
        <th>所需物品</th>
        <th>养护进度</th>
        <th>时间</th>
        <th>备注</th>
        <th>操作</th>
    </tr>
    `
    $('table').empty()
    $('table').html(initHtml)

    if(id){
        $.ajax({
            type:"get",
            dataType:"json",
            contentType:"application/json;charset=UTF-8",
            url:"userupdate/get?id="+parseInt(num),
            success(res){
                if(res.length==0){
                    alert("未找到该id对应的记录")
                    location.reload()
                    return;
                }
                let html = `
                <tr style="height:30px">
                <td>${res.username}</td>
                <td>${res.id}</td>
                <td>${res.name}</td>
                <td>${parseInt(res.status)==0?"养护中":(res.status=='1'?"良好":"不良")}
                <td>${res.expected}</td>
                <td>${res.actual}</td>
                <td>${res.fertilizer_num}</td>
                <td>${parseInt(res.status)==0?"维护中":"完成"}
                </td>
                <td>${res.date}</td>
                <td>${res.remarks}</td>
                <td>${parseInt(res.allow)==1?"已通过":"已否决"}
                </td>
            </tr>`
                let tr = document.createElement('tr').innerHTML=html
                $("table").append(tr)
                
                }
            })
        return;
    }
    if(search){
        $.ajax({
            type:"get",
            dataType:"json",
            contentType:"application/json;charset=UTF-8",
            url:"userupdate/getAllow?pageNum="+nowPage+"&allow="+parseInt(num),
            success(res){
                if(res.total==0){
                    alert("未找到对应的记录")
                    location.reload()
                    return;
                }
                allSearchPage=parseInt((res.total)/10)+parseInt(((res.total%10)>0)+0);
                $(res.data).each((index,item)=>{
                    let html = `
                    <tr style="height:30px">
                    <td>${item.username}</td>
                    <td>${item.updateid}</td>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${parseInt(item.status)==0?"养护中":(parseInt(item.status)=='1'?"良好":"不良")}
                    <td>${item.expected}</td>
                    <td>${item.actual}</td>
                    <td>${item.fertilizer_num}</td>
                    <td>${parseInt(item.status)==0?"维护中":"完成"}
                    </td>
                    <td>${item.date}</td>
                    <td>${item.remarks}</td>
                    <td>${parseInt(item.allow)==1?"已通过":"已否决"}
                    </td>
                </tr>`
                    let tr = document.createElement('tr').innerHTML=html
                    $("table").append(tr)
                })
            }
        })
        return;
    }
    //已通过的
    $.ajax({
        url:"userupdate/getAllow?pageNum="+num+"&allow=1",
        type:"get",
        dataType:"json",
        contentType:"application/json;charset=UTF-8",
        success(res){
            allow=res.total
            $(res.data).each((index,item)=>{
                let html = `
                <tr style="height:30px">
                    <td>${item.username}</td>
                    <td>${item.updateid}</td>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${parseInt(item.status)==0?"养护中":(item.status=='1'?"良好":"不良")}
                    <td>${item.expected}</td>
                    <td>${item.actual}</td>
                    <td>${item.fertilizer_num}</td>
                    <td>${parseInt(item.schedule)==0?"维护中":"完成"}
                    </td>
                    <td>${item.date}</td>
                    <td>${item.remarks}</td>
                    <td>已通过
                    </td>
                </tr>`
                let tr = document.createElement('tr').innerHTML=html
                $("table").append(tr)
            })
        }
    })
    //被否决的
    $.ajax({
        url:"userupdate/getAllow?pageNum="+num+"&allow=2",
        type:"get",
        dataType:"json",
        contentType:"application/json;charset=UTF-8",
        success(res){
            allPage=parseInt((res.total+allow)/10)+parseInt((((res.total+allow)%10)>0)+0);
            $(res.data).each((index,item)=>{
                let html = `
                <tr style="height:30px">
                    <td>${item.username}</td>
                    <td>${item.updateid}</td>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${parseInt(res.status)==0?"养护中":(res.status=='1'?"良好":"不良")}
                    <td>${item.expected}</td>
                    <td>${item.actual}</td>
                    <td>${item.fertilizer_num}</td>
                    <td>${parseInt(res.status)==0?"维护中":"完成"}
                    </td>
                    <td>${item.date}</td>
                    <td>${item.remarks}</td>
                    <td>已否决
                    </td>
                </tr>`
                let tr = document.createElement('tr').innerHTML=html
                $("table").append(tr)
            })
        }
    })
}