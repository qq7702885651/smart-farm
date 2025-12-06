import request from '@/utils/request'

export const loginUser = (manager) =>{
    return request({
        url: '/manage/user/login',
        method: 'POST',
        data: manager
    })
}