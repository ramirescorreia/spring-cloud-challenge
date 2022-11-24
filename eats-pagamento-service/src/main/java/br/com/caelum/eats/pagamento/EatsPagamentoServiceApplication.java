package br.com.caelum.eats.pagamento;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.nativex.hint.TypeHint;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInfo;
import com.netflix.discovery.shared.Application;

import br.com.caelum.eats.pagamento.config.LoggingIntializer;



@EnableFeignClients
@SpringBootApplication
@TypeHint(types = { Application.class, InstanceInfo.class, MyDataCenterInfo.class }, typeNames = {
		"com.netflix.discove,ry.shared.Application",
		"com.netflix.appinfo.InstanceInfo",
		"com.netflix.appinfo.InstanceInfo$PortWrapper",
		"com.netflix.appinfo.DataCenterInfo",
})
public class EatsPagamentoServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(EatsPagamentoServiceApplication.class).initializers(new LoggingIntializer()).run(args);
	}

}
