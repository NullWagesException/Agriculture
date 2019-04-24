const newLocal = console.log;
$(function(){
    //添加成员
    $(".addBtn").click(function(){
        let data={
            name:$('.addForm').eq(0).val(),
            type:$('.addForm:checked').eq(0).val(),
            position:$('.addForm').eq(3).val(),
            phone:$('.addForm').eq(4).val(),
            username:$('.addForm').eq(5).val(),
            password:$('.addForm').eq(6).val()
        }
        for(let i in data){
            if(data[i]==null||data[i].trim()==''){
                alert("注册信息不得为空")
                return
            }
        }
        $.ajax({
            type:"get",
            dataType:"json",
            data:data,
            contentType:"application/json;charset=UTF-8",
            url:"user/insert",
            success(res){
                location.reload()
            },
            error(res){
                alert(res.response)
            }
        })
    })

    //循环插入数据
    $.ajax({
        type:"get",
        dataType:"json",
        contentType:"application/json;charset=UTF-8",
        url:"user/getAll",
        success(res){
            $(res).each((index,item)=>{
                let html = `
                <li>
                    <input type="hidden" value=${item.id}>
                    <span>姓名：<input type="text" required value=${item.name}></span>
                    <span>职位：<input type="text" required value=${item.position}></span>
                    <span>电话：<input type="text" required value=${item.phone}></span>
                    <span>账号：<input type="text" required value=${item.username}></span>
                    <span>密码：<input type="text" required value=${item.password}></span>
                    <button class="btn1 sub">提交</button>
                    <button class="btn2 delete">删除</button>
                </li>`
                let li = document.createElement('li').innerHTML=html
                if(String(item.type)=='0'){
                    $(".checkPeople>ul").eq(1).append(li)
                }else if(String(item.type)=='1'){
                    $(".checkPeople>ul").eq(2).append(li)
                }
            })

            //绑定修改用户信息函数
            $('.checkPeople>ul .sub').click(function(){
                let all = $(this).siblings()
                let data = {
                    id:all[0].value,
                    name:$(all[1]).children().get(0).value,
                    position:$(all[2])[0].children[0].value,
                    phone:$(all[3])[0].children[0].value,
                    username:$(all[4])[0].children[0].value,
                    password:$(all[5])[0].children[0].value
                }
                $.ajax({
                    type:"get",
                    dataType:"json",
                    data:data,
                    contentType:"application/json;charset=UTF-8",
                    url:"user/update",
                    success(res){
                        location.reload()
                    }
                })
            })

            //绑定删除用户信息函数
            $('.checkPeople>ul .delete').click(function(){
                let data = {
                    id:$(this).siblings()[0].value
                }
                $.ajax({
                    type:"get",
                    dataType:"json",
                    data:data,
                    contentType:"application/json;charset=UTF-8",
                    url:"user/delete",
                    success(res){
                        location.reload()
                    },
                    error(res){
                        console.log(res)
                    }
                })
            })
        }
    })
})