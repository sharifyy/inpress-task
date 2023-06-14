package com.github.sharifyy.inpresstask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
class TestInpressTaskApplication {

	public static void main(String[] args) {
		SpringApplication.from(InpressTaskApplication::main).with(TestInpressTaskApplication.class).run(args);
	}

}
