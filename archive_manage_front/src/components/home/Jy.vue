<template>
    <div style="position: fixed;top:150px;left:230px">
        <el-table :data="tableData" style="width: 100%">
            <el-table-column prop="id" label="ID" width="180" />
            <el-table-column prop="archive" label="档案名称" width="180" />
            <el-table-column prop="user" label="借阅人" width="180" />
            <el-table-column prop="time" label="申请时间" width="180" />
            <el-table-column prop="state" label="状态" width="180">
                <template v-slot="scope">
                    <el-text class="mx-1" v-if="scope.row.state == 0">待审核</el-text>
                    <el-text class="mx-1" v-if="scope.row.state == 1">借阅成功</el-text>
                    <el-text class="mx-1" v-if="scope.row.state == 2">借阅失败</el-text>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="300">
                <template v-slot="scope">
                    <el-button type="warning" v-if="scope.row.state == 0" @click="check(scope.row.id, 1)">审批通过</el-button>
                    <el-button type="danger" v-if="scope.row.state == 0" @click="check(scope.row.id, 2)">审批不通过</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination :current-page="currentPage" :page-size="pageSize" :page-sizes="[10, 20, 50, 100]"
            layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange"
            @current-change="handleCurrentChange" @prev-click="handlePrevClick" @next-click="handleNextClick" />
    </div>
</template>
<script>
import { ElMessage } from 'element-plus'
export default {
    data() {
        return {
            tableData: [],
            form: {
                id: 0,
                archive_id: 0,
                user_id: 0,
                time: "",
                state: 0
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
                url: '/jieyue/selJieyuePage',
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
        check(id, state) {
            this.$http({
                method: 'put',
                url: '/jieyue/updState',
                data: {
                    id: id,
                    state: state
                }
            }).then(res => {
                if (res.state == 1) {
                    ElMessage({
                        message: '审批成功',
                        type: 'success',
                    })
                    this.selList();
                    this.status = false;
                }
            });
        }
    }
}
</script>
<style></style>