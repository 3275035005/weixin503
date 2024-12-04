
var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
Page({

  /**
   * 页面的初始数据
   */
  data: {
    current: 0,
    tabs: ['接单大厅', '我的发布'],
    thing: ["小件", "中件", "大件"],
    userId: '',
    // 拼团列表
    collage_list: [],
  },

  // tab
  tabChange(e) {
    let index = e.target.dataset.index
    this.setData({
      current: index
    })
    this.initData()
  },

  //  swiper
  swiperChange(e) {
    let index = e.detail.current
    this.setData({
      current: index
    })
    this.initData()
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      userId: wx.getStorageSync('token')
    })
    this.initData()
  },


  /**
 * 加载拼单数据
 */
  initData: function () {
    wx.showLoading({
      title: '数据加载中...'
    });
    call.getData('wx/getExpressageList/' + this.data.userId + '/' + this.data.current, this.onSuccess, this.onFail);
  },

  onSuccess(res) {

    wx.hideLoading();
    let that = this;
    if (res.success) {
      that.setData({
        collage_list: res.data.list
      })
    }
  },
  onFail() {
    wx.hideLoading();
    help.show("网络请求超时,请稍后再试")
  },

  /**
   * 领快递
   */
  clickGoodsInfo: function (e) {
    let id = e.currentTarget.dataset.pid;
    let that = this;
    wx.showModal({
      title: '确定要帮领快递吗',
      success(res) {
        if (res.confirm) {
          // 发送请求
          call.request('wx/bangExpressage', {
            goodsUserId: that.data.userId,
            id: id
          }, that.onSuccessJoin, that.onFailJoin);
        }
      }
    })
  },
  onSuccessJoin(res) {
    help.show("领取成功");
    this.initData();
  },
  onFailJoin() {
    help.show("网络请求失败");
  },


  DevTuchCall: function (e) {
    wx.navigateTo({
      url: '/pages/kuaidi_send/index'
    })
  }

})