import request from '@/utils/request'

export function pageQuery(page, limit, data) {
  return request({
    url: `/lost-found/pageQuery/${page}/${limit}`,
    method: 'post',
    data: data
  })
}
export function deleteById(id) {
  return request({
    url: `/lost-found/deleteById/${id}`,
    method: 'delete'
  })
}
export function update(data){
  return request({
    url: `/lost-found/update`,
    method: 'put',
    data: data
  })
}
