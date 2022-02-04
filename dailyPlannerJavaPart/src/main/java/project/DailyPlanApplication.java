package project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import project.dao.UserDataAccessService;

import java.util.UUID;
import java.util.concurrent.Executor;

@SpringBootApplication
@Component
public class DailyPlanApplication {
	public static void main(String[] args) {SpringApplication.run(DailyPlanApplication.class, args);}
}
