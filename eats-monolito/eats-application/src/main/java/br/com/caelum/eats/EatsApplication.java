package br.com.caelum.eats;

import org.flywaydb.core.api.logging.LogCreator;
import org.flywaydb.core.internal.logging.log4j2.Log4j2LogCreator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.nativex.hint.TypeHint;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInfo;
import com.netflix.discovery.shared.Application;

import br.com.caelum.eats.configuration.LoggingIntializer;

@EnableFeignClients
@SpringBootApplication
@TypeHint(types = { Application.class, InstanceInfo.class, MyDataCenterInfo.class, Log4j2LogCreator.class, LogCreator.class }, typeNames = {
		"com.netflix.discove,ry.shared.Application",
		"com.netflix.appinfo.InstanceInfo",
		"com.netflix.appinfo.InstanceInfo$PortWrapper",
		"com.netflix.appinfo.DataCenterInfo",
		"org.flywaydb.core.internal.logging.log4j2.Log4j2LogCreator",
		"org.flywaydb.core.api.logging.LogCreator"
})
public class EatsApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		new SpringApplicationBuilder(EatsApplication.class).initializers(new LoggingIntializer()).run(args);
	}

}
