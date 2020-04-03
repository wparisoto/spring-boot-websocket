package com.nibado.example.websocket.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nibado.example.impressao.Impressora;
import com.nibado.example.websocket.service.Greeting;
import com.nibado.example.websocket.service.RetornoImpressao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

@Slf4j
public class MySessionHandler extends StompSessionHandlerAdapter {
    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
    	session.subscribe("/topic/5981", this);
    	//session.subscribe("/topic/greetings", this);
        //session.send("/app/hello", "{\"name\":\"Client\"}".getBytes());

        log.info("New session: {}", session.getSessionId());
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        exception.printStackTrace();
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return String.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        log.info("Received: {}", (String) payload);
        
        RetornoImpressao retorno = null;
        try {
			retorno = new ObjectMapper().readValue((String) payload, RetornoImpressao.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Impressora impressora = new Impressora();
		impressora.imprime(retorno.getRelatorio());
		log.info("Impresso com sucesso!");
    }
}
