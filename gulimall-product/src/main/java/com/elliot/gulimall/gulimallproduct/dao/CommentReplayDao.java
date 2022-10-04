package com.elliot.gulimall.gulimallproduct.dao;

import com.elliot.gulimall.gulimallproduct.entity.CommentReplayEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价回复关系
 * 
 * @author Elliot
 * @email example@example.com
 * @date 2022-10-04 14:28:52
 */
@Mapper
public interface CommentReplayDao extends BaseMapper<CommentReplayEntity> {
	
}
