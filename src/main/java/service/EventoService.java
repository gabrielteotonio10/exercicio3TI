package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dao.InformacoesDAO;
import model.Informacoes;

public class EventoService {

    private InformacoesDAO dao;

    public EventoService() {
        dao = new InformacoesDAO();
    }

    /**
     * Tenta inserir um evento após validar regras de negócio
     * @param info Evento a ser inserido
     * @return true se inserido com sucesso, false caso haja erro
     * @throws IllegalArgumentException se a validação falhar
     */
    public boolean inserirEvento(Informacoes info) {
        // Validação: nome não pode ser vazio
        if (info.getNome() == null || info.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do evento não pode ser vazio.");
        }

        // Validação: data não pode ser anterior a hoje
        if (info.getData() == null || info.getData().trim().isEmpty()) {
            throw new IllegalArgumentException("A data do evento é obrigatória.");
        }

        LocalDate dataEvento;
        try {
            dataEvento = LocalDate.parse(info.getData(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato de data inválido. Use yyyy-MM-dd.");
        }

        if (dataEvento.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data do evento não pode ser anterior à data atual.");
        }

        // Se passou nas validações, insere no banco
        return dao.insert(info);
    }
}
