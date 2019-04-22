$(function(){
    //初始化
    $.ajax({
        type: "get",
        url: "curing/getAll",
        success(res) {
            $(res).each((index,item)=>{
                let html = `
                <tr>
                    <td><input type="number" name="id" disabled value=""></td>
                    <td><input type="text" name="name"></td>
                    <td>
                        <select name="status">
                            <option value="2">养护中</option>
                            <option value="1">良好</option>
                            <option value="0">不良</option>
                        </select></td>
                    <td><input type="text" name="expected"></td>
                    <td><input type="text" name="actual"></td>
                    <td><input type="text" name="fertilizer_num"></td>
                    <td class="radio">
                        <select name="schedule">
                            <option value="1">维护中</option>
                            <option value="0">完成</option>
                        </select>
                    </td>
                    <td><input type="text" value="2019-03-12" name = "date"></td>
                    <td><input type="text" name="remarks"></td>
                    <td>
                        <button>修改</button>
                        <button>取消</button>
                    </td>
                </tr>`
                        let tr = document.createElement('tr').innerHTML=html
                        $(".addNewTask").before(tr)
            })

        }
    })

    //绑定修改用户信息函数
    $("table button").eq(0).click(function(){
        let data = {}
        $(this).parent().siblings().each((index,item)=>{
            data[$(item).children().get(0).name]=$(item).children().val()
        })
        $.ajax({
            url:"curing/update",
            type:"post",
            data:JSON.stringify(data),
            dataType:"json",
            contentType: "application/json;charset=UTF-8",
            success(res){
                console.log(res)
            },
            error(res){
                console.log(res)
            }
        })
    })
    //取消操作
    $("table button").eq(1).click(function(){
        $(this).parent().siblings().each((index,item)=>{
            $(item).children().get(0).value=''
        })
    })

    //新增信息操作
    $("table .addNewTask button").click(function(){
        let subData={}
        $(this).parent().siblings().each((index,item)=>{
            subData[$(item).children().get(0).name]=$(item).children().val()
        })
    })
})