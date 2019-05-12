package com.child.programming.education.manage.controller;

import com.child.programming.base.dto.SuggestinInfoDto;
import com.child.programming.base.service.ISuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description：意见反馈
 * @Author：yangfan
 **/
@RestController
@RequestMapping("/suggestion")
public class SuggestionController {
    @Autowired
    private ISuggestionService iSuggestionService;

    /**
     * 获取所有意见
     * @return
     */
    @RequestMapping(value = "getAllList", method = RequestMethod.GET)
    public List<SuggestinInfoDto> getAllList(){
        return iSuggestionService.getAll();
    }
}
