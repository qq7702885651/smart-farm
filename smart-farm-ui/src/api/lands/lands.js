import request from '@/utils/request'

//分页查询土地
export const pageLands = (pageNum,pageSize) =>{
    return request({
        url:`/manage/lands/page/${pageNum}/${pageSize}`,
        method:'GET'
    })
}

//添加土地函数
export const addLand = (lands) =>{
    return request({
        url:'/manage/lands/addLands',
        method:'POST',
        data: lands
    })
}

//删除功能实现,实现下架功能
export const deleteLand = (uid) =>{
    return request({
        url:`/manage/lands/delete/${uid}`,
        method:'DELETE'
    })
}

//超级管理员删除功能实现
export const superDeleteLand = (uid) =>{
    return request({
        url:`/manage/lands/delete/super/${uid}`,
        method:'DELETE'
    })
}

//修改功能实现
export const updateLand = (lands,uid) =>{
    return request({
        url: `/manage/lands/update/${uid}`,
        method: 'PUT',
        data: lands
    })
}

//搜索土地信息实现
export const searchLand = (title) =>{
    return request({
        url:`/manage/lands/${title}`,
        method: 'GET'
    })
}