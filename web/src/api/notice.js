import request from '@/utils/request'

export function pageQuery(page, limit, data) {
  return request({
    url: `/notice/pageQuery/${page}/${limit}`,
    method: 'post',
    data: data
  })
}
export function deleteById(id) {
  return request({
    url: `/notice/deleteById/${id}`,
    method: 'delete'
  })
}
export function insert(data){
  return request({
    url: `/notice/insert`,
    method: 'post',
    data: data
  })
}

export function update(data){
  return request({
    url: `/notice/update`,
    method: 'put',
    data: data
  })
}
