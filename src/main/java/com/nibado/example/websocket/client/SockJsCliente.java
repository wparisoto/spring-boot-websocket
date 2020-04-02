package com.nibado.example.websocket.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

public class SockJsCliente {
	public static void main(String... argv) {
//		DefaultSetting.setDefaultNetwork();
//		if(args.length==0){
//			OutputMessage.error("please enter parameter");
//			return;
//		}
//		Map<String, String> params = parseParamsToMap(args);
//		if(params==null){
//			return;
//		}
		// set host, port and websocket port
//		DefaultSetting.setHostAndPort(params.get("host"), params.get("port"), params.get("wsPort"));
//		final String address = params.get("address");
//		final String WS_URI = DefaultSetting.getWsUri();

		// create WebSocket client
		List<Transport> transports = new ArrayList<>();
		transports.add(new WebSocketTransport(new StandardWebSocketClient()));
		WebSocketClient transport = new SockJsClient(transports);
		
		WebSocketStompClient stompClient = new WebSocketStompClient(transport);
		stompClient.setMessageConverter(new StringMessageConverter());
		
		String url = "ws://127.0.0.1:8080/hello";
		stompClient.connect(url, new MySessionHandler());
		
		new Scanner(System.in).nextLine();
		//stompClient.connect(WS_URI, new MySessionHandler());
		//block and monitor exit action
		//ScannerUtil.monitorExit();
    }
}
