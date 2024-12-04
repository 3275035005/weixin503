var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
Page({
  data: {

    indexTitle: 0, // 大标题
    userId:'',
    thing: [{
      str: "证件"
    }, {
      str: "服饰"
    }, {
      str: "数码"
    }, {
      str: "日用品"
    }, {
      str: "其他"
    }],


    thingIndex: 0,
    title: "",
    phone: "",
    remark: "",
    image: "",
    address:""
  },

  /*去登陆*/
  goto: function () {
    wx.switchTab({
      url: '/pages/user/user',
    })
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
  //失物招领与寻物启事切换
  indexTitle1: function () {
    this.setData({
      indexTitle: 0,
      thingIndex: 0,
      title: "",
      phone: "",
      remark: "",
      image: "",
      address:""
    });
  },
  indexTitle2: function () {
    this.setData({
      indexTitle: 1,
      thingIndex: 0,
      title: "",
      phone: "",
      remark: "",
      image: "",
    });
  },

  // 标题
  title: function (t) {
    this.setData({
      title: t.detail.value
    });
  },

  // 手机号
  phone: function (t) {
    this.setData({
      phone: t.detail.value
    });
  },
  // 物品种类
  thing: function (t) {
    this.setData({
      thingIndex: t.currentTarget.dataset.index
    })
  },

  //地址
  address: function (t) {
    this.setData({
      address: t.detail.value
    });
  },
  //备注
  remark: function (t) {
    this.setData({
      remark: t.detail.value
    });
  },
  // 图片
  uploadimg: function () {
    var t = this;
    wx.chooseImage({
      sizeType: ["original", "compressed"],
      sourceType: ["album", "camera"],
      success: function (res) {
      const base64Path = wx.getFileSystemManager().readFileSync(res.tempFilePaths[0], 'base64');
      t.setData({
        image: "data:image/png;base64,"+ base64Path
      });
      }
    });
  },
  
  click1() {// 发布失物招领
    if(this.data.title == ""){
			wx.showToast({
				title: '请输入标题',
				icon: 'none',
				duration: 1000
			})
			return false;
    }
    if(this.data.address == ""){
			wx.showToast({
				title: '请输入物品位置',
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
    if(this.data.image == ""){
			wx.showToast({
				title: '请选择图片',
				icon: 'none',
				duration: 1000
			})
			return false;
    }
    
    // 发送请求
    call.request('wx/sendLostFound',{
      title:this.data.title,
      phone:this.data.phone,
      address:this.data.address,
      image:this.data.image,
      userId:this.data.userId,
      remark:this.data.remark,
      type:'0',
      classified: this.data.thingIndex
    }, this.onSuccessJoin, this.onFailJoin);
  },

  onSuccessJoin(res) {
    wx.showModal({
      title: "提交成功",
      content: "感谢您的拾金不昧！",
      showCancel: !0,
      cancelColor: "#000000",
      confirmText: "确定",
      confirmColor: "#576B95",
      complete: function () {
        wx.reLaunch({
          url: "../shiwuzhaoling/index"
        });
      }
    });
  },
  onFailJoin() {
    help.show("网络请求失败");
  },


  click2() {
    if(this.data.title == ""){
			wx.showToast({
				title: '请输入标题',
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
    if(this.data.image == ""){
			wx.showToast({
				title: '请选择图片',
				icon: 'none',
				duration: 1000
			})
			return false;
    }
    
    // 发送请求
    call.request('wx/sendLostFound',{
      title:this.data.title,
      phone:this.data.phone,
      image:this.data.image,
      remark:this.data.remark,
      userId:this.data.userId,
      type:'1',
      classified: this.data.thingIndex
    }, this.onSuccess, this.onFail);
  },

  
  onSuccess(res) {
    wx.showModal({
      title: "提交成功",
      content: "发布寻找物品",
      showCancel: !0,
      cancelColor: "#000000",
      confirmText: "确定",
      confirmColor: "#576B95",
      complete: function () {
        wx.reLaunch({
          url: "../shiwuzhaoling/index"
        });
      }
    });
  },
  onFail() {
    help.show("网络请求失败");
  }
});