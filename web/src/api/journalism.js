import request from "@/utils/request";

export function pageQuery(page, limit, data) {
  return request({
    url: `/journalism/pageQuery/${page}/${limit}`,
    method: 'post',
    data: data
  })
}

export function deleteById(id) {
  return request({
    url: `/journalism/deleteById/${id}`,
    method: 'delete'
  })
}

export function insert(data) {
  return request({
    url: `/journalism/insert`,
    method: 'post',
    data: data
  })
}

export function update(data) {
  return request({
    url: `/journalism/update`,
    method: 'put',
    data: data
  })
}
