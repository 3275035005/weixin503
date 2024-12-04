var call = require("../../utils/request.js")
var help = require("../../utils/help.js")

Page({

    data: {
        current: 0,
        tabs: ['全部同学', '我的结实同学'],
        studentList: [],
        userId:''
    },
    onLoad: function () {
       
        this.setData({
            userId: wx.getStorageSync('token')
        })
        this.getStudentList();
    },

    // tab
    tabChange(e) {
        let index = e.target.dataset.index
        this.setData({
            current: index
        })
        this.getStudentList();
    },
    // 查询所有咨询师
    getStudentList() {
        call.getData('wx/getThisStudentList/'+this.data.userId+'/'+this.data.current, this.onSuccessStudentAll, this.onFaiStudentAll);
    },
    onSuccessStudentAll(res) {
        if (res.code == 20000) {
            this.setData({
                studentList: res.data.list
            })
        }
    },
    onFaiStudentAll() {
        help.show("网络请求失败");
    },

})