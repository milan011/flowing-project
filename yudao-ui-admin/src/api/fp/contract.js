import request from '@/utils/request'

// 创建合同
export function createContract(data) {
  return request({
    url: '/fp/contract/create',
    method: 'post',
    data: data
  })
}

// 更新合同
export function updateContract(data) {
  return request({
    url: '/fp/contract/update',
    method: 'put',
    data: data
  })
}

// 删除合同
export function deleteContract(id) {
  return request({
    url: '/fp/contract/delete?id=' + id,
    method: 'delete'
  })
}

// 获得合同
export function getContract(id) {
  return request({
    url: '/fp/contract/get?id=' + id,
    method: 'get'
  })
}

// 获得合同分页
export function getContractPage(query) {
  return request({
    url: '/fp/contract/page',
    method: 'get',
    params: query
  })
}

// 导出合同 Excel
export function exportContractExcel(query) {
  return request({
    url: '/fp/contract/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}

// 合同状态修改
export function changeContractStatus(id, status) {
  const data = {
    id,
    status
  }
  return request({
    url: '/fp/contract/update-status',
    method: 'put',
    data: data
  })
}
