<template>
    <el-button type="primary" @click="add" style="position: fixed;left:230px;top:110px">添加</el-button>
    <div style="position: fixed;top:150px;left:230px">
        <el-table :data="tableData" style="width: 100%">
            <el-table-column prop="id" label="ID" width="180" />
            <el-table-column prop="mingcheng" label="名称" width="180" />
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
            tableData: [],
            form: {
                id: 0,
                mingcheng: ""
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
    },
    methods: {
        selList() {
            this.$http({
                method: 'get',
                url: '/daly/selDalyPage',
                params: {
                    page: this.currentPage,
                    limit: this.pageSize
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
                url: "/daly/selDalyInfo",
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
                url: "/daly/delDaly",
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
                }
            });
        },
        sub() {
            if (this.form.id == 0) {
                this.$refs.formRef.validate(async valid => {
                    if (!valid) return;
                    this.$http({
                        url: '/daly/insDaly',
                        method: 'post',
                        data: this.form
                    }).then(res => {
                        if (res.state == 1) {
                            ElMessage({
                                message: '添加成功',
                                type: 'success'
                            })
                            this.selList();
                            this.status = false;
                        }
                    });
                })
            } else {
                this.$refs.formRef.validate(async valid => {
                    if (!valid) return;
                    this.$http({
                        method: 'put',
                        url: '/daly/updDaly',
                        data: this.form
                    }).then(res => {
                        if (res.state == 1) {
                            ElMessage({
                                message: '修改成功',
                                type: 'success',
                            })
                            this.selList();
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