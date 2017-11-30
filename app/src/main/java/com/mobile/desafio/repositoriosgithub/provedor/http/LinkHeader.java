package com.mobile.desafio.repositoriosgithub.provedor.http;

import cz.msebera.android.httpclient.HttpResponse;

public class LinkHeader {

    private static final String PAGE_LAST = "last";
    private static final String PAGE_PREV = "prev";
    private static final String PAGE_NEXT = "next";
    private static final String PAGE_FIRST = "first";

    private static final String ATRIBUTO_LINK = "Link";

    private String link;
    private Integer proxima;
    private Integer anterior;
    private Integer primeira;
    private Integer ultimo;

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public static LinkHeader fromHeader(String link) {
        LinkHeader linkHeader = new LinkHeader();
        linkHeader.setLink(link);
        linkHeader.setProxima(getNextLink(link));
        linkHeader.setAnterior(getPrevLink(link));
        linkHeader.setPrimeira(getFirstLink(link));
        linkHeader.setUltimo(getLastLink(link));
        return linkHeader;
    }

    private static Integer getLastLink(String link) {
        return getLinkFromPropriedade(link, PAGE_LAST);
    }

    private static Integer getLinkFromPropriedade(String link, String propriedade) {
        if (link != null) {
            String[] links = link.split(",");
            for (String l : links) {
                if (l.contains("rel=\"" + propriedade + "\"")) {
                    String s = l.split(">", 2)[0];
                    String[] split = s.split("=");
                    return Integer.valueOf(split[split.length - 1]);
                }

            }
        }
        return null;
    }

    private static Integer getFirstLink(String link) {
        return getLinkFromPropriedade(link, PAGE_FIRST);
    }

    public static LinkHeader get(HttpResponse resposta) {
        return resposta.containsHeader(ATRIBUTO_LINK) ? LinkHeader.fromHeader(resposta.getHeaders(ATRIBUTO_LINK)[0].toString()) : new LinkHeader();
    }

    private static Integer getPrevLink(String link) {
        return getLinkFromPropriedade(link, PAGE_PREV);
    }

    private static Integer getNextLink(String link) {
        return getLinkFromPropriedade(link, PAGE_NEXT);
    }

    public void setProxima(Integer proxima) {
        this.proxima = proxima;
    }

    public Integer getProxima() {
        return proxima;
    }

    public void setAnterior(Integer anterior) {
        this.anterior = anterior;
    }

    public Integer getAnterior() {
        return anterior;
    }

    public void setPrimeira(Integer primeira) {
        this.primeira = primeira;
    }

    public Integer getPrimeira() {
        return primeira;
    }

    public void setUltimo(Integer ultimo) {
        this.ultimo = ultimo;
    }

    public Integer getUltimo() {
        return ultimo;
    }
}