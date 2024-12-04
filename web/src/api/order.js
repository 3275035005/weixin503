import request from "@/utils/request";

export function pageQuery(page, limit, data) {
  return request({
    url: `/order/pageQuery/${page}/${limit}`,
    method: 'post',
    data: data
  })
}

export function deleteById(id) {
  return request({
    url: `/order/deleteById/${id}`,
    method: 'delete'
  })
}
