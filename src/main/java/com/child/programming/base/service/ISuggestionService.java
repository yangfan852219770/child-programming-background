package com.child.programming.base.service;

import com.child.programming.base.dto.SuggestinInfoDto;

import java.util.List;

/**
 * @Description：
 * @Author：yangfan
 **/
public interface ISuggestionService {
    /**
     * 意见反馈内容
     * @return
     */
    List<SuggestinInfoDto> getAll();
}
