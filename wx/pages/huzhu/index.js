
var call = require("../../utils/request.js")
var help = require("../../utils/help.js")
Page({

    /**
     * 页面的初始数据
     */
    data: {
        current:0,
        tabs:['互助大厅','我的互助',],
        // 互助列表
        collage_list:[],
    },

     // tab
     tabChange(e){
        let token = wx.getStorageSync('token');
        let index = e.target.dataset.index
        this.setData({
            current: index
        })
        // 发送请求
        call.getData('wx/getMutualList?type='+index+'&userId='+token, this.onSuccess, this.onFail);
    },
    
    //  swiper
    swiperChange(e){
        let token = wx.getStorageSync('token');
        let index = e.detail.current
        this.setData({
            current: index
        }) 
        call.getData('wx/getMutualList?type='+index+'&userId='+token, this.onSuccess, this.onFail);
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        this.initData()
    },


    /**
	 * 加载拼单数据
	 */
	initData: function () {
		wx.showLoading({
			title: '数据加载中...'
		});
		call.getData('wx/getMutualList?type=0', this.onSuccess, this.onFail);
    },

    onSuccess(res) {
        
		wx.hideLoading();
		if (res.success) {
			this.setData({
              collage_list:res.data.list
			})
		}
	},
	onFail() {
		wx.hideLoading();
		help.show("网络请求超时,请稍后再试")
    },
    
    /**
     * 获取拼车信息
     */
    clickGoodsInfo: function(e){
        let id = e.currentTarget.dataset.pid;
        console.log(id);
        wx.navigateTo({
            url: '/pages/huzhu_info/index?id='+id
        })
        
    },
    DevTuchCall:function(e){
        wx.navigateTo({
            url: '/pages/huzhu_send/index'
        })
    }

})