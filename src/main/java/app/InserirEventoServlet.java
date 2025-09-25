package app;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.InformacoesDAO;
import model.Informacoes;

@WebServlet("/inserirEvento")
public class InserirEventoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Pega os valores do formulário
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String data = request.getParameter("data");
        String hora = request.getParameter("hora");

        // Cria objeto Informacoes
        Informacoes info = new Informacoes(nome, descricao, data, hora);

        // Insere no banco
        InformacoesDAO dao = new InformacoesDAO();
        boolean inserido = dao.insert(info);
    }
}
