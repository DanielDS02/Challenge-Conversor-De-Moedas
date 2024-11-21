package Service;

import Model.Moeda;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ConversorService {

    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/06688cb1789d692675bb121c/latest/";

    public double obterTaxaDeCambio(String moedaBase, String moedaDestino) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_BASE_URL + moedaBase))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            RatesResponse ratesResponse = gson.fromJson(response.body(), RatesResponse.class);

            if (!"success".equals(ratesResponse.getResult())) {
                throw new RuntimeException("Resposta inválida da API.");
            }

            return ratesResponse.getConversionRates().get(moedaDestino);

        } catch (Exception e) {
            System.out.println("Erro ao obter a taxa de câmbio: " + e.getMessage());
            return -1;
        }
    }


    public double converter(Moeda origem, String destinoCodigo) {
        double taxaOrigemParaBase = obterTaxaDeCambio("USD", origem.getCodigo());
        double taxaDestinoParaBase = obterTaxaDeCambio("USD", destinoCodigo);

        if (taxaOrigemParaBase != -1 && taxaDestinoParaBase != -1) {

            double taxaFinal = taxaDestinoParaBase / taxaOrigemParaBase;
            return origem.getValor() * taxaFinal;
        } else {
            throw new RuntimeException("Não foi possível obter as taxas de câmbio.");
        }
    }



    private static class RatesResponse {
        private final String result;
        private final Map<String, Double> conversion_rates;

        private RatesResponse(String result, Map<String, Double> conversionRates) {
            this.result = result;
            conversion_rates = conversionRates;
        }

        public Map<String, Double> getConversionRates() {
            return conversion_rates;
        }

        public String getResult() {
            return result;
        }
    }

}

