package br.com.caelum.eats.distancia;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.nativex.hint.TypeAccess;
import org.springframework.nativex.hint.TypeHint;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInfo;
import com.netflix.discovery.shared.Application;



@SpringBootApplication
@TypeHint(types = { Application.class, InstanceInfo.class, MyDataCenterInfo.class }, typeNames = {
		"com.netflix.discove,ry.shared.Application",
		"com.netflix.appinfo.InstanceInfo",
		"com.netflix.appinfo.InstanceInfo$PortWrapper",
		"com.netflix.appinfo.DataCenterInfo",
})
@TypeHint(types = Restaurante.class, access = { TypeAccess.DECLARED_CONSTRUCTORS, TypeAccess.PUBLIC_METHODS })
public class EatsDistanciaServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(EatsDistanciaServiceApplication.class).initializers(new LoggingIntializer()).run(args);
	}

}
