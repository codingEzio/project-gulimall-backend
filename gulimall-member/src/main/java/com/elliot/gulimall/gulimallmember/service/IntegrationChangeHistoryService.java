package com.elliot.gulimall.gulimallmember.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elliot.common.utils.PageUtils;
import com.elliot.gulimall.gulimallmember.entity.IntegrationChangeHistoryEntity;

import java.util.Map;

/**
 * 积分变化历史记录
 *
 * @author Elliot
 * @email example@example.com
 * @date 2022-10-06 01:38:10
 */
public interface IntegrationChangeHistoryService extends IService<IntegrationChangeHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

