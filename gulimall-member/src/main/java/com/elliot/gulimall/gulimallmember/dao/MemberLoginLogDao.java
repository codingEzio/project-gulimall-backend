package com.elliot.gulimall.gulimallmember.dao;

import com.elliot.gulimall.gulimallmember.entity.MemberLoginLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员登录记录
 * 
 * @author Elliot
 * @email example@example.com
 * @date 2022-10-06 01:38:10
 */
@Mapper
public interface MemberLoginLogDao extends BaseMapper<MemberLoginLogEntity> {
	
}
