var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
Page({
  data: {

    indexTitle: 0, // 大标题
    userId:'',
    thing: [{
      str: "小件"
    }, {
      str: "中件"
    }, {
      str: "大件"
    }],
    phone: "",
    startAddress: "",
    endAddress: ""
  },


  onLoad: function (e) {
    let info = wx.getStorageSync('token');
    if (info == null || info == '' || info == 'undefined' || info == undefined) {
      wx.reLaunch({
        url: '/pages/login/index',
      })
    } else {
      this.setData({
        userId: info,
      })
    }
  },
  startAddress: function (t) {
    this.setData({
      startAddress: t.detail.value
    });
  },

  // 手机号
  phone: function (t) {
    this.setData({
      phone: t.detail.value
    });
  },
  // 类型
  thing: function (t) {
    this.setData({
      thingIndex: t.currentTarget.dataset.index
    })
  },

  //地址
  endAddress: function (t) {
    this.setData({
      endAddress: t.detail.value
    });
  },

  click1() {
    if(this.data.startAddress == ""){
			wx.showToast({
				title: '请输入领取地点',
				icon: 'none',
				duration: 1000
			})
			return false;
    }
    if(this.data.endAddress == ""){
			wx.showToast({
				title: '请输入收货地点',
				icon: 'none',
				duration: 1000
			})
			return false;
    }
    if(this.data.phone == ""){
			wx.showToast({
				title: '请输入手机号',
				icon: 'none',
				duration: 1000
			})
			return false;
    }
    
    // 发送请求
    call.request('wx/sendExpressage',{
      endAddress:this.data.endAddress,
      startAddress:this.data.startAddress,
      phone:this.data.phone,
      userId:this.data.userId,
      type: this.data.thingIndex
    }, this.onSuccessJoin, this.onFailJoin);
  },

  onSuccessJoin(res) {
    wx.showModal({
      title: "申请成功",
      content: "请等待送货上门",
      showCancel: !0,
      cancelColor: "#000000",
      confirmText: "确定",
      confirmColor: "#576B95",
      complete: function () {
        wx.reLaunch({
          url: "../kuaidi/index"
        });
      }
    });
  },
  onFailJoin() {
    help.show("网络请求失败");
  }
});