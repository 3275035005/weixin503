package com.cn.mutual.entity;

import lombok.Data;

import java.util.List;

@Data
public class TranslateVo {


    /**
     * 生词本id
     */
    private String vocabularyId;

    /**
     * 释义
     */
    private List<String> paraphraseList;

    /**
     * 英式音标
     */
    private String ukPhonetic;

    /**
     * 英式语言
     */
    private String ukSpeech;

    /**
     * 美式音标
     */
    private String usPhonetic;

    /**
     * 美式语言
     */
    private String usSpeech;

    /**
     * 词态变化
     */
    private List<String> wfs;
}
