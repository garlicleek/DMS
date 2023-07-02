<template>
    <el-button type="primary" @click="add" style="position: fixed;left:230px;top:110px">添加</el-button>
    <div style="position: fixed;top:150px;left:230px">
        <el-table :data="tableData" style="width: 100%">
            <el-table-column prop="id" label="ID" width="180" />
            <el-table-column prop="mingcheng" label="名称" width="180" />
            <el-table-column prop="dengji" label="等级" width="180" />
            <el-table-column prop="shangji" label="上级" width="180" />
            <el-table-column label="操作" width="180">
                <template v-slot="scope">
                    <el-button type="warning" @click="update(scope.row.id)">修改</el-button>
                    <el-button type="danger" @click="delet(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination :current-page="currentPage" :page-size="pageSize" :page-sizes="[10, 20, 50, 100]"
            layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange"
            @current-change="handleCurrentChange" @prev-click="handlePrevClick" @next-click="handleNextClick" />
    </div>
    <el-dialog :model-value="status" @close="status = false">
        <el-form :model="form" label-width="120px" ref="formRef">
            <el-form-item label="名称" required prop="mingcheng">
                <el-input v-model="form.mingcheng" />
            </el-form-item>
            <el-form-item label="等级" required prop="dengji">
                <el-select v-model="form.dengji" class="m-2" placeholder="Select" @change="dengjiChange">
                    <el-option v-for="item in dengjiList" :key="item.id" :label="item.mingcheng" :value="item.id" />
                </el-select>
            </el-form-item>
            <el-form-item label="上级" required prop="shangji_id">
                <el-select v-if="form.dengji == 2" v-model="form.shangji_id" class="m-2" placeholder="Select">
                    <el-option v-for="item in bumen.yijiList" :key="item.id" :label="item.mingcheng" :value="item.id" />
                </el-select>
                <el-select v-if="form.dengji == 3" v-model="bumen.yiji" class="m-2" placeholder="Select"
                    @change="selErjiList">
                    <el-option v-for="item in bumen.yijiList" :key="item.id" :label="item.mingcheng" :value="item.id" />
                </el-select>
                <el-select v-if="form.dengji == 3" v-model="form.shangji_id" class="m-2" placeholder="Select">
                    <el-option v-for="item in bumen.erjiList" :key="item.id" :label="item.mingcheng" :value="item.id" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="sub">提交</el-button>
                <el-button @click="reset">重置</el-button>
            </el-form-item>
        </el-form>
    </el-dialog>
</template>
<script>
import { ElMessage } from 'element-plus'
export default {
    data() {
        return {
            bumen: {
                yiji: 0,
                erji: 0,
                yijiList: [{
                    id: 0,
                    mingcheng: "请选择部门"
                }],
                erjiList: [{
                    id: 0,
                    mingcheng: "请选择部门"
                }]
            },
            dengjiList: [{
                id: 0,
                mingcheng: "请选择等级"
            }, {
                id: 1,
                mingcheng: "一级"
            }, {
                id: 2,
                mingcheng: "二级"
            }, {
                id: 3,
                mingcheng: "三级"
            }],
            tableData: [],
            form: {
                id: 0,
                mingcheng: "",
                dengji: 0,
                shangji_id: 0
            },
            status: false,
            currentPage: 1,
            pageSize: 10,
            total: 0
        }
    },
    components: {

    },
    mounted() {
        this.selList();
        this.selYijiList();
    },
    methods: {
        dengjiChange(val) {
            if (val == 1) {
                this.form.shangji_id = 0;
            }
        },
        selYijiList() {
            this.$http({
                method: 'get',
                url: '/bumen/selBumenPage',
                params: {
                    page: 1,
                    limit: 10000000,
                    dengji: 1,
                    shangji_id: 0
                }
            }).then(res => {
                this.bumen.yijiList = [{
                    id: 0,
                    mingcheng: "请选择部门"
                }];
                for (let i = 0; i < res.data.length; i++) {
                    this.bumen.yijiList.push(res.data[i]);
                }
            });
        },
        selErjiList(val) {
            this.$http({
                method: 'get',
                url: '/bumen/selBumenPage',
                params: {
                    page: 1,
                    limit: 10000000,
                    dengji: 2,
                    shangji_id: val
                }
            }).then(res => {
                this.bumen.erjiList = [{
                    id: 0,
                    mingcheng: "请选择部门"
                }];
                for (let i = 0; i < res.data.length; i++) {
                    this.bumen.erjiList.push(res.data[i]);
                }
            });
        },
        selList() {
            this.$http({
                method: 'get',
                url: '/bumen/selBumenPage',
                params: {
                    page: this.currentPage,
                    limit: this.pageSize,
                    dengji: -1,
                    shangji_id: -1
                }
            }).then(res => {
                this.tableData = res.data;
                this.total = res.count;
            });
        },
        handleSizeChange(data) {
            this.pageSize = data;
            this.selList();
        },
        handleCurrentChange(data) {
            this.currentPage = data;
            this.selList();
        },
        handlePrevClick() {
            this.selList();
        },
        handleNextClick() {
            this.selList();
        },
        add() {
            this.form = this.$options.data().form;
            this.status = true;
        },
        update(data) {
            this.$http({
                url: "/bumen/selBumenInfo",
                method: "get",
                params: {
                    id: data
                }
            }).then(res => {
                this.form = res.data;
                this.status = true;
            });
        },
        delet(data) {
            this.$http({
                url: "/bumen/delBumen",
                method: "delete",
                params: {
                    id: data
                }
            }).then(res => {
                if (res.state == 1) {
                    ElMessage({
                        message: '删除成功',
                        type: 'success'
                    })
                    this.selList();
                    this.selYijiList();
                }
            });
        },
        sub() {
            if (this.form.id == 0) {
                this.$refs.formRef.validate(async valid => {
                    if (!valid) return;
                    this.$http({
                        url: '/bumen/insBumen',
                        method: 'post',
                        data: this.form
                    }).then(res => {
                        if (res.state == 1) {
                            ElMessage({
                                message: '添加成功',
                                type: 'success'
                            })
                            this.selList();
                            this.selYijiList();
                            this.status = false;
                        }
                    });
                })
            } else {
                this.$refs.formRef.validate(async valid => {
                    if (!valid) return;
                    this.$http({
                        method: 'put',
                        url: '/bumen/updBumen',
                        data: this.form
                    }).then(res => {
                        if (res.state == 1) {
                            ElMessage({
                                message: '修改成功',
                                type: 'success',
                            })
                            this.selList();
                            this.selYijiList();
                            this.status = false;
                        }
                    });
                })
            }
        },
        reset() {
            this.$refs.formRef.resetFields();
        }
    }
}
</script>
<style></style>