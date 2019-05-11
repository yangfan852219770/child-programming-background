package com.child.programming.base.service.impl;

import com.child.programming.base.dto.MaterialInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.dto.StudentInfoDto;
import com.child.programming.base.mapper.TbMaterialDoMapper;
import com.child.programming.base.mapper.TbMaterialTypeDoMapper;
import com.child.programming.base.model.TbMaterialDo;
import com.child.programming.base.model.TbMaterialDoExample;
import com.child.programming.base.model.TbMaterialTypeDo;
import com.child.programming.base.model.TbMaterialTypeDoExample;
import com.child.programming.base.service.IMaterialService;
import com.child.programming.base.service.IStudentService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zdp
 * @description: TODO
 */
@Service
public class MaterialServiceImpl implements IMaterialService {

    @Autowired
    private TbMaterialDoMapper tbMaterialDoMapper;
    @Autowired
    private TbMaterialTypeDoMapper tbMaterialTypeDoMapper;
    @Autowired
    private IStudentService iStudentService;
    @Value("${IMAGE.BASE.MANAGE.URL}")
    private String baseUrl;
    @Override
    public List<MaterialInfoDto> getList(Integer materialTypeId) {

        TbMaterialDoExample tbMaterialDoExample = new TbMaterialDoExample();
        TbMaterialDoExample.Criteria criteria = tbMaterialDoExample.createCriteria();

        tbMaterialDoExample.setOrderByClause("download_number desc");
        if (!EmptyUtils.intIsEmpty(materialTypeId))
            criteria.andStatusNotEqualTo(Byte.valueOf("0")).andMaterialTypeIdEqualTo(materialTypeId);
        else
            criteria.andStatusNotEqualTo(Byte.valueOf("0"));
        List<TbMaterialDo> tbMaterialDos = tbMaterialDoMapper.selectByExample(tbMaterialDoExample);
        if (!EmptyUtils.listIsEmpty(tbMaterialDos))
            return ListUtil.convertElement(tbMaterialDos, MaterialInfoDto.class);
        else
            return null;
    }

    @Override
    public Boolean save(TbMaterialDo tbMaterialDo, Integer userId) {

        if (EmptyUtils.objectIsEmpty(tbMaterialDo))
            return false;
        TbMaterialTypeDoExample tbMaterialTypeDoExample= new TbMaterialTypeDoExample();
        TbMaterialTypeDoExample.Criteria criteria= tbMaterialTypeDoExample.createCriteria();
        criteria.andIdEqualTo(tbMaterialDo.getMaterialTypeId());
        List<TbMaterialTypeDo> tbMaterialTypeDos=tbMaterialTypeDoMapper.selectByExample(tbMaterialTypeDoExample);
        if(EmptyUtils.listIsEmpty(tbMaterialTypeDos))
            return false;
        //新增
        if (EmptyUtils.intIsEmpty(tbMaterialDo.getId())) {
            tbMaterialDo.setCreateId(userId);
            tbMaterialDo.setCreateTime(new Date());
            tbMaterialDo.setType(tbMaterialTypeDos.get(0).getName());
            tbMaterialDo.setStatus(Byte.valueOf("1"));
            return tbMaterialDoMapper.insert(tbMaterialDo) > 0;
        } else {
            //更新
            tbMaterialDo.setLastUpdateId(userId);
            tbMaterialDo.setType(tbMaterialTypeDos.get(0).getName());
            tbMaterialDo.setLastUpdateTime(new Date());
            return tbMaterialDoMapper.updateByPrimaryKeySelective(tbMaterialDo) > 0;
        }

    }

    @Override
    public Boolean delete(String[] idArray, Integer userId) {

        if (EmptyUtils.arrayIsEmpty(idArray))
            return false;

        int result = 0;
        for (String str:idArray
                ) {
            TbMaterialDo tbMaterialDo = tbMaterialDoMapper.selectByPrimaryKey(Integer.parseInt(str));
            if (!EmptyUtils.objectIsEmpty(tbMaterialDo)){
                tbMaterialDo.setStatus(Byte.valueOf("0"));
                tbMaterialDo.setLastUpdateId(userId);
                tbMaterialDo.setLastUpdateTime(new Date());
                result += tbMaterialDoMapper.updateByPrimaryKeySelective(tbMaterialDo);
            }
        }

        return result == idArray.length;
    }

    @Override
    public Boolean push(String[] idArray,String status, Integer userId) {

        if (EmptyUtils.arrayIsEmpty(idArray))
            return false;

        int result = 0;
        for (String str:idArray
                ) {
            TbMaterialDo tbMaterialDo = tbMaterialDoMapper.selectByPrimaryKey(Integer.parseInt(str));
            if (!EmptyUtils.objectIsEmpty(tbMaterialDo)){
                tbMaterialDo.setStatus(Byte.valueOf(status));
                tbMaterialDo.setLastUpdateId(userId);
                tbMaterialDo.setLastUpdateTime(new Date());
                result += tbMaterialDoMapper.updateByPrimaryKeySelective(tbMaterialDo);
            }
        }

        return result == idArray.length;
    }

    @Override
    public ResultDto portalSave(Integer id) {
      TbMaterialDo tbMaterialDo=  tbMaterialDoMapper.selectByPrimaryKey(id);

      if(EmptyUtils.objectIsEmpty(tbMaterialDo))
          return ResultDto.fail();
      tbMaterialDo.setDownloadNumber(tbMaterialDo.getDownloadNumber()+1);
      return  tbMaterialDoMapper.updateByPrimaryKeySelective(tbMaterialDo)>0?ResultDto.success():ResultDto.fail();
    }

    @Override
    public ResultDto portalVipCheck(Integer id,String phone) {

        boolean flag=false;
        TbMaterialDo tbMaterialDo= tbMaterialDoMapper.selectByPrimaryKey(id);
       if(EmptyUtils.objectIsEmpty(tbMaterialDo))
        return ResultDto.fail("不存在该资料");
       List<StudentInfoDto>  studentInfoDtos = iStudentService.getList("");
       if(EmptyUtils.listIsEmpty(studentInfoDtos))
           return  ResultDto.fail("VIP下载暂未开通");
        for (StudentInfoDto studentInfoDto:studentInfoDtos
             ) {
            if(studentInfoDto.getGuardianPhone().equals(phone))
                flag=true;
        }
        if(flag) {
            tbMaterialDo.setFileUrl(baseUrl+tbMaterialDo.getFileUrl());
            return ResultDto.success(tbMaterialDo);

        }
        else
            return ResultDto.fail("输入手机号没有权限下载！");
    }
}
