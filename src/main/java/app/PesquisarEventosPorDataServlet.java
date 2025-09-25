package app;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.InformacoesDAO;
import model.Informacoes;

@WebServlet("/pesquisarEventos")
public class PesquisarEventosPorDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Pega o valor do campo 'searchDate' do HTML
        String dataBusca = request.getParameter("data");

        // 1. Acessa a camada DAO
        InformacoesDAO dao = new InformacoesDAO();
        List<Informacoes> eventos = dao.getByData(dataBusca);

        // 2. Converte a lista de objetos Java para JSON
        Gson gson = new Gson();
        String json = gson.toJson(eventos);

        // 3. Configura a resposta
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}