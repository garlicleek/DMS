<template>
    <div style="position: fixed;top:150px;left:230px">
        <el-table :data="tableData" style="width: 100%">
            <el-table-column prop="id" label="ID" width="180" />
            <el-table-column prop="archive" label="档案" width="180">
                <template v-slot="scope">
                    <el-text class="mx-1" type="primary" v-if="scope.row.archive == null">已删除</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.archive != null">{{ scope.row.archive }}</el-text>
                </template>
            </el-table-column>
            <el-table-column prop="user" label="操作人" width="180" />
            <el-table-column prop="caozuo_id" label="操作" width="180">
                <template v-slot="scope">
                    <el-text class="mx-1" type="primary" v-if="scope.row.caozuo_id == 1">通过审批</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.caozuo_id == 2">拒绝审批</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.caozuo_id == 3">交接</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.caozuo_id == 4">鉴定</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.caozuo_id == 5">归档</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.caozuo_id == 6">保管</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.caozuo_id == 8">定期鉴定</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.caozuo_id == 9">通过销毁申请</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.caozuo_id == 10">拒绝销毁申请</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.caozuo_id == 11">销毁</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.caozuo_id == 12">删除</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.caozuo_id == 14">申请借阅</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.caozuo_id == 15">归还借阅</el-text>
                </template>
            </el-table-column>
            <el-table-column prop="time" label="操作时间" width="180" />
            <el-table-column prop="beizhu" label="备注" width="180" />
        </el-table>
        <el-pagination :current-page="currentPage" :page-size="pageSize" :page-sizes="[10, 20, 50, 100]"
            layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange"
            @current-change="handleCurrentChange" @prev-click="handlePrevClick" @next-click="handleNextClick" />
    </div>
</template>
<script>
export default {
    data() {
        return {
            tableData: [],
            form: {
                id: 0,
                archive_id: 0,
                caozuo_id: 0,
                user_id: 0,
                time: "",
                beizhu: "",
                archive: "",
                user: ""
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
                url: '/dals/selDalsPage',
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
        }
    }
}
</script>
<style></style>