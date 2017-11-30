package com.mobile.desafio.repositoriosgithub.dominio;

import org.junit.Test;

import static org.junit.Assert.*;


public class PaginaTest {

    @Test
    public void existeProxima() throws Exception {
        Pagina pagina = new Pagina();
        assertFalse(pagina.existeProxima());
        pagina.setProxima(2);
        assertTrue(pagina.existeProxima());
    }

}