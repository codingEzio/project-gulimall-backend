package com.elliot.gulimall.gulimallmember.dao;

import com.elliot.gulimall.gulimallmember.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author Elliot
 * @email example@example.com
 * @date 2022-10-06 01:38:10
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
