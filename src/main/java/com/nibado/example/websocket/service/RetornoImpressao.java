package com.nibado.example.websocket.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetornoImpressao {
    private String content;
    private byte[] relatorio;
}