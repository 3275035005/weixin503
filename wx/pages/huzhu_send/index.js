var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
var userId = '';

Page({

    /**
     * 页面的初始数据
     */
    data: {
        title: '',
        seat: 0
    },


    onLoad: function () {
        this.getInit();
    },

    /**
     * 获取登录用户信息
     */
    getInit() {
        userId = wx.getStorageSync("token")
        // 用户信息不存在跳转登录页面
        if (userId == null || userId == undefined || userId == '') {
            // 跳转到登录页面
            wx.reLaunch({
                url: '/pages/login/index'
            })
        }
    },

    updateBtn() {
        if (this.data.title == "") {
            wx.showToast({
                title: '起点站不能为空',
                icon: 'none',
                duration: 1000
            })
            return false;
        }
        if (this.data.seat == "" || this.data.seat == 0) {
            wx.showToast({
                title: '互助人数不能为空',
                icon: 'none',
                duration: 1000
            })
            return false;
        }
        wx.showLoading({
            title: '发起中...'
        });
        call.request('wx/sendMutual', {
            title: this.data.title,
            seat: this.data.seat,
            userId: userId
        }, this.onSuccess, this.onFail);
    },

    onSuccess(res) {
        wx.hideLoading();
        if (res.code == 20000) {
            help.show('发起互助成功')
            setTimeout(function () {
                wx.reLaunch({
                    url: '/pages/huzhu/index'
                })
            }, 2000)
        } else {
            help.show(res.message)
        }
    },

    onFail() {
        wx.hideLoading();
        help.show("网络请求超时,请稍后再试")
    },

})