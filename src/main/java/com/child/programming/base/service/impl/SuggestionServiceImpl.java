package com.child.programming.base.service.impl;

import com.child.programming.base.dto.SuggestinInfoDto;
import com.child.programming.base.mapper.TbSuggestionDoMapper;
import com.child.programming.base.model.TbSuggestionDo;
import com.child.programming.base.model.TbSuggestionDoExample;
import com.child.programming.base.service.ISuggestionService;
import com.child.programming.base.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description：
 * @Author：yangfan
 **/

@Service
public class SuggestionServiceImpl implements ISuggestionService {
    @Autowired
    private TbSuggestionDoMapper suggestionDoMapper;

    @Override
    public List<SuggestinInfoDto> getAll() {
        TbSuggestionDoExample example = new TbSuggestionDoExample();
        example.setOrderByClause("create_time desc");
        List<TbSuggestionDo> suggestionDoList = suggestionDoMapper.selectByExample(example);
        List<SuggestinInfoDto> suggestinInfoDtoList = ListUtil.convertElement(suggestionDoList, SuggestinInfoDto.class);
        return suggestinInfoDtoList;
    }

    @Override
    public List<TbSuggestionDo> getSuggesstionByStudentId(int studentId) {
        TbSuggestionDoExample example = new TbSuggestionDoExample();
        example.setOrderByClause("create_time desc");
        TbSuggestionDoExample.Criteria criteria = example.createCriteria();
        criteria.andCreateIdEqualTo(studentId);
        criteria.andStatusEqualTo((byte) 1);
        List<TbSuggestionDo> suggestionDoList = suggestionDoMapper.selectByExample(example);
        return suggestionDoList;
    }

    @Override
    public Boolean saveSuggesstion(TbSuggestionDo tbSuggestionDo) {
        int result = suggestionDoMapper.insert(tbSuggestionDo);
        if (result>0){
            return true;
        }
        return false;
    }
}
