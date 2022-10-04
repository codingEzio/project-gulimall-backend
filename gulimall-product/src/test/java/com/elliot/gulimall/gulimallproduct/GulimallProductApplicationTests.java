package com.elliot.gulimall.gulimallproduct;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.elliot.gulimall.gulimallproduct.entity.BrandEntity;
import com.elliot.gulimall.gulimallproduct.service.BrandService;

@SpringBootTest
class GulimallProductApplicationTests {

	@Autowired
	BrandService brandService;

	@Test
	void contextLoads() {
		BrandEntity brandEntity = new BrandEntity();

		brandEntity.setName("ok");
		brandService.save(brandEntity);

		System.out.println("Saved!");
	}

}
