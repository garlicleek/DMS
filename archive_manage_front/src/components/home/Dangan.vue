<template>
    <el-button type="primary" @click="add" style="position: fixed;left:230px;top:110px">立卷</el-button>
    <div style="position: fixed;top:150px;left:230px;">
        <el-table :data="tableData" style="width: 100%">
            <el-table-column prop="id" label="ID" width="60" />
            <el-table-column prop="bianhao" label="编号" width="100" />
            <el-table-column prop="mingcheng" label="名称" width="100" />
            <el-table-column prop="lujing" label="电子档案" width="150">
                <template v-slot="scope">
                    <el-link type="primary" :href="'http://localhost:8080' + scope.row.lujing">查看电子档案</el-link>
                </template>
            </el-table-column>
            <el-table-column prop="lujing" label="电子档案磁盘位置" width="200" />
            <el-table-column prop="leixing" label="类型" width="100" />
            <el-table-column prop="laiyuan" label="来源" width="100" />
            <el-table-column prop="miji_id" label="保密级别" width="100" />
            <el-table-column prop="danganjia" label="档案位置" width="100" />
            <el-table-column prop="zhuangtai" label="状态" width="100">
                <template v-slot="scope">
                    <el-text class="mx-1" type="danger" v-if="scope.row.zhuangtai == 0">立卷待审批</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.zhuangtai == 1">审批通过</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.zhuangtai == 2">审批不通过</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.zhuangtai == 3">交接成功</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.zhuangtai == 4">鉴定成功</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.zhuangtai == 5">归档成功</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.zhuangtai == 6">保管成功</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.zhuangtai == 7">借阅中</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.zhuangtai == 8">定期鉴定成功</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.zhuangtai == 9">销毁申请成功</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.zhuangtai == 10">销毁申请失败</el-text>
                    <el-text class="mx-1" type="primary" v-if="scope.row.zhuangtai == 11">销毁成功</el-text>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="270">
                <template v-slot="scope">
                    <el-button type="warning" v-if="scope.row.zhuangtai == 0"
                        @click="updSp1(scope.row.id, 1)">审批通过</el-button>
                    <el-button type="warning" v-if="scope.row.zhuangtai == 0"
                        @click="updSp2(scope.row.id, 2)">审批不通过</el-button>
                    <el-button type="warning" v-if="scope.row.zhuangtai == 1" @click="updJj(scope.row.id, 3)">交接</el-button>
                    <el-button type="warning" v-if="scope.row.zhuangtai == 3" @click="updJd(scope.row.id, 4)">鉴定</el-button>
                    <el-button type="warning" v-if="scope.row.zhuangtai == 4"
                        @click="gdForm.id = scope.row.id; gdStatus = true">归档</el-button>
                    <el-button type="warning" v-if="scope.row.zhuangtai == 5" @click="updBg(scope.row.id, 6)">保管</el-button>
                    <el-button type="warning" v-if="scope.row.zhuangtai == 6" @click="updJy(scope.row.id, 7)">申请借阅</el-button>
                    <el-button type="warning" v-if="scope.row.zhuangtai == 7" @click="updGh(scope.row.id, 6)">归还借阅</el-button>
                    <el-button type="warning" v-if="scope.row.zhuangtai == 6"
                        @click="updDqjd(scope.row.id, 8)">定期鉴定</el-button>
                    <el-button type="warning" v-if="scope.row.zhuangtai == 8"
                        @click="updXh1(scope.row.id, 9)">通过销毁申请</el-button>
                    <el-button type="warning" v-if="scope.row.zhuangtai == 8"
                        @click="updXh2(scope.row.id, 10)">拒绝销毁申请</el-button>
                    <el-button type="warning" v-if="scope.row.zhuangtai == 9"
                        @click="updXh(scope.row.id, 11)">销毁</el-button>
                    <el-button type="warning" v-if="scope.row.zhuangtai == 11" @click="update(scope.row.id)">修改</el-button>
                    <el-button type="danger" v-if="scope.row.zhuangtai == 11" @click="delet(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination :current-page="currentPage" :page-size="pageSize" :page-sizes="[10, 20, 50, 100]"
            layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange"
            @current-change="handleCurrentChange" @prev-click="handlePrevClick" @next-click="handleNextClick" />
    </div>
    <el-dialog :model-value="status" @close="status = false">
        <el-form :model="form" label-width="120px" ref="formRef">
            <el-form-item label="编号" required prop="bianhao">
                <el-input v-model="form.bianhao" />
            </el-form-item>
            <el-form-item label="名称" required prop="mingcheng">
                <el-input v-model="form.mingcheng" />
            </el-form-item>
            <el-form-item label="路径" required prop="lujing">
                <el-upload action="http://localhost:8080/upload/file" :headers="headers" :on-success="handleAvatarSuccess"
                    :on-remove="handleAvatarRemove" :limit="1">
                    <el-button type="primary">上传档案</el-button>
                </el-upload>
            </el-form-item>
            <el-form-item label="类型" required prop="leixing_id">
                <el-select v-model="form.leixing_id" class="m-2" placeholder="Select">
                    <el-option v-for="item in leixingList" :key="item.id" :label="item.mingcheng" :value="item.id" />
                </el-select>
            </el-form-item>
            <el-form-item label="来源" required prop="laiyuan_id">
                <el-select v-model="form.laiyuan_id" class="m-2" placeholder="Select">
                    <el-option v-for="item in laiyuanList" :key="item.id" :label="item.mingcheng" :value="item.id" />
                </el-select>
            </el-form-item>
            <el-form-item label="加密等级" required prop="miji_id">
                <el-input type="number" v-model="form.miji_id" />
            </el-form-item>
            <el-form-item label="状态" required prop="zhuangtai">
                <el-select v-model="form.zhuangtai" class="m-2" placeholder="Select">
                    <el-option v-for="item in zhuangtaiList" :key="item.id" :label="item.mingcheng" :value="item.id" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="sub">提交</el-button>
                <el-button @click="reset">重置</el-button>
            </el-form-item>
        </el-form>
    </el-dialog>
    <el-dialog :model-value="gdStatus" @close="gdStatus = false">
        <el-form :model="gdForm" label-width="120px" ref="formRef">
            <el-form-item label="档案架" required prop="danganjia">
                <el-input v-model="gdForm.danganjia" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="updGd">提交</el-button>
                <el-button @click="reset">重置</el-button>
            </el-form-item>
        </el-form>
    </el-dialog>
</template>
<script>
import { ElMessage } from 'element-plus'
import {
    Plus
} from '@element-plus/icons-vue'
export default {
    data() {
        return {
            headers: { Authorization: 'Bearer ' + window.sessionStorage.getItem('token') },
            tableData: [],
            leixingList: [{
                id: 0,
                mingcheng: "请选择类目"
            }],
            laiyuanList: [{
                id: 0,
                mingcheng: "请选择类目"
            }],
            zhuangtaiList: [
                {
                    id: 0,
                    mingcheng: "立卷"
                }
            ],
            gdForm: {
                id: 0,
                danganjia: ""
            },
            form: {
                id: 0,
                biaohao: "",
                mingcheng: "",
                lujing: "",
                leixing_id: 0,
                laiyuan_id: 0,
                miji_id: 0,
                zhuangtai: 0
            },
            status: false,
            gdStatus: false,
            currentPage: 1,
            pageSize: 10,
            total: 0
        }
    },
    components: {
        Plus
    },
    mounted() {
        this.selList();
        this.selLeixingList();
        this.selLaiyuanList();
    },
    methods: {
        updSp1(id, state) {
            this.$http({
                method: 'put',
                url: '/dangan/updSp1',
                data: {
                    id: id,
                    zhuangtai: state
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
        },
        updSp2(id, state) {
            this.$http({
                method: 'put',
                url: '/dangan/updSp2',
                data: {
                    id: id,
                    zhuangtai: state
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
        },
        updJj(id, state) {
            this.$http({
                method: 'put',
                url: '/dangan/updJj',
                data: {
                    id: id,
                    zhuangtai: state
                }
            }).then(res => {
                if (res.state == 1) {
                    ElMessage({
                        message: '交接成功',
                        type: 'success',
                    })
                    this.selList();
                    this.status = false;
                }
            });
        },
        updJd(id, state) {
            this.$http({
                method: 'put',
                url: '/dangan/updJd',
                data: {
                    id: id,
                    zhuangtai: state
                }
            }).then(res => {
                if (res.state == 1) {
                    ElMessage({
                        message: '鉴定成功',
                        type: 'success',
                    })
                    this.selList();
                    this.status = false;
                }
            });
        },
        updGd() {
            this.$http({
                method: 'put',
                url: '/dangan/updGd',
                data: {
                    id: this.gdForm.id,
                    zhuangtai: 5,
                    danganjia:this.gdForm.danganjia
                }
            }).then(res => {
                if (res.state == 1) {
                    ElMessage({
                        message: '归档成功',
                        type: 'success',
                    })
                    this.selList();
                    this.gdStatus = false;
                }
            });
        },
        updBg(id, state) {
            this.$http({
                method: 'put',
                url: '/dangan/updBg',
                data: {
                    id: id,
                    zhuangtai: state
                }
            }).then(res => {
                if (res.state == 1) {
                    ElMessage({
                        message: '保管成功',
                        type: 'success',
                    })
                    this.selList();
                    this.status = false;
                }
            });
        },
        updJy(id, state) {
            this.$http({
                method: 'put',
                url: '/dangan/updJy',
                data: {
                    id: id,
                    zhuangtai: state
                }
            }).then(res => {
                if (res.state == 1) {
                    ElMessage({
                        message: '申请借阅成功',
                        type: 'success',
                    })
                    this.selList();
                    this.status = false;
                }
            });
        },
        updGh(id, state) {
            this.$http({
                method: 'put',
                url: '/dangan/updGh',
                data: {
                    id: id,
                    zhuangtai: state
                }
            }).then(res => {
                if (res.state == 1) {
                    ElMessage({
                        message: '归还成功',
                        type: 'success',
                    })
                    this.selList();
                    this.status = false;
                }
            });
        },
        updDqjd(id, state) {
            this.$http({
                method: 'put',
                url: '/dangan/updDqjd',
                data: {
                    id: id,
                    zhuangtai: state
                }
            }).then(res => {
                if (res.state == 1) {
                    ElMessage({
                        message: '定期鉴定成功',
                        type: 'success',
                    })
                    this.selList();
                    this.status = false;
                }
            });
        },
        updXh1(id, state) {
            this.$http({
                method: 'put',
                url: '/dangan/updXh1',
                data: {
                    id: id,
                    zhuangtai: state
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
        },
        updXh2(id, state) {
            this.$http({
                method: 'put',
                url: '/dangan/updXh2',
                data: {
                    id: id,
                    zhuangtai: state
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
        },
        updXh(id, state) {
            this.$http({
                method: 'put',
                url: '/dangan/updXh',
                data: {
                    id: id,
                    zhuangtai: state
                }
            }).then(res => {
                if (res.state == 1) {
                    ElMessage({
                        message: '销毁成功',
                        type: 'success',
                    })
                    this.selList();
                    this.status = false;
                }
            });
        },
        updZt(id, state) {
            this.$http({
                method: 'put',
                url: '/dangan/updZhuangtai',
                data: {
                    id: id,
                    zhuangtai: state
                }
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
        },
        selList() {
            this.$http({
                method: 'get',
                url: '/dangan/selDanganPage',
                params: {
                    page: this.currentPage,
                    limit: this.pageSize
                }
            }).then(res => {
                this.tableData = res.data;
                this.total = res.count;
            });
        },
        selLeixingList() {
            this.$http({
                method: 'get',
                url: '/dalx/selDalxPage',
                params: {
                    page: 1,
                    limit: 100000000
                }
            }).then(res => {
                this.leixingList = [{
                    id: 0,
                    mingcheng: "请选择档案类型"
                }];
                for (let i = 0; i < res.data.length; i++) {
                    this.leixingList.push(res.data[i]);
                }
            });
        },
        selLaiyuanList() {
            this.$http({
                method: 'get',
                url: '/daly/selDalyPage',
                params: {
                    page: 1,
                    limit: 10000000
                }
            }).then(res => {
                this.laiyuanList = [{
                    id: 0,
                    mingcheng: "请选择档案来源"
                }];
                for (let i = 0; i < res.data.length; i++) {
                    this.laiyuanList.push(res.data[i]);
                }
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
        handleAvatarSuccess(res) {
            this.form.lujing = res.data.url;
        },
        handleAvatarRemove(res) {
            this.form.lujing = "";
        },
        add() {
            this.form = this.$options.data().form;
            this.status = true;
        },
        update(data) {
            this.$http({
                url: "/dangan/selDanganInfo",
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
                url: "/dangan/delDangan",
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
                        url: '/dangan/insDangan',
                        method: 'post',
                        data: this.form
                    }).then(res => {
                        if (res.state == 1) {
                            ElMessage({
                                message: '立卷成功',
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
                        url: '/dangan/updDangan',
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
<style scoped>
.avatar-uploader .avatar {
    width: 178px;
    height: 178px;
    display: block;
}
</style>
<style>
.avatar-uploader .el-upload {
    border: 1px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
    border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    text-align: center;
}
</style>