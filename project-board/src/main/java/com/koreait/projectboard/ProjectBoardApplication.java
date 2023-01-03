package com.koreait.projectboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan		// 이 프로젝트에 있는 configur~을 알아서 인식하고 빈으로 등록해준다. 	// ※EnableJpaAuditing @Configuration EnableJpaAuditing가 Configuration를시작시켜주는거.(얘네들이 있는 곳이 설정 파일안에서음)
@SpringBootApplication
public class ProjectBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectBoardApplication.class, args);
	}

}
