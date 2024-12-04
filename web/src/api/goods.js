import request from "@/utils/request";

export function pageQuery(page, limit, data) {
  return request({
    url: `/goods/pageQuery/${page}/${limit}`,
    method: 'post',
    data: data
  })
}

export function deleteById(id) {
  return request({
    url: `/goods/deleteById/${id}`,
    method: 'delete'
  })
}
