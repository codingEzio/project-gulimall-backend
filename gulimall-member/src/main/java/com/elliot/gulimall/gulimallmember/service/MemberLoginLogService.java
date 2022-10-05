package com.elliot.gulimall.gulimallmember.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elliot.common.utils.PageUtils;
import com.elliot.gulimall.gulimallmember.entity.MemberLoginLogEntity;

import java.util.Map;

/**
 * 会员登录记录
 *
 * @author Elliot
 * @email example@example.com
 * @date 2022-10-06 01:38:10
 */
public interface MemberLoginLogService extends IService<MemberLoginLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

