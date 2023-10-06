import request from '@/utils/request'

// 创建流水明细
export function createFlowing(data) {
  return request({
    url: '/fp/flowing/create',
    method: 'post',
    data: data
  })
}

// 更新流水明细
export function updateFlowing(data) {
  return request({
    url: '/fp/flowing/update',
    method: 'put',
    data: data
  })
}

// 删除流水明细
export function deleteFlowing(id) {
  return request({
    url: '/fp/flowing/delete?id=' + id,
    method: 'delete'
  })
}

// 获得流水明细
export function getFlowing(id) {
  return request({
    url: '/fp/flowing/get?id=' + id,
    method: 'get'
  })
}

// 获得流水明细分页
export function getFlowingPage(query) {
  return request({
    url: '/fp/flowing/page',
    method: 'get',
    params: query
  })
}

// 导出流水明细 Excel
export function exportFlowingExcel(query) {
  return request({
    url: '/fp/flowing/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
