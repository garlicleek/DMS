<template>
    <div>
        <el-container style="position: fixed;height: 100%;width: 100%;top: 0px;left: 0px;">
            <el-aside width="200px">
                <h3>档案管理系统</h3>
                <el-menu default-active="/home/dangan">
                    <el-sub-menu index="1">
                        <template #title>
                            <el-icon>
                                <HomeFilled />
                            </el-icon>
                            <span>档案管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="/home/dangan" @click="open('dangan')">查看档案列表</el-menu-item>
                        </el-menu-item-group>
                    </el-sub-menu>
                    <el-sub-menu index="2">
                        <template #title>
                            <el-icon>
                                <Edit />
                            </el-icon>
                            <span>借阅管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="2-1" @click="open('jy')">查看借阅列表</el-menu-item>
                        </el-menu-item-group>
                    </el-sub-menu>
                    <el-sub-menu index="3">
                        <template #title>
                            <el-icon>
                                <Edit />
                            </el-icon>
                            <span>用户管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="3-1" @click="open('user')">查看用户列表</el-menu-item>
                        </el-menu-item-group>
                    </el-sub-menu>
                    <el-sub-menu index="4">
                        <template #title>
                            <el-icon>
                                <Edit />
                            </el-icon>
                            <span>部门管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="4-1" @click="open('bumen')">查看部门列表</el-menu-item>
                        </el-menu-item-group>
                    </el-sub-menu>
                    <el-sub-menu index="5">
                        <template #title>
                            <el-icon>
                                <Edit />
                            </el-icon>
                            <span>档案类型管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="5-1" @click="open('dalx')">查看档案类型列表</el-menu-item>
                        </el-menu-item-group>
                    </el-sub-menu>
                    <el-sub-menu index="6">
                        <template #title>
                            <el-icon>
                                <Edit />
                            </el-icon>
                            <span>档案来源管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="6-1" @click="open('daly')">查看档案来源列表</el-menu-item>
                        </el-menu-item-group>
                    </el-sub-menu>
                    <el-sub-menu index="7">
                        <template #title>
                            <el-icon>
                                <Edit />
                            </el-icon>
                            <span>权限管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="7-1" @click="open('qx')">查看权限列表</el-menu-item>
                        </el-menu-item-group>
                    </el-sub-menu>
                    <el-sub-menu index="8">
                        <template #title>
                            <el-icon>
                                <Edit />
                            </el-icon>
                            <span>岗位权限管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="8-1" @click="open('bmqx')">查看岗位权限列表</el-menu-item>
                        </el-menu-item-group>
                    </el-sub-menu>
                    <el-sub-menu index="9">
                        <template #title>
                            <el-icon>
                                <Edit />
                            </el-icon>
                            <span>档案操作历史</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="9-1" @click="open('dals')">查看档案操作历史</el-menu-item>
                        </el-menu-item-group>
                    </el-sub-menu>
                </el-menu>
            </el-aside>
            <el-container>
                <el-header style="border-width: 1px;border-style: solid;border-color:#DCDFE6;">
                <el-link type="primary" style="position: absolute;right: 6%;top: 2%;font-size: large;" href="/">退出</el-link>
            </el-header>
                <el-main>
                    <el-breadcrumb separator="/">
                        <el-breadcrumb-item :to="{ path: '' }">{{ fm }}</el-breadcrumb-item>
                        <el-breadcrumb-item>{{ sm }}</el-breadcrumb-item>
                    </el-breadcrumb>
                    <dangan v-if="menu == 'dangan'"></dangan>
                    <jy v-if="menu == 'jy'"></jy>
                    <user v-if="menu == 'user'"></user>
                    <bumen v-if="menu == 'bumen'"></bumen>
                    <dalx v-if="menu == 'dalx'"></dalx>
                    <daly v-if="menu == 'daly'"></daly>
                    <qx v-if="menu == 'qx'"></qx>
                    <bmqx v-if="menu == 'bmqx'"></bmqx>
                    <dals v-if="menu == 'dals'"></dals>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>
  

<script>
import {
    HomeFilled,
    Edit
} from '@element-plus/icons-vue'
import dangan from './home/Dangan.vue'
import jy from './home/Jy.vue'
import user from './home/User.vue'
import bumen from './home/Bumen.vue'
import dalx from './home/Dalx.vue'
import daly from './home/Daly.vue'
import qx from './home/Quanxian.vue'
import bmqx from './home/Bmqx.vue'
import dals from './home/Dals.vue'

export default {
    data() {
        return {
            menu: "dangan",
            fm: "档案管理",
            sm: "档案列表"
        }
    },
    components: {
        dangan,
        jy,
        user,
        bumen,
        dalx,
        daly,
        qx,
        bmqx,
        dals,
        HomeFilled,
        Edit
    },
    beforeCreate() {
        document.querySelector('body').setAttribute('style', "background: url('');background-color:white")
    },
    beforeDestroy() {
        document.querySelector('body').removeAttribute('style')
    },
    methods: {
        open(data) {
            this.menu = data;
            if (data == 'dangan') {
                this.fm = "档案管理";
                this.sm = "档案列表";
            }
            if (data == 'jy') {
                this.fm = "借阅管理";
                this.sm = "借阅列表";
            }
            if (data == 'user') {
                this.fm = "用户管理";
                this.sm = "用户列表";
            }
            if (data == 'bumen') {
                this.fm = "部门管理";
                this.sm = "部门列表";
            }
            if (data == 'dalx') {
                this.fm = "档案类型管理";
                this.sm = "档案类型列表";
            }
            if (data == 'daly') {
                this.fm = "档案来源管理";
                this.sm = "档案来源列表";
            }
            if (data == 'qx') {
                this.fm = "权限管理";
                this.sm = "权限列表";
            }
            if (data == 'bmqx') {
                this.fm = "岗位权限管理";
                this.sm = "岗位权限列表";
            }
            if (data == 'dals') {
                this.fm = "档案操作历史管理";
                this.sm = "档案操作历史列表";
            }
        }
    }
}
</script>

<style></style>