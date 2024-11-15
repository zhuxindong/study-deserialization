package com.zxd;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
		// 开启Netty服务
//		NettyServer nettyServer =new NettyServer ();
//		nettyServer.start();
//		log.info("========Netty服务已经启动========");
	}



}
