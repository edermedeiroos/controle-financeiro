package br.com.projeto.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.projeto.model.Transacao;
import br.com.projeto.model.enums.TipoTransacao;
import br.com.projeto.repository.TransacaoRepository;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public List<Transacao> listarTodas() {
        return transacaoRepository.findAll();
    }

    public Transacao salvar(Transacao transacao) {
        return transacaoRepository.save(transacao);
    }

    public Optional<Transacao> buscarPorId(Long id) {
        return transacaoRepository.findById(id);
    }

    public void excluir(Long id) {
        transacaoRepository.deleteById(id);
    }

    public BigDecimal calcularTotalReceitas() {
        return transacaoRepository.findAll().stream()
                .filter(t -> t.getTipo() == TipoTransacao.RECEITA)
                .map(Transacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularTotalDespesas() {
        return transacaoRepository.findAll().stream()
                .filter(t -> t.getTipo() == TipoTransacao.DESPESA)
                .map(Transacao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularSaldo() {
        return calcularTotalReceitas().subtract(calcularTotalDespesas());
    }
}
