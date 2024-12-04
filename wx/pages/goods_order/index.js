var call = require("../../utils/request.js")
var help = require("../../utils/help.js")

Page({

  /**
   * 页面的初始数据
   */
  data: {
    name: '',
    phone: '',
    address: '',
    goodsId: '',
    userId:'',
  },


  onLoad: function (options) {
    const { id } = options;
    this.setData({
      goodsId: id
    })
    this.getInit();
  },

  /**
   * 获取登录用户信息
   */
  getInit() {
    let userId = wx.getStorageSync("token")
    // 用户信息不存在跳转登录页面
    if (userId == null || userId == undefined || userId == '') {
      // 跳转到登录页面
      wx.reLaunch({
        url: '/pages/login/index'
      })
    }else{
      this.setData({
        userId: userId
      })
    }
  },

  updateBtn() {
    if (this.data.name == "") {
      wx.showToast({
        title: '收货人不能为空',
        icon: 'none',
        duration: 1000
      })
      return false;
    }
    if (this.data.phone == "") {
      wx.showToast({
        title: '手机号不能为空',
        icon: 'none',
        duration: 1000
      })
      return false;
    }
    if (this.data.address == "") {
      wx.showToast({
        title: '地址不能为空',
        icon: 'none',
        duration: 1000
      })
      return false;
    }
    wx.showLoading({
      title: '购买中...'
    });
    call.request('wx/sendOrder', {
      name: this.data.name,
      phone: this.data.phone,
      address: this.data.address,
      goodsId: this.data.goodsId,
      buyUserId: this.data.userId
    }, this.onSuccess, this.onFail);
  },

  onSuccess(res) {
    wx.hideLoading();
    if (res.code == 20000) {
      help.show('购买成功')
      setTimeout(function () { // 下单成功跳转页面
        wx.reLaunch({
          url: '/pages/goods/index'
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