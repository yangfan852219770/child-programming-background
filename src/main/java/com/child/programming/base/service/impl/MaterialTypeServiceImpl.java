package com.child.programming.base.service.impl;

import com.child.programming.base.dto.MaterialTypeInfoDto;
import com.child.programming.base.mapper.TbMaterialTypeDoMapper;
import com.child.programming.base.model.TbMaterialTypeDo;
import com.child.programming.base.model.TbMaterialTypeDoExample;
import com.child.programming.base.service.IMaterialTypeService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zdp
 * @description: TODO
 */
@Service
public class MaterialTypeServiceImpl implements IMaterialTypeService{


    @Autowired
    private TbMaterialTypeDoMapper tbMaterialTypeDoMapper;

    @Override
    public List<MaterialTypeInfoDto> getList(String name) {

        TbMaterialTypeDoExample tbMaterialTypeDoExample = new TbMaterialTypeDoExample();
        TbMaterialTypeDoExample.Criteria criteria = tbMaterialTypeDoExample.createCriteria();

        tbMaterialTypeDoExample.setOrderByClause("create_time desc");
        if (!EmptyUtils.stringIsEmpty(name))
            criteria.andStatusEqualTo(Byte.valueOf("1")).andNameLike("%"+name+"%");
        else
            criteria.andStatusEqualTo(Byte.valueOf("1"));
        List<TbMaterialTypeDo> tbMaterialTypeDos = tbMaterialTypeDoMapper.selectByExample(tbMaterialTypeDoExample);
        if (!EmptyUtils.listIsEmpty(tbMaterialTypeDos))
            return ListUtil.convertElement(tbMaterialTypeDos, MaterialTypeInfoDto.class);
        else
            return null;
    }

    @Override
    public Boolean save(TbMaterialTypeDo tbMaterialTypeDo, Integer userId) {

        if (!EmptyUtils.objectIsEmpty(tbMaterialTypeDo))
            return false;
        //新增
        if (EmptyUtils.intIsEmpty(tbMaterialTypeDo.getId())) {
            tbMaterialTypeDo.setCreateId(userId);
            tbMaterialTypeDo.setCreateTime(new Date());
            tbMaterialTypeDo.setStatus(Byte.valueOf("1"));
            return tbMaterialTypeDoMapper.insert(tbMaterialTypeDo) > 0;
        } else {
            //更新
            tbMaterialTypeDo.setLastUpdateId(userId);
            tbMaterialTypeDo.setLastUpdateTime(new Date());
            return tbMaterialTypeDoMapper.updateByPrimaryKeySelective(tbMaterialTypeDo) > 0;
        }
    }

    @Override
    public Boolean delete(String[] idArray, Integer userId) {

        if (EmptyUtils.arrayIsEmpty(idArray))
            return false;

        int result = 0;
        for (String str:idArray
                ) {
            TbMaterialTypeDo tbMaterialTypeDo = tbMaterialTypeDoMapper.selectByPrimaryKey(Integer.parseInt(str));
            if (!EmptyUtils.objectIsEmpty(tbMaterialTypeDo)){
                tbMaterialTypeDo.setStatus(Byte.valueOf("0"));
                tbMaterialTypeDo.setLastUpdateId(userId);
                tbMaterialTypeDo.setLastUpdateTime(new Date());
                result += tbMaterialTypeDoMapper.updateByPrimaryKeySelective(tbMaterialTypeDo);
            }
        }

        return result == idArray.length;
    }
}
