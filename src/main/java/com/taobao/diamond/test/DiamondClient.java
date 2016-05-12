package com.taobao.diamond.test;

import java.util.concurrent.Executor;

import com.taobao.diamond.manager.DiamondManager;
import com.taobao.diamond.manager.ManagerListener;
import com.taobao.diamond.manager.impl.DefaultDiamondManager;

public class DiamondClient {

	public static void main(String[] str) {
		/*
		 * 服务器的默认ip与端口在 com.taobao.diamond.common.Constants中设置
		 * diamond client没有与spring集成，无法实现配置自动注入
		 */
		DiamondManager manager = new DefaultDiamondManager("DEFAULT_GROUP", "topicConfig", new ManagerListener() {
			//服务器修改配置时调用
			public void receiveConfigInfo(String configInfo) {
				System.out.println("修改后的配置内容: " + configInfo);
			}

			public Executor getExecutor() {
				return null;
			}
		}, "192.168.110.21");
		manager.getDiamondConfigure().setPort(8080);
		String availableConfigureInfomation = manager.getAvailableConfigureInfomation(5000);
		System.out.println("配置内容: " + availableConfigureInfomation);
	}
}
