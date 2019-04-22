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
        
    })

    //循环插入数据
    for(let i=0;i<5;i++){
        let html = `
        <li>
            <input type="hidden" value=${i}>
            <span>姓名：<input type="text" required></span>
            <span>职位：<input type="text" required></span>
            <span>电话：<input type="text" required></span>
            <span>账号：<input type="text" required></span>
            <span>密码：<input type="text" required></span>
            <button class="btn1">提交</button>
            <button class="btn2">删除</button>
        </li>`
        let li = document.createElement('li').innerHTML=html
        if(i<3){
            $(".checkPeople>ul").eq(1).append(li)
        }else{
            $(".checkPeople>ul").eq(2).append(li)
        }
    }
    //绑定修改用户信息函数
    $('.checkPeople>ul .btn1').click(function(){
        let all = $(this).siblings()
        let data = {
            id:all[0].value,
            name:$(all[1]).children().get(0).value,
            position:$(all[2])[0].children[0].value,
            phone:$(all[3])[0].children[0].value,
            username:$(all[4])[0].children[0].value,
            password:$(all[5])[0].children[0].value
        }
    })

    //绑定删除用户信息函数
    $('.checkPeople>ul .btn2').click(function(){
        let data = {
            id:$(this).siblings()[0].value
        }
    })
})