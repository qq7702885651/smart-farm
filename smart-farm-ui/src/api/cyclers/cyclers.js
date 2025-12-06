import request from "@/utils/request.js";
//获取全部租赁信息
export const getCyclers = (offset, pageSize) => {
    return request({
        url: '/manage/cyclers/list',
        method: 'GET',
        params: { offset, pageSize }
    })
}

//添加租赁信息
export const addCycler = (cycler) => {
    return request({
        url: '/manage/cyclers/addCyclers',  // ✅ 使用后端真实路径
        method: 'POST',
        data: cycler
    })
}

//更新租赁信息
export const updateCycler = (cycler) => {
    return request({
        url: '/manage/cyclers/editCyclers',  // ✅ 使用后端真实路径
        method: 'PUT',
        data: cycler
    })
}

//搜索功能实现
export const search = (keyword) => {
    return request({
        url: '/manage/cyclers/search',
        method: 'GET',
        params: { keyword }
    })
}