var call = require("../../utils/request.js")
var help = require("../../utils/help.js")

Page({
  data: {
    year: 0,
    month: 0,
    date: ['日', '一', '二', '三', '四', '五', '六'],
    dateArr: [],
    isToday: 0,
    isTodayWeek: false,
    todayIndex: 0,
    isDisabled: false,
    signLogDate: [],
    sumDays: 0,
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
  getHome() {

    call.getData('wx/getCardDate/'+this.data.userId, this.onSuccessclassAll, this.onFaiclassAll);
  
  },

    onSuccessclassAll(res) {
      console.log(res);
      if (res.success) {
        this.setData({
          signLogDate: res.data.row.signLogDate,
          sumDays: res.data.row.sumDays
        })
        let now = new Date();
    let year = now.getFullYear();
    let month = now.getMonth() + 1;
    this.dateInit();
    this.setData({
      year: year,
      month: month,
      isToday: '' + year + month + now.getDate()
    })
      }
    },
    onFaiclassAll() {
      help.show("网络请求失败");
    },
  

  dateInit: function (setYear, setMonth) {

    //全部时间的月份都是按0~11基准，显示月份才+1
    let dateArr = [];                        //需要遍历的日历数组数据
    let arrLen = 0;                            //dateArr的数组长度
    let now = setYear ? new Date(setYear, setMonth) : new Date();
    console.log("-=-=-=-=-=-=-=:" + now)
    let year = setYear || now.getFullYear();
    let nextYear = 0;
    let month = setMonth || now.getMonth();                    //没有+1方便后面计算当月总天数
    let nextMonth = (month + 1) > 11 ? 1 : (month + 1);
    let startWeek = new Date(year + ',' + (month + 1) + ',' + 1).getDay();                            //目标月1号对应的星期
    let dayNums = new Date(year, nextMonth, 0).getDate();                //获取目标月有多少天
    let obj = {};
    let num = 0;

    if (month + 1 > 11) {
      nextYear = year + 1;
      dayNums = new Date(nextYear, nextMonth, 0).getDate();

    }
    arrLen = startWeek + dayNums;
    for (let i = 0; i < arrLen; i++) {
      if (i >= startWeek) {

        num = i - startWeek + 1;
        obj = {
          isToday: '' + year + (month + 1) + num,
          dateNum: num,
          weight: 5,
          isSign: 0
        };

      } else {
        obj = {};
      }
      dateArr[i] = obj;
    }
    //
    for (let i = 0; i < dateArr.length; i++) {
      dateArr[i].isSign = 0
      console.log("dateArr[i].isToday ---->" + dateArr[i].isToday)
    }
    console.log("66666666666666 ---->" + this.data.signLogDate)
    for (let j = 0; j < this.data.signLogDate.length; j++) {
      for (let k = 0; k < dateArr.length; k++) {
        if (this.data.signLogDate[j] == dateArr[k].isToday) {
          dateArr[k].isSign = 1
          console.log(dateArr[k].isToday + "   ``````````````````````````````````````111111111111111")
        }
      }
    }

    this.setData({
      dateArr: dateArr
    })

    let nowDate = new Date();
    let nowYear = nowDate.getFullYear();
    let nowMonth = nowDate.getMonth() + 1;
    let nowWeek = nowDate.getDay();
    let getYear = setYear || nowYear;
    let getMonth = setMonth >= 0 ? (setMonth + 1) : nowMonth;

    if (nowYear == getYear && nowMonth == getMonth) {
      this.setData({
        isTodayWeek: true,
        todayIndex: nowWeek

      })

    } else {
      this.setData({
        isTodayWeek: false,
        todayIndex: - 1
      })
    }


  },



  lastMonth: function () {
    //全部时间的月份都是按0~11基准，显示月份才+1
    let year = this.data.month - 2 < 0 ? this.data.year - 1 : this.data.year;
    let month = this.data.month - 2 < 0 ? 11 : this.data.month - 2;
    this.setData({
      year: year,
      month: (month + 1)
    })
    this.dateInit(year, month);
  },
  nextMonth: function () {
    //全部时间的月份都是按0~11基准，显示月份才+1
    let year = this.data.month > 11 ? this.data.year + 1 : this.data.year;
    let month = this.data.month > 11 ? 0 : this.data.month;
    this.setData({
      year: year,
      month: (month + 1)
    })
    this.dateInit(year, month);
  },

  //跳转打卡页面
  dakaBtn(){
    wx.navigateTo({
      url: '/pages/biji/index',
    })
  },

  //条状我的笔记
  isSignFunc: function () {
    wx.navigateTo({
      url: '/pages/biji_list/index',
    })
  },
})