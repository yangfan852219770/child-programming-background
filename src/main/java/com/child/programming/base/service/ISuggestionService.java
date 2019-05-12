package com.child.programming.base.service;

import com.child.programming.base.dto.SuggestinInfoDto;
import com.child.programming.base.model.TbSuggestionDo;

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

    /**
     * 自己的意见反馈
     * @return
     */
    List<TbSuggestionDo> getSuggesstionByStudentId(int studentId);

    Boolean saveSuggesstion(TbSuggestionDo tbSuggestionDo);
}
