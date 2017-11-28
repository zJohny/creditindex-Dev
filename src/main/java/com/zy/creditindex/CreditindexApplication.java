package com.zy.creditindex;





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @EnableScheduling   //开启定时任务
 * @EnableAsync        //开启异步任务
 * @EnableCaching      //开启缓存
 */
@SpringBootApplication
//@EnableScheduling
@EnableCaching
public class CreditindexApplication  {
	protected static final Logger logger = LoggerFactory.getLogger(CreditindexApplication.class);

	public static void main(String[] args) {
		logger.info("*********************START********************");
		SpringApplication.run(CreditindexApplication.class, args);
		logger.info("*********************END**********************");
	}
}
