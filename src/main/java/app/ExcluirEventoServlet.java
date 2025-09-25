package app;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.InformacoesDAO;

@WebServlet("/excluirEvento")
public class ExcluirEventoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Pega os valores do HTML (data e nome)
        String dataExclusao = request.getParameter("data");
        String nomeExclusao = request.getParameter("nome");

        // 1. Acessa a camada DAO
        InformacoesDAO dao = new InformacoesDAO();
        boolean deletado = dao.delete(dataExclusao, nomeExclusao);

        // 2. Configura a resposta
        if (deletado) {
            // Se a exclusão foi bem-sucedida, redireciona o usuário para a página inicial
            response.sendRedirect("index.html?status=sucesso&acao=deletar");
        } else {
            // Se falhou (evento não encontrado ou erro), redireciona com status de erro
            response.sendRedirect("index.html?status=erro&acao=deletar");
        }
    }
}