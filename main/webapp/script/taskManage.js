let allPage=1;
let nowPage=1;
let searchPage=1,allSearchPage=1
let nowSearchType=0;
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
                <tr>
                    <td>${res.username}</td>
                    <td><input type="number" name="updateid" disabled value=${res.updateid}></td>
                    <td><input type="number" name="id" disabled value=${res.id}></td>
                    <td><input type="text" name="name" value=${res.name}></td>
                    <td>s
                        <select name="status">
                            <option value="0" ${res.status=='0'?'selected':''}>养护中</option>
                            <option value="1" ${res.status=='1'?'selected':''}>良好</option>
                            <option value="2" ${res.status=='2'?'selected':''}>不良</option>
                        </select></td>
                    <td><input type="text" name="expected" value=${res.expected}></td>
                    <td><input type="text" name="actual" value=${res.actual}></td>
                    <td><input type="text name="fertilizer_num" value=${res.fertilizer_num}></td>
                    <td class="radio">
                        <select name="schedule">
                            <option value="0" ${res.schedule=='0'?'selected':''}>维护中</option>
                            <option value="1" ${res.schedule=='1'?'selected':''}>完成</option>
                        </select>
                    </td>
                    <td><input type="text" value=${res.date} name = "date"></td>
                    <td><input type="text" name="remarks" value=${res.remarks}></td>
                    <td>
                    <button class='yes' allow=${res.allow} style="width:50px!important;background-color: #07c160!important;color: #fff!important;border-radius: 2px!important;border: 1px solid #07c160!important;">通过</button>
                    <button class='no' style="width:50px!important;background-color: #f44!important;color: #fff!important;border-radius: 2px!important;border: 1px solid #f44!important;">否决</button>
                    </td>
                </tr>`
                let tr = document.createElement('tr').innerHTML=html
                $("table").append(tr)
                //通过请求
                $('.yes').click(function(){
                    let data={
                        id:$(this).parent().siblings().eq(1).children().eq(0).val(),
                        allow:1
                    }
                    $.ajax({
                        url:"userupdate/update",
                        type:"get",
                        data:data,
                        dataType:"json",
                        contentType:"application/json;charset=UTF-8",
                        success(res){
                            location.reload()
                        }
                    })
                })

                //否决请求
                $('.no').click(function(){
                    $.ajax({
                        url:"userupdate/update",
                        type:"get",
                        data:{id:$(this).parent().siblings().eq(1).children().eq(0).val()},
                        dataType:"json",
                        contentType:"application/json;charset=UTF-8",
                        success(res){
                            location.reload()
                        }
                    })
                })
                
                }
            })
        return;
    }
    $.ajax({
        url:"userupdate/getAllow?pageNum="+num+"&allow=0",
        type:"get",
        dataType:"json",
        contentType:"application/json;charset=UTF-8",
        success(res){
            if(res.length==0){
                alert("暂无数据")
                return;
            }
            allPage=parseInt((res.total)/10)+parseInt(((res.total%10)>0)+0);
            $(res.data).each((index,item)=>{
                let html = `
                <tr>
                    <td>${item.username}</td>
                    <td><input type="number" name="updateid" disabled value=${item.updateid}></td>
                    <td><input type="number" name="id" disabled value=${item.id}></td>
                    <td><input type="text" name="name" value=${item.name}></td>
                    <td>
                        <select name="status">
                            <option value="0" ${item.status=='0'?'selected':''}>养护中</option>
                            <option value="1" ${item.status=='1'?'selected':''}>良好</option>
                            <option value="2" ${item.status=='2'?'selected':''}>不良</option>
                        </select></td>
                    <td><input type="text" name="expected" value=${item.expected}></td>
                    <td><input type="text" name="actual" value=${item.actual}></td>
                    <td><input type="text name="fertilizer_num" value=${item.fertilizer_num}></td>
                    <td class="radio">
                        <select name="schedule">
                            <option value="0" ${item.schedule=='0'?'selected':''}>维护中</option>
                            <option value="1" ${item.schedule=='1'?'selected':''}>完成</option>
                        </select>
                    </td>
                    <td><input type="text" value=value=${item.date} name = "date"></td>
                    <td><input type="text" name="remarks" value=${item.remarks}></td>
                    <td>
                    <button class='yes' allow=${item.allow} style="width:50px!important;background-color: #07c160!important;color: #fff!important;border-radius: 2px!important;border: 1px solid #07c160!important;">通过</button>
                    <button class='no' style="width:50px!important;background-color: #f44!important;color: #fff!important;border-radius: 2px!important;border: 1px solid #f44!important;">否决</button>
                    </td>
                </tr>`
                let tr = document.createElement('tr').innerHTML=html
                $("table").append(tr)
            })

            //通过请求
            $('.yes').click(function(){
                let data={
                    id:$(this).parent().siblings().eq(1).children().eq(0).val(),
                    allow:1
                }
                $.ajax({
                    url:"userupdate/update",
                    type:"get",
                    data:data,
                    dataType:"json",
                    contentType:"application/json;charset=UTF-8",
                    success(res){
                        location.reload()
                    }
                })
            })

            //否决请求
            $('.no').click(function(){
                let data={
                    id:$(this).parent().siblings().eq(1).children().eq(0).val(),
                    allow:2
                }
                $.ajax({
                    url:"userupdate/update",
                    type:"get",
                    data:data,
                    dataType:"json",
                    contentType:"application/json;charset=UTF-8",
                    success(res){
                        location.reload()
                    }
                })
            })
        }
    })
}