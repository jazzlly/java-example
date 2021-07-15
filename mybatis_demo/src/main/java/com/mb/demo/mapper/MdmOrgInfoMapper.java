package com.mb.demo.mapper;

import com.mb.demo.model.MdmOrgInfo;
import com.mb.demo.model.MdmOrgInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdmOrgInfoMapper {
    long countByExample(MdmOrgInfoExample example);

    int deleteByExample(MdmOrgInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(MdmOrgInfo record);

    int insertSelective(MdmOrgInfo record);

    List<MdmOrgInfo> selectByExample(MdmOrgInfoExample example);

    MdmOrgInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MdmOrgInfo record, @Param("example") MdmOrgInfoExample example);

    int updateByExample(@Param("record") MdmOrgInfo record, @Param("example") MdmOrgInfoExample example);

    int updateByPrimaryKeySelective(MdmOrgInfo record);

    int updateByPrimaryKey(MdmOrgInfo record);
}