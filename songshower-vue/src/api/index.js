import request from '@/utils/request'

export function show (data,param) {
  return request({
    url: `/songshower/show?username=${param}`,
    method: 'post',
    data: data,
  })
}