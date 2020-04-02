package com.nibado.example.websocket.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		transports.add(new WebSocketTransport(new StandardWebSocketClient()));
		WebSocketClient transport = new SockJsClient(transports);
		
		WebSocketStompClient stompClient = new WebSocketStompClient(transport);
		stompClient.setMessageConverter(new StringMessageConverter());
		stompClient.setInboundMessageSizeLimit(Integer.MAX_VALUE);
		
		String url = "ws://127.0.0.1:8080/hello";
		//String url = "wss://hml.desbravadorweb.com.br:443/printService";
		stompClient.connect(url, new MySessionHandler());
		
		new Scanner(System.in).nextLine();
    }
}
