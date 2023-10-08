import request from '@/utils/request'

// 创建银行账户
export function createBankAccount(data) {
  return request({
    url: '/fp/bank-account/create',
    method: 'post',
    data: data
  })
}

// 更新银行账户
export function updateBankAccount(data) {
  return request({
    url: '/fp/bank-account/update',
    method: 'put',
    data: data
  })
}

// 删除银行账户
export function deleteBankAccount(id) {
  return request({
    url: '/fp/bank-account/delete?id=' + id,
    method: 'delete'
  })
}

// 获得银行账户
export function getBankAccount(id) {
  return request({
    url: '/fp/bank-account/get?id=' + id,
    method: 'get'
  })
}

// 获得active账户列表(关联明细用)
export function getAcitveBankAccount() {
  return request({
    url: '/fp/bank-account/list-active',
    method: 'get'
  })
}

// 获得银行账户分页
export function getBankAccountPage(query) {
  return request({
    url: '/fp/bank-account/page',
    method: 'get',
    params: query
  })
}

// 导出银行账户 Excel
export function exportBankAccountExcel(query) {
  return request({
    url: '/fp/bank-account/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}

// 账户状态修改
export function changeBankAccountStatus(id, status) {
  const data = {
    id,
    status
  }
  return request({
    url: '/fp/bank-account/update-status',
    method: 'put',
    data: data
  })
}
