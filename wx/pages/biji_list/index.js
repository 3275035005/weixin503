var call = require("../../utils/request.js")
var help = require("../../utils/help.js")

Page({

    /**
     * 页面的初始数据
     */
    data: {
        list:[],
        userId:''
    },
    onLoad: function () {
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
      this.getHome();
    }
  },
  getHome(){
      call.getData('wx/getCardNote/'+this.data.userId , this.onSuccessclassAll, this.onFaiclassAll);
    },
  onSuccessclassAll(res) {
    if(res.code == 20000){
     this.setData({
      list:res.data.list
    })
  }
},
onFaiclassAll() {
  help.show("网络请求失败");
}
})
