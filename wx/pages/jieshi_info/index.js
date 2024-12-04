var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
var userId = '';
var counselorId = '';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    itme: {},
  },

  onLoad: function (options) {
    const { id } = options;
    counselorId = id
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
    } else {
      this.getInfo();
    }
  },

  getInfo() {
    call.getData('wx/getSeedStudentById/' + userId + "/" + counselorId, this.onSuccessclassAll, this.onFaiclassAll);
  },
  onSuccessclassAll(res) {
    if (res.code == 20000) {
      this.setData({
        item: res.data.row
      })
    }
  },
  onFaiclassAll() {
    help.show("网络请求失败");
  },

  // 跳转结实
  onlineBtnOk(){
    call.request('wx/seedStudent', {
        userId: userId,
        studentId: counselorId
    }, this.onSuccess, this.onFail);
  },

    // 取消结实
    onlineBtnError(){
        call.request('wx/delStudent', {
            userId: userId,
            studentId: counselorId
        }, this.onSuccess, this.onFail);
    },
   

    onSuccess(res) {
        help.show(res.message)
        this.getInfo();
    },

    onFail() {
        wx.hideLoading();
        help.show("网络请求超时,请稍后再试")
    },
})