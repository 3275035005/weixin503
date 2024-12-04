// pages/zhuce/index.js
const app = getApp();
var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
var userInfo = null;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    username: '',
    phone: '',
    password: '',
    name: ''
  },
  // 获取input输入框的值
  getNameValue: function (e) {
    this.setData({
      name: e.detail.value
    })
  },

  getPhoneValue: function (e) {
    this.setData({
      phone: e.detail.value
    })
  },

  getUsernameValue: function (e) {
    this.setData({
      username: e.detail.value
    })
  },


  getPassword: function (e) {
    this.setData({
      password: e.detail.value
    })
  },

  // 手机登录 - 提交表单信息
  register_Btn: function () {
    console.log(this.data.phone);
    var myreg = /^(14[0-9]|13[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]|19[0-9])\d{8}$$/;
    if (this.data.phone == "") {
      wx.showToast({
        title: '手机号不能为空',
        icon: 'none',
        duration: 1000
      })
    } else if (!myreg.test(this.data.phone)) {
      wx.showToast({
        title: '请输入正确的手机号',
        icon: 'none',
        duration: 1000
      })
    } else if (this.data.password == "") {
      wx.showToast({
        title: '密码不能为空',
        icon: 'none',
        duration: 1000
      })
    } else if (this.data.name == "") {
      wx.showToast({
        title: '姓名不能为空',
        icon: 'none',
        duration: 1000
      })
    }else {
      wx.showLoading({
        title: '数据上传中...'
      });
      let that = this;
      wx.getUserProfile({
        desc: "用于完善用户资料", // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
        success: (res) => {
          call.request('wx/register',{
            name: that.data.name,
            phone: that.data.phone,
            username:that.data.username,
            password:that.data.password,
            avatar: JSON.parse(res.rawData).avatarUrl
          }, that.onSuccess, that.onFail);
        },
        fail: function (err) {
          console.log(err);
        },
      });
      
     
    }
  },
  onSuccess(res) {
    wx.hideLoading();
    if (res.success) {
      help.show("注册成功")
      setTimeout(function () {
        wx.reLaunch({
          url: '/pages/login/index',
        })
      }, 2000)
    } else {
      help.show(res.errMsg)
    }
  },
  onFail() {
    wx.hideLoading();
    help.show("网络请求超时,请稍后再试")
  },




  toLogin() {
    wx.reLaunch({
      url: '/pages/login/index',
    })
  }

})