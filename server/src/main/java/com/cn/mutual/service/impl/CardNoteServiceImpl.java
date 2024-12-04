package com.cn.mutual.service.impl;

import com.cn.mutual.entity.CardNote;
import com.cn.mutual.mapper.CardNoteMapper;
import com.cn.mutual.service.CardNoteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 打卡笔记 服务实现类
 * </p>
 */
@Service
public class CardNoteServiceImpl extends ServiceImpl<CardNoteMapper, CardNote> implements CardNoteService {

}
