package com.elliot.gulimall.gulimallmember.feign;

import com.elliot.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("nacos-gulimall-coupon")
public interface CouponFeignService {

	@RequestMapping("/gulimallcoupon/coupon/member/list")
	public R membercoupons();
}