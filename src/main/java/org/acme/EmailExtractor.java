package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class EmailExtractor {

    public List<String> extractEmails(   String url) {
        List<String> emails = new ArrayList<>();
        try {
            // Carrega a página usando Jsoup
            Document document = Jsoup.connect(url).get();

            // Obtém o conteúdo de texto da página
            String pageText = document.text();

            // Procura por e-mails no texto usando regex
            emails = findEmails(pageText);
        } catch (IOException e) {
            System.out.println("Erro ao acessar a URL: " + e.getMessage());
        }

        return emails;
    }

    // Função para encontrar e-mails no texto usando regex
    public static List<String> findEmails(String text) {
        List<String> emails = new ArrayList<>();
        // Regex para detectar e-mails
        String emailRegex = "\\w+@\\w+\\.{1}\\w+";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(text);

        // Exibe os e-mails encontrados
        while (matcher.find()) {
            System.out.println("E-mail encontrado: " + matcher.group());
            emails.add(matcher.group());
        }
        return emails;
    }
}
