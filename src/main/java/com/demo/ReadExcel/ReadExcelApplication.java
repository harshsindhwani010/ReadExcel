package com.demo.ReadExcel;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReadExcelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadExcelApplication.class, args);
		System.out.println("hello");
	}

}
