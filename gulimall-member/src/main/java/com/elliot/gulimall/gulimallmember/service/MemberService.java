package com.elliot.gulimall.gulimallmember.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elliot.common.utils.PageUtils;
import com.elliot.gulimall.gulimallmember.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author Elliot
 * @email example@example.com
 * @date 2022-10-06 01:38:10
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

