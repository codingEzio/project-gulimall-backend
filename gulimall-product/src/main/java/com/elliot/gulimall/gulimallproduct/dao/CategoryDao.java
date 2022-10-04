package com.elliot.gulimall.gulimallproduct.dao;

import com.elliot.gulimall.gulimallproduct.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author Elliot
 * @email example@example.com
 * @date 2022-10-04 14:28:52
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
