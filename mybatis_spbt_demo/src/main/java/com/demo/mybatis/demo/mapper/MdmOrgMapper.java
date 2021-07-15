package com.demo.mybatis.demo.mapper;

import com.demo.mybatis.demo.model.MdmOrg;
import com.demo.mybatis.demo.model.MdmOrgExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

@Mapper
// @Transactional
public interface MdmOrgMapper {
    long countByExample(MdmOrgExample example);

    int deleteByExample(MdmOrgExample example);

    int deleteByPrimaryKey(String id);

    int insert(MdmOrg record);

    int insertSelective(MdmOrg record);

    List<MdmOrg> selectByExample(MdmOrgExample example);

    MdmOrg selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MdmOrg record, @Param("example") MdmOrgExample example);

    int updateByExample(@Param("record") MdmOrg record, @Param("example") MdmOrgExample example);

    int updateByPrimaryKeySelective(MdmOrg record);

    int updateByPrimaryKey(MdmOrg record);
}