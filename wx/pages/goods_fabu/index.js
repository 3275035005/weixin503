var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
Page({
  data: {
    userId:'',
    name: "",
    price: "",
    content: "",
    image: ""
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
  // 标题
  name: function (t) {
    this.setData({
      name: t.detail.value
    });
  },


  //价格
  price: function (t) {
    this.setData({
      price: t.detail.value
    });
  },
  //内容
  content: function (t) {
    this.setData({
      content: t.detail.value
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
  
  click1() {// 发布二手商品
    if(this.data.title == ""){
			wx.showToast({
				title: '请输入标题',
				icon: 'none',
				duration: 1000
			})
			return false;
    }
    if(this.data.price == ""){
			wx.showToast({
				title: '请输入价格',
				icon: 'none',
				duration: 1000
			})
			return false;
    }
    if(this.data.content == ""){
			wx.showToast({
				title: '请输入物品介绍',
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
    call.request('wx/sendGoods',{
      name:this.data.name,
      price:this.data.price,
      content:this.data.content,
      image:this.data.image,
      userId:this.data.userId
    }, this.onSuccessJoin, this.onFailJoin);
  },

  onSuccessJoin(res) {
    help.show("发布成功");

    wx.reLaunch({
      url: "../goods/index"
    });
  },
  onFailJoin() {
    help.show("网络请求失败");
  },
  
});