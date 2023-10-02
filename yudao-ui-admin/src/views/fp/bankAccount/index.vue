<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="卡号" prop="accountNumber">
        <el-input v-model="queryParams.accountNumber" placeholder="请输入卡号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <!--<el-form-item label="所属人/公司" prop="accountBelong">
        <el-input v-model="queryParams.accountBelong" placeholder="请输入所属人/公司" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="所属银行" prop="bank">
        <el-input v-model="queryParams.bank" placeholder="请输入所属银行" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="开户行" prop="bankBelong">
        <el-input v-model="queryParams.bankBelong" placeholder="请输入开户行" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="账户余额" prop="balance">
        <el-input v-model="queryParams.balance" placeholder="请输入账户余额" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="启用状态：0->禁用；1->启用" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择启用状态：0->禁用；1->启用" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="公/私：1->公户；2->个人账户" prop="mainBody">
        <el-input v-model="queryParams.mainBody" placeholder="请输入公/私：1->公户；2->个人账户" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="queryParams.remark" placeholder="请输入备注" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker v-model="queryParams.createTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']" />
      </el-form-item>
      <el-form-item label="修改时间" prop="modifyTime">
        <el-date-picker v-model="queryParams.modifyTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']" />
      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['fp:bank-account:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['fp:bank-account:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="卡号" align="center" prop="accountNumber" />
      <el-table-column label="所属人/公司" align="center" prop="accountBelong" />
      <el-table-column label="所属银行" align="center" prop="bank" />
      <el-table-column label="开户行" align="center" prop="bankBelong" />
      <el-table-column label="账户余额" align="center" prop="balance" />
      <el-table-column label="启用状态：0->禁用；1->启用" align="center" prop="status" />
      <el-table-column label="公/私：1->公户；2->个人账户" align="center" prop="mainBody" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" prop="modifyTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.modifyTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['fp:bank-account:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['fp:bank-account:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="卡号" prop="accountNumber">
          <el-input v-model="form.accountNumber" placeholder="请输入卡号" />
        </el-form-item>
        <el-form-item label="所属人/公司" prop="accountBelong">
          <el-input v-model="form.accountBelong" placeholder="请输入所属人/公司" />
        </el-form-item>
        <el-form-item label="所属银行" prop="bank">
          <el-input v-model="form.bank" placeholder="请输入所属银行" />
        </el-form-item>
        <el-form-item label="开户行" prop="bankBelong">
          <el-input v-model="form.bankBelong" placeholder="请输入开户行" />
        </el-form-item>
        <el-form-item label="账户余额" prop="balance">
          <el-input v-model="form.balance" placeholder="请输入账户余额" />
        </el-form-item>
        <el-form-item label="启用状态：0->禁用；1->启用" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="公/私：1->公户；2->个人账户" prop="mainBody">
          <el-input v-model="form.mainBody" placeholder="请输入公/私：1->公户；2->个人账户" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="修改时间" prop="modifyTime">
          <el-date-picker clearable v-model="form.modifyTime" type="date" value-format="timestamp" placeholder="选择修改时间" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { createBankAccount, updateBankAccount, deleteBankAccount, getBankAccount, getBankAccountPage, exportBankAccountExcel } from "@/api/fp/bankAccount";

export default {
  name: "BankAccount",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 银行账户列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        accountNumber: null,
        accountBelong: null,
        bank: null,
        bankBelong: null,
        balance: null,
        status: null,
        mainBody: null,
        remark: null,
        createTime: [],
        modifyTime: [],
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      getBankAccountPage(this.queryParams).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        accountNumber: undefined,
        accountBelong: undefined,
        bank: undefined,
        bankBelong: undefined,
        balance: undefined,
        status: undefined,
        mainBody: undefined,
        remark: undefined,
        modifyTime: undefined,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加银行账户";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getBankAccount(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改银行账户";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateBankAccount(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createBankAccount(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除银行账户编号为"' + id + '"的数据项?').then(function() {
          return deleteBankAccount(id);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.$modal.confirm('是否确认导出所有银行账户数据项?').then(() => {
          this.exportLoading = true;
          return exportBankAccountExcel(params);
        }).then(response => {
          this.$download.excel(response, '银行账户.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
