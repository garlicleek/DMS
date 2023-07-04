<template>
    <el-button type="primary" @click="add" style="position: fixed;left:230px;top:110px">立卷</el-button>
    <div style="position: fixed;top:150px;left:230px;">
        <el-table :data="tableData" style="width: 100%">
            <el-table-column prop="id" label="ID" width="60" />
            <el-table-column prop="bianhao" label="编号" width="100" />
            <el-table-column prop="mingcheng" label="名称" width="100" />
            <el-table-column prop="lujing" label="电子档案" width="150">
                <template v-slot="scope">
                    <el-link type="primary" :href="'http://123.249.31.57:82' + scope.row.lujing">查看电子档案</el-link>
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
                        @click="commonForm.archive_id = scope.row.id; commonForm.caozuo_id = 1; commonStatus = true">审批通过</el-button>
                    <el-button type="warning" v-if="scope.row.zhuangtai == 0"
                        @click="commonForm.archive_id = scope.row.id; commonForm.caozuo_id = 2; commonStatus = true">审批不通过</el-button>
                    <el-button type="warning" v-if="scope.row.zhuangtai == 1"
                        @click="commonForm.archive_id = scope.row.id; commonForm.caozuo_id = 3; commonStatus = true">交接</el-button>
                    <el-button type="warning" v-if="scope.row.zhuangtai == 3"
                        @click="commonForm.archive_id = scope.row.id; commonForm.caozuo_id = 4; commonStatus = true">鉴定</el-button>
                    <el-button type="warning" v-if="scope.row.zhuangtai == 4"
                        @click="commonForm.archive_id = scope.row.id; commonForm.caozuo_id = 5; commonStatus = true">归档</el-button>
                    <el-button type="warning" v-if="scope.row.zhuangtai == 5"
                        @click="commonForm.archive_id = scope.row.id; commonForm.caozuo_id = 6; commonStatus = true">保管</el-button>
                    <el-button type="warning" v-if="scope.row.zhuangtai == 6"
                        @click="commonForm.archive_id = scope.row.id; commonForm.caozuo_id = 14; commonStatus = true">申请借阅</el-button>
                    <el-button type="warning" v-if="scope.row.zhuangtai == 7"
                        @click="commonForm.archive_id = scope.row.id; commonForm.caozuo_id = 15; commonStatus = true">归还借阅</el-button>
                    <el-button type="warning" v-if="scope.row.zhuangtai == 6"
                        @click="commonForm.archive_id = scope.row.id; commonForm.caozuo_id = 8; commonStatus = true">定期鉴定</el-button>
                    <el-button type="warning" v-if="scope.row.zhuangtai == 8"
                        @click="commonForm.archive_id = scope.row.id; commonForm.caozuo_id = 9; commonStatus = true">通过销毁申请</el-button>
                    <el-button type="warning" v-if="scope.row.zhuangtai == 8"
                        @click="commonForm.archive_id = scope.row.id; commonForm.caozuo_id = 10; commonStatus = true">拒绝销毁申请</el-button>
                    <el-button type="warning" v-if="scope.row.zhuangtai == 9"
                        @click="commonForm.archive_id = scope.row.id; commonForm.caozuo_id = 11; commonStatus = true">销毁</el-button>
                    <el-button type="danger" v-if="scope.row.zhuangtai == 11"
                        @click="commonForm.archive_id = scope.row.id; commonForm.caozuo_id = 12; commonStatus = true">删除</el-button>
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
                <el-upload action="http://123.249.31.57:82/upload/file" :headers="headers" :on-success="handleAvatarSuccess"
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
        <el-form :model="gdForm" label-width="120px" ref="gdFormRef">
            <el-form-item label="档案架" required prop="danganjia">
                <el-input v-model="gdForm.danganjia" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="updGd1">提交</el-button>
            </el-form-item>
        </el-form>
    </el-dialog>
    <el-dialog :model-value="commonStatus" @close="commonStatus = false">
        <el-form :model="commonForm" label-width="120px" ref="commonFormRef">
            <el-form-item label="备注" required prop="beizhu">
                <el-input v-model="commonForm.beizhu" />
            </el-form-item>
            <el-form-item>
                <el-button v-if="commonForm.caozuo_id == 1" type="primary" @click="updSp1">审批通过</el-button>
                <el-button v-if="commonForm.caozuo_id == 2" type="primary" @click="updSp2">审批不通过</el-button>
                <el-button v-if="commonForm.caozuo_id == 3" type="primary" @click="updJj">交接</el-button>
                <el-button v-if="commonForm.caozuo_id == 4" type="primary" @click="updJd">鉴定</el-button>
                <el-button v-if="commonForm.caozuo_id == 5" type="primary" @click="updGd">归档</el-button>
                <el-button v-if="commonForm.caozuo_id == 6" type="primary" @click="updBg">保管</el-button>
                <el-button v-if="commonForm.caozuo_id == 14" type="primary" @click="updJy">申请借阅</el-button>
                <el-button v-if="commonForm.caozuo_id == 15" type="primary" @click="updGh">归还借阅</el-button>
                <el-button v-if="commonForm.caozuo_id == 8" type="primary" @click="updDqjd">定期鉴定</el-button>
                <el-button v-if="commonForm.caozuo_id == 9" type="primary" @click="updXh1">通过销毁申请</el-button>
                <el-button v-if="commonForm.caozuo_id == 10" type="primary" @click="updXh2">拒绝销毁申请</el-button>
                <el-button v-if="commonForm.caozuo_id == 11" type="primary" @click="updXh">销毁</el-button>
                <el-button v-if="commonForm.caozuo_id == 12" type="primary" @click="delet">删除</el-button>
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
            commonForm: {
                id: 0,
                archive_id: 0,
                caozuo_id: 0,
                beizhu: ""
            },
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
            commonStatus: false,
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
        updSp1() {
            this.$http({
                method: 'put',
                url: '/dangan/updSp1',
                data: this.commonForm
            }).then(res => {
                if (res.state == 1) {
                    ElMessage({
                        message: '审批成功',
                        type: 'success',
                    })
                    this.selList();
                    this.commonStatus = false;
                }
            });
        },
        updSp2() {
            this.$http({
                method: 'put',
                url: '/dangan/updSp2',
                data: this.commonForm
            }).then(res => {
                if (res.state == 1) {
                    ElMessage({
                        message: '审批成功',
                        type: 'success',
                    })
                    this.selList();
                    this.commonStatus = false;
                }
            });
        },
        updJj() {
            this.$http({
                method: 'put',
                url: '/dangan/updJj',
                data: this.commonForm
            }).then(res => {
                if (res.state == 1) {
                    ElMessage({
                        message: '交接成功',
                        type: 'success',
                    })
                    this.selList();
                    this.commonStatus = false;
                }
            });
        },
        updJd() {
            this.$http({
                method: 'put',
                url: '/dangan/updJd',
                data: this.commonForm
            }).then(res => {
                if (res.state == 1) {
                    ElMessage({
                        message: '鉴定成功',
                        type: 'success',
                    })
                    this.selList();
                    this.commonStatus = false;
                }
            });
        },
        updGd() {
            this.$http({
                method: 'put',
                url: '/dangan/updGd',
                data: this.commonForm
            }).then(res => {
                if (res.state == 1) {
                    ElMessage({
                        message: '操作记录成功',
                        type: 'success',
                    })
                    this.selList();
                    this.commonStatus = false;
                    this.gdForm.id = this.commonForm.archive_id;
                    this.gdStatus = true;
                }
            });
        },
        updGd1() {
            this.$http({
                method: 'put',
                url: '/dangan/updGd1',
                data: {
                    id: this.gdForm.id,
                    zhuangtai: 5,
                    danganjia: this.gdForm.danganjia
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
        updBg() {
            this.$http({
                method: 'put',
                url: '/dangan/updBg',
                data: this.commonForm
            }).then(res => {
                if (res.state == 1) {
                    ElMessage({
                        message: '保管成功',
                        type: 'success',
                    })
                    this.selList();
                    this.commonStatus = false;
                }
            });
        },
        updJy() {
            this.$http({
                method: 'put',
                url: '/dangan/updJy',
                data: this.commonForm
            }).then(res => {
                if (res.state == 1) {
                    ElMessage({
                        message: '申请借阅成功',
                        type: 'success',
                    })
                    this.selList();
                    this.commonStatus = false;
                }
            });
        },
        updGh() {
            this.$http({
                method: 'put',
                url: '/dangan/updGh',
                data: this.commonForm
            }).then(res => {
                if (res.state == 1) {
                    ElMessage({
                        message: '归还成功',
                        type: 'success',
                    })
                    this.selList();
                    this.commonStatus = false;
                }
            });
        },
        updDqjd() {
            this.$http({
                method: 'put',
                url: '/dangan/updDqjd',
                data: this.commonForm
            }).then(res => {
                if (res.state == 1) {
                    ElMessage({
                        message: '定期鉴定成功',
                        type: 'success',
                    })
                    this.selList();
                    this.commonStatus = false;
                }
            });
        },
        updXh1() {
            this.$http({
                method: 'put',
                url: '/dangan/updXh1',
                data: this.commonForm
            }).then(res => {
                if (res.state == 1) {
                    ElMessage({
                        message: '审批成功',
                        type: 'success',
                    })
                    this.selList();
                    this.commonStatus = false;
                }
            });
        },
        updXh2() {
            this.$http({
                method: 'put',
                url: '/dangan/updXh2',
                data: this.commonForm
            }).then(res => {
                if (res.state == 1) {
                    ElMessage({
                        message: '审批成功',
                        type: 'success',
                    })
                    this.selList();
                    this.commonStatus = false;
                }
            });
        },
        updXh() {
            this.$http({
                method: 'put',
                url: '/dangan/updXh',
                data: this.commonForm
            }).then(res => {
                if (res.state == 1) {
                    ElMessage({
                        message: '销毁成功',
                        type: 'success',
                    })
                    this.selList();
                    this.commonStatus = false;
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
                data: this.commonForm
            }).then(res => {
                if (res.state == 1) {
                    ElMessage({
                        message: '删除成功',
                        type: 'success'
                    })
                    this.selList();
                    this.commonStatus = false;
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