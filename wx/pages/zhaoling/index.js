var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
Page({
  data: {
    lose:{},
    about:"none",
    classifiedList:['证件','服饰','数码','日用品','其他'],
    statusList:['认领中','已认领']
  },

  onLoad: function (option) {
    // 发送请求
    call.getData('wx/getLostFoundById?id=' + option.id, this.onSuccess, this.onFail);
    
  },

  onSuccess(res) {
    if (res.success) {
      this.setData({
        lose: res.data.row
      })
    }
  },
  onFail() {
   
    help.show("网络请求超时,请稍后再试")
  },

  showPop() {
    this.setData({
      about: "block"
    })
  },

})