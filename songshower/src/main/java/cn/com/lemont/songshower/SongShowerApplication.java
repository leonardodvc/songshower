package cn.com.lemont.songshower;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.com.lemont.songshower.dao")
@SpringBootApplication
public class SongShowerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SongShowerApplication.class, args);
	}

}
