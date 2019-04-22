$(function(){
    //循环插入数据
    for(let i=0;i<5;i++){
        let html = `
        <tr>
            <td>汤姆</td>
            <td><input type="number" name="id" disabled></td>
            <td><input type="text" name="name"></td>
            <td>
                <select name="status">
                    <option value="2">养护中</option>
                    <option value="1">良好</option>
                    <option value="0">不良</option>
                </select></td>
            <td><input type="text" name="expected"></td>
            <td><input type="text" name="actual"></td>
            <td><input type="text name="fertilizer_num"></td>
            <td class="radio">
                <select name="schedule">
                    <option value="1">维护中</option>
                    <option value="0">完成</option>
                </select>
            </td>
            <td><input type="text" value="2019.3.12" name = "date"></td>
            <td><input type="text" name="remarks"></td>
            <td>
            <button style="width:50px!important;background-color: #07c160!important;color: #fff!important;border-radius: 2px!important;border: 1px solid #07c160!important;">通过</button>
            <button style="width:50px!important;background-color: #f44!important;color: #fff!important;border-radius: 2px!important;border: 1px solid #f44!important;">否决</button>
            </td>
        </tr>`
        let tr = document.createElement('tr').innerHTML=html
        $("table").append(tr)
    }
    //通过请求
})