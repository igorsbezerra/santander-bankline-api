package com.dio.santander.bankline.api.services;

import com.dio.santander.bankline.api.dto.NovaMovimentacaoDTO;
import com.dio.santander.bankline.api.model.Correntista;
import com.dio.santander.bankline.api.model.Movimentacao;
import com.dio.santander.bankline.api.model.MovimentacaoTipo;
import com.dio.santander.bankline.api.repository.CorrentistaRepository;
import com.dio.santander.bankline.api.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository repository;

    @Autowired
    private CorrentistaRepository correntistaRepository;

    public void save(NovaMovimentacaoDTO novaMovimentacaoDTO) {
        Movimentacao movimentacao = new Movimentacao();

        Double valor = novaMovimentacaoDTO.getTipo() == MovimentacaoTipo.RECEITA
                ? novaMovimentacaoDTO.getValor()
                : novaMovimentacaoDTO.getValor() * -1;

        movimentacao.setDataHora(LocalDateTime.now());
        movimentacao.setDescricao(novaMovimentacaoDTO.getDescricao());
        movimentacao.setIdConta(novaMovimentacaoDTO.getIdConta());
        movimentacao.setTipo(novaMovimentacaoDTO.getTipo());
        movimentacao.setValor(valor);

        Correntista correntista = correntistaRepository.findById(novaMovimentacaoDTO.getIdConta()).orElse(null);

        if (correntista != null) {
            correntista.getConta().setSaldo(correntista.getConta().getSaldo() + valor);
            correntistaRepository.save(correntista);
        }

        repository.save(movimentacao);
    }
}
