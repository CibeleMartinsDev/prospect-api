package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class LinkExtractor {
    public Set<String> extractLinks(String websiteUrl) throws IOException {
        // Coloque a URL do site desejado
        Set<String> internalLinks = getInternalLinks(websiteUrl);

       return internalLinks;
    }

    public static Set<String> getInternalLinks(String websiteUrl) throws IOException {
        Set<String> internalLinks = new HashSet<>();
        String baseDomain = getBaseDomain(websiteUrl); // Extrai o domínio base

        // Conecta ao site e obtém o documento HTML
        Document doc = Jsoup.connect(websiteUrl).get();

        // Seleciona todos os links (tag <a> com atributo href)
        Elements links = doc.select("a[href]");

        for (Element link : links) {
            String href = link.attr("href");

            // Verifica se o link é interno (mesmo domínio)
            if (isInternalLink(href, baseDomain)) {
                internalLinks.add(href);
            }
        }

        return internalLinks;
    }

    // Função para extrair o domínio base (ex: "example.com" a partir de "https://www.example.com/some-path")
    public static String getBaseDomain(String url) throws IOException {
        try {
            URL u = new URL(url);
            return u.getHost();
        } catch (Exception e) {
            throw new IOException("Erro ao extrair domínio: " + e.getMessage());
        }
    }

    // Função que verifica se o link é interno
    public static boolean isInternalLink(String link, String baseDomain) {
        try {
            if (link.startsWith("http") || link.startsWith("www") || link.startsWith("http") ) {
                URI linkUri = new URI(link);
                String host = linkUri.getHost();
                return host != null && host.equals(baseDomain);
            } else {
                return true; // Links relativos são considerados internos
            }
        } catch (Exception e) {
            return false;
        }
    }
}
