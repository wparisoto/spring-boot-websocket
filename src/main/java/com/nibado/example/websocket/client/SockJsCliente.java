package com.nibado.example.websocket.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

public class SockJsCliente {
	public static void main(String... argv) {

		List<Transport> transports = new ArrayList<>();
		StandardWebSocketClient s = new StandardWebSocketClient();

		WebSocketTransport wt = new WebSocketTransport(s);

		transports.add(wt);
		WebSocketClient transport = new SockJsClient(transports);

		// WebSocketStompClient stompClient = new WebSocketStompClient(transport);
		// stompClient.setMessageConverter(new Conversor());
//		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
//		stompClient.setInboundMessageSizeLimit((1024 * 1024) * 8);

		WebSocketStompClient stompClient = getClient();

		//String url = "ws://127.0.0.1:8080/hello";
		String url = "wss://hml.desbravadorweb.com.br:443/realtime";
		stompClient.connect(url, new MySessionHandler());

		new Scanner(System.in).nextLine();
	}

	public static WebSocketStompClient getClient() {
		List<Transport> transports = new ArrayList<>();
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		container.setDefaultMaxBinaryMessageBufferSize(1024 * 1024);
		container.setDefaultMaxTextMessageBufferSize(1024 * 1024);
		transports.add(new WebSocketTransport(new StandardWebSocketClient(container)));
		WebSocketClient webSocketClient = new SockJsClient(transports);
		WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
		stompClient.setInboundMessageSizeLimit(Integer.MAX_VALUE);
		stompClient.setMessageConverter(new StringMessageConverter());
		return stompClient;
	}
}
