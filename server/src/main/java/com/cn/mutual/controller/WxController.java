package com.cn.mutual.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cn.mutual.entity.*;
import com.cn.mutual.service.*;
import com.cn.mutual.utils.response.R;
import com.cn.mutual.utils.utils.AceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  对小程序提供后端接口
 * </p>
 */
@RestController
@RequestMapping("/wx")
public class WxController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private JournalismService journalismService;

    @Autowired
    private JournalismClassifiedService journalismClassifiedService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private CardNoteService cardNoteService;

    @Autowired
    private LostFoundService lostFoundService;

    @Autowired
    private CarSharingService carSharingService;

    @Autowired
    private CarSharingInfoService carSharingInfoService;

    @Autowired
    private ExpressageService expressageService;

    @Autowired
    private MutualInfoService mutualInfoService;

    @Autowired
    private MutualService mutualService;

    @Autowired
    private SeedStudentService seedStudentService;


    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;


    @Autowired
    private NoticeService noticeService;



    /**
     * 登录功能
     */
    @PostMapping("login")
    public R login(@RequestBody SysUser data){
        QueryWrapper<SysUser> qw = new QueryWrapper<>();
        LambdaQueryWrapper<SysUser> lambda = qw.lambda();
        lambda.eq(SysUser::getUsername, data.getUsername());
        SysUser pUser = sysUserService.getOne(qw);
        if(pUser == null){
            return R.error("账号不存在");
        }
        String string2MD5Password = AceUtils.string2MD5(data.getPassword());
        // 判断密码
        if(!string2MD5Password.equals(pUser.getPassword())){
            return R.error("密码不正确");
        }
        if(!"2".equals(pUser.getUserType())){
            return R.error("请登录用户账号");
        }
        String status = pUser.getStatus();

        // 判断账号状态
        if(!"1".equals(status)){
            return R.error("账号已被禁用");
        }

        return R.ok().data("token",pUser.getId());
    }


    /**
     * 密码修改
     */
    @PostMapping("updatePassword")
    public R updatePassword(@RequestBody SysUser data){
        SysUser pUser = sysUserService.getById(data.getId());

        // 加密旧密码
        String oldPassword = AceUtils.string2MD5(data.getOldPassword());

        if(!oldPassword.equals(pUser.getPassword())){
            return R.error("旧密码不正确");
        }
        // 更新密码
        pUser.setPassword(AceUtils.string2MD5(data.getPassword()));
        sysUserService.updateById(pUser);
        return R.ok();
    }


    /**
     * 注册功能
     * @param data
     * @return
     */
    @PostMapping("register")
    public R register(@RequestBody SysUser data){
        // MD5单向加密
        data.setPassword(AceUtils.string2MD5(data.getPassword()));
        data.setStatus("1");
        data.setUserType("2");
        sysUserService.save(data);
        return R.ok();
    }

    /**
     * 个人信息查询
     */
    @GetMapping("getUserInfo")
    public R getUserInfo(String id){
        SysUser ttUser = sysUserService.getById(id);
        return R.ok().data("row", ttUser);
    }

    /**
     * 个人信息修改
     */
    @PostMapping("updateUserInfo")
    public R updateUserInfo(@RequestBody SysUser ttUser){
        QueryWrapper<SysUser> qw = new QueryWrapper<>();
        qw.eq("id", ttUser.getId());
        if(!StringUtils.isEmpty(ttUser.getPassword())){
            ttUser.setPassword(AceUtils.string2MD5(ttUser.getPassword()));
        }
        sysUserService.update(ttUser, qw);
        return R.ok().data("row", ttUser);
    }

    /**
     * 首页数据查询
     * @return
     */
    @GetMapping("getHome")
    public R getHome(){
        List<Notice> notices = noticeService.list(new QueryWrapper<Notice>().orderByAsc("sort"));

        List<Mutual> mutuals = mutualService.mutualAndAvatarList();


        Map<String, Object> map = new HashMap<>();
        map.put("notices",notices);
        map.put("mutuals",mutuals);


        return R.ok().data("row", map);
    }


    /**
     * 查询资讯分类, 统计分类资讯数量
     */
    @GetMapping("getJournalismClassified")
    public R getJournalismClassifiedBySumNumber(){
        List<JournalismClassified> list =  journalismClassifiedService.getJournalismClassifiedBySumNumber();
        return R.ok().data("list", list);
    }

    /**
     * 查询资讯分类id 查询资讯列表
     * @param id 分类id
     * @return
     */
    @GetMapping("getJournalismListByClassifiedId/{id}")
    public R getJournalismListByClassifiedId(@PathVariable String id){
        List<Journalism> list = journalismService.list(new QueryWrapper<Journalism>().eq("classified_id", id));
        return R.ok().data("list", list);
    }

    /**
     * 查询资讯id 查询资讯详情
     * @param id 分类id
     * @return
     */
    @GetMapping("getJournalismById/{id}/{userId}")
    public R getJournalismById(@PathVariable String id, @PathVariable String userId){
        Journalism journalism = journalismService.getById(id);

        // 查询是否收藏了资讯
        Subscription subscription = subscriptionService.getOne(new QueryWrapper<Subscription>()
                .eq("journalism_id", id)
                .eq("user_id", userId));
        if(subscription != null){
            journalism.setSubscriptionId(subscription.getId());
        }
        return R.ok().data("list", journalism);
    }

    /**
     * 取消订阅资讯
     * @param
     * @return
     */
    @GetMapping("cancelSubscriptionJournalism/{id}")
    public R cancelSubscriptionMagazine(@PathVariable String id){
        subscriptionService.removeById(id);
        return R.ok();
    }

    /**
     * 查询所有资讯分类
     * @param
     * @return
     */
    @GetMapping("getClassifiedAll")
    public R getClassifiedAll(){
        List<JournalismClassified> list = journalismClassifiedService.list(null);
        return R.ok().data("row", list);
    }
    /**
     * 订阅资讯
     * @param
     * @return
     */
    @PostMapping("subscriptionJournalism")
    public R subscriptionJournalism(@RequestBody Subscription subscription){
        subscriptionService.save(subscription);
        return R.ok();
    }

    /**
     * 查询我的订阅资讯
     */
    @GetMapping("getSubscriptionJournalismByUserId")
    public R getSubscriptionJournalismByUserId(String userId,String classifiedId){
        List<Subscription> list = subscriptionService.getSubscriptionJournalismByUserId(userId, classifiedId);
        return R.ok().data("list", list);
    }

    /**
     * 查询我的打卡天数和打卡日期
     */
    @GetMapping("getCardDate/{userId}")
    public R getCardDate(@PathVariable String userId){
        Map<String, Object> map = new HashMap<>();
        LambdaQueryWrapper<CardNote> qw = new LambdaQueryWrapper<>();
        qw.eq(CardNote::getUserId, userId);
        int count = cardNoteService.count(qw);
        map.put("sumDays", count);
        List<CardNote> list = cardNoteService.list(qw);
        List<String> signLogDate = list.stream().map(CardNote::getDate).collect(Collectors.toList());
        map.put("signLogDate", signLogDate);
        return R.ok().data("row", map);
    }

    /**
     * 提交打卡
     */
    @PostMapping("sendCard")
    public R sendCard(@RequestBody CardNote cardNote){
        String godate = AceUtils.getGodate();
        LambdaQueryWrapper<CardNote> qw = new LambdaQueryWrapper<>();
        qw.eq(CardNote::getUserId, cardNote.getUserId());
        qw.eq(CardNote::getDate, godate);
        CardNote cardNote1 = cardNoteService.getOne(qw);
        if(cardNote1 != null){
            return R.error("今天已打过卡了");
        }
        cardNote.setDate(godate);
        cardNoteService.save(cardNote);

        return R.ok();
    }


    /**
     * 查询打卡记录
     */
    @GetMapping("getCardNote/{userId}")
    public R getCardNote(@PathVariable String userId){
        LambdaQueryWrapper<CardNote> qw = new LambdaQueryWrapper<>();
        qw.eq(CardNote::getUserId, userId);
        qw.orderByDesc(CardNote::getCreateTime);
        List<CardNote> list = cardNoteService.list(qw);
        return R.ok().data("list", list);
    }

    /**
     * 查询拼车记录查询
     * @param type 查询类型 0全部拼车 1我的拼车
     * @return
     */
    @GetMapping("getCarSharingList")
    public R getCarSharingList(String type, String userId){
        if("0".equals(type)){
            List<CarSharing> list = carSharingService.list(new QueryWrapper<CarSharing>().orderByDesc("create_time"));
            return R.ok().data("list", list);
        }else{
            List<CarSharing> list =  carSharingInfoService.getCarSharingByUserIdList(userId);
            return R.ok().data("list", list);
        }
    }

    /**
     * 参与拼车
     */
    @PostMapping("joinCarSharing/{userId}/{carId}")
    public R joinCarSharing(@PathVariable String userId,@PathVariable String carId){
        CarSharingInfo carSharingInfo = carSharingInfoService.getOne(
                new QueryWrapper<CarSharingInfo>().eq("user_id", userId).eq("car_id", carId));
        if(carSharingInfo != null){
            return R.error("您已参与了");
        }

        // 新增拼车记录
        CarSharingInfo carSharingInfo1 = new CarSharingInfo();
        carSharingInfo1.setCarId(carId);
        carSharingInfo1.setUserId(userId);
        carSharingInfoService.save(carSharingInfo1);


        int count = carSharingInfoService.count(new QueryWrapper<CarSharingInfo>().eq("car_id", carId));

        // 判断是否满员
        CarSharing carSharing = carSharingService.getById(carId);
        if(count == carSharing.getSeat()){
            carSharing.setStatus("2");
            carSharingService.updateById(carSharing);
        }
        return R.ok("拼车成功");

    }


    /**
     * 发起拼车
     */
    @PostMapping("sendCarSharing")
    public R sendCarSharing(@RequestBody CarSharing carSharing){

        carSharing.setStatus("1");
        if(carSharing.getSeat() == 1){
            carSharing.setStatus("2");
        }
        carSharingService.save(carSharing);

        // 新增拼车记录
        CarSharingInfo carSharingInfo1 = new CarSharingInfo();
        carSharingInfo1.setCarId(carSharing.getId());
        carSharingInfo1.setUserId(carSharing.getUserId());
        carSharingInfoService.save(carSharingInfo1);



        return R.ok();
    }

    /**
     * 查询拼车详情
     */
    @GetMapping("getCarSharingById/{id}")
    public R getCarSharingById(@PathVariable String id){
        CarSharing carSharing = carSharingService.getCarSharingById(id);
        return R.ok().data("row", carSharing);
    }


    /**
     * 失物和寻物数据查询
     * @param type 类型(0失物招领 1寻物启事)
     * @param classified 物品类型（0证件 1服饰 2数码 3日用品 4其它）
     * @return
     */
    @GetMapping("getLostFoundList")
    public R getLostFoundList(String type, String classified){
        List<LostFound> list = lostFoundService.getLostFoundList(type, classified);
        return R.ok().data("list", list);
    }

    /**
     * 失物和寻物详情查询
     * @return
     */
    @GetMapping("getLostFoundById")
    public R getLostFoundById(String id){
        LostFound lostFound = lostFoundService.getLostFoundById(id);
        return R.ok().data("row", lostFound);
    }
    /**
     * 发布失物和寻物
     * @return
     */
    @PostMapping("sendLostFound")
    public R sendLostFound(@RequestBody LostFound lostFound){
        lostFound.setStatus("0");
        lostFoundService.save(lostFound);
        return R.ok();
    }




    /**
     * 结实同学查询
     *
     */
    @GetMapping("getThisStudentList/{userId}/{type}")
    public R getThisStudentList(@PathVariable String userId,@PathVariable String type){
        List<SysUser> list;
        if("0".equals(type)){
            list = sysUserService.list(new QueryWrapper<SysUser>()
                    .eq("user_type", "2").ne("id", userId).orderByDesc("create_time"));
        }else{
            list = seedStudentService.getThisStudentList(userId);
        }
        return R.ok().data("list", list);

    }

    /**
     * 结实同学
     */
    @PostMapping("seedStudent")
    public R seedStudent(@RequestBody SeedStudent seedStudent){
        seedStudentService.save(seedStudent);
        return R.ok("结实同学成功");
    }

    /**
     * 取消结实同学
     */
    @PostMapping("delStudent")
    public R delStudent(@RequestBody SeedStudent seedStudent){
        seedStudentService.remove(new QueryWrapper<SeedStudent>().eq("user_id", seedStudent.getUserId()).eq("student_id", seedStudent.getStudentId()));
        return R.ok("取消结实成功");
    }

    /**
     * 查询结实同学详情
     */
    @GetMapping("getSeedStudentById/{userId}/{studentId}")
    public R getStudentById(@PathVariable String userId, @PathVariable String studentId){
        SysUser sysUser = sysUserService.getById(studentId);
        SeedStudent seedStudent = seedStudentService.getOne(new QueryWrapper<SeedStudent>().eq("user_id", userId).eq("student_id", studentId));
        if(seedStudent != null){
            sysUser.setFlag(true);
        }
        return R.ok().data("row", sysUser);
    }


    /**
     * 查询二手商品
     */
    @GetMapping("getGoodsList/{userId}/{type}")
    public R getGoodsList(@PathVariable String userId, @PathVariable String type){
        List<Goods> list;
        if(type.equals("0")){ // 查询所有二手商品
            list = goodsService.getGoodsList("1","");
        } else { // 查询自己发布的二手商品
            list = goodsService.getGoodsList("",userId);

        }
        return R.ok().data("list", list);
    }


    /**
     * 查询商品详情
     */
    @GetMapping("getGoodsById/{id}")
    public R getGoodsById(@PathVariable String id){
        Goods goods = goodsService.getById(id);
        return R.ok().data("row",goods);
    }

    /**
     * 商品发布
     */
    @PostMapping("sendGoods")
    public R sendGoods(@RequestBody Goods goods){
        goods.setStatus("1");
        goodsService.save(goods);
        return R.ok();
    }

    /**
     * 商品购买
     */
    @PostMapping("sendOrder")
    public R sendOrder(@RequestBody Order order){
        Goods goods = goodsService.getById(order.getGoodsId());
        if(goods.getUserId().equals(order.getBuyUserId())){
            return R.error("您不能购买自己商品");
        }
        order.setOrderNo(System.currentTimeMillis()+"");
        order.setPrice(goods.getPrice());
        goods.setStatus("2");
        goodsService.updateById(goods);
        orderService.save(order);
        return R.ok();
    }

    /**
     * 发布快递信息
     */
    @PostMapping("sendExpressage")
    public R sendExpressage(@RequestBody Expressage expressage){

        expressage.setStatus("0");
        expressageService.save(expressage);
        return R.ok();
    }

    /**
     * 查询快递信息
     *type 0查询所有快递信息，1查询自己发布的
     *
     */
    @GetMapping("getExpressageList/{userId}/{type}")
    public R getExpressageList(@PathVariable String userId, @PathVariable String type){
        List<Expressage> list;
        if("0".equals(type)){
            list = expressageService.list(new QueryWrapper<Expressage>()
                    .eq("status", "0")
                    .ne("user_id",userId));
        }else{
            list = expressageService.list(new QueryWrapper<Expressage>()
                    .eq("user_id",userId));
        }
        return R.ok().data("list", list);
    }

    /**
     * 快递帮领
     */
    @PostMapping("bangExpressage")
    public R bangExpressage(@RequestBody Expressage expressage){
        Expressage byId = expressageService.getById(expressage.getId());
        byId.setStatus("1");
        byId.setGoodsUserId(expressage.getGoodsUserId());
        expressageService.updateById(byId);
        return R.ok();
    }


    /**
     * 查询互助记录查询
     * @param type 查询类型 0全部互助 1我的互助
     * @return
     */
    @GetMapping("getMutualList")
    public R getMutualList(String type, String userId){
        if("0".equals(type)){
            List<Mutual> list = mutualService.list(new QueryWrapper<Mutual>().orderByDesc("create_time"));
            return R.ok().data("list", list);
        }else{
            List<Mutual> list =  mutualService.getMutualByUserIdList(userId);
            return R.ok().data("list", list);
        }
    }

    /**
     * 参与互助
     */
    @PostMapping("joinMutual/{userId}/{carId}")
    public R joinMutual(@PathVariable String userId,@PathVariable String carId){
        MutualInfo mutualInfo = mutualInfoService.getOne(
                new QueryWrapper<MutualInfo>().eq("user_id", userId).eq("car_id", carId));
        if(mutualInfo != null){
            return R.error("您已参与互助");
        }

        // 新增互助记录
        MutualInfo mutualInfo1 = new MutualInfo();
        mutualInfo1.setCarId(carId);
        mutualInfo1.setUserId(userId);
        mutualInfoService.save(mutualInfo1);


        int count = mutualInfoService.count(new QueryWrapper<MutualInfo>().eq("car_id", carId));

        // 判断是否满员
        Mutual mutual = mutualService.getById(carId);
        if(count == mutual.getSeat()){
            mutual.setStatus("2");
            mutualService.updateById(mutual);
        }
        return R.ok("互助成功");

    }


    /**
     * 发起互助
     */
    @PostMapping("sendMutual")
    public R sendMutual(@RequestBody Mutual mutual){

        mutual.setStatus("1");
        if(mutual.getSeat() == 1){
            mutual.setStatus("2");
        }
        mutualService.save(mutual);

        // 新增互助记录
        MutualInfo mutualInfo = new MutualInfo();
        mutualInfo.setCarId(mutual.getId());
        mutualInfo.setUserId(mutual.getUserId());
        mutualInfoService.save(mutualInfo);
        return R.ok();
    }

    /**
     * 查询互助详情
     */
    @GetMapping("getMutualById/{id}")
    public R getMutualById(@PathVariable String id){
        Mutual mutual = mutualService.getMutualById(id);
        return R.ok().data("row", mutual);
    }

    /**
     * 查询我的购买记录
     */
    @GetMapping("getOrderInfoList/{userId}")
    public R getOrderInfoList(@PathVariable String userId){
       List<Order> list =  orderService.getOrderInfoList(userId);
        return R.ok().data("list", list);
    }

    /**
     * 查询我的快递帮领记录
     */
    @GetMapping("getExpressageInfoList/{userId}")
    public R getExpressageInfoList(@PathVariable String userId){
        List<Expressage> list = expressageService.list(new QueryWrapper<Expressage>().eq("goods_user_id", userId));

        return R.ok().data("list", list);
    }

    /**
     * 查询通知详情
     */
    @GetMapping("getNoticeById")
    public R getNoticeById(String id){
        Notice notice = noticeService.getById(id);
        return R.ok().data("row", notice);
    }

}
