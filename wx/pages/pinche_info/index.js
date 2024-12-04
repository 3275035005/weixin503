var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
Page({

  /**
   * 页面的初始数据
   */
  data: {

    userId: '',
    // 拼车信息
    goods: {},
    // 拼车用户信息
    goods_group: [],
    pid:''
  },

  /**
   * 
   * @param {*} options 
   */
  onLoad(options) {

    let info = wx.getStorageSync('token');
    if (info == null || info == '' || info == 'undefined' || info == undefined) {
      wx.reLaunch({
        url: '/pages/login/index',
      })
    } else {
      this.setData({
        userId: info,
        pid: options.id
      })

      call.getData('wx/getCarSharingById/' + options.id, this.onSuccessInfo, this.onFailInfo);
    }
  },
  onSuccessInfo(res) {
    console.log(res)
    if (res.success) {
      this.setData({
        goods: res.data.row,
        goods_group: res.data.row.userList,
      })

    }
  },
  onFailInfo() {
    help.show("网络请求失败");
  },

  // 参与拼团
  groupBooking: function () {
    let that = this;
    wx.showModal({
      title: '提示',
      content: '确认参加拼车吗?',
      success: function (res) {
        if (res.confirm) {
          wx.showLoading({
            title: '处理中...'
          });
          // 发送请求
          call.request('wx/joinCarSharing/'+that.data.userId+"/"+that.data.pid,{}, that.onSuccessJoin, that.onFailJoin);
        }
      }
    })

  },

  onSuccessJoin(res) {
    help.show(res.message);
    call.getData('wx/getCarSharingById/' + this.data.pid, this.onSuccessInfo, this.onFailInfo);
  },
  onFailJoin() {
    help.show("网络请求失败");
  },
})