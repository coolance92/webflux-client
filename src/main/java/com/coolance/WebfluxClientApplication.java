package com.coolance;

import com.coolance.interfaces.ProxyCreator;
import com.coolance.proxys.JdkProxyCreator;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebfluxClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxClientApplication.class, args);
	}

	@Bean
	public ProxyCreator jdkProxyCreator() {
		return new JdkProxyCreator();
	}

	@Bean
	public FactoryBean<IUserApi> userApi(ProxyCreator proxyCreator) {
		return new FactoryBean<IUserApi>() {
			@Override
			public IUserApi getObject() throws Exception {
				return (IUserApi)proxyCreator.createProxy(this.getObjectType());
			}

			@Override
			public Class<?> getObjectType() {
				return IUserApi.class;
			}
		};
	}
}
