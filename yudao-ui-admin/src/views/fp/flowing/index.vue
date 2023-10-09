<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入名称" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="流水号" prop="flowingNumber">
        <el-input v-model="queryParams.flowingNumber" placeholder="请输入流水号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="所属账户" prop="accId">
        <el-select v-model="queryParams.accId" placeholder="请选择所属账户" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="项目" prop="proId">
        <el-select v-model="queryParams.proId" placeholder="请选择关联项目" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="合同" prop="conId">
        <el-select v-model="queryParams.conId" placeholder="请选择关联合同" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="金额" prop="money">
        <el-input v-model="queryParams.money" placeholder="请输入金额" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="资金类型" prop="moneyType">
        <el-select v-model="queryParams.moneyType" placeholder="请选择资金类型" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="费用类型" prop="costType">
        <el-select v-model="queryParams.costType" placeholder="请选择费用类型" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker v-model="queryParams.createTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['fp:flowing:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['fp:flowing:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="ID" align="center" prop="id" width="80px" />
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="流水号" align="center" prop="flowingNumber" />
      <el-table-column label="所属账户" align="center" prop="account.name" />
      <el-table-column label="项目" align="center" prop="project.name" />
      <el-table-column label="合同" align="center" prop="contract.name" />
      <el-table-column label="金额" align="center" prop="money" />
      <el-table-column label="资金类型" align="center" prop="moneyType">
        <template v-slot="scope">
          {{ moneyTypeReflect(scope.row.moneyType) }}
        </template>
      </el-table-column>
      <el-table-column label="费用类型" align="center" prop="costType">
        <template v-slot="scope">
          {{ costTypeReflect(scope.row.costType) }}
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template v-slot="scope">
          <el-tag v-if="scope.row.status == 1" type="success">启用</el-tag>
          <el-tag v-if="scope.row.status == 0" type="info">禁用</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="修改时间" align="center" prop="updateTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.updateTime) }}</span>
        </template>
      </el-table-column>-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120px">
        <template v-slot="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['fp:flowing:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['fp:flowing:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="流水号" prop="flowingNumber">
          <el-input-number :precision="0" :controls="false" v-model="form.flowingNumber" placeholder="请输入流水号" />
        </el-form-item>
        <el-form-item label="所属账户" prop="accId">
          <el-select v-model="form.accId" placeholder="请选择所属账户">
            <el-option v-for="item in bankAccounts" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="所属项目" prop="proId">
          <el-select v-model="form.proId" @change="projectChecked" placeholder="请选择所属项目">
            <el-option v-for="item in projects" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="所属合同" prop="conId">
          <el-select :disabled="!contractCheckAllow" v-model="form.conId" placeholder="请选择所属合同">
            <el-option v-for="item in contrantsDelled" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="金额" prop="money">
          <el-input-number :precision="2" :controls="false" v-model="form.money" placeholder="请输入金额" />
        </el-form-item>
        <el-form-item label="资金类型" prop="moneyType">
          <el-select v-model="form.moneyType" @change="moneyTypeChecked" placeholder="请选择资金类型">
            <el-option v-for="(item, index) in money_type" :key="index" :label="item.label" :value="item.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="费用类型" prop="costType">
          <el-select :disabled="!costCheckAllow" v-model="form.costType" placeholder="请选择费用类型">
            <el-option v-for="(item, index) in cost_type" :key="index" :label="item.label" :value="item.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio :label="1">启用</el-radio>
            <el-radio :label="0">禁用</el-radio>
          </el-radio-group>
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
import { createFlowing, updateFlowing, deleteFlowing, getFlowing, getFlowingPage, exportFlowingExcel } from "@/api/fp/flowing";
import {money_type, cost_type, account_belonge} from "@/utils/dict"
import { getAcitveBankAccount } from "@/api/fp/bankAccount"
import { getAcitveContract } from "@/api/fp/contract"
import { getAcitveProject } from "@/api/fp/project"
import loadTinymce from "@/utils/loadTinymce";

export default {
  name: "Flowing",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      money_type: money_type,
      cost_type: cost_type,
      bankAccounts: [],
      contrants: [],
      contrantsDelled: [],
      projects: [],
      contractCheckAllow: false,
      costCheckAllow: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 流水明细列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        name: '流水',
        flowingNumber: 2123,
        accId: null,
        proId: null,
        conId: null,
        money: null,
        moneyType: null,
        costType: null,
        status: null,
        createTime: [],
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [{ required: true, message: "名称不能为空", trigger: "blur" }],
        flowingNumber: [{ required: true, message: "流水号不能为空", trigger: "blur" }],
        accId: [{ required: true, message: "所属账户不能为空", trigger: "change" }],
        proId: [{ required: true, message: "所属项目", trigger: "change" }],
        conId: [{ required: true, message: "所属合同", trigger: "change" }],
        money: [{ required: true, message: "金额不能为空", trigger: "blur" }],
        moneyType: [{ required: true, message: "资金类型不能为空", trigger: "change" }],
        // costType: [{ required: true, message: "费用类型不能为空", trigger: "change" }],
        status: [{ required: true, message: "状态不能为空", trigger: "blur" }],
      }
    };
  },
  created() {
    this.getList();
    this.activeFetch();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      getFlowingPage(this.queryParams).then(response => {
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
        name: undefined,
        flowingNumber: undefined,
        accId: undefined,
        proId: undefined,
        conId: undefined,
        money: undefined,
        moneyType: undefined,
        costType: undefined,
        status: 1,
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
      this.contractCheckAllow = false
      this.open = true;
      this.title = "添加流水明细";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getFlowing(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改流水明细";
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
          updateFlowing(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createFlowing(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除流水明细编号为"' + id + '"的数据项?').then(function() {
          return deleteFlowing(id);
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
      this.$modal.confirm('是否确认导出所有流水明细数据项?').then(() => {
          this.exportLoading = true;
          return exportFlowingExcel(params);
        }).then(response => {
          this.$download.excel(response, '流水明细.xls');
          this.exportLoading = false;
        }).catch(() => {});
    },
    projectChecked(value){
      console.log('projectChecked', value)
      this.contractCheckAllow = (value !== null || true)
      this.form.conId = null
      this.contractDell(value)

      console.log('contractCheckAllow', this.contractCheckAllow)
    },
    contractDell(proId){
      console.log('proId', proId)
      this.contrantsDelled = this.contrants.filter((item,key,arr) => {
        console.log(item)
        return item.proId == proId;
      })

      console.log('contrantsDelled', this.contrantsDelled)
    },
    moneyTypeChecked(value){
      this.form.costType = null
      this.costCheckAllow = (value == 3)
      if(value == 3){
        this.$set(this.rules,'costType',[{ required: true, message: "费用类型不能为空", trigger: "change" }])
      }else{
        this.$set(this.rules,'costType',[{ required: false, message: "费用类型不能为空", trigger: "change" }])
      }
    },
    activeFetch(){
      getAcitveBankAccount().then((respons)=>{
        this.bankAccounts = respons.data
      })
      getAcitveProject().then((respons)=>{
        this.projects = respons.data
      })
      getAcitveContract().then((respons)=>{
        this.contrants = respons.data
      })
    },
    costTypeReflect(value){
      let item = cost_type.find((item)=>item.value == value)
      return item.label
    },
    moneyTypeReflect(value){
      let item = money_type.find((item)=>item.value == value)
      return item.label
    }
  }
};
</script>
