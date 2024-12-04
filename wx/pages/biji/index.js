var call = require("../../utils/request")
var help = require("../../utils/help")
var token = '';
Page({
  data: {
    content:'', // 笔记内容
    title:'', // 标题
  },

  onLoad: function() {
     this.getInit();
  },

  /**
   * 获取登录用户信息
   */
  getInit(){
    token = wx.getStorageSync("token")
    // 用户信息不存在跳转登录页面
    if(token == null || token == undefined || token == ''){
        // 跳转到登录页面
        wx.reLaunch({
          url: '/pages/login/index'
       })
    }
  },

  
  setTitle(e) {
    this.setData({
      title: e.detail.value
    })
  },
  setContent(e) {
    this.setData({
      content: e.detail.value
    })
  },

  /* 提交申报表 */
  inApplyData() {

    wx.showLoading({
        title: '正在提交...',
        mask: true
      })

      call.request('wx/sendCard' , {
          title: this.data.title,
          content: this.data.content,
          userId: token
      }, this.onSuccessSubmit, this.onFailSubmit);
  },
  onSuccessSubmit(res) {
    if(res.code == 20000){
      // 关闭加载框
      wx.hideLoading();
      help.okShow("打卡笔记成功");
      setTimeout(function () {
        wx.reLaunch({
          url: '/pages/index/index',
        })
      }, 2000)
    }else{
      help.show(res.message);
    }
  },
  onFailSubmit() {
    help.show("网络请求失败");
  },

})