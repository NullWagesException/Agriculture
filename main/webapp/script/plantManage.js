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

    //新增信息操作
    $("table .addNewTask button").click(function(){
        let subData={}
        $(this).parent().siblings().each((index,item)=>{
            console.log($(item).children().get(0))
            if($(item).children().get(0)&&(!(($(item).children().get(0).name=='date')||($(item).children().get(0).name=='id')))){
                subData[$(item).children().get(0).name]=$(item).children().val()
            }
        })
        $.ajax({
            type:"get",
            dataType:"json",
            data:subData,
            contentType:"application/json;charset=UTF-8",
            url:"curing/insert",
            success(res){
                console.log(res)
                alert("add success")
                location.reload();
            }
        })
    })
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
                <th>编号</th>
                <th>名称</th>
                <th>生长状况</th>
                <th>期望措施</th>
                <th>实际措施</th>
                <th>所需物品</th>
                <th>养护进度</th>
                <th>时间</th>
                <th>备注</th>
                <th>经度</th>
                <th>纬度</th>
                <th>二维码</th>
                <th>操作</th>
            </tr>
            <tr class="addNewTask">
                <td><input type="text" name="id" disabled value='自动获取'></td>
                <td><input type="text" name="name" required></td>
                <td>
                    <select name="status" required>
                        <option value="0">养护中</option>
                        <option value="1">良好</option>
                        <option value="2">不良</option>
                    </select></td>
                <td><input type="text" name="expected"></td>
                <td><input type="text" name="actual"></td>
                <td><input type="text" name="fertilizer_num" required></td>
                <td class="radio">
                    <select name="schedule" value='' required>
                        <option value="0">维护中</option>
                        <option value="1">完成</option>
                    </select>
                </td>
                <td><input type="text" value="自动获取" name = "date" disabled></td>
                <td><input type="text" name="remarks"></td>
                <td><input type="text" name="longitude"></td>
                <td><input type="text" name="latitude"></td>
                <td>二维码自动获取</td>
                <td>
                    <button>新增任务</button>
                </td>
            </tr>
    `
    $('table').empty()
    $('table').html(initHtml)

    if(id){
        $.ajax({
            type:"get",
            dataType:"json",
            contentType:"application/json;charset=UTF-8",
            url:"curing/get?id="+parseInt(num),
            success(res){
                if(res.length==0){
                    alert("该记录不存在")
                    location.reload()
                    return;
                }
                //获取对应二维码
                $.ajax({
                    type:"get",
                    url:"qrcode/getUrl?id="+res.id,
                    success(imageUrl){
                        let html = `
                    <tr>
                        <td><input type="number" name="id" disabled value='${res.id}'></td>
                        <td><input type="text" name="name" value="${res.name}" disabled></td>
                        <td>
                            <select name="status">
                                <option value="0" ${res.status=='0'?'selected':''}>养护中</option>
                                <option value="1" ${res.status=='1'?'selected':''}>良好</option>
                                <option value="2" ${res.status=='2'?'selected':''}>不良</option>
                            </select></td>
                        <td><input type="text" name="expected" value="${res.expected}"></td>
                        <td><input type="text" name="actual" value="${res.actual}"></td>
                        <td><input type="text" name="fertilizer_num" value="${res.fertilizer_num}"></td>
                        <td class="radio">
                            <select name="schedule">
                                <option value="0" ${res.schedule=='0'?'selected':''}>维护中</option>
                                <option value="1" ${res.schedule=='1'?'selected':''}>完成</option>
                            </select>
                        </td>
                        <td><input type="text" value="${res.date}" name = "date"></td>
                        <td><input type="text" name="remarks" value="${res.remarks}"></td>
                        <td><input type="text" name="longitude" value="${res.longitude}"></td>
                        <td><input type="text" name="latitude" value="${res.latitude}"></td>
                        <td><image src = ${'http://192.168.1.54:8080'+imageUrl} style="height:47px"/></td>
                        <td>
                            <button class="change">修改</button>
                            <button class="cancel">删除</button>
                        </td>
                    </tr>`
                    let tr = document.createElement('tr').innerHTML=html
                    $(".addNewTask").after(tr)
                    
                    //绑定修改用户信息函数
                    $(".change").eq(0).click(function(){
                        let data = {}
                        $(this).parent().siblings().each((index,item)=>{
                            if(!($(item).children().get(0).name=='date'||$(item).children().get(0).name=='')){
                                data[$(item).children().get(0).name]=$(item).children().val()
                            }
                        })
                        console.log(data)
                        $.ajax({
                            url:"curing/update",
                            type:"get",
                            data:data,
                            dataType:"json",
                            contentType:"application/json;charset=UTF-8",
                            success(res){
                                console.log(res) 
                                alert("modify success")
                                location.reload()
                            },
                            error(res){
                                console.log(res)
                            }
                        })
                    })
                    //删除操作
                    $(".cancel").click(function(){
                        $(this).parent().siblings().each((index,item)=>{
                            console.log($(item).children().get(0).name=='id')
                            if($(item).children().get(0).name=='id'){
                                $.ajax({
                                    url:"curing/delete",
                                    type:"get",
                                    data:{id:$(item).children().val()},
                                    dataType:"json",
                                    contentType:"application/json;charset=UTF-8",
                                    success(res){
                                        console.log(res)
                                        alert("delete success")
                                        location.reload()
                                    }
                                })
                                return
                            }
                        })
                    })
                    }
                })
            }
        })
        return;
    }
    if(search){
        $.ajax({
            type:"get",
            dataType:"json",
            contentType:"application/json;charset=UTF-8",
            url:"curing/getStatus?pageNum=1&status="+parseInt(num),
            success(res){
                if(res.total==0){
                    alert("对应记录不存在")
                    location.reload()
                    return;
                }
                allSearchPage=parseInt((res.total)/10)+parseInt(((res.total%10)>0)+0);
                $(res.data).each((index,item)=>{
                    //获取对应二维码
                    $.ajax({
                        type:"get",
                        url:"qrcode/getUrl?id="+item.id,
                        success(imageUrl){
                            let html = `
                        <tr>
                            <td><input type="number" name="id" disabled value='${item.id}'></td>
                            <td><input type="text" name="name" value="${item.name}" disabled></td>
                            <td>
                                <select name="status">
                                    <option value="0" ${item.status=='0'?'selected':''}>养护中</option>
                                    <option value="1" ${item.status=='1'?'selected':''}>良好</option>
                                    <option value="2" ${item.status=='2'?'selected':''}>不良</option>
                                </select></td>
                            <td><input type="text" name="expected" value="${item.expected}"></td>
                            <td><input type="text" name="actual" value="${item.actual}"></td>
                            <td><input type="text" name="fertilizer_num" value="${item.fertilizer_num}"></td>
                            <td class="radio">
                                <select name="schedule">
                                    <option value="0" ${item.schedule=='0'?'selected':''}>维护中</option>
                                    <option value="1" ${item.schedule=='1'?'selected':''}>完成</option>
                                </select>
                            </td>
                            <td><input type="text" value="${item.date}" name = "date"></td>
                            <td><input type="text" name="remarks" value="${item.remarks}"></td>
                            <td><input type="text" name="longitude" value="${item.longitude}"></td>
                            <td><input type="text" name="latitude" value="${item.latitude}"></td>
                            <td><image src = ${'http://192.168.1.54:8080'+imageUrl} style="height:47px"/></td>
                            <td>
                                <button class="change">修改</button>
                                <button class="cancel">删除</button>
                            </td>
                        </tr>`
                        let tr = document.createElement('tr').innerHTML=html
                        $(".addNewTask").after(tr)
                        
                        //绑定修改用户信息函数
                        $(".change").eq(0).click(function(){
                            let data = {}
                            $(this).parent().siblings().each((index,item)=>{
                                if(!($(item).children().get(0).name=='date'||$(item).children().get(0).name=='')){
                                    data[$(item).children().get(0).name]=$(item).children().val()
                                }
                            })
                            console.log(data)
                            $.ajax({
                                url:"curing/update",
                                type:"get",
                                data:data,
                                dataType:"json",
                                contentType:"application/json;charset=UTF-8",
                                success(res){
                                    console.log(res) 
                                    alert("modify success")
                                    location.reload()
                                },
                                error(res){
                                    console.log(res)
                                }
                            })
                        })
                        //删除操作
                        $(".cancel").click(function(){
                            $(this).parent().siblings().each((index,item)=>{
                                console.log($(item).children().get(0).name=='id')
                                if($(item).children().get(0).name=='id'){
                                    $.ajax({
                                        url:"curing/delete",
                                        type:"get",
                                        data:{id:$(item).children().val()},
                                        dataType:"json",
                                        contentType:"application/json;charset=UTF-8",
                                        success(res){
                                            console.log(res)
                                            alert("delete success")
                                            location.reload()
                                        }
                                    })
                                    return
                                }
                            })
                        })
                        }
                    })
                })
            }
        })
        return;
    }
    $.ajax({
        type:"get",
        dataType:"json",
        contentType:"application/json;charset=UTF-8",
        url:"curing/getAll?pageNum="+num,
        success(res){
            console.log(res)
            allPage=parseInt((res.total)/10)+parseInt(((res.total%10)>0)+0);
            $(res.data).each((index,item)=>{
                if(res.length==0||res.total==0){
                    alert("暂无数据")
                    location.reload()
                    return;
                }
                //获取对应二维码
                $.ajax({
                    type:"get",
                    url:"qrcode/getUrl?id="+item.id,
                    success(imageUrl){
                        let html = `
                    <tr>
                        <td><input type="number" name="id" disabled value='${item.id}'></td>
                        <td><input type="text" name="name" value="${item.name}" disabled></td>
                        <td>
                            <select name="status">
                                <option value="0" ${item.status=='0'?'selected':''}>养护中</option>
                                <option value="1" ${item.status=='1'?'selected':''}>良好</option>
                                <option value="2" ${item.status=='2'?'selected':''}>不良</option>
                            </select></td>
                        <td><input type="text" name="expected" value="${item.expected}"></td>
                        <td><input type="text" name="actual" value="${item.actual}"></td>
                        <td><input type="text" name="fertilizer_num" value="${item.fertilizer_num}"></td>
                        <td class="radio">
                            <select name="schedule">
                                <option value="0" ${item.schedule=='0'?'selected':''}>维护中</option>
                                <option value="1" ${item.schedule=='1'?'selected':''}>完成</option>
                            </select>
                        </td>
                        <td><input type="text" value="${item.date}" name = "date"></td>
                        <td><input type="text" name="remarks" value="${item.remarks}"></td>
                        <td><input type="text" name="longitude" value="${item.longitude}"></td>
                        <td><input type="text" name="latitude" value="${item.latitude}"></td>
                        <td><image src = ${'http://192.168.1.54:8080'+imageUrl} style="height:47px"/></td>
                        <td>
                            <button class="change">修改</button>
                            <button class="cancel">删除</button>
                        </td>
                    </tr>`
                    let tr = document.createElement('tr').innerHTML=html
                    $(".addNewTask").after(tr)
                    
                    //绑定修改用户信息函数
                    $(".change").eq(0).click(function(){
                        let data = {}
                        $(this).parent().siblings().each((index,item)=>{
                            if(!($(item).children().get(0).name=='date'||$(item).children().get(0).name=='')){
                                data[$(item).children().get(0).name]=$(item).children().val()
                            }
                        })
                        console.log(data)
                        $.ajax({
                            url:"curing/update",
                            type:"get",
                            data:data,
                            dataType:"json",
                            contentType:"application/json;charset=UTF-8",
                            success(res){
                                console.log(res) 
                                alert("modify success")
                                location.reload()
                            },
                            error(res){
                                console.log(res)
                            }
                        })
                    })
                    //删除操作
                    $(".cancel").click(function(){
                        $(this).parent().siblings().each((index,item)=>{
                            console.log($(item).children().get(0).name=='id')
                            if($(item).children().get(0).name=='id'){
                                $.ajax({
                                    url:"curing/delete",
                                    type:"get",
                                    data:{id:$(item).children().val()},
                                    dataType:"json",
                                    contentType:"application/json;charset=UTF-8",
                                    success(res){
                                        console.log(res)
                                        alert("delete success")
                                        location.reload()
                                    }
                                })
                                return
                            }
                        })
                    })
                    }
                })
            })
        },
        error(res){
            console.log(res)
        }
    })
}