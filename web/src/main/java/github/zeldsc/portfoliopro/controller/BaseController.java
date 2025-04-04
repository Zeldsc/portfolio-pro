package github.zeldsc.portfoliopro.controller;

import jakarta.servlet.http.HttpServletRequest;

public abstract class BaseController {

    protected void adicionarMensagemSucesso(HttpServletRequest request, String mensagem) {
        request.getSession().setAttribute("mensagemSucesso", mensagem);
    }

    protected void adicionarMensagemErro(HttpServletRequest request, String mensagem) {
        request.getSession().setAttribute("mensagemErro", mensagem);
    }

    protected void limparMensagens(HttpServletRequest request) {
        request.getSession().removeAttribute("mensagemSucesso");
        request.getSession().removeAttribute("mensagemErro");
    }
} 
