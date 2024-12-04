var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
Page({
  data: {
    list: [],
    indexTitle: 0, // 0商品大厅 1我的发布
    classifiedList:['在售','已出售']
  },
  /*标题的切换 */
  indexTitle1: function () {
    this.setData({
      indexTitle: 0,
      list: []
    }), this.onLoad();
  },
  indexTitle2: function () {
    this.setData({
      indexTitle: 1,
      list: [],
    }), this.onLoad();
  },
  onLoad: async function () {
    this.setData({
      userId: wx.getStorageSync('token')
    })

    // 发送请求
    call.getData('wx/getGoodsList/' + this.data.userId + '/' + this.data.indexTitle, this.onSuccess, this.onFail);
  },

  onSuccess(res) {
    wx.hideLoading();
    if (res.success) {
      this.setData({
        list: res.data.list
      })
      
    }
  },
  onFail() {
    wx.hideLoading();
    help.show("网络请求超时,请稍后再试")
  },

  // 详情跳转
  loseContent: function (t) {
    var e = this.data.list[t.currentTarget.dataset.index].id;
    wx.navigateTo({
      url: "/pages/goodsInfo/index?id=" + e
    });
  },


});