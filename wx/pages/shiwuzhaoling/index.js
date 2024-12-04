var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
Page({
  data: {
    seek: [], // 寻物
    lose: [],// 失物招领
    indexTitle: 0, // 0失物 1寻物
    classifiedList:['证件','服饰','数码','日用品','其他'],
    thing: [{
      str: "全部",
      str1: -1
    }, {
      str: "证件",
      str1: 0
    }, {
      str: "服饰",
      str1: 1
    }, {
      str: "数码",
      str1: 2
    }, {
      str: "日用品",
      str1: 3
    }, {
      str: "其他",
      str1: 4
    }],
    nowThing: -1
  },

  /*标题的切换 */
  indexTitle1: function () {
    this.setData({
      indexTitle: 0,
      seek: [],
      lose: [],
    }), this.onLoad();
  },
  indexTitle2: function () {
    this.setData({
      indexTitle: 1,
      seek: [],
      lose: [],
    }), this.onLoad();
  },
  /*类目的改变,按全部键不反应*/
  thing: function (t) {
    this.setData({
      nowThing: t.currentTarget.dataset.index - 1
    })
    this.onLoad();
  },
  onLoad: async function () {

    // 发送请求
    call.getData('wx/getLostFoundList?type=' + this.data.indexTitle + '&classified=' + this.data.nowThing, this.onSuccess, this.onFail);
  },

  onSuccess(res) {

    wx.hideLoading();
    if (res.success) {
      if(this.data.indexTitle == 0){
        this.setData({
          lose: res.data.list
        })
      }else{
        this.setData({
          seek: res.data.list
        })
      }
      console.log(this.data.seek);
      
    }
  },
  onFail() {
    wx.hideLoading();
    help.show("网络请求超时,请稍后再试")
  },

  // 详情跳转
  loseContent: function (t) {
    if (0 == this.data.indexTitle) {
      var e = this.data.lose[t.currentTarget.dataset.index].id;
      wx.navigateTo({
        url: "/pages/zhaoling/index?id=" + e
      });
    } else {
      e = this.data.seek[t.currentTarget.dataset.index].id;
      wx.navigateTo({
        url: "/pages/xunwu/index?id=" + e
      });
    }
  },


});