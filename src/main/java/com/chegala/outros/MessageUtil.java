/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chegala.outros;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Igor
 */
public class MessageUtil {
    public static void adicionarMensagem(FacesMessage.Severity Severity, String mensagem, String contexto) {
        FacesMessage msg = new FacesMessage(Severity, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(contexto, msg);
    }
}
